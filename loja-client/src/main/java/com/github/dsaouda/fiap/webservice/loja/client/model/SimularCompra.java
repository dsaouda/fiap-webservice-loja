package com.github.dsaouda.fiap.webservice.loja.client.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="simular_compra")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimularCompra {

	private List<String> valorProdutos;
	private double valorTotalProdutos;
	private double valorFrete;
	private double valorImpostos;
	private double valorTotal;
	private double porcentagemImpostos;
	
	public double getPorcentagemImpostos() {
		return porcentagemImpostos;
	}

	public void setPorcentagemImpostos(double porcentagemImpostos) {
		this.porcentagemImpostos = porcentagemImpostos;
	}

	public List<String> getValorProdutos() {
		return valorProdutos;
	}
	
	public void setValorProdutos(List<String> valorProdutos) {
		this.valorProdutos = valorProdutos;
	}
	
	public double getValorTotalProdutos() {
		return valorTotalProdutos;
	}
	
	public void setValorTotalProdutos(double valorTotalProdutos) {
		this.valorTotalProdutos = valorTotalProdutos;
	}
	
	public double getValorFrete() {
		return valorFrete;
	}
	
	public void setValorFrete(double valorFrete) {
		this.valorFrete = valorFrete;
	}
	
	public double getValorImpostos() {
		return valorImpostos;
	}
	
	public void setValorImpostos(double valorImpostos) {
		this.valorImpostos = valorImpostos;
	}
	
	public double getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	@Override
	public String toString() {
		return "SimularCompra [valorProdutos=" + valorProdutos + ", valorTotalProdutos=" + valorTotalProdutos
				+ ", valorFrete=" + valorFrete + ", valorImpostos=" + valorImpostos + ", valorTotal=" + valorTotal
				+ "]";
	}
}
