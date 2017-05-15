package com.github.dsaouda.fiap.webservice.loja.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="produto")
@XmlAccessorType(XmlAccessType.FIELD)
public class Produto {

	private long codigo;
	private String descricao;
	private int quantidadeEstoque;
	private Double valorUnitario;
	
	public Produto setCodigo(long codigo) {
		this.codigo = codigo;
		return this;
	}

	public Produto setDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}

	public Produto setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
		return this;
	}

	public Produto setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
		return this;
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
