package com.github.dsaouda.fiap.webservice.loja.api;

import java.io.IOException;
import java.net.URI;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class Application {
	
	private static final String baseUri = "http://0.0.0.0";
	
    public static HttpServer startServer() {
        final ResourceConfig resourceConfig = new ResourceConfig().packages("com.github.dsaouda.fiap.webservice.loja.api.rest");
        resourceConfig.register(JacksonFeature.class);
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(baseUri+":" + System.getenv("PORT")), resourceConfig);
    }

    public static void main(String[] args) throws IOException {
    	final HttpServer server = startServer();
        System.out.println(String.format("Projeto rodando em %s. Acesse para maiores informações da api %s/application.wadl", baseUri, baseUri));
        
        configLogger();
        server.start();
    }

	private static void configLogger() {
		Logger l = Logger.getLogger("org.glassfish.grizzly.http.server.HttpHandler");
        l.setLevel(Level.FINE);
        l.setUseParentHandlers(false);
        
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        l.addHandler(ch);
	}
}