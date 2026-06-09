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

public class EstoqueController extends ComponentAdapter {

    private TelaControleEstoque    view;
    private Navegador              navegador;
    private ProdutoDAO             produtoDAO;
    private SaidaDAO               saidaDAO;        // ← novo: carrega histórico
    private TelaAdicionarProduto   telaAdicionar;
    private TelaDetalhesProduto    telaDetalhesProduto;

    public EstoqueController(
            TelaControleEstoque  view,
            Navegador            navegador,
            ProdutoDAO           produtoDAO,
            TelaAdicionarProduto telaAdicionar,
            TelaDetalhesProduto  telaDetalhesProduto,
            SaidaDAO             saidaDAO) {           // ← parâmetro adicionado

        this.view                = view;
        this.navegador           = navegador;
        this.produtoDAO          = produtoDAO;
        this.saidaDAO            = saidaDAO;
        this.telaAdicionar       = telaAdicionar;
        this.telaDetalhesProduto = telaDetalhesProduto;

        // ── VER DETALHES ──────────────────────────────────────────────────────
        view.setDetalhesAcao(e -> {
            Produto produtoSelecionado = view.getProdutoSelecionado();
            if (produtoSelecionado == null) return;

            // 1. Preenche dados gerais do produto
            telaDetalhesProduto.preencherDados(produtoSelecionado);

            // 2. Carrega histórico real de saídas do banco
            try {
                int idProduto = Integer.parseInt(produtoSelecionado.getId_produto());
                List<HistoricoMovimentacao> historico =
                    saidaDAO.buscarHistoricoPorProduto(idProduto);
                telaDetalhesProduto.carregarHistorico(historico);
            } catch (NumberFormatException ex) {
                telaDetalhesProduto.carregarHistorico(null);
            }

            // 3. Navega para a tela de detalhes
            navegador.navegarPara(Principal.DETALHES);
        });

        // ── NAVEGAÇÃO ─────────────────────────────────────────────────────────
        view.setInicioAcao(()          -> navegador.navegarPara(Principal.INICIO));
        view.setControleEstoqueAcao(() -> navegador.navegarPara(Principal.ESTOQUE));
        view.setFornecedorAcao(()      -> navegador.navegarPara(Principal.FORNECEDOR));
        view.setPerfilAcao(()          -> navegador.navegarPara(Principal.PERFIL));
        view.setSaida(()               -> navegador.navegarPara(Principal.SAIDA));

        view.setAdicionar(() -> {
            telaAdicionar.limparCampos();   // garante modo cadastro
            navegador.navegarPara(Principal.ADICIONAR);
        });

        // ── EDITAR ────────────────────────────────────────────────────────────
        view.setEditarAcao(produto -> {
            telaAdicionar.preencherParaEdicao(produto);
            navegador.navegarPara(Principal.ADICIONAR);
        });

        // ── EXCLUIR ───────────────────────────────────────────────────────────
        view.setExcluirAcao(produto -> {
            int confirmar = JOptionPane.showConfirmDialog(
                view,
                "Tem certeza que deseja excluir o produto \"" + produto.getNome() + "\"?\n"
                + "Esta ação não pode ser desfeita.",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirmar == JOptionPane.YES_OPTION) {
                try {
                    int id = Integer.parseInt(produto.getId_produto());
                    if (produtoDAO.deletarProduto(id)) {
                        JOptionPane.showMessageDialog(
                            view,
                            "Produto \"" + produto.getNome() + "\" excluído com sucesso!",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                        view.recarregarTabela();
                    } else {
                        JOptionPane.showMessageDialog(
                            view,
                            "Erro ao excluir o produto.\n"
                            + "Verifique se ele possui histórico de saídas vinculado.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(view, "ID de produto inválido.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
