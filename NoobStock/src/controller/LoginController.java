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
			view.mostrarMensagem("Aviso", "Preencha todos os campos!", "AVISO");
			return;
		}

		try {
			// Criar um objeto Usuario com o NOME DE USUÁRIO para autenticar
			Usuario credenciais = new Usuario(nomeUsuario, null, senha);
			Usuario usuario = model.autenticar(credenciais);

			if (usuario != null) {
				view.mostrarMensagem("Sucesso", "Login realizado com sucesso!", "SUCESSO");
				
				if (navegador != null) {
					// 👉 ESTA É A LINHA QUE FALTAVA! Agora o sistema sabe quem está logado.
					navegador.setUsuarioLogado(usuario); 
					
					navegador.navegarPara("INICIO"); 
				}
			} else {
				view.mostrarMensagem("Erro", "Usuário ou senha inválidos!", "ERRO");
			}

		} catch (Exception e) {
			view.mostrarMensagem("Erro", "Erro ao fazer login!", "ERRO");
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
