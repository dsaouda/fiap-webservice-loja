package com.github.dsaouda.fiap.webservice.loja.api;

import java.io.IOException;

import org.glassfish.grizzly.http.server.HttpServer;

public class Application {
	
    public static void main(String[] args) throws IOException {
    	GrizzlyServer grizzly = new GrizzlyServer();
    	HttpServer server = grizzly.createServer();
    	
    	grizzly.enableLogger();
    	grizzly.info();
        server.start();
    }
}