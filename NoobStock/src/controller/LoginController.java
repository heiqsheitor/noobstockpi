package controller;

import java.awt.event.ComponentAdapter;

import model.Usuario;
import model.UsuarioDAO;
import view.TelaLogin;

public class LoginController extends ComponentAdapter {
	private TelaLogin view;
	private final UsuarioDAO model;
	private final Navegador navegador;

	public LoginController(TelaLogin view, UsuarioDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;

		configurarEventos();

	}

	private void configurarEventos() {
		view.adicionarListenerLogin(e -> fazerLogin());
		view.adicionarListenerCadastro(e -> irParaCadastro());
		view.adicionarListenerEsqueciSenha(e -> irParaRecuperacao());
	}

	private void fazerLogin() {
		String email = view.getEmail().trim();
		String senha = view.getSenha().trim();

		if (email.isEmpty() || senha.isEmpty()) {
			view.mostrarMensagem("Preencha todos os campos!");
			return;
		}

		try {
			Usuario usuario = model.autenticar(null);

			if (usuario != null) {
				view.mostrarMensagem("Login realizado com sucesso!");

				navegador.navegarPara("INICIO"); // ou tela principal
			} else {
				view.mostrarMensagem("Email ou senha inválidos!");
			}

		} catch (Exception e) {
			view.mostrarMensagem("Erro ao fazer login!");
			e.printStackTrace();
		}
	}
	private void irParaCadastro() {
        navegador.navegarPara("CADASTRO");
    }

    private void irParaRecuperacao() {
        navegador.navegarPara("RECUPERAR_SENHA");
    }

}