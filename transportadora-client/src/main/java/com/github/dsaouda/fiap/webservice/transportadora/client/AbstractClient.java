package com.github.dsaouda.fiap.webservice.transportadora.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;


abstract class AbstractClient {
	protected WebTarget target;

	public AbstractClient(WebTarget target) {
		this.target = target;
		
	}
	
	public AbstractClient() {
		//default
		String url = "http://transporte-agrego1.rhcloud.com/transportadora/";
		String user = "loja";
		String password = "loja";
		
		Client client = ClientBuilder.newClient();
		client.register(new Authenticator(user, password));
		target = client.target(url);
	}
}
