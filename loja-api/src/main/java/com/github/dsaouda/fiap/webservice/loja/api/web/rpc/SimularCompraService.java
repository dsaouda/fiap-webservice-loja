package com.github.dsaouda.fiap.webservice.loja.api.web.rpc;

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
import com.github.dsaouda.fiap.webservice.loja.api.model.Loja;
import com.github.dsaouda.fiap.webservice.loja.api.model.Produto;
import com.github.dsaouda.fiap.webservice.loja.api.repository.ProdutoRepository;
import com.github.dsaouda.fiap.webservice.loja.governo.client.factory.GovernoPortFactory;
import com.github.dsaouda.fiap.webservice.transportadora.client.CalcularFreteClient;
import com.github.dsaouda.fiap.webservice.transportadora.client.model.CalcularFreteRequest;

import br.com.governo.ws.Exception_Exception;
import br.com.governo.ws.Governo;
import io.swagger.annotations.Api;

@Api
@Path("/simular-compra")
public class SimularCompraService {
	
	private Governo governoService;

	public SimularCompraService() {
		governoService = GovernoPortFactory.create(Loja.GOVERNO_DOCUMENTO, Loja.GOVERNO_SENHA);
	}
	
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
		
		int quantidadeProdutos = produtos.size();
		double valorFrete = calculaFrete(quantidadeProdutos, valorTotalProdutos);
		double porcentagemImpostos;
		
		try {
			porcentagemImpostos = retornarImpostos();
		} catch (Exception e) {
			return Response.status(Status.BAD_GATEWAY).encoding(e.getMessage()).build();
		}
		
		double valorImpostos = (valorFrete+valorTotalProdutos) * porcentagemImpostos / 100;

		
		List<String> valoresProdutos = produtos.stream().map(p -> {
			return p.getCodigo() + " => " + p.getDescricao() + " ("+p.getValorUnitario()+")";
		}).collect(Collectors.toList());
		
		//preparando a resposta
		Map<String, Object> response = new LinkedHashMap<>();
		response.put("valorProdutos", valoresProdutos);
		response.put("valorTotalProdutos", valorTotalProdutos);
		response.put("valorFrete", valorFrete);
		response.put("valorImpostos", valorImpostos);
		response.put("porcentagemImpostos", porcentagemImpostos);
		response.put("valorTotal", valorFrete+valorTotalProdutos+valorImpostos);
		
		return Response.ok(response).build();
	}

	private double retornarImpostos() throws Exception_Exception {
		double valorImpostos = governoService.listarImpostos().stream().mapToDouble(i -> i.getAliquota()).sum();
		return valorImpostos;
	}

	private List<Produto> getProdutos(List<Produto> codigosProdutos) {		
		List<Produto> produtos = codigosProdutos.stream()
				.map(p -> ProdutoRepository.findByCodigo(p.getCodigo()))			
				.collect(Collectors.toList());
		return produtos;
	}	 
	
	
	private Double calculaFrete(int quantidadeProdutos, Double valorTotalRemessa ){
		
		CalcularFreteRequest req = new CalcularFreteRequest();
		req.setQuantidadeProdutos(quantidadeProdutos);
		req.setValorTotalRemessa(valorTotalRemessa);

		CalcularFreteClient client = new CalcularFreteClient();
		return client.calcular(req);
	}
}
