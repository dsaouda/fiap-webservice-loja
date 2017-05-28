package com.github.dsaouda.fiap.webservice.loja.api.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Produtos {

	private List<Produto> produtos;

	public Produtos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public double valorTotal() {
		return produtos.stream().mapToDouble(p -> p.getValorUnitario()).sum();
	}
	
	public List<Produto> getLista() {
		return Collections.unmodifiableList(produtos);
	}
	
	public int quantidade() {
		return produtos.size();
	}

	public List<String> valorCadaProduto() {
		return produtos.stream().map(p -> {
			return p.getCodigo() + " => " + p.getDescricao() + " ("+p.getValorUnitario()+")";
		}).collect(Collectors.toList());
	}

}
