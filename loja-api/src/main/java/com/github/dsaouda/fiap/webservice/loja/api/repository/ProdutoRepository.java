package com.github.dsaouda.fiap.webservice.loja.api.repository;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.github.dsaouda.fiap.webservice.loja.api.exception.ProdutoNaoEncontradoException;
import com.github.dsaouda.fiap.webservice.loja.api.model.Produto;

final public class ProdutoRepository {

	private static Map<Long, Produto> produtos = new LinkedHashMap<>();
	private ProdutoRepository() {}
	
	static {
		long codigo = 0;
		
		produtos.put(++codigo, new Produto(codigo, "Smart TV LED 40' Samsung 40J5500 Full HD com Conversor Digital 3 HDMI 2 USB Wi-Fi 120Hz", 5, 1699.00));
		produtos.put(++codigo, new Produto(codigo, "iPhone 5S 16GB Cinza Espacial Tela Retina 4' Câmera de 8MP - Apple", 10, 1299.99));
		produtos.put(++codigo, new Produto(codigo, "Smartphone Motorola Moto G4 Plus Dual Chip Android 6.0 Tela 5.5' 32GB Câmera 16MP - Preto", 8, 1099.0));
		produtos.put(++codigo, new Produto(codigo, "T Shirt Rock In Rio M C Gola Redonda Mundo Masculina", 100, 79.90));
		produtos.put(++codigo, new Produto(codigo, "Auto Rádio com MP3 Player e Rádio FM Naveg NVS 3068 com Entradas USB SD e Auxiliar", 15, 49.99));
		produtos.put(++codigo, new Produto(codigo, "Mixer Philips Walita Linha Daily Collection Preto RI1602/9 c/ copo dosador e mini processador - 250W", 30, 79.99));
		produtos.put(++codigo, new Produto(codigo, "Conjunto de Sobremesa Inox 12 Peças - Orb", 8, 24.90));
		produtos.put(++codigo, new Produto(codigo, "Liquidificador Philco Ph800 2,4L 4 Velocidades Preto - 800W", 5, 199.90));
		produtos.put(++codigo, new Produto(codigo, "Livro - Box Sherlock Holmes: As Aventuras de Sherlock Holmes (3 Volumes)", 100, 16.90));
		produtos.put(++codigo, new Produto(codigo, "Livro - O Livro das Criaturas de Harry Potter", 40, 61.99));
		produtos.put(++codigo, new Produto(codigo, "Box - Marvel: Guerra Civil e Guerras Secretas (Edição Slim) + Pôster", 12, 19.90));
		produtos.put(++codigo, new Produto(codigo, "Game: Injustice 2 Ed. Limitada PS4", 20, 249.90));
		produtos.put(++codigo, new Produto(codigo, "Game: Injustice 2 + Camiseta - PS4", 5, 269.99));
		produtos.put(++codigo, new Produto(codigo, "Massageador Eletrico Digital Para Dores E Tensao Muscular Acupuntura Fisioterapia Lcd Com 4 Eletrod", 15, 44.99));
	}
	
	public static Map<Long, Produto> getProdutos() {
		return Collections.unmodifiableMap(produtos);
	}
	
	public static Produto findByCodigo(long codigo) {
		if (!produtos.containsKey(codigo)) {
			throw new ProdutoNaoEncontradoException(codigo);
		}
		
		return produtos.get(codigo);
		
	}
}
