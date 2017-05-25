package com.github.dsaouda.fiap.webservice.loja.api.web.rpc;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.dsaouda.fiap.webservice.loja.api.exception.ProdutoNaoEncontradoException;
import com.github.dsaouda.fiap.webservice.loja.api.model.Produto;
import com.github.dsaouda.fiap.webservice.loja.api.repository.ProdutoRepository;

import io.swagger.annotations.Api;

@Api
@Path("/produtos")
public class ProdutoService {
	
	private static int acesso = 0;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response index() {
		acesso++;
		Collection<Produto> produtos = ProdutoRepository.getProdutos().values();
		return Response.ok(produtos).build();
	}
	
	@GET
	@Path("{codigoProduto}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response get(@PathParam("codigoProduto") long codigoProduto) {
		acesso++;
		
		try {
			Produto produto = ProdutoRepository.findByCodigo(codigoProduto);
			return Response.ok(produto).build();
		} catch (ProdutoNaoEncontradoException e) {
			return Response.status(Status.NOT_FOUND).entity("produto " + codigoProduto + " não encontrado").build();
		}
	}
	
	@GET
	@Path("/acessos")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public int acessos() {		
		return acesso;
	}
	
}
