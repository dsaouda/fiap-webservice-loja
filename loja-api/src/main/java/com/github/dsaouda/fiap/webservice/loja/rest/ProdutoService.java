package com.github.dsaouda.fiap.webservice.loja.rest;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.dsaouda.fiap.webservice.loja.repository.ProdutoRepository;

@Path("/produtos")
public class ProdutoService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<?,?> index() {
		return ProdutoRepository.getProdutos();
	}
	
}
