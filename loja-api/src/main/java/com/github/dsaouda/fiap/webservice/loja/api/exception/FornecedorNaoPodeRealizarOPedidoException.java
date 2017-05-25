package com.github.dsaouda.fiap.webservice.loja.api.exception;

public class FornecedorNaoPodeRealizarOPedidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public FornecedorNaoPodeRealizarOPedidoException() {
		super("Fornecedor não pode realizar o pedido");
	}
}
