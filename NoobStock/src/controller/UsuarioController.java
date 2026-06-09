package controller;

import java.awt.event.ComponentAdapter;

import javax.swing.JOptionPane;

import model.Usuario;
import model.UsuarioDAO;
import view.TelaCadastro;
import view.TelaMensagem;

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
				new TelaMensagem("Sucesso", "Usuário cadastrado com sucesso!", "SUCESSO").setVisible(true);

				if (this.navegador != null) {
					this.navegador.navegarPara("LOGIN");
				}
			} else {
				new TelaMensagem("Erro", "Preencha todos os campos!", "ERRO").setVisible(true);
			}
		});
	}
}