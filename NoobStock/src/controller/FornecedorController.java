package controller;

import java.util.List;
import javax.swing.JOptionPane;

import model.Fornecedor;
import model.FornecedorDAO;
import view.Principal;
import view.TelaAdicionarFornecedor;
import view.TelaFornecedor;
import view.TelaMensagem;

public class FornecedorController {
	private TelaFornecedor view;
	private Navegador navegador;
	private FornecedorDAO dao;
	private TelaAdicionarFornecedor view2;

	public FornecedorController(TelaFornecedor view, Navegador navegador, FornecedorDAO dao,
			TelaAdicionarFornecedor view2) {
		this.view = view;
		this.navegador = navegador;
		this.dao = dao;
		this.view2 = view2;

		// ── NAVEGAÇÃO DA TELA FORNECEDOR (LISTA) ──────────────────────────────
		view.setInicioAcao(() -> {
			navegador.navegarPara(Principal.INICIO);
		});
		view.setControleEstoqueAcao(() -> {
			navegador.navegarPara(Principal.ESTOQUE);
		});
		view.setPerfilAcao(() -> {
			navegador.navegarPara(Principal.PERFIL);
		});

		view.setSaida(() -> {
			navegador.navegarPara(Principal.SAIDA);
		});

		// Vai para a tela de Adicionar (limpa campos garantindo modo cadastro)
		view.setAdicionar(() -> {
			view2.limparCampos();
			navegador.navegarPara(Principal.ADICIONARFOR);
		});

		// ── NAVEGAÇÃO DA TELA DE ADICIONAR ────────────────────────────────────
		view2.setAcaoVoltar(() -> {
			view2.limparCampos(); // Sai do modo edição ao voltar
			navegador.navegarPara(Principal.FORNECEDOR);
		});

		// ── LÓGICA DE SALVAR (CADASTRO OU EDIÇÃO) ─────────────────────────────
		view2.setAdicionarAcao(e -> {
			String nome = view2.getNomeFornecedor();
			String cnpj = view2.getCnpj();
			String email = view2.getEmail();
			String duracao = view2.getDuracao();

			if (nome.isEmpty() || email.isEmpty()) {
				new TelaMensagem("Aviso", "Preencha pelo menos o Nome e o Email!", "AVISO").setVisible(true);
				return;
			}

			if (view2.isEdicao()) {
				// ── MODO EDIÇÃO: atualiza o fornecedor existente ───────────────
				Fornecedor fornecedor = new Fornecedor(nome, cnpj, email, duracao);
				fornecedor.setIdfornecedor(view2.getIdEmEdicao());

				if (dao.atualizar(fornecedor)) {
					new TelaMensagem("Sucesso", "Fornecedor atualizado com sucesso!", "SUCESSO").setVisible(true);
					view2.limparCampos();
					view.carregarTabelaFornecedores();
					navegador.navegarPara(Principal.FORNECEDOR);
				} else {
					new TelaMensagem("Erro", "Erro ao atualizar o fornecedor. Verifique os dados.", "ERRO")
							.setVisible(true);
				}

			} else {
				// ── MODO CADASTRO: adiciona novo fornecedor ────────────────────
				Fornecedor fornecedor = new Fornecedor(nome, cnpj, email, duracao);

				if (dao.adicionar(fornecedor)) {
					new TelaMensagem("Sucesso", "Fornecedor salvo com sucesso!", "SUCESSO").setVisible(true);
					view2.limparCampos();
					view.carregarTabelaFornecedores();
					navegador.navegarPara(Principal.FORNECEDOR);
				} else {
					new TelaMensagem("Erro", "Erro ao salvar o fornecedor no banco.", "ERRO").setVisible(true);
				}
			}
		});

		// ── EDITAR FORNECEDOR ──────────────────────────────────────────────────
		// Ao clicar em "Editar" no popup: pré-preenche a TelaAdicionarFornecedor
		// com os dados do fornecedor selecionado e navega para ela.
		view.setEditarAcao(fornecedor -> {
			view2.preencherParaEdicao(fornecedor);
			navegador.navegarPara(Principal.ADICIONARFOR);
		});

		// ── EXCLUIR FORNECEDOR ─────────────────────────────────────────────────
		// Ao clicar em "Excluir" no popup: pede confirmação, deleta do banco
		// e recarrega a tabela sem precisar trocar de tela.
		view.setExcluirAcao(fornecedor -> {
			TelaMensagem confirmacao = new TelaMensagem(
				"Confirmar exclusão", 
				"Tem certeza que deseja excluir o fornecedor \"" + fornecedor.getNome() + "\"?\nEsta ação não pode ser desfeita."
			);
			confirmacao.setVisible(true); // O ecrã vai bloquear aqui à espera da resposta do utilizador

			// Quando a janela fechar, verificamos se ele clicou em "Sim"
			if (confirmacao.isConfirmado()) {
				if (dao.deletar(fornecedor.getIdfornecedor())) {
					new TelaMensagem("Sucesso", "Fornecedor \"" + fornecedor.getNome() + "\" excluído com sucesso!", "SUCESSO").setVisible(true);
					view.recarregarTabela();
				} else {
					new TelaMensagem("Erro", "Erro ao excluir o fornecedor. Tente novamente.\n(Verifique se há produtos vinculados a ele.)", "ERRO").setVisible(true);
				}
			}
		});
	}

	public List<Fornecedor> buscarTodos() {
		return dao.listar();
	}
}
