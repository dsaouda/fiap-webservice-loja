package com.github.dsaouda.fiap.webservice.transportadora.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="calculaFreteResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class CalcularFreteResponse {
	
	private String valorTotalFrete;

	public String getValorTotalFrete() {
		return valorTotalFrete;
	}

	public void setValorTotalFrete(String valorTotalFrete) {
		this.valorTotalFrete = valorTotalFrete;
	}
}
