package com.github.dsaouda.fiap.webservice.transportadora.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="gerarFrete")
@XmlAccessorType(XmlAccessType.FIELD)
public class GerarFreteRequest {
	
	private String cpfCnpjDestinatario;
	private String cpfCnpjRemetente;
	private Integer quantidadeProdutos;
	private Double valorTotalRemessa;

	public String getCpfCnpjDestinatario() {
		return cpfCnpjDestinatario;
	}
	public void setCpfCnpjDestinatario(String cpfCnpjDestinatario) {
		this.cpfCnpjDestinatario = cpfCnpjDestinatario;
	}
	public String getCpfCnpjRemetente() {
		return cpfCnpjRemetente;
	}
	public void setCpfCnpjRemetente(String cpfCnpjRemetente) {
		this.cpfCnpjRemetente = cpfCnpjRemetente;
	}
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
