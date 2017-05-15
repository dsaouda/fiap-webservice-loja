package com.github.dsaouda.fiap.webservice.loja.client;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.dsaouda.fiap.webservice.loja.client.model.Produto;

public class ProdutoClient extends AbstractClient {
	
	public ProdutoClient() {
		super();
	}
	
	public ProdutoClient(WebTarget target) {
		super(target);
	}

	public List<Produto> todos() {
		WebTarget target = this.target.path("produtos");
		
		Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		Produto[] produtos = response.readEntity(Produto[].class);
		
		return Arrays.asList(produtos);
	}

	public Produto get(long l) {
		WebTarget target = this.target.path("produtos").path(String.valueOf(l));
		Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		return response.readEntity(Produto.class);
	}
	
}
