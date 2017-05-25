package com.github.dsaouda.fiap.webservice.loja.client.exemplo;

import com.github.dsaouda.fiap.webservice.loja.client.EfetuarCompraClient;
import com.github.dsaouda.fiap.webservice.loja.client.builder.PedidoBuilder;
import com.github.dsaouda.fiap.webservice.loja.client.model.Pedido;
import com.github.dsaouda.fiap.webservice.loja.client.model.Produto;
import com.github.dsaouda.fiap.webservice.loja.client.model.TipoDocumento;

public class ExemploEfetuarCompra {

	public static void main(String[] args) {	
		EfetuarCompraClient client = new EfetuarCompraClient();
				
		Pedido pedido = new PedidoBuilder()
			.cliente("José dos Santos")
			.documento("12364477801", TipoDocumento.CPF)
			.adicionarProduto(new Produto().setCodigo(1L))
			.adicionarProduto(new Produto().setCodigo(4L))
			.adicionarProduto(new Produto().setCodigo(5L))
			.build();
			
		System.out.println(client.comprar(pedido));	
	}
}
