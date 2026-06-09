package controller;

import java.awt.event.ComponentAdapter;

import javax.swing.JOptionPane;

import model.Produto;
import model.ProdutoDAO;
import view.Principal;
import view.TelaAdicionarProduto;
import view.TelaControleEstoque;
import view.TelaDetalhesProduto;
import view.TelaMensagem;

public class EstoqueController extends ComponentAdapter {

	private TelaControleEstoque view;
	private Navegador navegador;
	private ProdutoDAO produtoDAO;
	private TelaAdicionarProduto telaAdicionar;
	private TelaDetalhesProduto telaDetalhesProduto;

	public EstoqueController(TelaControleEstoque view, Navegador navegador, ProdutoDAO produtoDAO,
			TelaAdicionarProduto telaAdicionar, TelaDetalhesProduto telaDetalhesProduto) {
		this.view = view;
		this.navegador = navegador;
		this.produtoDAO = produtoDAO;
		this.telaAdicionar = telaAdicionar;
		this.telaDetalhesProduto = telaDetalhesProduto;

		view.setDetalhesAcao(e -> {
			Produto produtoSelecionado = view.getProdutoSelecionado();

			if (produtoSelecionado != null) {
				telaDetalhesProduto.preencherDados(produtoSelecionado);
				navegador.navegarPara(Principal.DETALHES);
			}
		});

		// ── NAVEGAÇÃO ──────────────────────────────────────────────────────────
		view.setInicioAcao(() -> {
			navegador.navegarPara(Principal.INICIO);
		});

		view.setControleEstoqueAcao(() -> {
			navegador.navegarPara(Principal.ESTOQUE);
		});

		view.setAdicionar(() -> {
			telaAdicionar.limparCampos(); // Garante que abre em modo cadastro
			navegador.navegarPara(Principal.ADICIONAR);
		});

		view.setPerfilAcao(() -> {
			navegador.navegarPara(Principal.PERFIL);
		});

		view.setFornecedorAcao(() -> {
			navegador.navegarPara(Principal.FORNECEDOR);
		});
		
		view.setSaida(() -> {
			navegador.navegarPara(Principal.SAIDA);
		});

		view.setEditarAcao(produto -> {
			telaAdicionar.preencherParaEdicao(produto);
			navegador.navegarPara(Principal.ADICIONAR);
		});

		view.setExcluirAcao(produto -> {
			TelaMensagem confirmacao = new TelaMensagem("Confirmar exclusão", "Tem certeza que deseja excluir este item?");
			confirmacao.setVisible(true);

			if (confirmacao.isConfirmado()) {
				try {
					int id = Integer.parseInt(produto.getId_produto());
					if (produtoDAO.deletarProduto(id)) {
						new TelaMensagem("Sucesso", "Produto excluído com sucesso!", "SUCESSO").setVisible(true);
						view.recarregarTabela(); // Atualiza a tabela na mesma tela
					} else {
						JOptionPane.showMessageDialog(view, "Erro ao excluir o produto. Tente novamente.", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(view, "ID de produto inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
