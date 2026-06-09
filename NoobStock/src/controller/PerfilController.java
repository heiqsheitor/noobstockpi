package controller;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Usuario;
import model.UsuarioDAO;
import view.Principal;
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
			JOptionPane.showMessageDialog(view, "Erro: Faça login primeiro para testar esta tela!");
			return;
		}

		String novoNome = view.getNome();
		String novoEmail = view.getEmail();
		String novaSenha = view.getSenha();

		if (novoNome.isEmpty() || novoEmail.isEmpty() || novaSenha.isEmpty()) {
			JOptionPane.showMessageDialog(view, "Preencha todos os campos.");
			return;
		}

		if (model.atualizarUsuario(logado.getId_usuario(), novoNome, novoEmail, novaSenha)) {
			logado.setNome(novoNome);
			logado.setEmail(novoEmail);
			logado.setSenha(novaSenha);
			JOptionPane.showMessageDialog(view, "Dados atualizados com sucesso!");
		} else {
			JOptionPane.showMessageDialog(view, "Erro ao atualizar no banco de dados.");
		}
	}

	private void excluir() {
		Usuario logado = navegador.getUsuarioLogado();
		if (logado == null) {
			JOptionPane.showMessageDialog(view, "Erro: Faça login primeiro para testar esta tela!");
			return;
		}

		String emailDigitado = view.getEmail();

		if (!emailDigitado.equals(logado.getEmail())) {
			JOptionPane.showMessageDialog(view, "Para excluir, digite seu e-mail atual corretamente no campo E-mail.");
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(view, "Tem certeza? Esta ação excluirá sua conta permanentemente.",
				"Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (confirm == JOptionPane.YES_OPTION) {
			if (model.excluirUsuario(logado.getEmail())) {
				JOptionPane.showMessageDialog(view, "Conta excluída.");
				deslogar();
			}
		}
	}

	private void deslogar() {
		navegador.setUsuarioLogado(null);
		navegador.navegarPara(Principal.LOGIN);
	}
}