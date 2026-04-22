package model;

public class Produto {
	private String id_produto;
	private String SKU;
	private String nome;
	private String Qtd;
	private int estoqueMinimo;
	private String Localização;
	private String Fornecedor;
	private String Categoria;

	// Construtor completo (usado ao buscar do banco)
	public Produto(String id_produto, String SKU, String nome, String qtd, String localização, String fornecedor,
			String categoria) {
		this.id_produto = id_produto;
		this.SKU = SKU;
		this.nome = nome;
		this.Qtd = qtd;
		this.Localização = localização;
		this.Fornecedor = fornecedor;
		this.Categoria = categoria;
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

	public int getEstoqueMinimo() {
		return estoqueMinimo;
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

	public void setEstoqueMinimo(int estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}
}
