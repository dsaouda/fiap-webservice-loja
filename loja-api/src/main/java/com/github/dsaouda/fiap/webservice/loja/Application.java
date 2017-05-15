package com.github.dsaouda.fiap.webservice.loja;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Application {
	private static String baseUri;
	
	static {
		Properties env = new Properties();
    	try {
			env.load(Application.class.getResourceAsStream("/application.properties"));
			baseUri = env.getProperty("server.host");
		} catch (IOException e) {
			System.out.println("Você precisa configurar um arquivo chamado application.properties");
			
			throw new RuntimeException(e);
		}
    	
	}
	
    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("com.github.dsaouda.fiap.webservice.loja.rest");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(baseUri), rc);
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