package com.github.dsaouda.fiap.webservice.loja.api.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dsaouda.fiap.webservice.loja.api.exception.TransportadoraIndisponivelException;
import com.github.dsaouda.fiap.webservice.loja.api.model.Loja;
import com.github.dsaouda.fiap.webservice.loja.api.model.Pedido;
import com.github.dsaouda.fiap.webservice.loja.api.model.Produtos;
import com.github.dsaouda.fiap.webservice.transportadora.client.CalcularFreteClient;
import com.github.dsaouda.fiap.webservice.transportadora.client.GerarFreteClient;
import com.github.dsaouda.fiap.webservice.transportadora.client.model.CalcularFreteRequest;
import com.github.dsaouda.fiap.webservice.transportadora.client.model.GerarFreteRequest;
import com.github.dsaouda.fiap.webservice.transportadora.client.model.GerarFreteResponse;

import br.com.governo.ws.NotaFiscal;

public class TransportadoraManager {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public void solicitarEntrega(Pedido pedido, NotaFiscal notaFiscal) {
		
		GerarFreteRequest request = new GerarFreteRequest();
		request.setValorTotalRemessa(notaFiscal.getValorTotalComImpostos());
		request.setQuantidadeProdutos(pedido.getTotalProdutos());
		request.setCpfCnpjDestinatario(pedido.getDocumento());
		request.setCpfCnpjRemetente(Loja.CNPJ_LOJA);

		GerarFreteClient client = new GerarFreteClient();

		try {
			GerarFreteResponse entrega = client.gerar(request);
			logger.info("transportadora resposta {}", entrega.getMensagem());
		} catch (Exception e) {
			throw new TransportadoraIndisponivelException();
		}
	}
	
	public Double calculaFrete(Produtos produtos){
		
		try {
			CalcularFreteRequest req = new CalcularFreteRequest();
			req.setQuantidadeProdutos(produtos.quantidade());
			req.setValorTotalRemessa(produtos.valorTotal());
	
			CalcularFreteClient client = new CalcularFreteClient();
			return client.calcular(req);
		} catch (Exception e) {
			e.printStackTrace();
			return 0.0;
		}
	}

}
