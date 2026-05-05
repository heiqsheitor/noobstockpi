package model;

public class Usuario {
	private String id_usuario;
	private String nome;
	private String email;
	private String senha;

	// Construtor completo (usado para o LOGIN, quando o banco já tem o ID)
	public Usuario(String id_usuario, String nome, String email, String senha) {
		this.id_usuario = id_usuario;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	// NOVO: Construtor sem ID (usado para o CADASTRO)
	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	// Setters (Necessários para atualizar os dados do usuário na memória após
	// edição)
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}