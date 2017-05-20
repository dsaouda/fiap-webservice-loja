package com.github.dsaouda.fiap.webservice.loja.fornecedor.client.exemplo;

import com.github.dsaouda.fiap.webservice.loja.fornecedor.client.factory.FornecedorPortFactory;

import br.com.fiap.fornecedor.ws.FornecedorException_Exception;
import br.com.fiap.fornecedor.ws.FornecedorWS;
import br.com.fiap.fornecedor.ws.PedidoDTO;
import br.com.fiap.fornecedor.ws.ProdutoDTO;

public class ExemploFornecedorPedido {
	public static void main(String[] args) throws FornecedorException_Exception {		
		FornecedorWS port = FornecedorPortFactory.create();
		
		System.out.println("Efetuar pedido");
		
		PedidoDTO pedido = new PedidoDTO();
		pedido.setCpfCnpj("22233377701");
		
		ProdutoDTO p1 = new ProdutoDTO();
		p1.setCodigo(1);
		p1.setDescricao("d1");
		p1.setValor(10);		
		pedido.getProdutos().add(p1);
		
		ProdutoDTO p2 = new ProdutoDTO();
		p1.setCodigo(2);
		p1.setDescricao("d2");
		p1.setValor(12);
		
		pedido.getProdutos().add(p2);
		
		boolean hasPedidoEfetuado = port.efetuarPedido(pedido);
		
		System.out.println("Pedido Efetuado?");
		System.out.println(hasPedidoEfetuado);
	}
}
