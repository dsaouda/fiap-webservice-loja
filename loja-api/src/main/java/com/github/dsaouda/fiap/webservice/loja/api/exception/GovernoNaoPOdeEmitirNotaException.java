package com.github.dsaouda.fiap.webservice.loja.api.exception;

public class GovernoNaoPodeEmitirNotaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public GovernoNaoPodeEmitirNotaException() {
		super("Governo não pode emitir nota");
	}
}
