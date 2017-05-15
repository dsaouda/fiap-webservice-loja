package com.github.dsaouda.fiap.webservice.loja.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Produto {

	private long codigo;
	private String descricao;
	private int quantidadeEstoque;
	private Double valorUnitario;
	
	protected Produto() {}
	
	public Produto(long codigo, String descricao, int quantidadeEstoque, Double valorUnitario) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.quantidadeEstoque = quantidadeEstoque;
		this.valorUnitario = valorUnitario;
	}
	
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public long getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao + ", quantidadeEstoque=" + quantidadeEstoque
				+ ", valorUnitario=" + valorUnitario + "]";
	}
}
