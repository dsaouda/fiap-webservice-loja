package com.github.dsaouda.fiap.webservice.loja.api.web.rpc;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.dsaouda.fiap.webservice.loja.api.exception.ProdutoNaoEncontradoException;
import com.github.dsaouda.fiap.webservice.loja.api.manager.GovernoManager;
import com.github.dsaouda.fiap.webservice.loja.api.manager.ProdutoManager;
import com.github.dsaouda.fiap.webservice.loja.api.manager.TransportadoraManager;
import com.github.dsaouda.fiap.webservice.loja.api.model.Produto;
import com.github.dsaouda.fiap.webservice.loja.api.model.Produtos;

import io.swagger.annotations.Api;

@Api
@Path("/simular-compra")
public class SimularCompraService {
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response simular(List<Produto> codigosProdutos) {
		Produtos produtos;
		
		try {
			produtos = new ProdutoManager().getProdutoListWhereInCodigo(codigosProdutos);
		} catch (ProdutoNaoEncontradoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
		double valorFrete = new TransportadoraManager().calculaFrete(produtos);
		double porcentagemImpostos;
		
		try {
			porcentagemImpostos = new GovernoManager().porcentagemImpostos();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_GATEWAY).encoding(e.getMessage()).build();
		}
		
		double valorImpostos = (valorFrete+produtos.valorTotal()) * porcentagemImpostos / 100;
		
		//preparando a resposta
		Map<String, Object> response = new LinkedHashMap<>();
		response.put("valorProdutos", produtos.valorTotal());
		response.put("valorTotalProdutos", produtos.valorCadaProduto());
		response.put("valorFrete", valorFrete);
		response.put("valorImpostos", valorImpostos);
		response.put("porcentagemImpostos", porcentagemImpostos);
		response.put("valorTotal", valorFrete+produtos.valorTotal()+valorImpostos);
		
		return Response.ok(response).build();
	}
}
