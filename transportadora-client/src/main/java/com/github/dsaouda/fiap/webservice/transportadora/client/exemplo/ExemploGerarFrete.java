package com.github.dsaouda.fiap.webservice.transportadora.client.exemplo;

import com.github.dsaouda.fiap.webservice.transportadora.client.GerarFreteClient;
import com.github.dsaouda.fiap.webservice.transportadora.client.model.GerarFreteRequest;

public class ExemploGerarFrete {

	public static void main(String[] args) {
		
		GerarFreteRequest request = new GerarFreteRequest();
		request.setValorTotalRemessa(100000000.0);
		request.setQuantidadeProdutos(1000);
		request.setCpfCnpjDestinatario("1111");
		request.setCpfCnpjRemetente("2222");

		GerarFreteClient client = new GerarFreteClient();
		
		System.out.println(client.gerar(request).getMensagem());
		
	}	
}
