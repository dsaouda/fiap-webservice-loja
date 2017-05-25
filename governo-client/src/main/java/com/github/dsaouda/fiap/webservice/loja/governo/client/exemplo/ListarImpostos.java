package com.github.dsaouda.fiap.webservice.loja.governo.client.exemplo;

import com.github.dsaouda.fiap.webservice.loja.governo.client.factory.GovernoPortFactory;

import br.com.governo.ws.Exception_Exception;
import br.com.governo.ws.Governo;

public class ListarImpostos {
	public static void main(String[] args) throws Exception_Exception {
		Governo governo = GovernoPortFactory.create("22233344455", "1234");
		
		governo.listarImpostos().stream().forEach(System.out::println);
	}
}
