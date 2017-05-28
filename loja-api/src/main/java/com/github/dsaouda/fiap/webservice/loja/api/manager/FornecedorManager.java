package com.github.dsaouda.fiap.webservice.loja.api.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dsaouda.fiap.webservice.loja.api.exception.FornecedorNaoPodeRealizarOPedidoException;
import com.github.dsaouda.fiap.webservice.loja.api.model.Loja;
import com.github.dsaouda.fiap.webservice.loja.api.model.Pedido;
import com.github.dsaouda.fiap.webservice.loja.api.model.Produto;
import com.github.dsaouda.fiap.webservice.loja.fornecedor.client.factory.FornecedorPortFactory;

import br.com.fiap.fornecedor.ws.FornecedorException_Exception;
import br.com.fiap.fornecedor.ws.FornecedorWS;
import br.com.fiap.fornecedor.ws.PedidoDTO;
import br.com.fiap.fornecedor.ws.ProdutoDTO;

public class FornecedorManager {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private FornecedorWS fornecedorService = FornecedorPortFactory.create(Loja.FORNECEDOR_USERNAME, Loja.FORNECEDOR_PASSWORD);	
	
	public void enviarPedidoSeForNecessario(Pedido pedido) {
		logger.info("api fornecedor");
		
		PedidoDTO pedidoFornecedor = getPedidoParaFornecedor(pedido);
		if (!hasEnviarPedidoFornecedor(pedidoFornecedor)) {
			logger.info("não foram enviados pedidos ao fornecedor");
			return ;
		}
		
		try {
			boolean result = fornecedorService.efetuarPedido(pedidoFornecedor);
			logger.info("pedido enviado ao fornecedor com resultado {}", result);
			
			if (result == true) {
				return ;
			}
			
			throw new FornecedorNaoPodeRealizarOPedidoException();
			
		} catch (FornecedorException_Exception e) {
			logger.error("erro ao enviar pedido ao fornecedor {}", e);
			throw new FornecedorNaoPodeRealizarOPedidoException();
		}
	}
	
	private boolean hasEnviarPedidoFornecedor(PedidoDTO pedidoFornecedor) {
		return pedidoFornecedor.getProdutos().size() > 0;
	}

	private PedidoDTO getPedidoParaFornecedor(Pedido pedido) {
		PedidoDTO pedidoFornecedor = new PedidoDTO();
		pedidoFornecedor.setCpfCnpj(pedido.getDocumento());
		
		for(Produto p : pedido.getListaProdutos()) {
			
			if (p.getQuantidadeEstoque() == 0) {
				
				logger.info("enviar produto {} ", p.getCodigo());
				
				//adiciona todos os produtos que precisa enviar no pedido
				ProdutoDTO produtoFornecedor = new ProdutoDTO();
				produtoFornecedor.setCodigo((int)p.getCodigo());
				produtoFornecedor.setDescricao(p.getDescricao());
				produtoFornecedor.setValor(p.getValorUnitario());
				
				pedidoFornecedor.getProdutos().add(produtoFornecedor);
			}
		}
		
		return pedidoFornecedor;
	}
	
}
