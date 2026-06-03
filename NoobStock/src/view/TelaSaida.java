package view;

import controller.ComponentUtils;
import model.ItemSaida;
import model.Produto;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

public class TelaSaida extends JPanel {

    private static final long serialVersionUID = 1L;

   
    private JTextField txtQuantidade;
    private JTextField txtDataSaida;
    private JTextArea txtAreaObservacao;
    private JTextField txtResponsavel;
    private JTable tableProdutos;

    
    private JComboBox<String> cmbProduto;
    private DefaultTableModel tableModel;
    private JButton btnAdicionar;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JButton btnRemover;

    
    private JLabel LInicio, LEstoque, LFor, LSaida, lblPerfil;

   
    private LinkedHashMap<String, Produto> mapaProdutos = new LinkedHashMap<>();

    public TelaSaida() {
        setBackground(new Color(255, 255, 255));
        setLayout(new MigLayout("",
                "[40px:n,grow 0][135px:n,grow 0][][20px:n][grow 7][grow 11][grow 1]",
                "[40px:n,grow 0][35px:n][35px:n][35px:n][35px:n][35px:n][grow 11][grow 11][grow 11][grow 11]"));

        
        lblPerfil = new JLabel("");
        lblPerfil.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/image8.png")));
        lblPerfil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(lblPerfil, "cell 0 0 2 1,alignx center,aligny center");

        JLabel lblDescubra = new JLabel("Descubra");
        lblDescubra.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(lblDescubra, "cell 0 1 2 1");

        JLabel lblIconInicio = new JLabel("");
        lblIconInicio.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/home.png")));
        add(lblIconInicio, "cell 0 2,alignx center");

        LInicio = new JLabel("Início");
        LInicio.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(LInicio, "cell 1 2,aligny center");

        JLabel lblIconEstoque = new JLabel("");
        lblIconEstoque.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/caixa(1)1.png")));
        add(lblIconEstoque, "cell 0 3,alignx left");

        LEstoque = new JLabel("Controle de Estoque");
        LEstoque.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(LEstoque, "cell 1 3,aligny center");

        JLabel lblIconFor = new JLabel("");
        lblIconFor.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/grafico.png")));
        add(lblIconFor, "cell 0 4,alignx center");

        LFor = new JLabel("Fornecedores");
        LFor.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(LFor, "cell 1 4,aligny center");

        JLabel lblIconSaida = new JLabel("");
        lblIconSaida.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/entradaesaida(1)1.png")));
        add(lblIconSaida, "cell 0 5,alignx center");

        LSaida = new JLabel("Saída de Estoque");
        LSaida.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(LSaida, "cell 1 5,aligny center");

        
        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setForeground(new Color(0, 0, 0));
        add(separator, "cell 2 0 1 10,gapx 2 2,growy");

        
        ImageIcon icon = new ImageIcon(TelaSaida.class.getResource("/img/logopng.png"));
        Image imgRedimensionada = icon.getImage().getScaledInstance(70, 35, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(imgRedimensionada));
        add(lblLogo, "cell 0 9 2 1,alignx center,aligny bottom");

        
        JLabel lblVoltar = new JLabel("");
        lblVoltar.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/button→svg.png")));
        add(lblVoltar, "cell 4 0,aligny center");

        JLabel lblTitulo = new JLabel("Saída de Estoque");
        lblTitulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 26));
        add(lblTitulo, "cell 4 0,aligny center,gapleft 30");

        JLabel lblSubtitulo = new JLabel("Registre uma nova saída de produtos do estoque");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        add(lblSubtitulo, "cell 4 1,aligny top,gapleft 30");

        
        JLabel lblDataHora = new JLabel("");
        lblDataHora.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        add(lblDataHora, "cell 6 0,alignx right,aligny center");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Timer timer = new Timer(1000, e -> lblDataHora.setText(dtf.format(LocalDateTime.now())));
        timer.start();
        lblDataHora.setText(dtf.format(LocalDateTime.now()));

        
        JPanel panelAdicionarProduto = new JPanel();
        panelAdicionarProduto.setBackground(new Color(240, 240, 240));
        panelAdicionarProduto.setLayout(new MigLayout("insets 10", "[grow][100px][160px]", "[pref!][pref!]"));
        add(panelAdicionarProduto, "cell 4 2 3 2,growx,aligny center,gapy 10 10");

        JLabel lblProdutoHeader = new JLabel("Adicionar Produto à Saída");
        lblProdutoHeader.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
        panelAdicionarProduto.add(lblProdutoHeader, "cell 0 0");

        JLabel lblQuantidadeHeader = new JLabel("Quantidade");
        panelAdicionarProduto.add(lblQuantidadeHeader, "cell 1 0");

        
        cmbProduto = new JComboBox<>();
        cmbProduto.addItem("Carregando produtos...");
        panelAdicionarProduto.add(cmbProduto, "cell 0 1,growx");

        txtQuantidade = new JTextField("1");
        txtQuantidade.setColumns(5);
        panelAdicionarProduto.add(txtQuantidade, "cell 1 1,growx");

        btnAdicionar = new JButton("+ Adicionar ao Caminhão");
        btnAdicionar.setBackground(new Color(30, 30, 30));
        btnAdicionar.setForeground(Color.WHITE);
        btnAdicionar.setFocusPainted(false);
        btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelAdicionarProduto.add(btnAdicionar, "cell 2 1,growx");

        
        JLabel lblProdutosAdicionados = new JLabel("Produtos Adicionados ao Caminhão");
        lblProdutosAdicionados.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
        add(lblProdutosAdicionados, "cell 4 4,gapy 10 0");

        
        String[] colunas = {"Produto", "SKU", "Qtd. Saída", "Estoque Disponível"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tableProdutos = new JTable(tableModel);
        tableProdutos.setRowHeight(26);
        tableProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableProdutos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableProdutos.getTableHeader().setBackground(new Color(30, 30, 30));
        tableProdutos.getTableHeader().setForeground(Color.WHITE);
        tableProdutos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tableProdutos.setGridColor(new Color(220, 220, 220));
        tableProdutos.getColumnModel().getColumn(2).setMaxWidth(100);
        tableProdutos.getColumnModel().getColumn(3).setMaxWidth(150);
        JScrollPane scrollPane = new JScrollPane(tableProdutos);
        add(scrollPane, "cell 4 5 3 2,grow");

    
        JLabel lblInformacoesSaida = new JLabel("Informações da Saída");
        lblInformacoesSaida.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
        add(lblInformacoesSaida, "cell 4 7 3 1,gapy 10 0");

        JPanel panelInfoSaida = new JPanel();
        panelInfoSaida.setBackground(new Color(255, 255, 255));
        panelInfoSaida.setLayout(new MigLayout("insets 0", "[grow][grow][grow]", "[pref!][pref!]"));
        add(panelInfoSaida, "cell 4 8 3 1,growx");

        JLabel lblResponsavel = new JLabel("Responsável *");
        lblResponsavel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelInfoSaida.add(lblResponsavel, "cell 0 0");

        JLabel lblData = new JLabel("Data da Saída");
        lblData.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelInfoSaida.add(lblData, "cell 1 0");

        JLabel lblObservacao = new JLabel("Observação (opcional)");
        lblObservacao.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelInfoSaida.add(lblObservacao, "cell 2 0");

        txtResponsavel = new JTextField();
        txtResponsavel.setColumns(10);
        panelInfoSaida.add(txtResponsavel, "cell 0 1,growx");
        txtDataSaida = new JTextField(dtf.format(LocalDateTime.now()));
        txtDataSaida.setEditable(false);
        txtDataSaida.setBackground(new Color(230, 230, 230));
        txtDataSaida.setColumns(10);
        panelInfoSaida.add(txtDataSaida, "cell 1 1,growx");

        txtAreaObservacao = new JTextArea();
        txtAreaObservacao.setRows(2);
        txtAreaObservacao.setLineWrap(true);
        txtAreaObservacao.setWrapStyleWord(true);
        txtAreaObservacao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JScrollPane scrollObs = new JScrollPane(txtAreaObservacao);
        panelInfoSaida.add(scrollObs, "cell 2 1,grow");


        JPanel panelBotoes = new JPanel();
        panelBotoes.setBackground(new Color(255, 255, 255));
        panelBotoes.setLayout(new MigLayout("insets 5 0 5 0", "[grow][][]", "[pref!]"));
        add(panelBotoes, "cell 4 9 3 1,growx,aligny bottom,gapy 8 0");

        btnRemover = new JButton("🗑 Remover Selecionado");
        btnRemover.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnRemover.setBackground(new Color(200, 50, 50));
        btnRemover.setForeground(Color.WHITE);
        btnRemover.setFocusPainted(false);
        btnRemover.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelBotoes.add(btnRemover, "cell 0 0,alignx left");

        btnCancelar = new JButton("✕  Cancelar");
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCancelar.setBackground(new Color(220, 220, 220));
        btnCancelar.setForeground(new Color(30, 30, 30));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelBotoes.add(btnCancelar, "cell 1 0,gapx 6 6");

        btnConfirmar = new JButton("✓  Confirmar Saída");
        btnConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnConfirmar.setBackground(new Color(20, 20, 20));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFocusPainted(false);
        btnConfirmar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelBotoes.add(btnConfirmar, "cell 2 0");
    }



    public void popularComboBoxProdutos(LinkedHashMap<String, Produto> mapa) {
        this.mapaProdutos = mapa;
        cmbProduto.removeAllItems();
        cmbProduto.addItem("Selecione um produto...");
        for (String label : mapa.keySet()) {
            cmbProduto.addItem(label);
        }
    }

    public Produto getProdutoSelecionado() {
        String selecionado = (String) cmbProduto.getSelectedItem();
        if (selecionado == null || selecionado.startsWith("Selecione")) return null;
        return mapaProdutos.get(selecionado);
    }


    public int getQuantidadeInserida() {
        try {
            return Integer.parseInt(txtQuantidade.getText().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String getResponsavel()  { return txtResponsavel.getText().trim(); }
    public String getObservacao()   { return txtAreaObservacao.getText().trim(); }
    public DefaultTableModel getTableModel() { return tableModel; }

    public void adicionarItemNaTabela(ItemSaida item) {
        Produto p = item.getProduto();
        tableModel.addRow(new Object[]{
            p.getNome(),
            p.getSKU(),
            item.getQuantidade(),
            p.getQtd()         
        });
    }

    public void atualizarQuantidadeNaTabela(int linha, int novaQtd) {
        tableModel.setValueAt(novaQtd, linha, 2);
    }


    public void removerLinhaDaTabela(int linha) {
        if (linha >= 0 && linha < tableModel.getRowCount()) {
            tableModel.removeRow(linha);
        }
    }


    public int getLinhaSelecionada() { return tableProdutos.getSelectedRow(); }

   
    public void limparTabela() { tableModel.setRowCount(0); }

 
    public void limparCampos() {
        if (cmbProduto.getItemCount() > 0) cmbProduto.setSelectedIndex(0);
        txtQuantidade.setText("1");
    }


    public void setAdicionarAcao(ActionListener a)  { btnAdicionar.addActionListener(a); }
    public void setConfirmarAcao(ActionListener a)  { btnConfirmar.addActionListener(a); }
    public void setCancelarAcao(ActionListener a)   { btnCancelar.addActionListener(a);  }
    public void setRemoverAcao(ActionListener a)    { btnRemover.addActionListener(a);   }
    public void setInicio(Runnable acao)     { ComponentUtils.transformarEmLink(LInicio,  acao); }
    public void setEstoque(Runnable acao)    { ComponentUtils.transformarEmLink(LEstoque, acao); }
    public void setFornecedor(Runnable acao) { ComponentUtils.transformarEmLink(LFor,     acao); }
    public void setSaida(Runnable acao)      { ComponentUtils.transformarEmLink(LSaida,   acao); }
    public void setPerfil(Runnable acao)     { ComponentUtils.transformarEmLink(lblPerfil,acao); }
}
