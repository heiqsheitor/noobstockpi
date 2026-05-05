package model;

public class Fornecedor {
	private int id_fornecedor;
	private String nome;
	private String cnpj;
	private String email;

	public Fornecedor(int id_fornecedor, String nome, String cnpj, String email) {
		super();
		this.id_fornecedor = id_fornecedor;
		this.nome = nome;
		this.cnpj = cnpj;
		this.email = email;
	}

	public int getId_fornecedor() {
		return id_fornecedor;
	}

	public String getNome() {
		return nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getEmail() {
		return email;
	}

}
