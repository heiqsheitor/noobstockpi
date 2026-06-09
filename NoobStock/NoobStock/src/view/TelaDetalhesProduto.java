package view;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import controller.ComponentUtils;
import model.HistoricoMovimentacao;
import model.Produto;

public class TelaDetalhesProduto extends JPanel {
    private static final long serialVersionUID = 1L;

    // ── CAMPOS DE DADOS DO PRODUTO ────────────────────────────────────────────
    private JLabel lblValorNome, lblValorSKU, lblValorID, lblValorQtd;
    private JLabel lblValorLocal, lblValorFornecedor, lblValorCategoria;
    private JLabel lblValorPreco, lblValorAtualizacao;
    private JLabel Voltar;

    // ── HISTÓRICO (painel dinâmico) ───────────────────────────────────────────
    private JPanel listaMovimentacoes;

    // ── CONSTANTES DE ESTILO (reutilizadas em carregarHistorico) ─────────────
    private static final Font FONTE_HIST_QTD    = new Font("Segoe UI", Font.BOLD,  13);
    private static final Font FONTE_HIST_MOTIVO = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FONTE_HIST_USER   = new Font("Segoe UI", Font.PLAIN, 11);
    private static final Font FONTE_HIST_DATA   = new Font("Segoe UI", Font.BOLD,  12);
    private static final Font FONTE_MINI_VAL    = new Font("Segoe UI", Font.BOLD,  14);

    private static final Color COR_PRINCIPAL  = new Color(33, 37, 41);
    private static final Color COR_MUTED      = new Color(110, 117, 124);
    private static final Color COR_VERMELHO   = new Color(220, 53, 69);
    private static final Color COR_VERDE      = new Color(40, 167, 69);

    public TelaDetalhesProduto() {
        setBackground(new Color(245, 246, 248));
        setLayout(new MigLayout(
            "fill, insets 25 35 25 35",
            "[grow, fill]20[grow, fill]",
            "[pref!][pref!][grow, fill]"
        ));

        Font fonteTituloTela    = new Font("Segoe UI", Font.BOLD,  22);
        Font fonteNomeProduto   = new Font("Segoe UI", Font.BOLD,  24);
        Font fonteSecao         = new Font("Segoe UI", Font.BOLD,  15);
        Font fonteValoresGerais = new Font("Segoe UI", Font.PLAIN, 14);
        Font fonteMuted         = new Font("Segoe UI", Font.PLAIN, 13);
        Font fonteMiniTit       = new Font("Segoe UI", Font.PLAIN, 12);

        Border bordaCard = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(222, 226, 230), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );

        // ── 1. HEADER ─────────────────────────────────────────────────────────
        JPanel painelHeader = new JPanel(new MigLayout("insets 0, fillx", "[pref!][grow]", "[]"));
        painelHeader.setOpaque(false);

        Voltar = new JLabel("");
        Voltar.setIcon(new ImageIcon(TelaAdicionarFornecedor.class.getResource("/img/button→svg.png")));
        painelHeader.add(Voltar, "cell 0 0, aligny center, gapright 10");

        JLabel lblTitulo = new JLabel("Detalhes do Produto");
        lblTitulo.setFont(fonteTituloTela);
        lblTitulo.setForeground(COR_PRINCIPAL);
        painelHeader.add(lblTitulo, "cell 1 0, aligny center");

        add(painelHeader, "cell 0 0 2 1, growx, gapbottom 15");

        // ── 2. CARD PRINCIPAL ─────────────────────────────────────────────────
        JPanel cardPrincipal = new JPanel();
        cardPrincipal.setBackground(Color.WHITE);
        cardPrincipal.setBorder(bordaCard);
        cardPrincipal.setLayout(new MigLayout("fillx, insets 20", "[grow][pref!]", "[][][pref!]"));

        lblValorNome = new JLabel("-");
        lblValorNome.setFont(fonteNomeProduto);
        lblValorNome.setForeground(COR_PRINCIPAL);
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
        lblTxtSKU.setForeground(COR_MUTED);
        lblValorSKU = new JLabel("-");
        lblValorSKU.setFont(fonteValoresGerais);
        lblValorSKU.setForeground(COR_MUTED);
        painelSubInfo.add(lblTxtSKU);
        painelSubInfo.add(lblValorSKU, "gapright 15");
        JLabel lblTxtID = new JLabel("ID: ");
        lblTxtID.setFont(fonteValoresGerais);
        lblTxtID.setForeground(COR_MUTED);
        lblValorID = new JLabel("-");
        lblValorID.setFont(fonteValoresGerais);
        lblValorID.setForeground(COR_MUTED);
        painelSubInfo.add(lblTxtID);
        painelSubInfo.add(lblValorID);
        cardPrincipal.add(painelSubInfo, "cell 0 1, span 2, gapbottom 15");

        // Mini-cards: Fornecedor | Data Cadastro | Quantidade | Preço
        JPanel linhaMiniCards = new JPanel(new MigLayout(
            "insets 0, fillx",
            "[grow, fill]15[grow, fill]15[grow, fill]15[grow, fill]",
            "[fill]"
        ));
        linhaMiniCards.setOpaque(false);

        linhaMiniCards.add(criarMiniCard("Fornecedor",          fonteMiniTit, bordaCard, true),  "cell 0 0, grow");
        linhaMiniCards.add(criarMiniCard("Data de Cadastro",    fonteMiniTit, bordaCard, false), "cell 1 0, grow");
        linhaMiniCards.add(criarMiniCardQtd(fonteMiniTit, bordaCard),                           "cell 2 0, grow");
        linhaMiniCards.add(criarMiniCardPreco(fonteMiniTit, bordaCard),                         "cell 3 0, grow");

        cardPrincipal.add(linhaMiniCards, "cell 0 2, span 2, growx");
        add(cardPrincipal, "cell 0 1 2 1, growx, gapbottom 20");

        // ── 3. LINHA INFERIOR: LOCALIZAÇÃO (esq) + HISTÓRICO (dir) ───────────

        // Card Localização
        JPanel cardLocalizacao = new JPanel();
        cardLocalizacao.setBackground(Color.WHITE);
        cardLocalizacao.setBorder(bordaCard);
        cardLocalizacao.setLayout(new MigLayout("fill, insets 20", "[grow][right]", "[pref!][][][]"));

        JLabel lblTitLocal = new JLabel("Localização no Estoque");
        lblTitLocal.setFont(fonteSecao);
        lblTitLocal.setForeground(COR_PRINCIPAL);
        cardLocalizacao.add(lblTitLocal, "cell 0 0, span 2, gapbottom 10");

        JLabel lblArmazemTxt = new JLabel("Armazém:");
        lblArmazemTxt.setFont(fonteMuted);
        lblArmazemTxt.setForeground(COR_MUTED);
        cardLocalizacao.add(lblArmazemTxt, "cell 0 1, gapy 6 6");
        JLabel lblArmazemVal = new JLabel("Armazém A");
        lblArmazemVal.setFont(fonteValoresGerais);
        cardLocalizacao.add(lblArmazemVal, "cell 1 1, gapy 6 6");

        JLabel lblCorredorTxt = new JLabel("Corredor:");
        lblCorredorTxt.setFont(fonteMuted);
        lblCorredorTxt.setForeground(COR_MUTED);
        cardLocalizacao.add(lblCorredorTxt, "cell 0 2, gapy 6 6");
        JLabel lblCorredorVal = new JLabel("Corredor 3");
        lblCorredorVal.setFont(fonteValoresGerais);
        cardLocalizacao.add(lblCorredorVal, "cell 1 2, gapy 6 6");

        JLabel lblPrateleiraTxt = new JLabel("Prateleira:");
        lblPrateleiraTxt.setFont(fonteMuted);
        lblPrateleiraTxt.setForeground(COR_MUTED);
        cardLocalizacao.add(lblPrateleiraTxt, "cell 0 3, gapy 6 6");
        lblValorLocal = new JLabel("-");
        lblValorLocal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cardLocalizacao.add(lblValorLocal, "cell 1 3, gapy 6 6");

        add(cardLocalizacao, "cell 0 2, grow");

        // ── Card Histórico (dinâmico) ─────────────────────────────────────────
        JPanel cardHistorico = new JPanel();
        cardHistorico.setBackground(Color.WHITE);
        cardHistorico.setBorder(bordaCard);
        cardHistorico.setLayout(new MigLayout("fill, insets 20", "[grow, fill]", "[pref!][grow, fill]"));

        JLabel lblTitHist = new JLabel("Histórico de Saídas");
        lblTitHist.setFont(fonteSecao);
        lblTitHist.setForeground(COR_PRINCIPAL);
        cardHistorico.add(lblTitHist, "cell 0 0, growx, gapbottom 15");

        // listaMovimentacoes é o painel dinâmico — começa vazio com placeholder
        listaMovimentacoes = new JPanel(new MigLayout(
            "insets 0, fillx, wrap 1, gapy 10",
            "[grow, fill]",
            ""
        ));
        listaMovimentacoes.setOpaque(false);

        JLabel lblPlaceholder = new JLabel("Carregando histórico...");
        lblPlaceholder.setFont(FONTE_HIST_MOTIVO);
        lblPlaceholder.setForeground(COR_MUTED);
        listaMovimentacoes.add(lblPlaceholder);

        JScrollPane scrollHistorico = new JScrollPane(listaMovimentacoes);
        scrollHistorico.setBorder(BorderFactory.createEmptyBorder());
        scrollHistorico.getVerticalScrollBar().setUnitIncrement(10);
        cardHistorico.add(scrollHistorico, "cell 0 1, grow");

        add(cardHistorico, "cell 1 2, grow");
    }

    // ── HELPERS PARA MINI-CARDS ───────────────────────────────────────────────

    private JPanel criarMiniCard(String titulo, Font fonteMiniTit, Border borda, boolean isFornecedor) {
        JPanel card = new JPanel(new MigLayout("insets 10 14 10 14, fill", "[grow]", "[]4[]"));
        card.setBackground(new Color(248, 249, 250));
        card.setBorder(BorderFactory.createLineBorder(new Color(233, 236, 239), 1));

        JLabel lblTit = new JLabel(titulo);
        lblTit.setFont(fonteMiniTit);
        lblTit.setForeground(COR_MUTED);
        card.add(lblTit, "cell 0 0");

        if (isFornecedor) {
            lblValorFornecedor = new JLabel("-");
            lblValorFornecedor.setFont(FONTE_MINI_VAL);
            lblValorFornecedor.setForeground(COR_PRINCIPAL);
            card.add(lblValorFornecedor, "cell 0 1, growx");
        } else {
            lblValorAtualizacao = new JLabel("-");
            lblValorAtualizacao.setFont(FONTE_MINI_VAL);
            lblValorAtualizacao.setForeground(COR_PRINCIPAL);
            card.add(lblValorAtualizacao, "cell 0 1, growx");
        }
        return card;
    }

    private JPanel criarMiniCardQtd(Font fonteMiniTit, Border borda) {
        JPanel card = new JPanel(new MigLayout("insets 10 14 10 14, fill", "[grow, fill]", "[]4[]"));
        card.setBackground(new Color(248, 249, 250));
        card.setBorder(BorderFactory.createLineBorder(new Color(233, 236, 239), 1));

        JLabel lblTit = new JLabel("Quantidade em Estoque");
        lblTit.setFont(fonteMiniTit);
        lblTit.setForeground(COR_MUTED);
        card.add(lblTit, "cell 0 0");

        JPanel linhaNum = new JPanel(new MigLayout("insets 0", "[pref!][pref!]", "[]"));
        linhaNum.setOpaque(false);
        lblValorQtd = new JLabel("-");
        lblValorQtd.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblValorQtd.setForeground(COR_PRINCIPAL);
        JLabel lblUnid = new JLabel(" unidades");
        lblUnid.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblUnid.setForeground(COR_MUTED);
        linhaNum.add(lblValorQtd);
        linhaNum.add(lblUnid, "aligny bottom, gapbottom 1");
        card.add(linhaNum, "cell 0 1, growx");
        return card;
    }

    private JPanel criarMiniCardPreco(Font fonteMiniTit, Border borda) {
        JPanel card = new JPanel(new MigLayout("insets 10 14 10 14, fill", "[grow]", "[]4[]"));
        card.setBackground(new Color(248, 249, 250));
        card.setBorder(BorderFactory.createLineBorder(new Color(233, 236, 239), 1));

        JLabel lblTit = new JLabel("Preço Unitário");
        lblTit.setFont(fonteMiniTit);
        lblTit.setForeground(COR_MUTED);
        card.add(lblTit, "cell 0 0");

        lblValorPreco = new JLabel("R$ -");
        lblValorPreco.setFont(FONTE_MINI_VAL);
        lblValorPreco.setForeground(COR_VERDE);
        card.add(lblValorPreco, "cell 0 1, growx");
        return card;
    }

    // ── PREENCHER DADOS DO PRODUTO ────────────────────────────────────────────
    public void preencherDados(Produto p) {
        lblValorNome.setText(p.getNome() != null ? p.getNome() : "Não informado");
        lblValorSKU .setText(p.getSKU()  != null ? p.getSKU()  : "Não informado");
        lblValorID  .setText(String.valueOf(p.getId_produto()));

        lblValorQtd.setText((p.getQtd() != null) ? p.getQtd() : "0");

        lblValorLocal.setText(
            (p.getLocalização() != null && !p.getLocalização().isEmpty())
            ? p.getLocalização() : "Não alocado"
        );

        lblValorFornecedor.setText(
            (p.getFornecedor() != null && !p.getFornecedor().isEmpty())
            ? p.getFornecedor() : "Sem fornecedor"
        );

        lblValorCategoria.setText(
            (p.getCategoria() != null && !p.getCategoria().isEmpty())
            ? p.getCategoria() : "Não categorizado"
        );

        java.text.DecimalFormatSymbols simbolos =
            new java.text.DecimalFormatSymbols(new java.util.Locale("pt", "BR"));
        java.text.DecimalFormat dfPreco = new java.text.DecimalFormat("R$ #,##0.00", simbolos);
        lblValorPreco.setText(dfPreco.format(p.getPreco()));

        String dataAtu = p.getDataCriacao();
        lblValorAtualizacao.setText(
            (dataAtu != null && !dataAtu.isEmpty()) ? dataAtu : "Não informado"
        );
    }

    // ── CARREGAR HISTÓRICO REAL DO BANCO ─────────────────────────────────────
    /**
     * Recebe a lista de movimentações vindas do SaidaDAO e popula o painel
     * dinâmico de histórico. Chamado pelo EstoqueController após preencherDados().
     */
    public void carregarHistorico(List<HistoricoMovimentacao> historico) {
        listaMovimentacoes.removeAll();

        if (historico == null || historico.isEmpty()) {
            JLabel lblVazio = new JLabel("Nenhuma saída registrada para este produto.");
            lblVazio.setFont(FONTE_HIST_MOTIVO);
            lblVazio.setForeground(COR_MUTED);
            listaMovimentacoes.add(lblVazio);
        } else {
            for (HistoricoMovimentacao h : historico) {
                listaMovimentacoes.add(criarItemHistorico(h));
            }
        }

        listaMovimentacoes.revalidate();
        listaMovimentacoes.repaint();
    }

    /** Constrói uma linha visual para um único registro de saída. */
    private JPanel criarItemHistorico(HistoricoMovimentacao h) {
        JPanel item = new JPanel(new MigLayout(
            "insets 6 0 6 0, fillx",
            "[pref!][grow][right]",
            "[]"
        ));
        item.setOpaque(false);

        // Quantidade (sempre negativa = saída)
        JLabel lblQtd = new JLabel("-" + h.getQuantidade() + " un.");
        lblQtd.setFont(FONTE_HIST_QTD);
        lblQtd.setForeground(COR_VERMELHO);

        // Textos: motivo/observação + responsável
        JPanel painelTextos = new JPanel(new MigLayout("insets 0 10 0 0", "[grow]", "[][]"));
        painelTextos.setOpaque(false);

        String descricao = h.getObservacao().isEmpty()
            ? "Saída de estoque"
            : h.getObservacao();
        JLabel lblMotivo = new JLabel(descricao);
        lblMotivo.setFont(FONTE_HIST_MOTIVO);
        lblMotivo.setForeground(COR_PRINCIPAL);

        JLabel lblResponsavel = new JLabel("Responsável: " + h.getResponsavel());
        lblResponsavel.setFont(FONTE_HIST_USER);
        lblResponsavel.setForeground(COR_MUTED);

        painelTextos.add(lblMotivo,      "wrap");
        painelTextos.add(lblResponsavel);

        // Data
        JLabel lblData = new JLabel(h.getDatahora());
        lblData.setFont(FONTE_HIST_DATA);
        lblData.setForeground(COR_MUTED);

        item.add(lblQtd,        "cell 0 0, aligny center");
        item.add(painelTextos,  "cell 1 0, growx, aligny center");
        item.add(lblData,       "cell 2 0, aligny center");

        return item;
    }

    // ── AÇÃO VOLTAR ───────────────────────────────────────────────────────────
    public void acaoVoltar(Runnable acao) {
        ComponentUtils.transformarEmLink(this.Voltar, acao);
    }
}
