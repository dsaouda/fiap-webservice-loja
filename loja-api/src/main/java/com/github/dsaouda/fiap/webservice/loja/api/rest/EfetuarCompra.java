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
public class EfetuarCompra {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean store(Pedido pedido) {		
		//@TODO precisamos debitar do estoque?
		
		//capturando maiores informações sobre os produtos
		//o cliente não envia detalhes do protudo
		pedido.setListaProdutos(
			pedido.getListaProdutos()
				.stream()
				.map(p -> ProdutoRepository.findByCodigo(p.getCodigo()))
				.collect(Collectors.toList())
		);
		
		System.out.println(pedido);
		
		
		//@TODO aguardando os outros grupos
		
		//emitir nota fiscal com grupo governo
		
		//valor total debitado do grupo financeira
		
		//solicitacao de entrega ao grupo transportadora
		
		//qtd estoque 0, solicitar ao grupo fornecedor, se o grupo não tiver produto, retornar 0
		
		return true;
	}
	
}
