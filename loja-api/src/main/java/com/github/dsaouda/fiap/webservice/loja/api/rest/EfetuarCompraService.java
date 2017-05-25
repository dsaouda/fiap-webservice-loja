package com.github.dsaouda.fiap.webservice.loja.api.rest;

import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.dsaouda.fiap.webservice.loja.api.exception.FornecedorNaoPodeRealizarOPedidoException;
import com.github.dsaouda.fiap.webservice.loja.api.model.Pedido;
import com.github.dsaouda.fiap.webservice.loja.api.model.Produto;
import com.github.dsaouda.fiap.webservice.loja.api.repository.ProdutoRepository;
import com.github.dsaouda.fiap.webservice.loja.fornecedor.client.factory.FornecedorPortFactory;
import com.github.dsaouda.fiap.webservice.transportadora.client.GerarFreteClient;
import com.github.dsaouda.fiap.webservice.transportadora.client.model.GerarFreteRequest;

import br.com.fiap.fornecedor.ws.FornecedorException_Exception;
import br.com.fiap.fornecedor.ws.FornecedorWS;
import br.com.fiap.fornecedor.ws.PedidoDTO;
import br.com.fiap.fornecedor.ws.ProdutoDTO;
import io.swagger.annotations.Api;

@Api
@Path("/efetuar-compra")
public class EfetuarCompraService {
	
	private static final String CNPJ_LOJA = null;
	private FornecedorWS fornecedorService;

	public EfetuarCompraService() {
		fornecedorService = FornecedorPortFactory.create();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean comprar(Pedido pedido) {		
		//@TODO precisamos debitar do estoque?
		//@TODO aguardando os outros grupos
		
		try {
			preencherProdutoNoPedido(pedido);
			
			//enviando para servico fornecedor
			//se retorno false, então retornar false
			if (enviarPedidoFornecedor(pedido) == false) {
				throw new FornecedorNaoPodeRealizarOPedidoException();
			}
			
			//emitir nota fiscal com grupo governo
			//boolean notaEmitida = governoClient.emitirNota();
			//throw new GovernoNaoPOdeEmitirNotaException();
			
			//valor total debitado do grupo financeira
			//financeiraClient.debitarValor(valorTotal);
			
			//solicitacao de entrega ao grupo transportadora
			gerarFrete(pedido);
			
			return true;
			
		} catch (Exception e) {
			//voltando os produtos no estoque devido a um erro que não permite a venda
			pedido.getListaProdutos().stream().forEach(Produto::acrescentaNoEstoque);
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

	private boolean enviarPedidoFornecedor(Pedido pedido) throws FornecedorException_Exception {
		PedidoDTO pedidoFornecedor = new PedidoDTO();
		pedidoFornecedor.setCpfCnpj(pedido.getDocumento());
		
		//verificando a quantidade de produtos em estoque
		pedido.getListaProdutos().stream().forEach(p -> {
			
			//qtd estoque 0, solicitar ao grupo fornecedor, se o grupo não tiver o produto, retornar 0
			if (p.getQuantidadeEstoque() == 0) {
				ProdutoDTO produtoFornecedor = new ProdutoDTO();
				produtoFornecedor.setCodigo((int)p.getCodigo());
				produtoFornecedor.setDescricao(p.getDescricao());
				produtoFornecedor.setValor(p.getValorUnitario());
				
				pedidoFornecedor.getProdutos().add(produtoFornecedor);
			}
		});		
		
		//dúvida: Só enviar pedido quando não houver estoque???
		//fazer uma solicitação para cada pedido???
		return fornecedorService.efetuarPedido(pedidoFornecedor);
	}
	
	
	private void gerarFrete(Pedido pedido) {

		double valorTotalRemessa = pedido.getListaProdutos().stream().mapToDouble(p -> p.getValorUnitario()).sum();
		int quantidadeProdutos = pedido.getListaProdutos().size();

		GerarFreteRequest request = new GerarFreteRequest();
		request.setValorTotalRemessa(valorTotalRemessa);
		request.setQuantidadeProdutos(quantidadeProdutos);
		request.setCpfCnpjDestinatario(pedido.getDocumento());
		request.setCpfCnpjRemetente(CNPJ_LOJA);

		GerarFreteClient client = new GerarFreteClient();
		client.gerar(request);
	}	
}
