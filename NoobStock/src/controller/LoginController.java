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
		String nomeUsuario = view.getUsuario();
		String senha = view.getSenha().trim();

		if (nomeUsuario.isEmpty() || senha.isEmpty()) {
			view.mostrarMensagem("Preencha todos os campos!");
			return;
		}

		try {
			// CORREÇÃO: Criar um objeto Usuario com o NOME DE USUÁRIO para autenticar
			Usuario credenciais = new Usuario(nomeUsuario, null, senha);
			Usuario usuario = model.autenticar(credenciais);

			if (usuario != null) {
				view.mostrarMensagem("Login realizado com sucesso!");
				
				if (navegador != null) {
					navegador.navegarPara("INICIO"); 
				}
			} else {
				view.mostrarMensagem("Usuário ou senha inválidos!");
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
