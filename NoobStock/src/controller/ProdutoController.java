package controller;

import java.text.Normalizer;
import model.Produto;
import model.ProdutoDAO;
import view.Principal;
import view.TelaAdicionarProduto;
import view.TelaControleEstoque;
import view.TelaMensagem;

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

	private static String removerAcentos(String s) {
		if (s == null)
			return "";
		return Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	private static String gerarSKU(String nome, String categoria, String fornecedor, int sequencial) {
		String n = (removerAcentos(nome).toUpperCase().replaceAll("[^A-Z0-9]", "") + "XXXX").substring(0, 4);
		String c = (removerAcentos(categoria).toUpperCase().replaceAll("[^A-Z0-9]", "") + "XXX").substring(0, 3);
		String f = (removerAcentos(fornecedor).toUpperCase().replaceAll("[^A-Z0-9]", "") + "XXX").substring(0, 3);
		return c + "-" + f + "-" + n + "-" + String.format("%04d", sequencial);
	}

	private void adicionarProduto() {
		if (!view.validarCampos()) {
			return;
		}

		String nomeProduto = view.getNomeProduto();
		String qtd = view.getQuantidade();
		String localizacao = view.getLocalizacao();
		String fornecedor = view.getFornecedor();
		String categoria = view.getCategoria();
		String precoTexto = view.getPreco();
		int proximoNum = model.contarProdutos() + 1;
		String sku = gerarSKU(nomeProduto, categoria, fornecedor, proximoNum);
		view.setSKU(sku);

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
		if (model.cadastrarProduto(novo)) {
			new TelaMensagem("Sucesso", "Produto cadastrado com sucesso!\nSKU gerado: " + sku, "SUCESSO")
					.setVisible(true);
			view.limparCampos();
			telaEstoque.recarregarTabela();
			navegador.navegarPara(Principal.ESTOQUE);
		} else {
			new TelaMensagem("Erro", "Erro ao cadastrar o produto. Verifique os dados no banco de dados.", "ERRO")
					.setVisible(true);
		}
	}

	private void editarProduto() {
		if (!view.validarCampos()) {
			return;
		}

		String nomeProduto = view.getNomeProduto();
		String sku = view.getSKU();
		String qtd = view.getQuantidade();
		String localizacao = view.getLocalizacao();
		String fornecedor = view.getFornecedor();
		String categoria = view.getCategoria();
		String id = view.getProdutoIdEmEdicao();
		String precoTexto = view.getPreco();
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

		if (model.atualizarProduto(produtoAtualizado)) {
			new TelaMensagem("Sucesso", "Produto atualizado com sucesso!", "SUCESSO").setVisible(true);
			view.limparCampos();
			telaEstoque.recarregarTabela();
			navegador.navegarPara(Principal.ESTOQUE);
		} else {
			new TelaMensagem("Erro", "Erro ao atualizar o produto.", "ERRO").setVisible(true);
		}
	}
}
