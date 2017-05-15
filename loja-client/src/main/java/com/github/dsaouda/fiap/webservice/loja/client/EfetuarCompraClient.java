package com.github.dsaouda.fiap.webservice.loja.client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.dsaouda.fiap.webservice.loja.client.model.Pedido;

public class EfetuarCompraClient extends AbstractClient {
	
	public EfetuarCompraClient() {
		super();
	}
	
	public EfetuarCompraClient(WebTarget target) {
		super(target);
	}

	public boolean comprar(Pedido pedido) {
		WebTarget target = this.target.path("efetuar-compra");
		
		Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		Entity<Pedido> entity = Entity.entity(pedido, MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(entity);
						
		return response.readEntity(Boolean.class);
	}
}
