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
	// ── GERAÇÃO AUTOMÁTICA DE SKU ─────────────────────────────────────────────
	/**
	 * Remove acentos e caracteres não-ASCII de uma string.
	 * Exemplo: "Eletrônicos" → "Eletronicos"
	 */
	private static String removerAcentos(String s) {
		if (s == null) return "";
		return Normalizer.normalize(s, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "");
	}

	/**
	 * Gera um SKU no formato real de mercado:  CAT-FOR-NOME-NNNN
	 *
	 *  CAT  = 3 letras da Categoria   (ex.: "MON" para "Monitores")
	 *  FOR  = 3 letras do Fornecedor  (ex.: "TEC" para "TechStore")
	 *  NOME = 4 letras do Produto     (ex.: "MONI" para "Monitor LG")
	 *  NNNN = sequencial 4 dígitos    (ex.: "0001", "0002", …)
	 *
	 * Exemplo final: MON-TEC-MONI-0001
	 */
	private static String gerarSKU(String nome, String categoria, String fornecedor, int sequencial) {
		String n = (removerAcentos(nome).toUpperCase().replaceAll("[^A-Z0-9]", "") + "XXXX").substring(0, 4);
		String c = (removerAcentos(categoria).toUpperCase().replaceAll("[^A-Z0-9]", "") + "XXX").substring(0, 3);
		String f = (removerAcentos(fornecedor).toUpperCase().replaceAll("[^A-Z0-9]", "") + "XXX").substring(0, 3);
		return c + "-" + f + "-" + n + "-" + String.format("%04d", sequencial);
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
