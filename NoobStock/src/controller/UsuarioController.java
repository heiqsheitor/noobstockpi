package controller;

import java.awt.event.ComponentAdapter;

import javax.swing.JOptionPane;

import model.Usuario;
import model.UsuarioDAO;
import view.TelaCadastro;

public class UsuarioController extends ComponentAdapter {

	private final TelaCadastro view;
	private final UsuarioDAO model;
	private final Navegador navegador;

	public UsuarioController(TelaCadastro view, UsuarioDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;
		this.view.Cadastrar(e -> {
			String nome = view.getNome();
			String email = view.getEmail();
			String senha = view.getSenha();

			if (!nome.equals("") && !email.equals("") && !senha.equals("")) {
				Usuario u = new Usuario(nome, email, senha);
				this.model.cadastrarUsuario(u);

				this.view.limparFormulario();
				JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
				
				// CORREÇÃO: Usar a chave de navegação correta definida em Principal.java
				if (this.navegador != null) {
					this.navegador.navegarPara("LOGIN");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Erro, Preencha todos os campos!");
			}
		});
	}
}
