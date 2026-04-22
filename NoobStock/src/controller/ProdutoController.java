package controller;

import model.Produto;
import model.ProdutoDAO;
import view.TelaAdicionarProduto;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class ProdutoController extends ComponentAdapter {

    private final TelaAdicionarProduto view;
    private final ProdutoDAO model;

    public ProdutoController(TelaAdicionarProduto view, ProdutoDAO model) {
        this.view = view;
        this.model = model;

        // Injetando a ação no botão da view
        this.view.adicionarproduto(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto();
            }
        });
    }

    private void adicionarProduto() {
        // 1. Pegar os dados da tela
        String nomeProduto = view.getNomeProduto();
        String sku = view.getSKU();
        String qtd = view.getQuantidade();
        String localizacao = view.getLocalizacao();
        String fornecedor = view.getFornecedor();
        String categoria = view.getCategoria();

        // 2. Validação simples (exemplo)
        if (nomeProduto.trim().isEmpty() || sku.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha pelo menos o Nome e o SKU.");
            return;
        }

        // 3. Empacotar os dados no Model
        Produto novoProduto = new Produto(null, sku, nomeProduto, qtd, localizacao, fornecedor, categoria);

        // 4. Mandar para o Banco de Dados
        if (model.cadastrarProduto(novoProduto)) {
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            view.limparCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto. Verifique os dados.");
        }
    }
}