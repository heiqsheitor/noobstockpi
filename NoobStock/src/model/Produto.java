package model;

import java.util.Random;

public class Produto {
	private String id_produto;
	private String SKU;
	private String nome;
	private String Qtd;
	private String Localização;
	private String Fornecedor;
	private String Categoria;
	public Produto(String id_produto, String sKU, String nome, String qtd, String localização, String fornecedor,
			String categoria) {
		super();
		this.id_produto = id_produto;
		SKU = sKU;
		this.nome = nome;
		Qtd = qtd;
		Localização = localização;
		Fornecedor = fornecedor;
		Categoria = categoria;
	}
	
	public String getId_produto() {
		return id_produto;
	}
	public String getSKU() {
		return SKU;
	}
	public String getNome() {
		return nome;
	}
	public String getQtd() {
		return Qtd;
	}
	public String getLocalização() {
		return Localização;
	}
	public String getFornecedor() {
		return Fornecedor;
	}
	public String getCategoria() {
		return Categoria;
	}

}