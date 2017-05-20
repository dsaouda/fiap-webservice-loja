package com.github.dsaouda.fiap.webservice.loja.api.rest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.dsaouda.fiap.webservice.loja.api.exception.ProdutoNaoEncontradoException;
import com.github.dsaouda.fiap.webservice.loja.api.model.Produto;
import com.github.dsaouda.fiap.webservice.loja.api.repository.ProdutoRepository;

import io.swagger.annotations.Api;

@Api
@Path("/simular-compra")
public class SimularCompraService {

	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response simular(List<Produto> codigosProdutos) {
		List<Produto> produtos;
		
		try {
			produtos = getProdutos(codigosProdutos);
		} catch (ProdutoNaoEncontradoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
		//valor total dos produtos
		double valorTotalProdutos = produtos.stream().mapToDouble(p -> p.getValorUnitario()).sum();
		
		double valorFrete = 18.0;
		double valorImpostos = valorTotalProdutos * 0.05;
		
		List<String> valoresProdutos = produtos.stream().map(p -> {
			return p.getCodigo() + " => " + p.getDescricao() + " ("+p.getValorUnitario()+")";
		}).collect(Collectors.toList());
		
		//preparando a resposta
		Map<String, Object> response = new LinkedHashMap<>();
		response.put("valorProdutos", valoresProdutos);
		response.put("valorTotalProdutos", valorTotalProdutos);
		response.put("valorFrete", valorFrete);
		response.put("valorImpostos", valorImpostos);
		response.put("valorTotal", valorFrete+valorImpostos+valorTotalProdutos);
		
		return Response.ok(response).build();
	}

	private List<Produto> getProdutos(List<Produto> codigosProdutos) {		
		List<Produto> produtos = codigosProdutos.stream()
				.map(p -> ProdutoRepository.findByCodigo(p.getCodigo()))			
				.collect(Collectors.toList());
		return produtos;
	}	 
}
