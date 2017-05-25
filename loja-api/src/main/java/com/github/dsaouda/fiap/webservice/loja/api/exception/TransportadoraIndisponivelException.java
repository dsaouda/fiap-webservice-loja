package com.github.dsaouda.fiap.webservice.loja.api.exception;

public class TransportadoraIndisponivelException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public TransportadoraIndisponivelException() {
		super("Não foi possível se comunicar com a transportadora");
	}
}
