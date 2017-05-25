package com.github.dsaouda.fiap.webservice.loja.financeiro.client.exemplo;

import com.github.dsaouda.fiap.webservice.loja.financeiro.client.factory.FinanceiroPortFactory;

import br.com.fincliente.Cobranca;
import br.com.fincliente.CobrarCliente;

public class FinanceiroExemplo {
	public static void main(String[] args) {
		CobrarCliente cobrarClientPort = FinanceiroPortFactory.create();
		
		Cobranca cobranca = new Cobranca();
		cobranca.setCpf(123);
		cobranca.setValor(10);
		
		System.out.println(cobrarClientPort.cobrar(cobranca));
	}
}
