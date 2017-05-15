package com.github.dsaouda.fiap.webservice.loja.client.model;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="pedido")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pedido {
	
	private String documento;
	private TipoDocumento tipoDocumento;
	private String nomeCliente;
	private List<Produto>listaProdutos;
	
	public String getDocumento() {
		return documento;
	}
	
	public Pedido setDocumento(String documento) {
		this.documento = documento;
		return this;
	}
	
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	
	public Pedido setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
		return this;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public Pedido setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
		return this;
	}
	
	public List<Produto> getListaProdutos() {
		return Collections.unmodifiableList(listaProdutos);
	}
	
	public Pedido setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
		return this;
	}
}
