package com.github.dsaouda.fiap.webservice.loja.governo.client.exemplo;

import com.github.dsaouda.fiap.webservice.loja.governo.client.factory.GovernoPortFactory;

import br.com.governo.ws.Exception_Exception;
import br.com.governo.ws.Governo;
import br.com.governo.ws.NotaFiscal;

public class GovernoExemplo {
	public static void main(String[] args) throws Exception_Exception {
		Governo governo = GovernoPortFactory.create("22233344455", "1234");
		
		NotaFiscal notaFiscal = governo.emitirNotaFiscal("12345678920", 10.0);
		
		System.out.println(notaFiscal.getDocumentoEmissor());
		System.out.println(notaFiscal.getDocumentoReceptor());
		System.out.println(notaFiscal.getValorTotal());
		System.out.println(notaFiscal.getValorTotalImpostos());
		System.out.println(notaFiscal.getValorTotalComImpostos());
	}
}
