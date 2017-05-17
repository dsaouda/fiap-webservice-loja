package com.github.dsaouda.fiap.webservice.loja.api.rest;

import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.dsaouda.fiap.webservice.loja.api.model.Pedido;
import com.github.dsaouda.fiap.webservice.loja.api.repository.ProdutoRepository;

@Path("/efetuar-compra")
public class EfetuarCompraService {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean store(Pedido pedido) {		
		//@TODO precisamos debitar do estoque?
		//@TODO aguardando os outros grupos
		
		
		//capturando maiores informações sobre o produto
		//o cliente não envia detalhe dos produtos, apenas código
		pedido.setListaProdutos(
			pedido.getListaProdutos()
				.stream()
				.map(p -> ProdutoRepository.findByCodigo(p.getCodigo()))
				.collect(Collectors.toList())
		);
		
		System.out.println(pedido);
		
		//verificando a quantidade de produtos em estoque
		pedido.getListaProdutos().stream().forEach(p -> {
			
			//qtd estoque 0, solicitar ao grupo fornecedor, se o grupo não tiver o produto, retornar 0
			if (p.getQuantidadeEstoque() == 0) {
				//fornecedorClient.solicitarPedido()
			}
		});		
		
		//emitir nota fiscal com grupo governo
		//boolean notaEmitida = governoClient.emitirNota();
		
		//valor total debitado do grupo financeira
		//financeiraClient.debitarValor(valorTotal);
		
		//solicitacao de entrega ao grupo transportadora
		//transportadoraClient.solicitarEntrega(produtos);
		
		return true;
	}
	
}
