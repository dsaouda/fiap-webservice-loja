package com.github.dsaouda.fiap.webservice.loja.client.builder;

import java.util.ArrayList;
import java.util.List;

import com.github.dsaouda.fiap.webservice.loja.client.model.Pedido;
import com.github.dsaouda.fiap.webservice.loja.client.model.Produto;
import com.github.dsaouda.fiap.webservice.loja.client.model.TipoDocumento;

final public class PedidoBuilder {
	private Pedido pedido;
	private List<Produto> produtos = new ArrayList<>();
	
	public PedidoBuilder() {
		pedido = new Pedido();
	}
	
	public PedidoBuilder documento(String documento, TipoDocumento tipo) {
		pedido.setDocumento(documento);
		pedido.setTipoDocumento(tipo);
		return this;
	}
	
	public PedidoBuilder cliente(String nomeCliente) {
		pedido.setNomeCliente(nomeCliente);
		return this;
	}
	
	public PedidoBuilder adicionarProduto(Produto produto) {
		produtos.add(produto);
		return this;
	}
	
	public Pedido build() {
		pedido.setListaProdutos(produtos);
		return pedido;
	}
}
