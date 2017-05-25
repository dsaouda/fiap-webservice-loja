package com.github.dsaouda.fiap.webservice.loja.api.model;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="pedido")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pedido {
	private static long numeroPedido;
	private String documento;
	private TipoDocumento tipoDocumento;
	private String nomeCliente;
	private List<Produto>listaProdutos;
	
	public Pedido() {
		numeroPedido++;
	}
	
	public long getNumeroPedido() {
		return numeroPedido;
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

	public Double getValorTotal() {
		return getListaProdutos().stream().mapToDouble(p -> p.getValorUnitario()).sum();
	}
	
	@Override
	public String toString() {
		return "Pedido [numeroPedido=" + numeroPedido + ", documento=" + documento + ", tipoDocumento=" + tipoDocumento
				+ ", nomeCliente=" + nomeCliente + ", listaProdutos=" + listaProdutos + "]";
	}

}
