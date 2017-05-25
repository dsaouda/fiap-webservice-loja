package com.github.dsaouda.fiap.webservice.transportadora.client;

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
		String user = "user";
		String password = "password";
		
		try {
			Properties env = new Properties();
			env.load(AbstractClient.class.getResourceAsStream("/application.properties"));
			url = env.getProperty("server.host", url);
			user = env.getProperty("server.user", user);
			password = env.getProperty("server.password", password);
		} catch (IOException e) {}
		
		Client client = ClientBuilder.newClient();
		client.register(new Authenticator(user, password));
		target = client.target(url);
	}
}
