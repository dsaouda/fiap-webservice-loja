package com.github.dsaouda.fiap.webservice.loja.api.rest;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.dsaouda.fiap.webservice.loja.api.model.Produto;
import com.github.dsaouda.fiap.webservice.loja.api.repository.ProdutoRepository;

@Path("/produtos")
public class ProdutoService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Produto> index() {
		return ProdutoRepository.getProdutos().values();
	}
	
	@GET
	@Path("{codigoProduto}")
	@Produces(MediaType.APPLICATION_JSON)
	public Produto get(@PathParam("codigoProduto") long codigoProduto) {
		return ProdutoRepository.findByCodigo(codigoProduto);
	}
	
}
