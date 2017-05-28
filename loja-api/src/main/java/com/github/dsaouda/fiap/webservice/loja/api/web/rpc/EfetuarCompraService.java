package com.github.dsaouda.fiap.webservice.loja.api.web.rpc;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dsaouda.fiap.webservice.loja.api.manager.FinanceiroManager;
import com.github.dsaouda.fiap.webservice.loja.api.manager.FornecedorManager;
import com.github.dsaouda.fiap.webservice.loja.api.manager.GovernoManager;
import com.github.dsaouda.fiap.webservice.loja.api.manager.PedidoManager;
import com.github.dsaouda.fiap.webservice.loja.api.manager.TransportadoraManager;
import com.github.dsaouda.fiap.webservice.loja.api.model.Pedido;

import br.com.governo.ws.NotaFiscal;
import io.swagger.annotations.Api;

@Api
@Path("/efetuar-compra")
public class EfetuarCompraService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response comprar(Pedido pedido) {		
		
		try {
			new PedidoManager().preencherDetalheProduto(pedido);
			
			logger.info("=============================================");
			logger.info("pedido recebido");
			logger.info("número pedido {}", pedido.getNumeroPedido());
			logger.info("documento pedido {}", pedido.getDocumento());
			logger.info("tipo documento {}", pedido.getTipoDocumento().name());
			logger.info("nome cliente {}", pedido.getNomeCliente());
			logger.info("valor total pedido sem impostos {}", pedido.getValorTotal());
			logger.info("total de produtos", pedido.getTotalProdutos());
			
			logger.info("fornecedor: enviarPedidoSeForNecessario");
			new FornecedorManager().enviarPedidoSeForNecessario(pedido);
			
			logger.info("governo: emitirNota");
			NotaFiscal notaFiscal = new GovernoManager().emitirNota(pedido);
			
			logger.info("financeiro: debitarValor");
			new FinanceiroManager().debitarValor(pedido, notaFiscal);
			
			logger.info("transportadora: solicitarEntrega");
			new TransportadoraManager().solicitarEntrega(pedido, notaFiscal);
			
			logger.info("pedido finalizado com sucesso");
			return Response.ok(true).build();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ocorreu algum problema que não pode efetivar o pedido", e.getMessage());
			
			new PedidoManager().voltarProdutosNoEstoque(pedido);
			
			return Response.ok(false)
				.header("x-exception-message", e.getMessage())
				.header("x-exception-class", e.getClass().getName())
				.build();
		}
	}
}
