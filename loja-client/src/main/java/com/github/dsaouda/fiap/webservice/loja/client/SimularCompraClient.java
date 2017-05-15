package com.github.dsaouda.fiap.webservice.loja.client;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.dsaouda.fiap.webservice.loja.client.model.Produto;
import com.github.dsaouda.fiap.webservice.loja.client.model.SimularCompra;

public class SimularCompraClient extends AbstractClient {
	
	public SimularCompraClient() {
		super();
	}
	
	public SimularCompraClient(WebTarget target) {
		super(target);
	}

	public SimularCompra simular(Set<Long> codigos) {
		List<Produto> produtos = codigos.stream()
			.map(c -> new Produto().setCodigo(c))
			.collect(Collectors.toList());
		
		return simular(produtos);
	}
	
	public SimularCompra simular(List<Produto> produtos) {
		WebTarget target = this.target.path("simular-compra");
		
		Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		Entity<List<Produto>> entity = Entity.entity(produtos, MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(entity);
						
		return response.readEntity(SimularCompra.class);
	}
	
}
