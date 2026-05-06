package controller;

import java.util.List;
import javax.swing.JOptionPane;

import model.Fornecedor;
import model.FornecedorDAO;
import view.Principal;
import view.TelaAdicionarFornecedor;
import view.TelaFornecedor;

public class FornecedorController {
	private TelaFornecedor view;
	private Navegador navegador;
	private FornecedorDAO dao;
	private TelaAdicionarFornecedor view2;

	public FornecedorController(TelaFornecedor view, Navegador navegador, FornecedorDAO dao,
			TelaAdicionarFornecedor view2) {
		this.view = view;
		this.navegador = navegador;
		this.dao = dao; // Usa o DAO que veio do Main
		this.view2 = view2;

		// ── NAVEGAÇÃO DA TELA FORNECEDOR (LISTA) ──
		view.setInicioAcao(() -> {
			navegador.navegarPara(Principal.INICIO);
		});
		view.setControleEstoqueAcao(() -> {
			navegador.navegarPara(Principal.ESTOQUE);
		});
		view.setPerfilAcao(() -> {
			navegador.navegarPara(Principal.PERFIL);
		});

		// Vai para a tela de Adicionar
		view.setAdicionar(() -> {
			navegador.navegarPara(Principal.ADICIONARFOR);
		});

		// ── NAVEGAÇÃO DA TELA DE ADICIONAR ──
		// Volta para a lista
		view2.setAcaoVoltar(() -> {
			navegador.navegarPara(Principal.FORNECEDOR);
		});

		// ── LÓGICA DE SALVAR O FORNECEDOR ──
		view2.setAdicionarAcao(e -> {
			// Puxa os dados da tela
			String nome = view2.getNomeFornecedor();
			String cnpj = view2.getCnpj();
			String email = view2.getEmail();
			String duracao = view2.getDuracao();

			// Validação
			if (nome.isEmpty() || email.isEmpty()) {
				JOptionPane.showMessageDialog(view2, "Preencha pelo menos o Nome e o Email!");
				return;
			}

			// Cria o objeto e salva no banco
			Fornecedor fornecedor = new Fornecedor(nome, cnpj, email, duracao);

			if (dao.adicionar(fornecedor)) {
				JOptionPane.showMessageDialog(view2, "Fornecedor salvo com sucesso!");
				view2.limparCampos(); // Limpa a tela

				// ATUALIZA A TABELA NA HORA!
				view.carregarTabelaFornecedores();

				// Volta para a tela da tabela
				navegador.navegarPara(Principal.FORNECEDOR);
			} else {
				JOptionPane.showMessageDialog(view2, "Erro ao salvar o fornecedor no banco.");
			}
		});
	}

	// Mantido apenas caso você precise usar em outro lugar
	public List<Fornecedor> buscarTodos() {
		return dao.listar();
	}
}