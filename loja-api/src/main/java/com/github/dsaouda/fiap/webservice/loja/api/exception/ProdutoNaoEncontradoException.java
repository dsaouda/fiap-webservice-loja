package com.github.dsaouda.fiap.webservice.loja.api.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ProdutoNaoEncontradoException() {
		super("Produto não encontrado.");
	}
}
