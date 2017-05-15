package com.github.dsaouda.fiap.webservice.loja.client;

import java.io.IOException;
import java.util.Properties;

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
		String url = "http://localhost:8080";
		
		try {
			Properties env = new Properties();
			env.load(AbstractClient.class.getResourceAsStream("/application.properties"));
			url = env.getProperty("server.host", url);
		} catch (IOException e) {}
		
		Client client = ClientBuilder.newClient();
		target = client.target(url);
	}
}
