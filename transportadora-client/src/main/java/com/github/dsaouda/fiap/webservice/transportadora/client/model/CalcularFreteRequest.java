package com.github.dsaouda.fiap.webservice.transportadora.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="calculaFreteRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class CalcularFreteRequest {
	
	private int quantidadeProdutos;
	private double valorTotalRemessa;
	
	public CalcularFreteRequest() {}
	
	public Integer getQuantidadeProdutos() {
		return quantidadeProdutos;
	}
	public void setQuantidadeProdutos(int quantidadeProdutos) {
		this.quantidadeProdutos = quantidadeProdutos;
	}
	public Double getValorTotalRemessa() {
		return valorTotalRemessa;
	}
	public void setValorTotalRemessa(double valorTotalRemessa) {
		this.valorTotalRemessa = valorTotalRemessa;
	}

}
