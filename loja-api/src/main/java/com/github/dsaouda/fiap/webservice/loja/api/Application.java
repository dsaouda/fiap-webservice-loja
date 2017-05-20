package com.github.dsaouda.fiap.webservice.loja.api;

import java.io.IOException;

import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;

public class Application {
	
    public static void main(String[] args) throws IOException {
    	GrizzlyServer grizzly = new GrizzlyServer();
    	HttpServer server = grizzly.createServer();
    	
    	CLStaticHttpHandler staticHttpHandler = new CLStaticHttpHandler(Application.class.getClassLoader(), "swagger-ui/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/docs");
    	
    	grizzly.enableLogger();
    	grizzly.info();
        server.start();
    }
}