package com.github.dsaouda.fiap.webservice.loja.api.manager;

import java.util.List;
import java.util.stream.Collectors;

import com.github.dsaouda.fiap.webservice.loja.api.model.Produto;
import com.github.dsaouda.fiap.webservice.loja.api.model.Produtos;
import com.github.dsaouda.fiap.webservice.loja.api.repository.ProdutoRepository;

public class ProdutoManager {

	public Produtos getProdutoListWhereInCodigo(List<Produto> codigosProdutos) {		
		List<Produto> produtos = codigosProdutos.stream()
				.map(p -> ProdutoRepository.findByCodigo(p.getCodigo()))			
				.collect(Collectors.toList());
		return new Produtos(produtos);
	}
	
}
