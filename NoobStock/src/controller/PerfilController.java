package controller;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Usuario;
import model.UsuarioDAO;
import view.Principal;
import view.TelaMensagem;
import view.TelaPerfil;

public class PerfilController {
	private final TelaPerfil view;
	private final UsuarioDAO model;
	private final Navegador navegador;

	public PerfilController(TelaPerfil view, UsuarioDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;

		view.setInicioAcao(() -> {
			navegador.navegarPara(Principal.INICIO);
		});

		view.setEstoqueAcao(() -> {
			navegador.navegarPara(Principal.ESTOQUE);
		});

		view.setFornecedorAcao(() -> {
			navegador.navegarPara(Principal.FORNECEDOR);
		});
		
		view.setSaidaAcao(() -> {
			navegador.navegarPara(Principal.SAIDA);
		});

		configurarEventos();
	}

	private void configurarEventos() {
		view.adicionarAtualizarListener(e -> atualizar());
		view.adicionarCancelarListener(e -> view.limparCampos());
		view.adicionarDeslogar(e -> deslogar());
		view.adicionarExcluirContaListener(e -> excluir());
	}

	private void atualizar() {
		Usuario logado = navegador.getUsuarioLogado();
		if (logado == null) {
			new TelaMensagem("Aviso", "Erro: Faça login primeiro para testar esta tela!", "AVISO").setVisible(true);
			return;
		}

		String novoNome = view.getNome();
		String novoEmail = view.getEmail();
		String novaSenha = view.getSenha();

		if (novoNome.isEmpty() || novoEmail.isEmpty() || novaSenha.isEmpty()) {
			new TelaMensagem("Aviso", "Preencha todos os campos.", "AVISO").setVisible(true);
			return;
		}

		if (model.atualizarUsuario(logado.getId_usuario(), novoNome, novoEmail, novaSenha)) {
			logado.setNome(novoNome);
			logado.setEmail(novoEmail);
			logado.setSenha(novaSenha);
			new TelaMensagem("Sucesso", "Dados atualizados com sucesso!", "SUCESSO").setVisible(true);
		} else {
			new TelaMensagem("Erro", "Erro ao atualizar no banco de dados.", "ERRO").setVisible(true);
		}
	}

	private void excluir() {
		Usuario logado = navegador.getUsuarioLogado();
		if (logado == null) {
			new TelaMensagem("Aviso", "Erro: Faça login primeiro para testar esta tela!", "AVISO").setVisible(true);
			return;
		}

		String emailDigitado = view.getEmail();

		if (!emailDigitado.equals(logado.getEmail())) {
			new TelaMensagem("Aviso", "Para excluir, digite seu e-mail atual corretamente no campo E-mail.", "AVISO").setVisible(true);
			return;
		}

		TelaMensagem confirmacao = new TelaMensagem("Aviso", "Tem certeza? Esta ação excluirá sua conta permanentemente.");
		confirmacao.setVisible(true);

		if (confirmacao.isConfirmado()) {
			if (model.excluirUsuario(logado.getEmail())) {
				new TelaMensagem("Sucesso", "Conta excluída.", "SUCESSO").setVisible(true);
				deslogar();
			}
		}
	}

	private void deslogar() {
		navegador.setUsuarioLogado(null);
		navegador.navegarPara(Principal.LOGIN);
	}
}