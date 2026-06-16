package controller;

import java.awt.event.ComponentAdapter;
import java.util.List;

import javax.swing.JOptionPane;

import model.HistoricoMovimentacao;
import model.Produto;
import model.ProdutoDAO;
import model.SaidaDAO;
import view.Principal;
import view.TelaAdicionarProduto;
import view.TelaControleEstoque;
import view.TelaDetalhesProduto;
import view.TelaMensagem;

public class EstoqueController extends ComponentAdapter {

	private TelaControleEstoque view;
	private Navegador navegador;
	private ProdutoDAO produtoDAO;
	private SaidaDAO saidaDAO;
	private TelaAdicionarProduto telaAdicionar;
	private TelaDetalhesProduto telaDetalhesProduto;

	public EstoqueController(TelaControleEstoque view, Navegador navegador, ProdutoDAO produtoDAO,
			TelaAdicionarProduto telaAdicionar, TelaDetalhesProduto telaDetalhesProduto, SaidaDAO saidaDAO) {
		this.view = view;
		this.navegador = navegador;
		this.produtoDAO = produtoDAO;
		this.saidaDAO = saidaDAO;
		this.telaAdicionar = telaAdicionar;
		this.telaDetalhesProduto = telaDetalhesProduto;

		view.setDetalhesAcao(e -> {
			Produto produtoSelecionado = view.getProdutoSelecionado();
			if (produtoSelecionado == null)
				return;

			telaDetalhesProduto.preencherDados(produtoSelecionado);
			try {
				int idProduto = Integer.parseInt(produtoSelecionado.getId_produto());
				List<HistoricoMovimentacao> historico = saidaDAO.buscarHistoricoPorProduto(idProduto);
				telaDetalhesProduto.carregarHistorico(historico);
			} catch (NumberFormatException ex) {
				telaDetalhesProduto.carregarHistorico(null);
			}
			navegador.navegarPara(Principal.DETALHES);
		});

		view.setInicioAcao(() -> navegador.navegarPara(Principal.INICIO));
		view.setControleEstoqueAcao(() -> navegador.navegarPara(Principal.ESTOQUE));
		view.setFornecedorAcao(() -> navegador.navegarPara(Principal.FORNECEDOR));
		view.setPerfilAcao(() -> navegador.navegarPara(Principal.PERFIL));
		view.setSaida(() -> navegador.navegarPara(Principal.SAIDA));
		view.setAdicionar(() -> {
			telaAdicionar.limparCampos();
			navegador.navegarPara(Principal.ADICIONAR);
		});

		view.setEditarAcao(produto -> {
			telaAdicionar.preencherParaEdicao(produto);
			navegador.navegarPara(Principal.ADICIONAR);
		});

		view.setExcluirAcao(produto -> {
			TelaMensagem confirmacao = new TelaMensagem("Confirmar exclusão",
					"Tem certeza que deseja excluir o produto \"" + produto.getNome() + "\"?\n"
							+ "Esta ação não pode ser desfeita.");
			confirmacao.setVisible(true);

			if (confirmacao.isConfirmado()) {
				try {
					int id = Integer.parseInt(produto.getId_produto());
					if (produtoDAO.deletarProduto(id)) {
						new TelaMensagem("Sucesso", "Produto \"" + produto.getNome() + "\" excluído com sucesso!",
								"SUCESSO").setVisible(true);
						view.recarregarTabela();
					} else {
						new TelaMensagem("Erro",
								"Erro ao excluir o produto.\nVerifique se ele possui histórico de saídas vinculado.",
								"ERRO").setVisible(true);
					}
				} catch (NumberFormatException e) {
					new TelaMensagem("Erro", "ID de produto inválido.", "ERRO").setVisible(true);
				}
			}
		});
	}
}
