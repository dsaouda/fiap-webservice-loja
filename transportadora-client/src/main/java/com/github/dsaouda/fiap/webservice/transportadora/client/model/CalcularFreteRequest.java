package com.github.dsaouda.fiap.webservice.transportadora.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="calculaFreteRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class CalcularFreteRequest {
	
	private Integer quantidadeProdutos;
	private Double valorTotalRemessa;
	public Integer getQuantidadeProdutos() {
		return quantidadeProdutos;
	}
	public void setQuantidadeProdutos(Integer quantidadeProdutos) {
		this.quantidadeProdutos = quantidadeProdutos;
	}
	public Double getValorTotalRemessa() {
		return valorTotalRemessa;
	}
	public void setValorTotalRemessa(Double valorTotalRemessa) {
		this.valorTotalRemessa = valorTotalRemessa;
	}

}
