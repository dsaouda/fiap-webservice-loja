package com.github.dsaouda.fiap.webservice.loja.model;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pedido {
	private long numeroPedido;
	private String documento;
	private TipoDocumento tipoDocumento;
	private String nomeCliente;
	private List<Produto>listaProdutos;
	
	public long getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(long numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public List<Produto> getListaProdutos() {
		return Collections.unmodifiableList(listaProdutos);
	}
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
}
