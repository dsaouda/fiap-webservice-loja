package com.github.dsaouda.fiap.webservice.loja.api;

import java.net.URI;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.github.dsaouda.fiap.webservice.loja.api.exception.ExceptionHandler;

public class GrizzlyServer {

	private static final String defaultHost = "http://0.0.0.0";
	private static final String defaultPort = "8080";
	private URI uri;
	
	public HttpServer createServer() {
		String host = env("HOST", defaultHost);
		String port = env("PORT", defaultPort); 
		uri = URI.create(host + ":" + port);
		
        final ResourceConfig resourceConfig = new ResourceConfig().packages("com.github.dsaouda.fiap.webservice.loja.api.rest");
        resourceConfig.register(JacksonFeature.class);
        resourceConfig.registerClasses(ExceptionHandler.class);
        
        return GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);
    }
	
	private String env(String name, String defaultValue) {
		String value = System.getenv(name);		
		return value == null || value.trim().isEmpty() ? defaultValue : value;
	}

	public void info() {
		System.out.println(String.format("Ouvindo em %s\nWSDL %s/application.wadl", uri, uri));		
	}
	
	public void enableLogger() {
		Logger l = Logger.getLogger("org.glassfish.grizzly.http.server.HttpHandler");
        l.setLevel(Level.FINE);
        l.setUseParentHandlers(false);
        
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        l.addHandler(ch);
	}
}
