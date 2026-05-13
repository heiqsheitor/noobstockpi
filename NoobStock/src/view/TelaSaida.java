package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JTextField;

public class TelaSaida extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TelaSaida() {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[40px:n,grow 0][135px:n,grow 0][][20px:n][grow 7][grow 11][grow 1]", "[40px:n,grow 0][35px:n][35px:n][35px:n][35px:n][35px:n][grow 11][grow 11][grow 11][grow 11]"));
		
		JLabel lblPerfil = new JLabel("");
		lblPerfil.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/image8.png")));
		add(lblPerfil, "cell 0 0 2 1,alignx center,aligny center");
		
		JLabel lblNewLabel_5 = new JLabel("Saída de Estoque");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 26));
		add(lblNewLabel_5, "cell 4 0,aligny center");
		
		JLabel lblNewLabel_4 = new JLabel("Descubra");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblNewLabel_4, "cell 0 1 2 1");
		
		JLabel lblInicio = new JLabel("");
		lblInicio.setForeground(new Color(255, 255, 255));
		lblInicio.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/home.png")));
		add(lblInicio, "cell 0 2,alignx center");
		
		JLabel lblNewLabel = new JLabel("Início");
		add(lblNewLabel, "cell 1 2");
		
		JLabel lblControleEstoq = new JLabel("");
		lblControleEstoq.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/caixa(1)1.png")));
		add(lblControleEstoq, "cell 0 3,alignx left");
		
		JLabel lblNewLabel_1 = new JLabel("Controle de Estoque");
		add(lblNewLabel_1, "cell 1 3");
		
		JLabel lblEstatis = new JLabel("");
		lblEstatis.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/grafico.png")));
		add(lblEstatis, "cell 0 4,alignx center");
		
		JLabel lblNewLabel_2 = new JLabel("Estatísticas");
		add(lblNewLabel_2, "cell 1 4");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(0, 0, 0));
		add(separator, "cell 2 0 1 10,gapx 2 2,growy");
		
		JLabel lblEstraSai = new JLabel("");
		lblEstraSai.setIcon(new ImageIcon(TelaSaida.class.getResource("/img/entradaesaida(1)1.png")));
		add(lblEstraSai, "cell 0 5,alignx center");
		
		JLabel lblNewLabel_3 = new JLabel("Entrada e Saída");
		add(lblNewLabel_3, "cell 1 5");

		ImageIcon icon = new ImageIcon(TelaPerfil.class.getResource("/img/logopng.png"));
		Image img = icon.getImage();

		Image imgRedimensionada = img.getScaledInstance(70, 35, Image.SCALE_SMOOTH);
		
		JLabel lblLogo = new JLabel("");
		
				lblLogo.setIcon(new ImageIcon(imgRedimensionada));
				lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 16));
				add(lblLogo, "cell 0 9 2 1,alignx center,aligny bottom");
	}

}