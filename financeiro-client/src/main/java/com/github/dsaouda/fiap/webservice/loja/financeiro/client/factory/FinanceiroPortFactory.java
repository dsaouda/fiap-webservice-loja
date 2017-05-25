package com.github.dsaouda.fiap.webservice.loja.financeiro.client.factory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import br.com.fincliente.CobrarCliente;
import br.com.fincliente.CobrarClienteService;

public class FinanceiroPortFactory {

	public static CobrarCliente create() {
		return create("cargo", "estabelecimento");
	}
	
	public static CobrarCliente create(String key, String value) {
		CobrarCliente cobraClientePort = new CobrarClienteService().getCobrarClientePort();
		
		BindingProvider bindingProvider = (BindingProvider) cobraClientePort;
		bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://54.205.89.34:8080/FinanciamentoMBA/Cobrar_Cliente");
		
		Map<String, Object> reqCtx = ((BindingProvider)cobraClientePort).getRequestContext();		
		Map<String, List<String>> headers = new HashMap<>();
		
		headers.put(key, Collections.singletonList(value));
		
		reqCtx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
		return cobraClientePort;
	}
	
}