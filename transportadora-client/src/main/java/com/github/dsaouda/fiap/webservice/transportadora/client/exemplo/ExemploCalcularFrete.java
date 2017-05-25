package com.github.dsaouda.fiap.webservice.transportadora.client.exemplo;

import com.github.dsaouda.fiap.webservice.transportadora.client.CalcularFreteClient;
import com.github.dsaouda.fiap.webservice.transportadora.client.model.CalcularFreteRequest;

public class ExemploCalcularFrete {

	public static void main(String[] args) {
		
		
		CalcularFreteRequest request = new CalcularFreteRequest();
		request.setValorTotalRemessa(1000000.0);
		request.setQuantidadeProdutos(1000);
		
		// OU PADRÃO
		CalcularFreteClient client = new CalcularFreteClient();
		
		System.out.println(client.calcular(request));
		
	}	
}
