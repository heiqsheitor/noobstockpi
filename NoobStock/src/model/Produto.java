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
	private String dataCriacao; 
	private double preco;

	// Construtor completo para uso no DAO
	public Produto(String id_produto, String sKU, String nome, String qtd, int estoqueMinimo, String localização,
			String fornecedor, String categoria, String dataCriacao, double preco) {
		this.id_produto = id_produto;
		this.SKU = sKU;
		this.nome = nome;
		this.Qtd = qtd;
		this.estoqueMinimo = estoqueMinimo;
		this.Localização = localização;
		this.Fornecedor = fornecedor;
		this.Categoria = categoria;
		this.dataCriacao = dataCriacao;
		this.preco = preco;
	}

	// Construtor vazio para inicialização flexível
	public Produto() {
	}

	// Getters e Setters
	public String getId_produto() {
		return id_produto;
	}

	public void setId_produto(String id_produto) {
		this.id_produto = id_produto;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getQtd() {
		return Qtd;
	}

	public void setQtd(String qtd) {
		Qtd = qtd;
	}

	public int getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(int estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public String getLocalização() {
		return Localização;
	}

	public void setLocalização(String localização) {
		Localização = localização;
	}

	public String getFornecedor() {
		return Fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		Fornecedor = fornecedor;
	}

	public String getCategoria() {
		return Categoria;
	}

	public void setCategoria(String categoria) {
		Categoria = categoria;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
}