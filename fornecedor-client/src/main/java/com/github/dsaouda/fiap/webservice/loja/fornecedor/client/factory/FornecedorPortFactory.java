package com.github.dsaouda.fiap.webservice.loja.fornecedor.client.factory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import br.com.fiap.fornecedor.ws.FornecedorWS;
import br.com.fiap.fornecedor.ws.FornecedorWSService;

public class FornecedorPortFactory {

	public static FornecedorWS create() {
		return create("lojista", "123456");
	}
	
	public static FornecedorWS create(String username, String password) {
		FornecedorWS fornecedorPort = new FornecedorWSService().getFornecedorWSPort();
		
		Map<String, Object> reqCtx = ((BindingProvider)fornecedorPort).getRequestContext();		
		Map<String, List<String>> headers = new HashMap<>();
		
		headers.put("username", Collections.singletonList(username));
		headers.put("password", Collections.singletonList(password));
		
		reqCtx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
		return fornecedorPort;
	}
	
}
