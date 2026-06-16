package controller;

import model.ItemSaida;
import model.Produto;
import model.ProdutoDAO;
import model.SaidaDAO;
import model.SaidaEstoque;
import view.Principal;
import view.TelaSaida;
import view.TelaComprovante;
import view.TelaMensagem;

import javax.swing.*;
import java.awt.Window;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SaidaController {

	private TelaSaida view;
	private Navegador navegador;
	private ProdutoDAO produtoDAO;
	private SaidaDAO saidaDAO;

	private List<ItemSaida> itensAdicionados = new ArrayList<>();

	public SaidaController(TelaSaida view, Navegador navegador, ProdutoDAO produtoDAO, SaidaDAO saidaDAO) {
		this.view = view;
		this.navegador = navegador;
		this.produtoDAO = produtoDAO;
		this.saidaDAO = saidaDAO;

		view.setPerfil(() -> navegador.navegarPara(Principal.PERFIL));
		view.setInicio(() -> navegador.navegarPara(Principal.INICIO));
		view.setEstoque(() -> navegador.navegarPara(Principal.ESTOQUE));
		view.setFornecedor(() -> navegador.navegarPara(Principal.FORNECEDOR));

		view.setAdicionarAcao(e -> adicionarProduto());
		view.setRemoverAcao(e -> removerProduto());
		view.setConfirmarAcao(e -> confirmarSaida());
		view.setCancelarAcao(e -> cancelar());

		carregarProdutos();
		
		view.setAoExibir(() -> carregarProdutos());
	}

	private void carregarProdutos() {
		try {
			List<Produto> produtos = produtoDAO.listarProdutos();
			LinkedHashMap<String, Produto> mapa = new LinkedHashMap<>();
			for (Produto p : produtos) {
				String label = p.getNome() + "  |  SKU: " + p.getSKU() + "  |  Estoque: " + p.getQtd();
				mapa.put(label, p);
			}
			view.popularComboBoxProdutos(mapa);
		 } catch (Exception ex) {
			new TelaMensagem("Erro", "Erro ao carregar produtos do banco:\n" + ex.getMessage(), "ERRO").setVisible(true);
		}
	}

	private void adicionarProduto() {
		Produto produto = view.getProdutoSelecionado();
		if (produto == null) {
			new TelaMensagem("Atenção", "Selecione um produto antes de adicionar.", "AVISO").setVisible(true);
			return;
		}

		int quantidade = view.getQuantidadeInserida();
		if (quantidade <= 0) {
			new TelaMensagem("Atenção", "Informe uma quantidade válida (maior que zero).", "AVISO").setVisible(true);
			return;
		}

		int estoqueAtual = Integer.parseInt(produto.getQtd());

		for (int i = 0; i < itensAdicionados.size(); i++) {
			ItemSaida itemExistente = itensAdicionados.get(i);
			if (itemExistente.getProduto().getId_produto().equals(produto.getId_produto())) {

				int novaQtd = itemExistente.getQuantidade() + quantidade;
				if (novaQtd > estoqueAtual) {
		            new TelaMensagem("Estoque insuficiente", "A quantidade total (" + novaQtd + ") excede o estoque disponível (" + estoqueAtual + " unidades).", "AVISO").setVisible(true);
		            return;
		        }

				itemExistente.setQuantidade(novaQtd);
				view.atualizarQuantidadeNaTabela(i, novaQtd);
				view.limparCampos();
				return;
			}
		}

		if (quantidade > estoqueAtual) {
            new TelaMensagem("Estoque insuficiente", "Quantidade (" + quantidade + ") excede o estoque disponível (" + estoqueAtual + " unidades).", "AVISO").setVisible(true);
            return;
        }

		ItemSaida novoItem = new ItemSaida(produto, quantidade);
		itensAdicionados.add(novoItem);
		view.adicionarItemNaTabela(novoItem);
		view.limparCampos();
	}

	private void removerProduto() {
		int linha = view.getLinhaSelecionada();
		if (linha < 0) {
			new TelaMensagem("Atenção", "Selecione um item na tabela para remover.", "AVISO").setVisible(true);
			return;
		}
		itensAdicionados.remove(linha);
		view.removerLinhaDaTabela(linha);
	}

	private void confirmarSaida() {
		if (itensAdicionados.isEmpty()) {
			new TelaMensagem("Atenção", "Adicione ao menos um produto ao caminhão antes de confirmar.", "AVISO").setVisible(true);
			return;
		}

		String responsavel = view.getResponsavel();
		if (responsavel.isEmpty()) {
			new TelaMensagem("Atenção", "Informe o nome do responsável pela saída.", "AVISO").setVisible(true);
			return;
		}

		String observacao = view.getObservacao();

		SaidaEstoque saida = new SaidaEstoque(0, responsavel, observacao, new ArrayList<>(itensAdicionados));

		int idGerado = saidaDAO.registrarSaida(saida);

		if (idGerado > 0) {

			SaidaEstoque saidaFinal = new SaidaEstoque(idGerado, responsavel, observacao,
					new ArrayList<>(itensAdicionados));

			mostrarComprovante(saidaFinal);

			itensAdicionados.clear();
			view.limparTabela();
			view.limparCampos();
			carregarProdutos();
		} else {
			new TelaMensagem("Erro", "Erro ao registrar a saída no banco de dados.\nVerifique a conexão e tente novamente.", "ERRO").setVisible(true);
		}
	}

	private void cancelar() {
		if (!itensAdicionados.isEmpty()) {
			TelaMensagem confirmacao = new TelaMensagem("Cancelar operação", "Deseja cancelar? Os itens adicionados serão removidos.");
			confirmacao.setVisible(true);
			if (!confirmacao.isConfirmado()) return;
		}
		itensAdicionados.clear();
		view.limparTabela();
		view.limparCampos();
	}

	private void mostrarComprovante(SaidaEstoque saida) {
		Window janelaPai = SwingUtilities.getWindowAncestor(view);
		JFrame frame = (janelaPai instanceof JFrame) ? (JFrame) janelaPai : null;
		TelaComprovante comprovante = new TelaComprovante(frame, saida);
		comprovante.setVisible(true);
	}
}