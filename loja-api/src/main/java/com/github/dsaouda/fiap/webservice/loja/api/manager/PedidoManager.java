package com.github.dsaouda.fiap.webservice.loja.api.manager;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dsaouda.fiap.webservice.loja.api.model.Pedido;
import com.github.dsaouda.fiap.webservice.loja.api.model.Produto;
import com.github.dsaouda.fiap.webservice.loja.api.repository.ProdutoRepository;

public class PedidoManager {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public void preencherDetalheProduto(Pedido pedido) {
		pedido.setListaProdutos(
			pedido.getListaProdutos()
				.stream()
				.map(p -> ProdutoRepository.findByCodigo(p.getCodigo()))
				.collect(Collectors.toList())
		);
		
		//depois de localizar todos os produtos, decrementa do estoque
		pedido.getListaProdutos().stream().forEach(Produto::decrementaDoEstoque);
		
		logger.info("{}", pedido);
	}
	
	public void voltarProdutosNoEstoque(Pedido pedido) {
		pedido.getListaProdutos().stream().forEach(Produto::acrescentaNoEstoque);
	}
	
}
