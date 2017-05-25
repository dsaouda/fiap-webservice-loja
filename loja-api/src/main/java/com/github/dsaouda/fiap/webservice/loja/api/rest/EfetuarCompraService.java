package com.github.dsaouda.fiap.webservice.loja.api.rest;

import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.dsaouda.fiap.webservice.loja.api.exception.FinanceiroNaoPodeDebitarValorException;
import com.github.dsaouda.fiap.webservice.loja.api.exception.FornecedorNaoPodeRealizarOPedidoException;
import com.github.dsaouda.fiap.webservice.loja.api.model.Pedido;
import com.github.dsaouda.fiap.webservice.loja.api.model.Produto;
import com.github.dsaouda.fiap.webservice.loja.api.repository.ProdutoRepository;
import com.github.dsaouda.fiap.webservice.loja.financeiro.client.factory.FinanceiroPortFactory;
import com.github.dsaouda.fiap.webservice.loja.fornecedor.client.factory.FornecedorPortFactory;

import br.com.fiap.fornecedor.ws.FornecedorException_Exception;
import br.com.fiap.fornecedor.ws.FornecedorWS;
import br.com.fiap.fornecedor.ws.PedidoDTO;
import br.com.fiap.fornecedor.ws.ProdutoDTO;
import br.com.fincliente.Cobranca;
import br.com.fincliente.CobrarCliente;
import io.swagger.annotations.Api;

@Api
@Path("/efetuar-compra")
public class EfetuarCompraService {
	
	private FornecedorWS fornecedorService;
	private CobrarCliente financeiroService;

	public EfetuarCompraService() {
		fornecedorService = FornecedorPortFactory.create();
		financeiroService = FinanceiroPortFactory.create();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response comprar(Pedido pedido) {		
		
		try {
			preencherProdutoNoPedido(pedido);
			
			if (enviarPedidoFornecedor(pedido) == false) {
				throw new FornecedorNaoPodeRealizarOPedidoException();
			}
	
			//emitir nota fiscal com grupo governo
			//boolean notaEmitida = governoClient.emitirNota();
			//throw new GovernoNaoPOdeEmitirNotaException();
			
			double valorTotal = 0;
			
			if (debitarValorFinanceira(pedido, valorTotal) == false) {
				throw new FinanceiroNaoPodeDebitarValorException();
			}
			
			//solicitacao de entrega ao grupo transportadora
			//transportadoraClient.solicitarEntrega(produtos);
			
			return Response.ok(true).build();
			
		} catch (Exception e) {
			e.printStackTrace();
			voltarProdutosNoEstoque(pedido);
			
			return Response.ok(false)
				.header("x-exception-message", e.getMessage())
				.header("x-exception-class", e.getClass().getName())
				.build();
		}
	}

	private void voltarProdutosNoEstoque(Pedido pedido) {
		pedido.getListaProdutos().stream().forEach(Produto::acrescentaNoEstoque);
	}

	private boolean debitarValorFinanceira(Pedido pedido, double valorTotal) {
		Cobranca cobranca = new Cobranca();
		cobranca.setCpf(Long.parseLong(pedido.getDocumento()));
		cobranca.setValor(valorTotal);
		
		return financeiroService.cobrar(cobranca);
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

	private boolean enviarPedidoFornecedor(Pedido pedido) throws FornecedorException_Exception {
		PedidoDTO pedidoFornecedor = new PedidoDTO();
		pedidoFornecedor.setCpfCnpj(pedido.getDocumento());
		
		boolean hasFazerPedido = false;
		
		for(Produto p : pedido.getListaProdutos()) {
			
			if (p.getQuantidadeEstoque() == 0) {
				
				System.out.println("enviarPedidoFornecedor() -> produto: " + p.getCodigo());
				
				//adiciona todos os produtos que precisa enviar no pedido
				ProdutoDTO produtoFornecedor = new ProdutoDTO();
				produtoFornecedor.setCodigo((int)p.getCodigo());
				produtoFornecedor.setDescricao(p.getDescricao());
				produtoFornecedor.setValor(p.getValorUnitario());
				
				pedidoFornecedor.getProdutos().add(produtoFornecedor);
			}
		}
		
		if (hasFazerPedido == true) {
			return fornecedorService.efetuarPedido(pedidoFornecedor);
		}
		
		return true;
	}
	
}
