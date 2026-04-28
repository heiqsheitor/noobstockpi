package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class TelaControleEstoque extends JPanel {

	private static final long serialVersionUID = 1L;
	private TelaLogin login;
	private BufferedImage imagemOriginal;
	private JTable table;
	private JTextField txtPesquisar;

	public TelaControleEstoque() throws IOException {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[40px:n:40px,grow 0][135px:n:135px,grow 0][][][grow 12][grow 10][grow 4][grow 4][grow 4][grow 2]", "[40px:n:40px,grow 0][35px:n:35px][35px:n:35px][35px:n:35px][35px:n:35px][grow 1][grow][]"));

		JLabel lblPerfil = new JLabel("");
		lblPerfil.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/image8.png")));
		add(lblPerfil, "cell 0 0 2 1,alignx center,aligny center");

		JLabel lblNewLabel_1 = new JLabel("Descubra");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblNewLabel_1, "cell 0 1 2 1");

		JLabel lblNewLabel_2 = new JLabel("Controle de Estoque");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblNewLabel_2, "cell 4 1 2 1");

		JLabel lblInicio = new JLabel("");
		lblInicio.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/home.png")));
		add(lblInicio, "cell 0 2,alignx center");

		txtPesquisar = new JTextField(){
		    @Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        
		        if (getText().isEmpty()) {
		            Graphics2D g2 = (Graphics2D) g.create();
		            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		            
		            g2.setColor(new Color(192, 192, 192));
		            g2.setFont(getFont());
		            
		            FontMetrics fm = g2.getFontMetrics();
		            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
		            
		            g2.drawString("Buscar Itens...", getInsets().left, y);
		            g2.dispose();
		        }
		    }
		};
		add(txtPesquisar, "cell 4 2 3 1,grow");
		txtPesquisar.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("");
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/filter (1).png")));

		JLabel lblNewLabel_4 = new JLabel("Filtrar");
		panel_1.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setForeground(new Color(128, 128, 128));

		JButton btnNewButton = new JButton("Adicionar");
		add(btnNewButton, "cell 8 2,grow");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 7 2,grow");

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/calendar.png")));
		panel.add(lblNewLabel_6);

		JSeparator separator = new JSeparator();
		add(separator, "cell 2 0 1 9,gapx 2 2,growy");
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);

		JLabel lblcontroleEstoq = new JLabel("");
		lblcontroleEstoq.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/caixa(1)1.png")));
		add(lblcontroleEstoq, "cell 0 3,alignx left");
		
		JLabel lblNewLabel = new JLabel("");
		add(lblNewLabel, "flowx,cell 4 3");
		
		JLabel lblNewLabel_7 = new JLabel("SKU");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblNewLabel_7, "cell 5 3,alignx center,aligny center");
		
		JLabel lblNewLabel_7_1 = new JLabel("Disponibilidade");
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblNewLabel_7_1, "cell 6 3,alignx center,aligny center");
		
		JLabel lblNewLabel_7_1_1 = new JLabel("Quantidade");
		lblNewLabel_7_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblNewLabel_7_1_1, "cell 7 3,alignx center,aligny center");
		
		JLabel lblNewLabel_7_1_1_1 = new JLabel("Data");
		lblNewLabel_7_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblNewLabel_7_1_1_1, "cell 8 3,alignx center,aligny center");
		
		table = new JTable(data, columnNames);
		table.setModel(
					new DefaultTableModel(new Object[][] { { "0044831576", "Mouse", "ELEC-101-BK", "Baixa", "30" }, },
								new String[] { "ID", "Produto", "SKU", "Disponibilidade", "Quantidade" }) {
							Class[] columnTypes = new Class[] { String.class, Object.class, Object.class, Object.class,
									Object.class };

							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
						});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(table, "cell 4 4 5 3,grow");

		JLabel lblEstatis = new JLabel("");
		lblEstatis.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/grafico.png")));
		add(lblEstatis, "cell 0 4,alignx center");

		imagemOriginal = ImageIO.read(getClass().getResource("/img/LOGO1.png"));
		Image scaled = imagemOriginal.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

		String[] columnNames = { "ID", "Produto", "SKU", "Disponibilidade", "Quantidade" };
		Object[][] data = { { "0044831576", "Mouse", "ELEC-101-BK", "Baixa", "30" }, };

		JLabel lblEntraSai = new JLabel("");
		lblEntraSai.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/entradaesaida(1)1.png")));
		add(lblEntraSai, "cell 0 5,alignx center");

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaLogin.class.getResource("/img/LOGO1.png")));
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLogo.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/LOGO1.png")));
		add(lblLogo, "cell 1 7,growx");

		JLabel LInicio = new JLabel("Inicio");
		LInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		LInicio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LInicio, "cell 1 2,alignx left");

		JLabel LControleEstoq = new JLabel("Controle de estoque");
		LControleEstoq.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LControleEstoq, "cell 1 3,alignx left");

		JLabel LEstatis = new JLabel("Estatísticas");
		LEstatis.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LEstatis, "cell 1 4");

		JLabel LEntraSai = new JLabel("Entrada e saída");
		LEntraSai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LEntraSai, "cell 1 5");
		
		JLabel lblNewLabel_5 = new JLabel("Produto");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel_5, "cell 4 3,growx,aligny center");

	}

	private void redimensionarImagem(int largura, int altura) {
		largura /= 4;
		altura /= 4;
		System.out.println(largura);
		if (largura <= 0 || altura <= 0)
			return;

		// Escala a imagem mantendo a proporção
		Image scaled = imagemOriginal.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);

	}

	public void ajustarFonte(int largura, int altura) {
		// TODO Auto-generated method stub

	}
}
