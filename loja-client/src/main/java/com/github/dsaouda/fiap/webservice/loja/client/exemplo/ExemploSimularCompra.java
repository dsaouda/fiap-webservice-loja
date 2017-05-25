package com.github.dsaouda.fiap.webservice.loja.client.exemplo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.dsaouda.fiap.webservice.loja.client.SimularCompraClient;
import com.github.dsaouda.fiap.webservice.loja.client.model.Produto;
import com.github.dsaouda.fiap.webservice.loja.client.model.SimularCompra;

public class ExemploSimularCompra {

	public static void main(String[] args) {
		
		SimularCompraClient client = new SimularCompraClient();
		
		List<Produto> produtos = Arrays.asList(
			new Produto().setCodigo(1L),
			new Produto().setCodigo(4L),
			new Produto().setCodigo(5L)
		);
		
		SimularCompra simular = client.simular(produtos);
		imprimirResultado(simular);
		
		System.out.println("\n\n");
		System.out.println("Exemplo métodos 2");
		
		Set<Long> codigos = new HashSet<>();		
		codigos.add(9L);
		
		SimularCompra simular2 = client.simular(codigos);
		imprimirResultado(simular2);
		
	}

	private static void imprimirResultado(SimularCompra simular) {
		System.out.println("Produtos e valores");
		simular.getValorProdutos().stream().forEach(System.out::println);
		
		System.out.println("================================");
		System.out.println("Valor frete: " + simular.getValorFrete());		
		System.out.println("Valor impostos: " + simular.getValorImpostos());
		System.out.println("Valor total produtos: " + simular.getValorTotalProdutos());
		System.out.println("Valor total (frete + impostos + produtos): " + simular.getValorTotal());
	}	
}
