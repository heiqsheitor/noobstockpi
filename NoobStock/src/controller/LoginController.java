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
			// CORREÇÃO: Criar um objeto Usuario com as credenciais para autenticar
			Usuario credenciais = new Usuario(null, null, email, senha);
			Usuario usuario = model.autenticar(credenciais);

			if (usuario != null) {
				view.mostrarMensagem("Login realizado com sucesso!");
				// CORREÇÃO: Usar a constante correta de navegação (ESTOQUE ou INICIO se existisse)
				// Como "INICIO" não existe em Principal.java, vamos usar ESTOQUE que é a tela principal do sistema
				if (navegador != null) {
					navegador.navegarPara("ESTOQUE"); 
				}
			} else {
				view.mostrarMensagem("Email ou senha inválidos!");
			}

		} catch (Exception e) {
			view.mostrarMensagem("Erro ao fazer login!");
			e.printStackTrace();
		}
	}
	private void irParaCadastro() {
		if (navegador != null) {
			navegador.navegarPara("CADASTRO");
		}
    }

    private void irParaRecuperacao() {
		if (navegador != null) {
			navegador.navegarPara("REDEFINIR");
		}
    }

}
