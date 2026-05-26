package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class TelaSaida extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtQuantidade;
	private JTable tableProdutos;
	private JTextField txtResponsavel;
	private JTextField txtDataSaida;
	private JTextArea txtAreaObservacao;

	/**
	 * Create the panel.
	 */
	public TelaSaida() {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[40px:n,grow 0][135px:n,grow 0][][20px:n][grow 7][grow 11][grow 1]", "[40px:n,grow 0][35px:n][35px:n][35px:n][35px:n][35px:n][grow 11][grow 11][grow 11][grow 11]"));
		
		// --- Dashboard da Esquerda ---
		JLabel lblPerfil = new JLabel("");
		lblPerfil.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/image8.png"))); // Placeholder
		add(lblPerfil, "cell 0 0 2 1,alignx center,aligny center");
		
		JLabel lblNewLabel_4 = new JLabel("Descubra");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblNewLabel_4, "cell 0 1 2 1");
		
		JLabel lblInicio = new JLabel("");
		lblInicio.setForeground(new Color(255, 255, 255));
		lblInicio.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/home.png"))); // Placeholder
		add(lblInicio, "cell 0 2,alignx center");
		
		JLabel lblNewLabel = new JLabel("Início");
		add(lblNewLabel, "cell 1 2");
		
		JLabel lblControleEstoq = new JLabel("");
		lblControleEstoq.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/caixa(1)1.png"))); // Placeholder
		add(lblControleEstoq, "cell 0 3,alignx left");
		
		JLabel lblNewLabel_1 = new JLabel("Controle de Estoque");
		add(lblNewLabel_1, "cell 1 3");
		
		JLabel lblEstatis = new JLabel("");
		lblEstatis.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/grafico.png"))); // Placeholder
		add(lblEstatis, "cell 0 4,alignx center");
		
		JLabel lblNewLabel_2 = new JLabel("Estatísticas");
		add(lblNewLabel_2, "cell 1 4");
		
		JLabel lblEstraSai = new JLabel("");
		lblEstraSai.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/entradaesaida(1)1.png"))); // Placeholder
		add(lblEstraSai, "cell 0 5,alignx center");
		
		JLabel lblNewLabel_3 = new JLabel("Entrada e Saída");
		add(lblNewLabel_3, "cell 1 5");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(0, 0, 0));
		add(separator, "cell 2 0 1 10,gapx 2 2,growy");
		
		ImageIcon icon = new ImageIcon(TelaSaida.class.getResource("/img/logopng.png")); // Placeholder
		Image img = icon.getImage();
		Image imgRedimensionada = img.getScaledInstance(70, 35, Image.SCALE_SMOOTH);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(imgRedimensionada));
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblLogo, "cell 0 9 2 1,alignx center,aligny bottom");
		
		// --- Conteúdo da Nova Tela (Saída de Estoque) ---
		JLabel lblVoltar = new JLabel("");
		lblVoltar.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/back_arrow.png"))); // Placeholder
		add(lblVoltar, "cell 4 0,aligny center");
		
		JLabel lblTitulo = new JLabel("Saída de Estoque");
		lblTitulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 26));
		add(lblTitulo, "cell 4 0,aligny center, gapleft 30");
		
		JLabel lblSubtitulo = new JLabel("Registre uma nova saída de produtos do estoque");
		lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		add(lblSubtitulo, "cell 4 1,aligny top, gapleft 30");
		
		JLabel lblDataHora = new JLabel("26/05/2025 14:30"); // Placeholder for dynamic date/time
		lblDataHora.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		add(lblDataHora, "cell 6 0,alignx right,aligny center");
		
		// --- Adicionar Produto à Saída ---
		JPanel panelAdicionarProduto = new JPanel();
		panelAdicionarProduto.setBackground(new Color(240, 240, 240));
		panelAdicionarProduto.setLayout(new MigLayout("", "[grow][100px][150px]", "[pref!][pref!]"));
		add(panelAdicionarProduto, "cell 4 2 3 1,growx,gapy 10 10");
		
		JLabel lblProduto = new JLabel("Produto");
		panelAdicionarProduto.add(lblProduto, "cell 0 0");
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		panelAdicionarProduto.add(lblQuantidade, "cell 1 0");
		
		JComboBox<String> cmbProduto = new JComboBox<>();
		cmbProduto.addItem("Selecione um produto...");
		panelAdicionarProduto.add(cmbProduto, "cell 0 1,growx");
		
		txtQuantidade = new JTextField("0");
		panelAdicionarProduto.add(txtQuantidade, "cell 1 1,growx");
		txtQuantidade.setColumns(10);
		
		JButton btnAdicionar = new JButton("+ Adicionar ao Caminhão");
		btnAdicionar.setBackground(new Color(0, 0, 0));
		btnAdicionar.setForeground(new Color(255, 255, 255));
		panelAdicionarProduto.add(btnAdicionar, "cell 2 1,growx");
		
		// --- Produtos Adicionados ao Caminhão ---
		JLabel lblProdutosAdicionados = new JLabel("Produtos Adicionados ao Caminhão");
		lblProdutosAdicionados.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		add(lblProdutosAdicionados, "cell 4 3 3 1,gapy 10 0");
		
		tableProdutos = new JTable();
		tableProdutos.setModel(new DefaultTableModel(
			new Object[][] {
				{"Mouse Gamer Logitech G203", "MS001", "10", "45", new ImageIcon(TelaSaida.class.getResource("/img/delete_icon.png"))}, // Placeholder
				{"Teclado Mecânico Redragon K552", "TC009", "5", "28", new ImageIcon(TelaSaida.class.getResource("/img/delete_icon.png"))}, // Placeholder
				{"Headset HyperX Cloud Stinger", "HD007", "3", "12", new ImageIcon(TelaSaida.class.getResource("/img/delete_icon.png"))}, // Placeholder
				{"Mousepad Speed Large", "MP003", "8", "30", new ImageIcon(TelaSaida.class.getResource("/img/delete_icon.png"))}, // Placeholder
			},
			new String[] {
				"Produto", "SKU", "Quantidade", "Estoque Atual", "Ação"
			}
		));
		JScrollPane scrollPane = new JScrollPane(tableProdutos);
		add(scrollPane, "cell 4 4 3 2,grow");
		
		// --- Cards de Resumo ---
		JPanel panelResumo = new JPanel();
		panelResumo.setBackground(new Color(255, 255, 255));
		panelResumo.setLayout(new MigLayout("", "[grow][grow][grow]", "[pref!]"));
		add(panelResumo, "cell 4 6 3 1,growx,gapy 10 10");
		
		// Card Itens Adicionados
		JPanel cardItens = new JPanel();
		cardItens.setBackground(new Color(240, 240, 240));
		cardItens.setLayout(new MigLayout("", "[grow]", "[pref!][pref!]"));
		panelResumo.add(cardItens, "cell 0 0,grow");
		JLabel lblIconeItens = new JLabel("");
		lblIconeItens.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/box_icon.png"))); // Placeholder
		cardItens.add(lblIconeItens, "cell 0 0,alignx center");
		JLabel lblItensAdicionados = new JLabel("Itens adicionados");
		cardItens.add(lblItensAdicionados, "cell 0 1,alignx center");
		JLabel lblNumItens = new JLabel("4");
		lblNumItens.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		cardItens.add(lblNumItens, "cell 0 2,alignx center");
		
		// Card Quantidade Total
		JPanel cardQuantidade = new JPanel();
		cardQuantidade.setBackground(new Color(240, 240, 240));
		cardQuantidade.setLayout(new MigLayout("", "[grow]", "[pref!][pref!]"));
		panelResumo.add(cardQuantidade, "cell 1 0,grow");
		JLabel lblIconeQuantidade = new JLabel("");
		lblIconeQuantidade.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/cart_icon.png"))); // Placeholder
		cardQuantidade.add(lblIconeQuantidade, "cell 0 0,alignx center");
		JLabel lblQuantidadeTotal = new JLabel("Quantidade total");
		cardQuantidade.add(lblQuantidadeTotal, "cell 0 1,alignx center");
		JLabel lblNumQuantidade = new JLabel("26");
		lblNumQuantidade.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		cardQuantidade.add(lblNumQuantidade, "cell 0 2,alignx center");
		
		// Card Valor Estimado
		JPanel cardValor = new JPanel();
		cardValor.setBackground(new Color(240, 240, 240));
		cardValor.setLayout(new MigLayout("", "[grow]", "[pref!][pref!]"));
		panelResumo.add(cardValor, "cell 2 0,grow");
		JLabel lblIconeValor = new JLabel("");
		lblIconeValor.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/money_icon.png"))); // Placeholder
		cardValor.add(lblIconeValor, "cell 0 0,alignx center");
		JLabel lblValorEstimado = new JLabel("Valor estimado");
		cardValor.add(lblValorEstimado, "cell 0 1,alignx center");
		JLabel lblValor = new JLabel("R$ 1.456,90");
		lblValor.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		cardValor.add(lblValor, "cell 0 2,alignx center");
		
		// --- Informações da Saída ---
		JLabel lblInformacoesSaida = new JLabel("Informações da Saída");
		lblInformacoesSaida.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		add(lblInformacoesSaida, "cell 4 7 3 1,gapy 10 0");
		
		JPanel panelInfoSaida = new JPanel();
		panelInfoSaida.setBackground(new Color(255, 255, 255));
		panelInfoSaida.setLayout(new MigLayout("", "[grow][grow][grow]", "[pref!][pref!]"));
		add(panelInfoSaida, "cell 4 8 3 1,growx");
		
		JLabel lblResponsavel = new JLabel("Responsável");
		panelInfoSaida.add(lblResponsavel, "cell 0 0");
		
		JLabel lblData = new JLabel("Data da Saída");
		panelInfoSaida.add(lblData, "cell 1 0");
		
		JLabel lblObservacao = new JLabel("Observação (opcional)");
		panelInfoSaida.add(lblObservacao, "cell 2 0");
		
		txtResponsavel = new JTextField("Ian");
		panelInfoSaida.add(txtResponsavel, "cell 0 1,growx");
		txtResponsavel.setColumns(10);
		
		txtDataSaida = new JTextField("26/05/2025 14:30"); // Placeholder for dynamic date/time
		panelInfoSaida.add(txtDataSaida, "cell 1 1,growx");
		txtDataSaida.setColumns(10);
		
		txtAreaObservacao = new JTextArea("Digite uma observação...");
		txtAreaObservacao.setRows(3);
		txtAreaObservacao.setLineWrap(true);
		txtAreaObservacao.setWrapStyleWord(true);
		JScrollPane scrollPaneObservacao = new JScrollPane(txtAreaObservacao);
		panelInfoSaida.add(scrollPaneObservacao, "cell 2 1,grow");
		
		// --- Botões de Ação ---
		JPanel panelBotoes = new JPanel();
		panelBotoes.setBackground(new Color(255, 255, 255));
		panelBotoes.setLayout(new MigLayout("", "[grow][grow]", "[pref!]"));
		add(panelBotoes, "cell 4 9 3 1,growx,aligny bottom,gapy 10 0");
		
		JButton btnCancelar = new JButton("X Cancelar");
		btnCancelar.setBackground(new Color(240, 240, 240));
		btnCancelar.setForeground(new Color(0, 0, 0));
		panelBotoes.add(btnCancelar, "cell 0 0,alignx right");
		
		JButton btnConfirmar = new JButton("✓ Confirmar Saída");
		btnConfirmar.setBackground(new Color(0, 0, 0));
		btnConfirmar.setForeground(new Color(255, 255, 255));
		panelBotoes.add(btnConfirmar, "cell 1 0,alignx right");
	}

}
