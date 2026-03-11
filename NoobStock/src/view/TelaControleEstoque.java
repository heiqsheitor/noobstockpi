package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Image;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JInternalFrame;
import java.awt.Panel;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Scrollbar;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.CardLayout;
import javax.swing.JList;

public class TelaControleEstoque extends JPanel {

	private static final long serialVersionUID = 1L;
	private TelaLogin login;
	private BufferedImage imagemOriginal;
	
	    public TelaControleEstoque() throws IOException {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[][][grow][][][][][][]", "[][grow 1][grow 1][grow 1][grow 1][grow 1][grow 50][][]"));
				
						JLabel lblPerfil = new JLabel("");
						lblPerfil.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/image8.png")));
						add(lblPerfil, "cell 0 0,alignx center,aligny center");
		
				JLabel lblNewLabel_1 = new JLabel("Descubra");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
				add(lblNewLabel_1, "cell 0 1");
				
				JLabel lblNewLabel_2 = new JLabel("Controle de Estoque");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
				add(lblNewLabel_2, "cell 2 1");
		
				JLabel lblInicio = new JLabel("");
				lblInicio.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/home.png")));
				add(lblInicio, "flowx,cell 0 2,alignx left");
				
				
				
				JLabel lblNewLabel_3 = new JLabel("");
				lblNewLabel_3.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/filter (1).png")));
				add(lblNewLabel_3, "cell 3 2");
				
				JLabel lblNewLabel_4 = new JLabel("Filtrar");
				lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel_4.setForeground(new Color(192, 192, 192));
				add(lblNewLabel_4, "flowy,cell 4 2");
				
				JButton btnNewButton = new JButton("Adicionar");
				add(btnNewButton, "cell 5 2");
				
				JPanel panel = new JPanel();
				panel.setBackground(new Color(255, 255, 255));
				add(panel, "cell 6 2,grow");
				
				JLabel lblNewLabel_5 = new JLabel("");
				lblNewLabel_5.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/grid.png")));
				panel.add(lblNewLabel_5);
				
				JLabel lblNewLabel_6 = new JLabel("");
				lblNewLabel_6.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/calendar.png")));
				panel.add(lblNewLabel_6);
		
				JLabel lblcontroleEstoq = new JLabel("");
				lblcontroleEstoq.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/caixa(1)1.png")));
				add(lblcontroleEstoq, "flowx,cell 0 3,alignx left");
				
				JSeparator separator = new JSeparator();
				add(separator, "cell 1 0 1 9");
				separator.setOrientation(SwingConstants.VERTICAL);
				separator.setForeground(Color.BLACK);
		
				JLabel lblEstatis = new JLabel("");
				lblEstatis.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/grafico.png")));
				add(lblEstatis, "flowx,cell 0 4,alignx left");
		
				JLabel lblEntraSai = new JLabel("");
				lblEntraSai.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/entradaesaida(1)1.png")));
				add(lblEntraSai, "flowx,cell 0 5,alignx left");
		        imagemOriginal = ImageIO.read(getClass().getResource("/img/LOGO1.png"));
                Image scaled = imagemOriginal.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		
				JLabel lblLogo = new JLabel("");
				lblLogo.setIcon(new ImageIcon(TelaLogin.class.getResource("/img/LOGO1.png")));
				lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblLogo.setIcon(new ImageIcon(TelaControleEstoque.class.getResource("/img/LOGO1.png")));
				add(lblLogo, "cell 0 8,growx");
				
						JLabel LInicio = new JLabel("Inicio");
						LInicio.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {

							}
						});
						LInicio.setFont(new Font("Tahoma", Font.PLAIN, 13));
						add(LInicio, "cell 0 2,alignx left");
						
								JLabel LControleEstoq = new JLabel("Controle de estoque");
								LControleEstoq.setFont(new Font("Tahoma", Font.PLAIN, 13));
								add(LControleEstoq, "cell 0 3,alignx left");
								
										JLabel LEstatis = new JLabel("Estatísticas");
										LEstatis.setFont(new Font("Tahoma", Font.PLAIN, 13));
										add(LEstatis, "cell 0 4");
										
												JLabel LEntraSai = new JLabel("Entrada e saída");
												LEntraSai.setFont(new Font("Tahoma", Font.PLAIN, 13));
												add(LEntraSai, "cell 0 5");
				
//				// adicionando a barra com o jseparator
//				JSeparator separator = new JSeparator();
//				separator.setOrientation(SwingConstants.VERTICAL);
//				separator.setForeground(Color.BLACK);
												
												

	}

	private Component createRow(String string, String string2, String string3, String string4, String string5) {
		// TODO Auto-generated method stub
		return null;
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
