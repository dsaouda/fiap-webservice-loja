package com.github.dsaouda.fiap.webservice.loja.api.web.rpc;

import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dsaouda.fiap.webservice.loja.api.exception.FinanceiroNaoPodeDebitarValorException;
import com.github.dsaouda.fiap.webservice.loja.api.exception.FornecedorNaoPodeRealizarOPedidoException;
import com.github.dsaouda.fiap.webservice.loja.api.exception.GovernoNaoPodeEmitirNotaException;
import com.github.dsaouda.fiap.webservice.loja.api.exception.TransportadoraIndisponivelException;
import com.github.dsaouda.fiap.webservice.loja.api.model.Loja;
import com.github.dsaouda.fiap.webservice.loja.api.model.Pedido;
import com.github.dsaouda.fiap.webservice.loja.api.model.Produto;
import com.github.dsaouda.fiap.webservice.loja.api.repository.ProdutoRepository;
import com.github.dsaouda.fiap.webservice.loja.financeiro.client.factory.FinanceiroPortFactory;
import com.github.dsaouda.fiap.webservice.loja.fornecedor.client.factory.FornecedorPortFactory;
import com.github.dsaouda.fiap.webservice.loja.governo.client.factory.GovernoPortFactory;
import com.github.dsaouda.fiap.webservice.transportadora.client.GerarFreteClient;
import com.github.dsaouda.fiap.webservice.transportadora.client.model.GerarFreteRequest;
import com.github.dsaouda.fiap.webservice.transportadora.client.model.GerarFreteResponse;

import br.com.fiap.fornecedor.ws.FornecedorException_Exception;
import br.com.fiap.fornecedor.ws.FornecedorWS;
import br.com.fiap.fornecedor.ws.PedidoDTO;
import br.com.fiap.fornecedor.ws.ProdutoDTO;
import br.com.fincliente.Cobranca;
import br.com.fincliente.CobrarCliente;
import br.com.governo.ws.Governo;
import br.com.governo.ws.NotaFiscal;
import io.swagger.annotations.Api;

@Api
@Path("/efetuar-compra")
public class EfetuarCompraService {
	
	private Logger logger = LoggerFactory.getLogger(EfetuarCompraService.class);
	
	private FornecedorWS fornecedorService;
	private CobrarCliente financeiroService;
	private Governo governoService;

	public EfetuarCompraService() {
		fornecedorService = FornecedorPortFactory.create(Loja.FORNECEDOR_USERNAME, Loja.FORNECEDOR_PASSWORD);
		financeiroService = FinanceiroPortFactory.create(Loja.FINANCEIRO_CARGO, Loja.FINANCEIRO_ESTABELECIMENTO);
		governoService = GovernoPortFactory.create(Loja.GOVERNO_DOCUMENTO, Loja.GOVERNO_SENHA);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response comprar(Pedido pedido) {		
		
		try {
			preencherProdutoNoPedido(pedido);
			logger.info("{}", pedido);
			
			if (enviarPedidoFornecedor(pedido) == false) {
				throw new FornecedorNaoPodeRealizarOPedidoException();
			}
			
			NotaFiscal notaFiscal = emitirNotaNoGoverno(pedido);
			logger.info("{}", notaFiscal);
			
			if (debitarValorFinanceira(pedido, notaFiscal.getValorTotalComImpostos()) == false) {
				throw new FinanceiroNaoPodeDebitarValorException();
			}
			
			try {
				logger.info("solicitação de entrega a transportadora");
				GerarFreteResponse entrega = solicitacaoDeEntrega(pedido);
				logger.info("transportadora resposta {}", entrega.getMensagem());
				
				
			} catch (Exception e) {
				throw new TransportadoraIndisponivelException();
			}
			
			return Response.ok(true).build();
			
		} catch (Exception e) {
			logger.error("ocorreu um erro {}", e.getMessage());
			
			e.printStackTrace();
			voltarProdutosNoEstoque(pedido);
			
			return Response.ok(false)
				.header("x-exception-message", e.getMessage())
				.header("x-exception-class", e.getClass().getName())
				.build();
		}
	}

	private NotaFiscal emitirNotaNoGoverno(Pedido pedido) {
		try {
			logger.info("emitindo nota no governo");
			return governoService.emitirNotaFiscal(pedido.getDocumento(), pedido.getValorTotal());
		} catch (Exception e) {
			logger.error("erro ao emitir nota no governo");
			throw new GovernoNaoPodeEmitirNotaException();
		}
	}

	private void voltarProdutosNoEstoque(Pedido pedido) {
		pedido.getListaProdutos().stream().forEach(Produto::acrescentaNoEstoque);
	}

	private boolean debitarValorFinanceira(Pedido pedido, double valorTotal) {
		Cobranca cobranca = new Cobranca();
		cobranca.setCpf(Long.parseLong(pedido.getDocumento()));
		cobranca.setValor(valorTotal);
		
		try {
			logger.info("enviando dados para financeira");
			return financeiroService.cobrar(cobranca);
		} catch (Exception e) {
			logger.error("erro ao enviar dados a financeira");
			return false;
		}
	}

	private void preencherProdutoNoPedido(Pedido pedido) {
		//capturando maiores informações sobre o produto
		//o cliente não envia detalhe dos produtos, apenas código
		pedido.setListaProdutos(
			pedido.getListaProdutos()
				.stream()
				.map(p -> ProdutoRepository.findByCodigo(p.getCodigo()))
				.collect(Collectors.toList())
		);
		
		//depois de localizar todos os produtos, decrementa do estoque
		pedido.getListaProdutos().stream().forEach(Produto::decrementaDoEstoque);
	}

	private boolean enviarPedidoFornecedor(Pedido pedido) {
		logger.info("enviar pedido fornecedor");
		
		PedidoDTO pedidoFornecedor = new PedidoDTO();
		pedidoFornecedor.setCpfCnpj(pedido.getDocumento());
		
		boolean hasFazerPedido = false;
		
		for(Produto p : pedido.getListaProdutos()) {
			
			if (p.getQuantidadeEstoque() == 0) {
				
				logger.info("enviar produto {} ", p.getCodigo());
				
				//adiciona todos os produtos que precisa enviar no pedido
				ProdutoDTO produtoFornecedor = new ProdutoDTO();
				produtoFornecedor.setCodigo((int)p.getCodigo());
				produtoFornecedor.setDescricao(p.getDescricao());
				produtoFornecedor.setValor(p.getValorUnitario());
				
				pedidoFornecedor.getProdutos().add(produtoFornecedor);
			}
		}
		
		if (hasFazerPedido == true) {
			try {
				logger.info("enviando pedido ao fornecedor");
				return fornecedorService.efetuarPedido(pedidoFornecedor);
			} catch (FornecedorException_Exception e) {
				logger.error("erro ao tentar enviar pedido ao fornecedor");
				return false;
			}
		}
		
		logger.info("nÃ£o foram enviados pedidos ao fornecedor");
		return true;
	}
	
	private GerarFreteResponse solicitacaoDeEntrega(Pedido pedido) {

		double valorTotalRemessa = pedido.getListaProdutos().stream().mapToDouble(p -> p.getValorUnitario()).sum();
		int quantidadeProdutos = pedido.getListaProdutos().size();

		GerarFreteRequest request = new GerarFreteRequest();
		request.setValorTotalRemessa(valorTotalRemessa);
		request.setQuantidadeProdutos(quantidadeProdutos);
		request.setCpfCnpjDestinatario(pedido.getDocumento());
		request.setCpfCnpjRemetente(Loja.CNPJ_LOJA);

		GerarFreteClient client = new GerarFreteClient();
		return client.gerar(request);
	}	
	
}
