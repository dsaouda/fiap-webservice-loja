package com.github.dsaouda.fiap.webservice.loja.api.exception;

public class FinanceiroNaoPodeDebitarValorException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	
	public FinanceiroNaoPodeDebitarValorException() {
		super("Financeiro não pode debitar o valor");
	}
}
