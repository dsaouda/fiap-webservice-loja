package com.github.dsaouda.fiap.webservice.transportadora.client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.dsaouda.fiap.webservice.transportadora.client.model.CalcularFreteRequest;
import com.github.dsaouda.fiap.webservice.transportadora.client.model.CalcularFreteResponse;

public class CalcularFreteClient extends AbstractClient {
	
	public CalcularFreteClient() {
		super();
	}
	
	public CalcularFreteClient(WebTarget target) {
		super(target);
	}

	public Double calcular(CalcularFreteRequest req) {
		WebTarget target = this.target.path("calcularFrete");
		
		Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		Entity<CalcularFreteRequest> entity = Entity.entity(req, MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(entity);
						
		 CalcularFreteResponse calcularFreteResponse = response.readEntity(CalcularFreteResponse.class);
		 return Double.parseDouble(calcularFreteResponse.getValorTotalFrete());
	}
}
