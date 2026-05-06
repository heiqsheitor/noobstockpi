package controller;

import model.Produto;
import model.ProdutoDAO;
import view.Principal;
import view.TelaAdicionarProduto;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class ProdutoController extends ComponentAdapter {

    private final TelaAdicionarProduto view;
    private final ProdutoDAO model;
    private final Navegador navegador;

    public ProdutoController(TelaAdicionarProduto view, ProdutoDAO model, Navegador navegador) {
        this.view = view;
        this.model = model;
        this.navegador = navegador;

        
        
        this.view.adicionarproduto(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto();
            }
        });
        
        view.voltaracaoo(() -> {
        	navegador.navegarPara(Principal.ESTOQUE);
        });
    }

    private void adicionarProduto() {
        
        String nomeProduto = view.getNomeProduto();
        String sku = view.getSKU();
        String qtd = view.getQuantidade();
        String localizacao = view.getLocalizacao();
        String fornecedor = view.getFornecedor();
        String categoria = view.getCategoria();

       
        if (nomeProduto.trim().isEmpty() || sku.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha pelo menos o Nome e o SKU.");
            return;
        }

        
        Produto novoProduto = new Produto(null, sku, nomeProduto, qtd, localizacao, fornecedor, categoria);

        
        if (model.cadastrarProduto(novoProduto)) {
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            view.limparCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto. Verifique os dados.");
        }
    }
    
    
}