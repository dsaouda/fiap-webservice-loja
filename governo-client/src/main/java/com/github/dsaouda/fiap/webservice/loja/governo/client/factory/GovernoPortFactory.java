package com.github.dsaouda.fiap.webservice.loja.governo.client.factory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import br.com.governo.ws.Governo;
import br.com.governo.ws.GovernoService;

public class GovernoPortFactory {

	public static Governo create(String documento, String senha) {
		Governo governoPort = new GovernoService().getGovernoPort();
		BindingProvider bindingProvider = (BindingProvider) governoPort;
		bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://52.15.126.233:80/fiap-governo-ws/Governo");
		
		Map<String, Object> reqCtx = ((BindingProvider)governoPort).getRequestContext();		
		Map<String, List<String>> headers = new HashMap<>();
		
		headers.put("documento", Collections.singletonList(documento));
		headers.put("senha", Collections.singletonList(senha));
		
		reqCtx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
		return governoPort;
	}
	
}
