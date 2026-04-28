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
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;

public class TelaControleEstoque extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private BufferedImage imagemOriginal;
	private JTextField textField;
	private JButton btnAdicionar;

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public TelaControleEstoque() throws IOException {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[][][][][grow 1][grow 1][][][][][]", "[][grow 1][][grow 1][grow 1][grow 1][grow][][]"));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/image8.png")));
		add(lblNewLabel, "cell 1 0");
		
		JLabel lblNewLabel_1 = new JLabel("Descubra");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblNewLabel_1, "cell 0 1");
		
		JLabel lblNewLabel_2 = new JLabel("Controle de Estoque");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblNewLabel_2, "cell 4 1");
		
		JLabel lblInicio = new JLabel("");
		lblInicio.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/home.png")));
		add(lblInicio, "cell 0 2,alignx center");
		
		JLabel lblNewLabel_3 = new JLabel("Início");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblNewLabel_3, "cell 1 2");
		
		textField = new JTextField();
		add(textField, "cell 4 2 2 1,growx");
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 6 2 5 1,grow");
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/filter (1).png")));
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/grid.png")));
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/calendar.png")));
		panel.add(lblNewLabel_6);
		
		JSeparator separator = new JSeparator();
		add(separator, "cell 2 0 1 9,gapx 10 10,growy");
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		
				JLabel lblcontroleEstoq = new JLabel("");
				lblcontroleEstoq.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/caixa(1)1.png")));
				add(lblcontroleEstoq, "cell 0 3,alignx left");
		
				JLabel lblEstatis = new JLabel("");
				lblEstatis.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/grafico.png")));
				add(lblEstatis, "cell 0 4,alignx center");

		// CORREÇÃO: Alterado de LOGO1.png para logopng.png que existe no projeto
		URL logoUrl = getClass().getResource("/img/logopng.png");
		if (logoUrl != null) {
			imagemOriginal = ImageIO.read(logoUrl);
			Image scaled = imagemOriginal.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			// Se precisar usar a imagem escalada em algum lugar, pode adicionar aqui
		}
                
                String[] columnNames = {"ID",  "Produto", "SKU", "Disponibilidade", "Quantidade"};
                Object[][] data = {
                {"0044831576", "Mouse", "ELEC-101-BK", "Baixa", "30"},
                };
				
				table = new JTable(data, columnNames);
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{"0044831576", "Mouse", "ELEC-101-BK", "Baixa", "30"},
					},
					new String[] {
						"ID", "Produto", "SKU", "Disponibilidade", "Quantidade"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, Object.class, Object.class, Object.class, Object.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				add(table, "cell 3 3 2 4,grow");
				
						JLabel lblEntraSai = new JLabel("");
						lblEntraSai.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/entradaesaida(1)1.png")));
						add(lblEntraSai, "cell 0 5,alignx center");
				
				JLabel lblNewLabel_7 = new JLabel("Estatísticas");
				lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
				add(lblNewLabel_7, "cell 1 4");
				
				JLabel lblNewLabel_8 = new JLabel("Entrada e Saída");
				lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
				add(lblNewLabel_8, "cell 1 5");
				
				JLabel lblNewLabel_9 = new JLabel("Controle de Estoque");
				lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
				add(lblNewLabel_9, "cell 1 3");
				
				btnAdicionar = new JButton("Adicionar");
				btnAdicionar.setBackground(new Color(0, 0, 0));
				btnAdicionar.setForeground(new Color(255, 255, 255));
				add(btnAdicionar, "cell 4 7,growx");
				
				JLabel lblLogo = new JLabel("");
				if (logoUrl != null) {
					lblLogo.setIcon(new ImageIcon(logoUrl));
				}
				add(lblLogo, "cell 0 8");

	}
	public void ajustarFonte(int largura, int altura) {
		// TODO Auto-generated method stub
		
	}
	
	public void adicionarProduto(ActionListener listener) {
		btnAdicionar.addActionListener(listener);
	}

}
