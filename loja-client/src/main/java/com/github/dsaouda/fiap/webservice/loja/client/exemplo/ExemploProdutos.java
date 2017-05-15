package com.github.dsaouda.fiap.webservice.loja.client.exemplo;

import com.github.dsaouda.fiap.webservice.loja.client.ProdutoClient;

public class ExemploProdutos {

	public static void main(String[] args) {
		
		//USAR
		
		//Client client = ClientBuilder.newClient();
		//WebTarget target = client.target("http://localhost:8080");
		//ProdutoClient produtoClient = new ProdutoClient(target);
		
		// OU PADRÃO
		ProdutoClient produtoClient = new ProdutoClient();
		
		System.out.println("TODOS OS PRODUTOS");
		produtoClient.todos().stream().forEach(System.out::println);
		
		System.out.println("======================");
		System.out.println("PRODUTO ÚNICO");
		System.out.println(produtoClient.get(1L));
	}	
}
