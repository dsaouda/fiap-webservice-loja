package com.github.dsaouda.fiap.webservice.transportadora.client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.dsaouda.fiap.webservice.transportadora.client.model.GerarFreteRequest;
import com.github.dsaouda.fiap.webservice.transportadora.client.model.GerarFreteResponse;

public class GerarFreteClient extends AbstractClient {
	
	public GerarFreteClient() {
		super();
	}
	
	public GerarFreteClient(WebTarget target) {
		super(target);
	}

	public GerarFreteResponse gerar(GerarFreteRequest req) {
		WebTarget target = this.target.path("gerarFrete");
		
		Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		Entity<GerarFreteRequest> entity = Entity.entity(req, MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(entity);
						
		
		 return response.readEntity(GerarFreteResponse.class);
	}
}
