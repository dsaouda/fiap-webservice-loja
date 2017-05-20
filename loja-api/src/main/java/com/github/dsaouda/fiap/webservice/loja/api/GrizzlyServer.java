package com.github.dsaouda.fiap.webservice.loja.api;

import java.net.URI;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.github.dsaouda.fiap.webservice.loja.api.exception.ExceptionHandler;
import com.github.dsaouda.fiap.webservice.loja.api.filter.CORSResponseFilter;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.Contact;
import io.swagger.models.Info;

public class GrizzlyServer {

	private static final String defaultHost = "http://0.0.0.0";
	private static final String defaultPort = "8080";
	private URI uri;

	public HttpServer createServer() {
		String host = env("HOST", defaultHost);
		String port = env("PORT", defaultPort); 
		uri = URI.create(host + ":" + port);

		String[] packages = {"com.github.dsaouda.fiap.webservice.loja.api.rest", "com.wordnik.swagger.jersey.listing"};

		final ResourceConfig resourceConfig = new ResourceConfig().packages(packages);
		resourceConfig.register(CORSResponseFilter.class);
		resourceConfig.register(JacksonFeature.class);
		resourceConfig.register(ServletConfig.class);
		resourceConfig.register(ExceptionHandler.class);
		resourceConfig.register(io.swagger.jaxrs.listing.ApiListingResource.class);
		resourceConfig.register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setResourcePackage("com.github.dsaouda.fiap.webservice.loja.api.rest");
		beanConfig.setVersion("1.0.0");
		beanConfig.setSchemes(new String[]{ "http", "https" });
		beanConfig.setHost("fiap-ws-loja.herokuapp.com");
		beanConfig.setBasePath("/");
		beanConfig.setScan(true);

		Contact contact = new Contact()
				.name("Diego Saouda")
				.email("diegosaouda@gmail.com");

		Info info = new Info()
				.title("Fiap WebService Loja")
				.description("Projeto final da disciplina")
				.version("v1")
				.contact(contact);

		beanConfig.setInfo(info);
		beanConfig.setTitle("fiap webservice loja");
		beanConfig.setContact("diegosaouda@gmail.com");

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
