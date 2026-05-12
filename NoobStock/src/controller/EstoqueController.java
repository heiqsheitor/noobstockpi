package controller;

import java.awt.event.ComponentAdapter;

import javax.swing.JOptionPane;

import model.Produto;
import model.ProdutoDAO;
import view.Principal;
import view.TelaAdicionarProduto;
import view.TelaControleEstoque;

public class EstoqueController extends ComponentAdapter {

	private TelaControleEstoque view;
    private Navegador navegador;
    private ProdutoDAO produtoDAO;
    private TelaAdicionarProduto telaAdicionar;

    public EstoqueController(TelaControleEstoque view, Navegador navegador,
                             ProdutoDAO produtoDAO, TelaAdicionarProduto telaAdicionar) {
        this.view = view;
        this.navegador = navegador;
        this.produtoDAO = produtoDAO;
        this.telaAdicionar = telaAdicionar;

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

        // ── EDITAR PRODUTO ─────────────────────────────────────────────────────
        // Ao clicar em "Editar" no popup: pré-preenche a TelaAdicionarProduto
        // com os dados do produto selecionado e navega para ela.
        view.setEditarAcao(produto -> {
            telaAdicionar.preencherParaEdicao(produto);
            navegador.navegarPara(Principal.ADICIONAR);
        });

        // ── EXCLUIR PRODUTO ────────────────────────────────────────────────────
        // Ao clicar em "Excluir" no popup: pede confirmação, deleta do banco
        // e recarrega a tabela sem precisar trocar de tela.
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
                        view.recarregarTabela(); // Atualiza a tabela na mesma tela
                    } else {
                        JOptionPane.showMessageDialog(
                            view,
                            "Erro ao excluir o produto. Tente novamente.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(view, "ID de produto inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
