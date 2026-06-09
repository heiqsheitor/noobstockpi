package controller;

import model.Produto;
import model.ProdutoDAO;
import view.Principal;
import view.TelaAdicionarProduto;
import view.TelaControleEstoque;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class ProdutoController extends ComponentAdapter {

	private final TelaAdicionarProduto view;
	private final ProdutoDAO model;
	private final Navegador navegador;
	private final TelaControleEstoque telaEstoque;

	public ProdutoController(TelaAdicionarProduto view, ProdutoDAO model, Navegador navegador,
			TelaControleEstoque telaEstoque) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;
		this.telaEstoque = telaEstoque;

		this.view.adicionarproduto(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.isEdicao()) {
					editarProduto();
				} else {
					adicionarProduto();
				}
			}
		});

		view.voltaracaoo(() -> {
			view.limparCampos();
			navegador.navegarPara(Principal.ESTOQUE);
		});
	}

	private void adicionarProduto() {
		// 👉 PASSO 1: Chama a validação da tela. Se falhar, interrompe o processo.
		if (!view.validarCampos()) {
			return;
		}

		// 👉 PASSO 2: Coleta os dados (já garantidos como válidos pela tela)
		String nomeProduto = view.getNomeProduto();
		String sku = view.getSKU();
		String qtd = view.getQuantidade();
		String localizacao = view.getLocalizacao();
		String fornecedor = view.getFornecedor();
		String categoria = view.getCategoria();
		String precoTexto = view.getPreco();

		// Formatação segura do preço
		double valorPreco = 0.0;
		try {
			if (precoTexto != null && !precoTexto.isEmpty()) {
				String precoLimpo = precoTexto.replace(".", "").replace(",", ".");
				valorPreco = Double.parseDouble(precoLimpo);
			}
		} catch (NumberFormatException ex) {
			valorPreco = 0.0;
		}

		Produto novo = new Produto(null, sku, nomeProduto, qtd, 0, localizacao, fornecedor, categoria, null,
				valorPreco);

		// 👉 PASSO 3: Salva no banco de dados
		if (model.cadastrarProduto(novo)) {
			JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
			view.limparCampos();
			telaEstoque.recarregarTabela();
			navegador.navegarPara(Principal.ESTOQUE);
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto. Verifique os dados no banco de dados.");
		}
	}

	private void editarProduto() {
		// 👉 PASSO 1: Chama a validação da tela. Se falhar, interrompe o processo.
		if (!view.validarCampos()) {
			return;
		}

		// 👉 PASSO 2: Coleta os dados (já garantidos como válidos pela tela)
		String nomeProduto = view.getNomeProduto();
		String sku = view.getSKU();
		String qtd = view.getQuantidade();
		String localizacao = view.getLocalizacao();
		String fornecedor = view.getFornecedor();
		String categoria = view.getCategoria();
		String id = view.getProdutoIdEmEdicao();
		String precoTexto = view.getPreco();

		// Formatação segura do preço
		double valorPreco = 0.0;
		try {
			if (precoTexto != null && !precoTexto.isEmpty()) {
				String precoLimpo = precoTexto.replace(".", "").replace(",", ".");
				valorPreco = Double.parseDouble(precoLimpo);
			}
		} catch (NumberFormatException ex) {
			valorPreco = 0.0;
		}
		
		Produto produtoAtualizado = new Produto(id, sku, nomeProduto, qtd, 0, localizacao, fornecedor, categoria, null,
				valorPreco);

		// 👉 PASSO 3: Atualiza no banco de dados
		if (model.atualizarProduto(produtoAtualizado)) {
			JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
			view.limparCampos();
			telaEstoque.recarregarTabela();
			navegador.navegarPara(Principal.ESTOQUE);
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar o produto.");
		}
	}
}