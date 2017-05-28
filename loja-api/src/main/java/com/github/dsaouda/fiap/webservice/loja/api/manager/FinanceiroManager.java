package com.github.dsaouda.fiap.webservice.loja.api.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dsaouda.fiap.webservice.loja.api.exception.FinanceiroNaoPodeDebitarValorException;
import com.github.dsaouda.fiap.webservice.loja.api.model.Loja;
import com.github.dsaouda.fiap.webservice.loja.api.model.Pedido;
import com.github.dsaouda.fiap.webservice.loja.financeiro.client.factory.FinanceiroPortFactory;

import br.com.fincliente.Cobranca;
import br.com.fincliente.CobrarCliente;
import br.com.governo.ws.NotaFiscal;

public class FinanceiroManager {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private CobrarCliente financeiroService = FinanceiroPortFactory.create(Loja.FINANCEIRO_CARGO, Loja.FINANCEIRO_ESTABELECIMENTO);
	
	public void debitarValor(Pedido pedido, NotaFiscal nota) {
		double valorTotal = nota.getValorTotal();
		Cobranca cobranca = new Cobranca();
		cobranca.setCpf(Long.parseLong(pedido.getDocumento()));
		cobranca.setValor(valorTotal);
		
		try {
			boolean result = financeiroService.cobrar(cobranca); 
			logger.info("cobrança realizada com resultado {}", result);
			throw new FinanceiroNaoPodeDebitarValorException();
		} catch (Exception e) {
			logger.error("erro ao enviar dados a financeira");
			throw new FinanceiroNaoPodeDebitarValorException();
		}
	}

}
