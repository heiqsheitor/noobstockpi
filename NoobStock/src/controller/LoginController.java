package controller;

import java.awt.event.ComponentAdapter;
import model.Usuario;
import model.UsuarioDAO;
import view.Principal;
import view.TelaLogin;

/**
 * Controller responsável pela lógica da tela de Login.
 */
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

	/**
	 * Vincula os botões da View às funções deste Controller.
	 */
	private void configurarEventos() {
		view.adicionarListenerLogin(e -> fazerLogin());
		view.adicionarListenerCadastro(e -> irParaCadastro());
		view.adicionarListenerEsqueciSenha(e -> irParaRecuperacao());
	}

	/**
	 * Tenta realizar a autenticação do usuário.
	 */
	private void fazerLogin() {
		String nomeUsuario = view.getUsuario();
		String senha = view.getSenha().trim();

		// Validação simples de campos vazios
		if (nomeUsuario.isEmpty() || senha.isEmpty()) {
			view.mostrarMensagem("Por favor, preencha todos os campos!");
			return;
		}

		try {
			// Tenta autenticar no banco de dados
			Usuario credenciais = new Usuario(nomeUsuario, null, senha);
			Usuario usuarioLogado = model.autenticar(credenciais);

			if (usuarioLogado != null) {
				// PASSO CRÍTICO: Guarda o usuário logado na "sessão" do Navegador
				// Isso permite que o PerfilController saiba o ID do usuário depois.
				navegador.setUsuarioLogado(usuarioLogado); 
				
				view.mostrarMensagem("Login realizado com sucesso!");
				
				// Redireciona para a tela de início
				navegador.navegarPara(Principal.INICIO); 
			} else {
				view.mostrarMensagem("Usuário ou senha inválidos!");
			}

		} catch (Exception e) {
			view.mostrarMensagem("Erro ao tentar realizar login. Verifique sua conexão.");
			e.printStackTrace();
		}
	}

	/**
	 * Navega para a tela de cadastro.
	 */
	private void irParaCadastro() {
		navegador.navegarPara(Principal.CADASTRO);
	}

	/**
	 * Navega para a tela de recuperação de senha.
	 */
	private void irParaRecuperacao() {
		navegador.navegarPara(Principal.REDEFINIR);
	}
}