package com.github.dsaouda.fiap.webservice.loja.api.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dsaouda.fiap.webservice.loja.api.exception.GovernoNaoPodeEmitirNotaException;
import com.github.dsaouda.fiap.webservice.loja.api.model.Loja;
import com.github.dsaouda.fiap.webservice.loja.api.model.Pedido;
import com.github.dsaouda.fiap.webservice.loja.governo.client.factory.GovernoPortFactory;

import br.com.governo.ws.Exception_Exception;
import br.com.governo.ws.Governo;
import br.com.governo.ws.NotaFiscal;

public class GovernoManager {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Governo governoService = GovernoPortFactory.create(Loja.GOVERNO_DOCUMENTO, Loja.GOVERNO_SENHA);
	
	public NotaFiscal emitirNota(Pedido pedido) {
		try {
			logger.info("emitindo nota no governo");
			return governoService.emitirNotaFiscal(pedido.getDocumento(), pedido.getValorTotal());
		} catch (Exception e) {
			logger.error("erro ao emitir nota no governo");
			throw new GovernoNaoPodeEmitirNotaException();
		}
	}
	
	public double porcentagemImpostos() {
		try {
			return governoService.listarImpostos().stream().mapToDouble(i -> i.getAliquota()).sum();
		} catch (Exception_Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
