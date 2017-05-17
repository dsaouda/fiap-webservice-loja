package com.github.dsaouda.fiap.webservice.loja.api.repository;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.github.dsaouda.fiap.webservice.loja.api.exception.ProdutoNaoEncontradoException;
import com.github.dsaouda.fiap.webservice.loja.api.model.Produto;

final public class ProdutoRepository {

	private static Map<Long, Produto> produtos = new LinkedHashMap<>();
	private ProdutoRepository() {}
	
	static {
		long codigo = 0;
		
		produtos.put(++codigo, new Produto(codigo, "PS4", 10, 1200.0));
		produtos.put(++codigo, new Produto(codigo, "Controle PS4", 100, 59.90));
		produtos.put(++codigo, new Produto(codigo, "Jogo 1", 8, 199.90));
		produtos.put(++codigo, new Produto(codigo, "Jogo 2", 8, 199.90));
		produtos.put(++codigo, new Produto(codigo, "Jogo 3", 8, 199.90));
		produtos.put(++codigo, new Produto(codigo, "Jogo 4", 8, 199.90));
		produtos.put(++codigo, new Produto(codigo, "Jogo 5", 8, 199.90));
		produtos.put(++codigo, new Produto(codigo, "Jogo 6", 8, 199.90));		
	}
	
	public static Map<Long, Produto> getProdutos() {
		return Collections.unmodifiableMap(produtos);
	}
	
	public static Produto findByCodigo(long codigo) {
		if (!produtos.containsKey(codigo)) {
			throw new ProdutoNaoEncontradoException(codigo);
		}
		
		return produtos.get(codigo);
		
	}
}
