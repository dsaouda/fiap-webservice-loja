package com.github.dsaouda.fiap.webservice.loja.api.rest;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.dsaouda.fiap.webservice.loja.api.model.Produto;
import com.github.dsaouda.fiap.webservice.loja.api.repository.ProdutoRepository;

@Path("/produtos")
public class ProdutoService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response index() {
		Collection<Produto> produtos = ProdutoRepository.getProdutos().values();
		return Response.ok(produtos).build();
	}
	
	@GET
	@Path("{codigoProduto}")
	@Produces(MediaType.APPLICATION_JSON)
	public Produto get(@PathParam("codigoProduto") long codigoProduto) {
		return ProdutoRepository.findByCodigo(codigoProduto);
	}
	
}
