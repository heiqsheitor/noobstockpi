package view;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import controller.ComponentUtils;
import model.Produto;

public class TelaDetalhesProduto extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel lblValorNome, lblValorSKU, lblValorID, lblValorQtd, lblValorLocal, lblValorFornecedor, lblValorCategoria;
	// Agora exposta como campo para poder ser preenchida em preencherDados()
	private JLabel lblValorAtualizacao;
	private JLabel Voltar;

	public TelaDetalhesProduto() {
		setBackground(new Color(245, 246, 248));
		setLayout(new MigLayout(
			"fill, insets 25 35 25 35",
			"[grow, fill]20[grow, fill]",
			"[pref!][pref!][grow, fill]"
		));

		Font fonteTituloTela    = new Font("Segoe UI", Font.BOLD, 22);
		Font fonteNomeProduto   = new Font("Segoe UI", Font.BOLD, 24);
		Font fonteSecao         = new Font("Segoe UI", Font.BOLD, 15);
		Font fonteValoresGerais = new Font("Segoe UI", Font.PLAIN, 14);
		Font fonteMuted         = new Font("Segoe UI", Font.PLAIN, 13);
		Font fonteMiniTit       = new Font("Segoe UI", Font.PLAIN, 12);
		Font fonteMiniVal       = new Font("Segoe UI", Font.BOLD, 14);
		Font fonteHistQtd       = new Font("Segoe UI", Font.BOLD, 13);
		Font fonteHistMotivo    = new Font("Segoe UI", Font.PLAIN, 13);
		Font fonteHistUser      = new Font("Segoe UI", Font.PLAIN, 11);
		Font fonteHistData      = new Font("Segoe UI", Font.BOLD, 12);

		Color corTextoPrincipal = new Color(33, 37, 41);
		Color corTextoMuted     = new Color(110, 117, 124);
		Color corVerde          = new Color(40, 167, 69);
		Color corVermelho       = new Color(220, 53, 69);

		Border bordaCard = BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(222, 226, 230), 1),
			BorderFactory.createEmptyBorder(5, 5, 5, 5)
		);

		// ── 1. HEADER ─────────────────────────────────────────────────────
		JPanel painelHeader = new JPanel(new MigLayout("insets 0, fillx", "[pref!][grow]", "[]"));
		painelHeader.setOpaque(false);

		Voltar = new JLabel("");
		Voltar.setIcon(new ImageIcon(TelaAdicionarFornecedor.class.getResource("/img/button→svg.png")));
		painelHeader.add(Voltar, "cell 0 0, aligny center, gapright 10");

		JLabel lblTitulo = new JLabel("Detalhes do Produto");
		lblTitulo.setFont(fonteTituloTela);
		lblTitulo.setForeground(corTextoPrincipal);
		painelHeader.add(lblTitulo, "cell 1 0, aligny center");

		add(painelHeader, "cell 0 0 2 1, growx, gapbottom 15");

		// ── 2. CARD SUPERIOR ──────────────────────────────────────────────
		JPanel cardPrincipal = new JPanel();
		cardPrincipal.setBackground(Color.WHITE);
		cardPrincipal.setBorder(bordaCard);
		cardPrincipal.setLayout(new MigLayout("fillx, insets 20", "[grow][pref!]", "[][][pref!]"));

		lblValorNome = new JLabel("-");
		lblValorNome.setFont(fonteNomeProduto);
		lblValorNome.setForeground(corTextoPrincipal);
		cardPrincipal.add(lblValorNome, "cell 0 0, alignx left");

		// Badge Categoria
		JPanel badgeCategoria = new JPanel(new MigLayout("insets 4 12 4 12"));
		badgeCategoria.setBackground(new Color(241, 243, 245));
		lblValorCategoria = new JLabel("-");
		lblValorCategoria.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblValorCategoria.setForeground(new Color(73, 80, 87));
		badgeCategoria.add(lblValorCategoria);
		cardPrincipal.add(badgeCategoria, "cell 1 0, alignx right, aligny top");

		// SKU e ID
		JPanel painelSubInfo = new JPanel(new MigLayout("insets 0"));
		painelSubInfo.setOpaque(false);
		JLabel lblTxtSKU = new JLabel("SKU: ");
		lblTxtSKU.setFont(fonteValoresGerais);
		lblTxtSKU.setForeground(corTextoMuted);
		lblValorSKU = new JLabel("-");
		lblValorSKU.setFont(fonteValoresGerais);
		lblValorSKU.setForeground(corTextoMuted);
		painelSubInfo.add(lblTxtSKU);
		painelSubInfo.add(lblValorSKU, "gapright 15");
		JLabel lblTxtID = new JLabel("ID: ");
		lblTxtID.setFont(fonteValoresGerais);
		lblTxtID.setForeground(corTextoMuted);
		lblValorID = new JLabel("-");
		lblValorID.setFont(fonteValoresGerais);
		lblValorID.setForeground(corTextoMuted);
		painelSubInfo.add(lblTxtID);
		painelSubInfo.add(lblValorID);
		cardPrincipal.add(painelSubInfo, "cell 0 1, span 2, gapbottom 15");

		// ── Mini-cards: Fornecedor | Última Atualização | Quantidade ──────
		JPanel linhaMiniCards = new JPanel(new MigLayout(
			"insets 0, fillx",
			"[grow, fill]15[grow, fill]15[grow, fill]",
			"[fill]"
		));
		linhaMiniCards.setOpaque(false);

		// Mini Card Fornecedor
		JPanel miniFornecedor = new JPanel(new MigLayout("insets 10 14 10 14, fill", "[grow]", "[]4[]"));
		miniFornecedor.setBackground(new Color(248, 249, 250));
		miniFornecedor.setBorder(BorderFactory.createLineBorder(new Color(233, 236, 239), 1));
		JLabel lblTitMiniForn = new JLabel("Fornecedor");
		lblTitMiniForn.setFont(fonteMiniTit);
		lblTitMiniForn.setForeground(corTextoMuted);
		miniFornecedor.add(lblTitMiniForn, "cell 0 0");
		lblValorFornecedor = new JLabel("-");
		lblValorFornecedor.setFont(fonteMiniVal);
		lblValorFornecedor.setForeground(corTextoPrincipal);
		miniFornecedor.add(lblValorFornecedor, "cell 0 1, growx");

		// Mini Card Última Atualização
		JPanel miniAtualizacao = new JPanel(new MigLayout("insets 10 14 10 14, fill", "[grow]", "[]4[]"));
		miniAtualizacao.setBackground(new Color(248, 249, 250));
		miniAtualizacao.setBorder(BorderFactory.createLineBorder(new Color(233, 236, 239), 1));
		JLabel lblTitMiniAtu = new JLabel("Última Atualização");
		lblTitMiniAtu.setFont(fonteMiniTit);
		lblTitMiniAtu.setForeground(corTextoMuted);
		miniAtualizacao.add(lblTitMiniAtu, "cell 0 0");
		// FIX: campo agora é instância de campo (antes era variável local, nunca preenchida)
		lblValorAtualizacao = new JLabel("-");
		lblValorAtualizacao.setFont(fonteMiniVal);
		lblValorAtualizacao.setForeground(corTextoPrincipal);
		miniAtualizacao.add(lblValorAtualizacao, "cell 0 1, growx");

		// Mini Card Quantidade
		JPanel miniQuantidade = new JPanel(new MigLayout("insets 10 14 10 14, fill", "[grow, fill]", "[]4[]"));
		miniQuantidade.setBackground(new Color(248, 249, 250));
		miniQuantidade.setBorder(BorderFactory.createLineBorder(new Color(233, 236, 239), 1));

		JLabel lblTitMiniQtd = new JLabel("Quantidade em Estoque");
		lblTitMiniQtd.setFont(fonteMiniTit);
		lblTitMiniQtd.setForeground(corTextoMuted);
		miniQuantidade.add(lblTitMiniQtd, "cell 0 0");

		JPanel linhaNumero = new JPanel(new MigLayout("insets 0", "[pref!][pref!]", "[]"));
		linhaNumero.setOpaque(false);
		lblValorQtd = new JLabel("-");
		lblValorQtd.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblValorQtd.setForeground(corTextoPrincipal);
		JLabel lblUnidMini = new JLabel(" unidades");
		lblUnidMini.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblUnidMini.setForeground(corTextoMuted);
		linhaNumero.add(lblValorQtd);
		linhaNumero.add(lblUnidMini, "aligny bottom, gapbottom 1");
		miniQuantidade.add(linhaNumero, "cell 0 1, growx");

		linhaMiniCards.add(miniFornecedor, "cell 0 0, grow");
		linhaMiniCards.add(miniAtualizacao, "cell 1 0, grow");
		linhaMiniCards.add(miniQuantidade, "cell 2 0, grow");
		cardPrincipal.add(linhaMiniCards, "cell 0 2, span 2, growx");
		add(cardPrincipal, "cell 0 1 2 1, growx, gapbottom 20");

		// ── 3. LINHA INFERIOR: LOCALIZAÇÃO (esq) + HISTÓRICO (dir) ───────

		// Card Localização
		JPanel cardLocalizacao = new JPanel();
		cardLocalizacao.setBackground(Color.WHITE);
		cardLocalizacao.setBorder(bordaCard);
		cardLocalizacao.setLayout(new MigLayout("fill, insets 20", "[grow][right]", "[pref!][][][]"));

		JLabel lblTitLocal = new JLabel("Localização no Estoque");
		lblTitLocal.setFont(fonteSecao);
		lblTitLocal.setForeground(corTextoPrincipal);
		cardLocalizacao.add(lblTitLocal, "cell 0 0, span 2, gapbottom 10");

		JLabel lblArmazemTxt = new JLabel("Armazém:");
		lblArmazemTxt.setFont(fonteMuted);
		lblArmazemTxt.setForeground(corTextoMuted);
		cardLocalizacao.add(lblArmazemTxt, "cell 0 1, gapy 6 6");
		JLabel lblArmazemVal = new JLabel("Armazém A");
		lblArmazemVal.setFont(fonteValoresGerais);
		cardLocalizacao.add(lblArmazemVal, "cell 1 1, gapy 6 6");

		JLabel lblCorredorTxt = new JLabel("Corredor:");
		lblCorredorTxt.setFont(fonteMuted);
		lblCorredorTxt.setForeground(corTextoMuted);
		cardLocalizacao.add(lblCorredorTxt, "cell 0 2, gapy 6 6");
		JLabel lblCorredorVal = new JLabel("Corredor 3");
		lblCorredorVal.setFont(fonteValoresGerais);
		cardLocalizacao.add(lblCorredorVal, "cell 1 2, gapy 6 6");

		JLabel lblPrateleiraTxt = new JLabel("Prateleira:");
		lblPrateleiraTxt.setFont(fonteMuted);
		lblPrateleiraTxt.setForeground(corTextoMuted);
		cardLocalizacao.add(lblPrateleiraTxt, "cell 0 3, gapy 6 6");
		lblValorLocal = new JLabel("-");
		lblValorLocal.setFont(new Font("Segoe UI", Font.BOLD, 14));
		cardLocalizacao.add(lblValorLocal, "cell 1 3, gapy 6 6");

		add(cardLocalizacao, "cell 0 2, grow");

		// Card Histórico
		JPanel cardHistorico = new JPanel();
		cardHistorico.setBackground(Color.WHITE);
		cardHistorico.setBorder(bordaCard);
		cardHistorico.setLayout(new MigLayout("fill, insets 20", "[grow, fill]", "[pref!][grow, fill]"));

		JPanel headerHistorico = new JPanel(new MigLayout("insets 0, fillx", "[grow]", "[]"));
		headerHistorico.setOpaque(false);
		JLabel lblTitHist = new JLabel("Histórico de Movimentações");
		lblTitHist.setFont(fonteSecao);
		lblTitHist.setForeground(corTextoPrincipal);
		headerHistorico.add(lblTitHist, "cell 0 0, aligny center");
		cardHistorico.add(headerHistorico, "cell 0 0, growx, gapbottom 15");

		JPanel listaMovimentacoes = new JPanel(new MigLayout("insets 0, fillx, wrap 1", "[grow, fill]", "[]12[]12[]"));
		listaMovimentacoes.setOpaque(false);

		// Item 1
		JPanel item1 = new JPanel(new MigLayout("insets 2 0 2 0, fillx", "[pref!][grow][right]", "[]"));
		item1.setOpaque(false);
		JLabel lblQtd1 = new JLabel("+50 unidades");
		lblQtd1.setFont(fonteHistQtd);
		lblQtd1.setForeground(corVerde);
		JPanel painelTextos1 = new JPanel(new MigLayout("insets 0 12 0 0", "[grow]", "[][]"));
		painelTextos1.setOpaque(false);
		JLabel lblMotivo1 = new JLabel("Recebimento fornecedor");
		lblMotivo1.setFont(fonteHistMotivo);
		lblMotivo1.setForeground(corTextoPrincipal);
		JLabel lblUser1 = new JLabel("João Silva");
		lblUser1.setFont(fonteHistUser);
		lblUser1.setForeground(corTextoMuted);
		painelTextos1.add(lblMotivo1, "wrap");
		painelTextos1.add(lblUser1);
		JLabel lblData1 = new JLabel("28 Nov");
		lblData1.setFont(fonteHistData);
		lblData1.setForeground(corTextoMuted);
		item1.add(lblQtd1, "cell 0 0, aligny center");
		item1.add(painelTextos1, "cell 1 0, growx, aligny center");
		item1.add(lblData1, "cell 2 0, aligny center");
		listaMovimentacoes.add(item1);

		// Item 2
		JPanel item2 = new JPanel(new MigLayout("insets 2 0 2 0, fillx", "[pref!][grow][right]", "[]"));
		item2.setOpaque(false);
		JLabel lblQtd2 = new JLabel("-25 unidades");
		lblQtd2.setFont(fonteHistQtd);
		lblQtd2.setForeground(corVermelho);
		JPanel painelTextos2 = new JPanel(new MigLayout("insets 0 12 0 0", "[grow]", "[][]"));
		painelTextos2.setOpaque(false);
		JLabel lblMotivo2 = new JLabel("Venda direta balcão");
		lblMotivo2.setFont(fonteHistMotivo);
		lblMotivo2.setForeground(corTextoPrincipal);
		JLabel lblUser2 = new JLabel("Maria Santos");
		lblUser2.setFont(fonteHistUser);
		lblUser2.setForeground(corTextoMuted);
		painelTextos2.add(lblMotivo2, "wrap");
		painelTextos2.add(lblUser2);
		JLabel lblData2 = new JLabel("29 Nov");
		lblData2.setFont(fonteHistData);
		lblData2.setForeground(corTextoMuted);
		item2.add(lblQtd2, "cell 0 0, aligny center");
		item2.add(painelTextos2, "cell 1 0, growx, aligny center");
		item2.add(lblData2, "cell 2 0, aligny center");
		listaMovimentacoes.add(item2);

		// Item 3
		JPanel item3 = new JPanel(new MigLayout("insets 2 0 2 0, fillx", "[pref!][grow][right]", "[]"));
		item3.setOpaque(false);
		JLabel lblQtd3 = new JLabel("-15 unidades");
		lblQtd3.setFont(fonteHistQtd);
		lblQtd3.setForeground(corVermelho);
		JPanel painelTextos3 = new JPanel(new MigLayout("insets 0 12 0 0", "[grow]", "[][]"));
		painelTextos3.setOpaque(false);
		JLabel lblMotivo3 = new JLabel("Ajuste de inventário");
		lblMotivo3.setFont(fonteHistMotivo);
		lblMotivo3.setForeground(corTextoPrincipal);
		JLabel lblUser3 = new JLabel("Carlos Lima");
		lblUser3.setFont(fonteHistUser);
		lblUser3.setForeground(corTextoMuted);
		painelTextos3.add(lblMotivo3, "wrap");
		painelTextos3.add(lblUser3);
		JLabel lblData3 = new JLabel("01 Dez");
		lblData3.setFont(fonteHistData);
		lblData3.setForeground(corTextoMuted);
		item3.add(lblQtd3, "cell 0 0, aligny center");
		item3.add(painelTextos3, "cell 1 0, growx, aligny center");
		item3.add(lblData3, "cell 2 0, aligny center");
		listaMovimentacoes.add(item3);

		cardHistorico.add(listaMovimentacoes, "cell 0 1, grow, aligny top");
		add(cardHistorico, "cell 1 2, grow");
	}

	public void acaoVoltar(Runnable acao) {
		ComponentUtils.transformarEmLink(this.Voltar, acao);
	}

	/**
	 * Preenche todos os dados da tela com base no objeto Produto recebido.
	 *
	 * CORREÇÕES E MELHORIAS:
	 * 1. lblValorAtualizacao agora é campo de instância → a data é exibida corretamente.
	 * 2. Fornecedor preenchido via p.getFornecedor() — garante que o valor volta ao editar.
	 * 3. Data de atualização vinda de p.getDataAtualizacao() (campo novo no model/banco).
	 */
	public void preencherDados(Produto p) {
		lblValorNome.setText(p.getNome() != null ? p.getNome() : "Não informado");
		lblValorSKU.setText(p.getSKU() != null ? p.getSKU() : "Não informado");
		lblValorID.setText(String.valueOf(p.getId_produto()));

		// Quantidade
		String qtdStr = (p.getQtd() != null) ? p.getQtd() : "0";
		lblValorQtd.setText(qtdStr);
 
		// Localização
		lblValorLocal.setText((p.getLocalização() != null && !p.getLocalização().isEmpty())
				? p.getLocalização() : "Não alocado");

		// FIX: Fornecedor — antes nunca era preenchido pois o campo era local no construtor
		lblValorFornecedor.setText((p.getFornecedor() != null && !p.getFornecedor().isEmpty())
				? p.getFornecedor() : "Sem fornecedor");

		// Categoria
		lblValorCategoria.setText((p.getCategoria() != null && !p.getCategoria().isEmpty())
				? p.getCategoria() : "Não categorizado");

		// FIX: Última Atualização — agora preenche o campo correto (era variável local antes)
		// p.getDataAtualizacao() deve retornar String já formatada (ex: "27/05/2026 14:30")
		// Se o model ainda não tiver esse getter, adicione conforme o banco.sql atualizado.
		String dataAtu = p.getDataAtualizacao();
		lblValorAtualizacao.setText((dataAtu != null && !dataAtu.isEmpty()) ? dataAtu : "Não atualizado");
	}

	public void acaoVoltar(Object acao) {
		// Mantido para compatibilidade
	}
}