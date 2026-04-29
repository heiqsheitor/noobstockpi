package view;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

import javax.swing.ImageIcon;

import java.awt.Color;

import java.awt.Font;

import javax.swing.JSeparator;

import javax.swing.SwingConstants;

import javax.swing.border.BevelBorder;

import javax.swing.border.LineBorder;

public class TelaDetalhesProduto extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Create the panel.
	 * 
	 */

	public TelaDetalhesProduto() {

		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[][][grow 10][][grow][grow][][grow][grow 3][][grow][]",
				"[][][][grow][][][][][grow][grow 1][grow 1][grow 1][grow][][]"));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/image8.png")));

		add(lblNewLabel, "cell 1 0");

		JLabel lblNewLabel_12 = new JLabel("Detalhes do Produto");

		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 15));

		add(lblNewLabel_12, "cell 5 1");

		JLabel lblNewLabel_1 = new JLabel("Descubra");

		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));

		add(lblNewLabel_1, "cell 0 2");

		JLabel lblNewLabel_11 = new JLabel("");

		lblNewLabel_11.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/button→svg.png")));

		add(lblNewLabel_11, "cell 4 2");

		JLabel lblNewLabel_2 = new JLabel("");

		lblNewLabel_2.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/home.png")));

		add(lblNewLabel_2, "cell 0 3,alignx center");

		JLabel lblNewLabel_3 = new JLabel("Início");

		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));

		add(lblNewLabel_3, "cell 1 3");

		JPanel panel = new JPanel();

		panel.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel.setBackground(new Color(255, 255, 255));

		add(panel, "cell 4 3 7 4,grow");

		panel.setLayout(null);

		JLabel lblNewLabel_13 = new JLabel("Mouse");

		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblNewLabel_13.setBounds(10, 11, 46, 14);

		panel.add(lblNewLabel_13);

		JLabel lblNewLabel_14 = new JLabel("SKU: ELEC-101-BK");

		lblNewLabel_14.setForeground(new Color(128, 128, 128));

		lblNewLabel_14.setBounds(10, 36, 103, 14);

		panel.add(lblNewLabel_14);

		JLabel lblNewLabel_15 = new JLabel("ID: 0044831576");

		lblNewLabel_15.setForeground(new Color(128, 128, 128));

		lblNewLabel_15.setBounds(10, 52, 89, 14);

		panel.add(lblNewLabel_15);

		JPanel panel_1 = new JPanel();

		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel_1.setBackground(new Color(243, 243, 243));

		panel_1.setForeground(new Color(0, 0, 0));

		panel_1.setBounds(10, 77, 149, 56);

		panel.add(panel_1);

		panel_1.setLayout(null);

		JLabel lblNewLabel_14_1 = new JLabel("Fornecedor");

		lblNewLabel_14_1.setForeground(Color.GRAY);

		lblNewLabel_14_1.setBounds(10, 11, 103, 14);

		panel_1.add(lblNewLabel_14_1);

		JLabel lblNewLabel_16 = new JLabel("");

		lblNewLabel_16.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/icone user.png")));

		lblNewLabel_16.setBounds(10, 36, 46, 14);

		panel_1.add(lblNewLabel_16);

		JLabel lblNewLabel_17 = new JLabel("TechSupply Ltda");

		lblNewLabel_17.setBounds(32, 36, 107, 14);

		panel_1.add(lblNewLabel_17);

		JPanel panel_1_1 = new JPanel();

		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel_1_1.setLayout(null);

		panel_1_1.setForeground(Color.BLACK);

		panel_1_1.setBackground(new Color(243, 243, 243));

		panel_1_1.setBounds(194, 77, 149, 56);

		panel.add(panel_1_1);

		JLabel lblNewLabel_14_1_1 = new JLabel("Data");

		lblNewLabel_14_1_1.setForeground(Color.GRAY);

		lblNewLabel_14_1_1.setBounds(10, 11, 103, 14);

		panel_1_1.add(lblNewLabel_14_1_1);

		JLabel lblNewLabel_17_1 = new JLabel("Dez 5");

		lblNewLabel_17_1.setBounds(32, 36, 107, 14);

		panel_1_1.add(lblNewLabel_17_1);

		JLabel lblNewLabel_18 = new JLabel("");

		lblNewLabel_18.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/calendario.png")));

		lblNewLabel_18.setBounds(10, 36, 46, 14);

		panel_1_1.add(lblNewLabel_18);

		JPanel panel_1_2 = new JPanel();

		panel_1_2.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel_1_2.setLayout(null);

		panel_1_2.setForeground(Color.BLACK);

		panel_1_2.setBackground(new Color(243, 243, 243));

		panel_1_2.setBounds(368, 77, 136, 56);

		panel.add(panel_1_2);

		JLabel lblNewLabel_14_1_2 = new JLabel("Disponibilidade");

		lblNewLabel_14_1_2.setForeground(Color.GRAY);

		lblNewLabel_14_1_2.setBounds(10, 11, 103, 14);

		panel_1_2.add(lblNewLabel_14_1_2);

		JLabel lblNewLabel_19 = new JLabel("");

		lblNewLabel_19.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/ex baixa.png")));

		lblNewLabel_19.setBounds(10, 31, 46, 14);

		panel_1_2.add(lblNewLabel_19);

		JLabel lblNewLabel_14_3 = new JLabel("Categoria");

		lblNewLabel_14_3.setForeground(Color.GRAY);

		lblNewLabel_14_3.setBounds(450, 13, 54, 14);

		panel.add(lblNewLabel_14_3);

		JLabel lblNewLabel_4 = new JLabel("");

		lblNewLabel_4.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/caixa(1)1.png")));

		add(lblNewLabel_4, "cell 0 4,alignx center");

		JSeparator separator_1 = new JSeparator();

		separator_1.setOrientation(SwingConstants.VERTICAL);

		separator_1.setBackground(new Color(255, 255, 255));

		separator_1.setForeground(new Color(0, 0, 0));

		add(separator_1, "cell 3 0 1 15,gapx 10 10,growy");

		JLabel lblNewLabel_5 = new JLabel("Controle de Estoque");

		add(lblNewLabel_5, "cell 1 4");

		JLabel lblNewLabel_6 = new JLabel("");

		lblNewLabel_6.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/grafico.png")));

		add(lblNewLabel_6, "cell 0 5,alignx center");

		JLabel lblNewLabel_7 = new JLabel("Estatísticas");

		add(lblNewLabel_7, "cell 1 5");

		JLabel lblNewLabel_8 = new JLabel("");

		lblNewLabel_8.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/entradaesaida(1)1.png")));

		add(lblNewLabel_8, "cell 0 6,alignx center");

		JLabel lblNewLabel_9 = new JLabel("Entrada e Saída");

		add(lblNewLabel_9, "cell 1 6");

		JPanel panel_2 = new JPanel();

		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel_2.setBackground(new Color(255, 255, 255));

		add(panel_2, "cell 4 8 2 4,grow");

		panel_2.setLayout(null);

		JLabel lblNewLabel_13_1 = new JLabel("Localização no Estoque");

		lblNewLabel_13_1.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblNewLabel_13_1.setBounds(45, 11, 169, 14);

		panel_2.add(lblNewLabel_13_1);

		JLabel lblNewLabel_20 = new JLabel("");

		lblNewLabel_20.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/loc.png")));

		lblNewLabel_20.setBounds(7, 36, 20, 20);

		panel_2.add(lblNewLabel_20);

		JLabel lblNewLabel_14_2 = new JLabel("Armazém:");

		lblNewLabel_14_2.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblNewLabel_14_2.setForeground(Color.GRAY);

		lblNewLabel_14_2.setBounds(33, 42, 87, 14);

		panel_2.add(lblNewLabel_14_2);

		JLabel lblNewLabel_14_2_1 = new JLabel("Armazém:");

		lblNewLabel_14_2_1.setForeground(Color.GRAY);

		lblNewLabel_14_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblNewLabel_14_2_1.setBounds(33, 74, 87, 14);

		panel_2.add(lblNewLabel_14_2_1);

		JLabel lblNewLabel_14_2_2 = new JLabel("Armazém:");

		lblNewLabel_14_2_2.setForeground(Color.GRAY);

		lblNewLabel_14_2_2.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblNewLabel_14_2_2.setBounds(33, 107, 87, 14);

		panel_2.add(lblNewLabel_14_2_2);

		JLabel lblNewLabel_14_2_3 = new JLabel("Armazem A");

		lblNewLabel_14_2_3.setForeground(new Color(0, 0, 0));

		lblNewLabel_14_2_3.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblNewLabel_14_2_3.setBounds(207, 42, 87, 14);

		panel_2.add(lblNewLabel_14_2_3);

		JLabel lblNewLabel_14_2_3_1 = new JLabel("Corredor 3");

		lblNewLabel_14_2_3_1.setForeground(Color.BLACK);

		lblNewLabel_14_2_3_1.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblNewLabel_14_2_3_1.setBounds(207, 71, 87, 20);

		panel_2.add(lblNewLabel_14_2_3_1);

		JLabel lblNewLabel_14_2_3_2 = new JLabel("Prateleira 12");

		lblNewLabel_14_2_3_2.setForeground(Color.BLACK);

		lblNewLabel_14_2_3_2.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblNewLabel_14_2_3_2.setBounds(207, 107, 87, 14);

		panel_2.add(lblNewLabel_14_2_3_2);

		JSeparator separator = new JSeparator();

		separator.setBounds(7, 61, 275, 2);

		panel_2.add(separator);

		JSeparator separator_2 = new JSeparator();

		separator_2.setBounds(7, 91, 275, 2);

		panel_2.add(separator_2);

		JPanel panel_2_1_1 = new JPanel();

		panel_2_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel_2_1_1.setBackground(new Color(255, 255, 255));

		add(panel_2_1_1, "cell 7 8 5 7,grow");

		panel_2_1_1.setLayout(null);

		JLabel lblNewLabel_13_1_2 = new JLabel("Histórico de Movimentações");

		lblNewLabel_13_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));

		lblNewLabel_13_1_2.setBounds(10, 11, 175, 14);

		panel_2_1_1.add(lblNewLabel_13_1_2);

		JLabel lblNewLabel_14_2_3_3 = new JLabel("+50 unidades");

		lblNewLabel_14_2_3_3.setForeground(Color.BLACK);

		lblNewLabel_14_2_3_3.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblNewLabel_14_2_3_3.setBounds(60, 39, 87, 14);

		panel_2_1_1.add(lblNewLabel_14_2_3_3);

		JLabel lblNewLabel_23 = new JLabel("");

		lblNewLabel_23.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/seta det p.png")));

		lblNewLabel_23.setBounds(10, 36, 40, 40);

		panel_2_1_1.add(lblNewLabel_23);

		JLabel lblNewLabel_14_3_1 = new JLabel("Recebimento Fornecedor");

		lblNewLabel_14_3_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblNewLabel_14_3_1.setForeground(Color.GRAY);

		lblNewLabel_14_3_1.setBounds(60, 53, 125, 14);

		panel_2_1_1.add(lblNewLabel_14_3_1);

		JLabel lblNewLabel_24 = new JLabel("");

		lblNewLabel_24.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/seta det n.png")));

		lblNewLabel_24.setBounds(10, 113, 40, 40);

		panel_2_1_1.add(lblNewLabel_24);

		JLabel lblNewLabel_14_2_3_3_1 = new JLabel("25 unidades");

		lblNewLabel_14_2_3_3_1.setForeground(Color.BLACK);

		lblNewLabel_14_2_3_3_1.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblNewLabel_14_2_3_3_1.setBounds(60, 124, 87, 14);

		panel_2_1_1.add(lblNewLabel_14_2_3_3_1);

		JLabel lblNewLabel_14_3_1_1 = new JLabel("Venda");

		lblNewLabel_14_3_1_1.setForeground(Color.GRAY);

		lblNewLabel_14_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblNewLabel_14_3_1_1.setBounds(60, 139, 125, 14);

		panel_2_1_1.add(lblNewLabel_14_3_1_1);

		JLabel lblNewLabel_25 = new JLabel("");

		lblNewLabel_25.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/cube det.png")));

		lblNewLabel_25.setBounds(10, 184, 40, 43);

		panel_2_1_1.add(lblNewLabel_25);

		JLabel lblNewLabel_14_2_3_3_1_1 = new JLabel("-5 unidades");

		lblNewLabel_14_2_3_3_1_1.setForeground(Color.BLACK);

		lblNewLabel_14_2_3_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblNewLabel_14_2_3_3_1_1.setBounds(60, 198, 87, 14);

		panel_2_1_1.add(lblNewLabel_14_2_3_3_1_1);

		JLabel lblNewLabel_14_3_1_2 = new JLabel("Recebimento Fornecedor");

		lblNewLabel_14_3_1_2.setForeground(Color.GRAY);

		lblNewLabel_14_3_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblNewLabel_14_3_1_2.setBounds(60, 213, 125, 14);

		panel_2_1_1.add(lblNewLabel_14_3_1_2);

		JSeparator separator_3 = new JSeparator();

		separator_3.setBounds(10, 87, 195, 2);

		panel_2_1_1.add(separator_3);

		JSeparator separator_3_1 = new JSeparator();

		separator_3_1.setBounds(10, 171, 195, 2);

		panel_2_1_1.add(separator_3_1);

		JLabel lblNewLabel_10 = new JLabel("");

		lblNewLabel_10.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/LOGO1.png")));

		add(lblNewLabel_10, "cell 0 14");

		JPanel panel_2_1 = new JPanel();

		panel_2_1.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel_2_1.setBackground(new Color(255, 255, 255));

		add(panel_2_1, "cell 4 12 2 3,grow");

		panel_2_1.setLayout(null);

		JLabel lblNewLabel_13_1_1 = new JLabel("Quantidade em Tempo Real");

		lblNewLabel_13_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblNewLabel_13_1_1.setBounds(51, 11, 224, 14);

		panel_2_1.add(lblNewLabel_13_1_1);

		JLabel lblNewLabel_13_1_1_1 = new JLabel("250");

		lblNewLabel_13_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblNewLabel_13_1_1_1.setBounds(82, 28, 35, 25);

		panel_2_1.add(lblNewLabel_13_1_1_1);

		JLabel lblNewLabel_14_2_1_1 = new JLabel("Unidades Disponíveis");

		lblNewLabel_14_2_1_1.setForeground(Color.GRAY);

		lblNewLabel_14_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblNewLabel_14_2_1_1.setBounds(122, 36, 132, 14);

		panel_2_1.add(lblNewLabel_14_2_1_1);

		JLabel lblNewLabel_14_2_1_1_1 = new JLabel("Estoque mínimo:");

		lblNewLabel_14_2_1_1_1.setForeground(Color.GRAY);

		lblNewLabel_14_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblNewLabel_14_2_1_1_1.setBounds(10, 57, 96, 14);

		panel_2_1.add(lblNewLabel_14_2_1_1_1);

		JLabel lblNewLabel_21 = new JLabel("");

		lblNewLabel_21.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/linha green.png")));

		lblNewLabel_21.setBounds(10, 74, 272, 14);

		panel_2_1.add(lblNewLabel_21);

		JLabel lblNewLabel_14_2_1_1_1_1 = new JLabel("50");

		lblNewLabel_14_2_1_1_1_1.setForeground(Color.GRAY);

		lblNewLabel_14_2_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblNewLabel_14_2_1_1_1_1.setBounds(110, 58, 96, 14);

		panel_2_1.add(lblNewLabel_14_2_1_1_1_1);

		JLabel lblNewLabel_14_2_1_1_1_1_1 = new JLabel("100%");

		lblNewLabel_14_2_1_1_1_1_1.setForeground(Color.GRAY);

		lblNewLabel_14_2_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblNewLabel_14_2_1_1_1_1_1.setBounds(248, 57, 46, 14);

		panel_2_1.add(lblNewLabel_14_2_1_1_1_1_1);

		JLabel lblNewLabel_22 = new JLabel("");

		lblNewLabel_22.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/caixa mini.png")));

		lblNewLabel_22.setBounds(10, 11, 27, 27);

		panel_2_1.add(lblNewLabel_22);

	}

	/**
	 * 
	 * Create the panel.
	 * 
	 * @return
	 * 
	 */

	public void TelaDetalhesProduto2() {

		setBackground(new Color(255, 255, 255));

		setLayout(new MigLayout("", "[][][grow 10][][grow][grow][][grow 3][][grow][]",
				"[][][][grow][][][][][grow][grow 1][grow 1][grow 1][grow][][]"));

		JLabel lblNewLabel = new JLabel("");

		lblNewLabel.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/image8.png")));

		add(lblNewLabel, "cell 1 0");

		JLabel lblNewLabel_12 = new JLabel("Detalhes do Produto");

		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 15));

		add(lblNewLabel_12, "cell 5 1");

		JLabel lblNewLabel_1 = new JLabel("Descubra");

		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));

		add(lblNewLabel_1, "cell 0 2");

		JLabel lblNewLabel_11 = new JLabel("");

		lblNewLabel_11.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/button→svg.png")));

		add(lblNewLabel_11, "cell 4 2");

		JLabel lblNewLabel_2 = new JLabel("");

		lblNewLabel_2.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/home.png")));

		add(lblNewLabel_2, "cell 0 3,alignx center");

		JLabel lblNewLabel_3 = new JLabel("Início");

		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));

		add(lblNewLabel_3, "cell 1 3");

		JPanel panel = new JPanel();

		panel.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel.setBackground(new Color(255, 255, 255));

		add(panel, "cell 4 3 6 4,grow");

		panel.setLayout(null);

		JLabel lblNewLabel_13 = new JLabel("Mouse");

		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblNewLabel_13.setBounds(10, 11, 46, 14);

		panel.add(lblNewLabel_13);

		JLabel lblNewLabel_14 = new JLabel("SKU: ELEC-101-BK");

		lblNewLabel_14.setForeground(new Color(128, 128, 128));

		lblNewLabel_14.setBounds(10, 36, 103, 14);

		panel.add(lblNewLabel_14);

		JLabel lblNewLabel_15 = new JLabel("ID: 0044831576");

		lblNewLabel_15.setForeground(new Color(128, 128, 128));

		lblNewLabel_15.setBounds(10, 52, 89, 14);

		panel.add(lblNewLabel_15);

		JPanel panel_1 = new JPanel();

		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel_1.setBackground(new Color(243, 243, 243));

		panel_1.setForeground(new Color(0, 0, 0));

		panel_1.setBounds(10, 77, 149, 56);

		panel.add(panel_1);

		panel_1.setLayout(null);

		JLabel lblNewLabel_14_1 = new JLabel("Fornecedor");

		lblNewLabel_14_1.setForeground(Color.GRAY);

		lblNewLabel_14_1.setBounds(10, 11, 103, 14);

		panel_1.add(lblNewLabel_14_1);

		JLabel lblNewLabel_16 = new JLabel("");

		lblNewLabel_16.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/icone user.png")));

		lblNewLabel_16.setBounds(10, 36, 46, 14);

		panel_1.add(lblNewLabel_16);

		JLabel lblNewLabel_17 = new JLabel("TechSupply Ltda");

		lblNewLabel_17.setBounds(32, 36, 107, 14);

		panel_1.add(lblNewLabel_17);

		JPanel panel_1_1 = new JPanel();

		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel_1_1.setLayout(null);

		panel_1_1.setForeground(Color.BLACK);

		panel_1_1.setBackground(new Color(243, 243, 243));

		panel_1_1.setBounds(202, 77, 149, 56);

		panel.add(panel_1_1);

		JLabel lblNewLabel_14_1_1 = new JLabel("Fornecedor");

		lblNewLabel_14_1_1.setForeground(Color.GRAY);

		lblNewLabel_14_1_1.setBounds(10, 11, 103, 14);

		panel_1_1.add(lblNewLabel_14_1_1);

		JLabel lblNewLabel_17_1 = new JLabel("Dez 5");

		lblNewLabel_17_1.setBounds(32, 36, 107, 14);

		panel_1_1.add(lblNewLabel_17_1);

		JLabel lblNewLabel_18 = new JLabel("");

		lblNewLabel_18.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/calendario.png")));

		lblNewLabel_18.setBounds(10, 36, 46, 14);

		panel_1_1.add(lblNewLabel_18);

		JPanel panel_1_2 = new JPanel();

		panel_1_2.setBorder(new LineBorder(new Color(0, 0, 0)));

		panel_1_2.setLayout(null);

		panel_1_2.setForeground(Color.BLACK);

		panel_1_2.setBackground(new Color(243, 243, 243));

		panel_1_2.setBounds(395, 77, 149, 56);

		panel.add(panel_1_2);

		JLabel lblNewLabel_14_1_2 = new JLabel("Disponibilidade");

		lblNewLabel_14_1_2.setForeground(Color.GRAY);

		lblNewLabel_14_1_2.setBounds(10, 11, 103, 14);

		panel_1_2.add(lblNewLabel_14_1_2);

		JLabel lblNewLabel_19 = new JLabel("");

		lblNewLabel_19.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/ex baixa.png")));

		lblNewLabel_19.setBounds(10, 31, 46, 14);

		panel_1_2.add(lblNewLabel_19);

		JLabel lblNewLabel_4 = new JLabel("");

		lblNewLabel_4.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/caixa(1)1.png")));

		add(lblNewLabel_4, "cell 0 4,alignx center");

		JSeparator separator_1 = new JSeparator();

		separator_1.setOrientation(SwingConstants.VERTICAL);

		separator_1.setBackground(new Color(255, 255, 255));

		separator_1.setForeground(new Color(0, 0, 0));

		add(separator_1, "cell 3 0 1 15,gapx 10 10,growy");

		JLabel lblNewLabel_5 = new JLabel("Controle de Estoque");

		add(lblNewLabel_5, "cell 1 4");

		JLabel lblNewLabel_6 = new JLabel("");

		lblNewLabel_6.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/grafico.png")));

		add(lblNewLabel_6, "cell 0 5,alignx center");

		JLabel lblNewLabel_7 = new JLabel("Estatísticas");

		add(lblNewLabel_7, "cell 1 5");

		JLabel lblNewLabel_8 = new JLabel("");

		lblNewLabel_8.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/entradaesaida(1)1.png")));

		add(lblNewLabel_8, "cell 0 6,alignx center");

		JLabel lblNewLabel_9 = new JLabel("Entrada e Saída");

		add(lblNewLabel_9, "cell 1 6");

		JPanel panel_2 = new JPanel();

		add(panel_2, "cell 4 8 2 3,grow");

		JPanel panel_2_1 = new JPanel();

		add(panel_2_1, "cell 4 11 2 3,grow");

		JLabel lblNewLabel_10 = new JLabel("");

		lblNewLabel_10.setIcon(new ImageIcon(TelaDetalhesProduto.class.getResource("/img/LOGO1.png")));

		add(lblNewLabel_10, "cell 0 14");

	}
}