package com.github.dsaouda.fiap.webservice.loja.fornecedor.client.exemplo;

import com.github.dsaouda.fiap.webservice.loja.fornecedor.client.factory.FornecedorPortFactory;

import br.com.fiap.fornecedor.ws.FornecedorException_Exception;
import br.com.fiap.fornecedor.ws.FornecedorWS;

public class ExemploFornecedorProdutos {
	public static void main(String[] args) throws FornecedorException_Exception {		
		FornecedorWS port = FornecedorPortFactory.create();
		
		System.out.println("Produtos");
		
		port.listarProdutos().stream().forEach(p -> {
			System.out.println("Código: " + p.getCodigo());
			System.out.println("Descrição: " + p.getDescricao());
			System.out.println("Valor: " + p.getValor());
			System.out.println("------------------------");
		});
	}
}
