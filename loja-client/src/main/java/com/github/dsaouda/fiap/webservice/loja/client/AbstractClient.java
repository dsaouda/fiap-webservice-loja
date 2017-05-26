package com.github.dsaouda.fiap.webservice.loja.client;

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
		String url = "https://fiap-ws-loja.herokuapp.com";
		
		Client client = ClientBuilder.newClient();
		target = client.target(url);
	}
}
