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

public class TelaIniGestor extends JPanel {

	private static final long serialVersionUID = 1L;
	private TelaLogin login;
	private BufferedImage imagemOriginal;

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public TelaIniGestor() throws IOException {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[][grow 10][][grow 3][][grow][]",
				"[][][grow 1][grow 1][grow 1][grow 1][grow 50][][]"));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaIniGestor.class.getResource("/img/image8.png")));
		add(lblNewLabel, "cell 1 0,alignx left,aligny center");

		JLabel lblNewLabel_1 = new JLabel("Descubra");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblNewLabel_1, "cell 1 1");

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(TelaIniGestor.class.getResource("/img/home.png")));
		add(lblNewLabel_2, "cell 0 2,alignx center");

		JLabel lblNewLabel_3 = new JLabel("Inicio");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblNewLabel_3, "cell 1 2,alignx left");

		JLabel lblNewLabel_11 = new JLabel("Início");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblNewLabel_11, "cell 4 2");

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TelaIniGestor.class.getResource("/img/caixa(1)1.png")));
		add(lblNewLabel_4, "cell 0 3,alignx center");

		JLabel lblNewLabel_5 = new JLabel("Controle de estoque");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblNewLabel_5, "cell 1 3,alignx left");

		JLabel lblNewLabel_12 = new JLabel("Bem vindo(a), (Nome de usuário)");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblNewLabel_12, "cell 4 3");

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(TelaIniGestor.class.getResource("/img/grafico.png")));
		add(lblNewLabel_6, "cell 0 4,alignx center");

		JLabel lblNewLabel_7 = new JLabel("Estatísticas");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblNewLabel_7, "cell 1 4");

		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(TelaIniGestor.class.getResource("/img/entradaesaida(1)1.png")));
		add(lblNewLabel_8, "cell 0 5,alignx center");

		JLabel lblNewLabel_9 = new JLabel("Entrada e saída");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblNewLabel_9, "cell 1 5");

		JLabel lblLogo = new JLabel("");
		imagemOriginal = ImageIO.read(getClass().getResource("/img/LOGO1.png"));
		lblLogo.setIcon(new ImageIcon(TelaLogin.class.getResource("/img/LOGO1.png")));
        Image scaled = imagemOriginal.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLogo.setIcon(new ImageIcon(TelaIniGestor.class.getResource("/img/LOGO1.png")));
		add(lblLogo, "cell 0 8,growx");

	}
	private void redimensionarImagem(int largura, int altura) {
		 largura /=4;
	       altura /=4;
System.out.println(largura);
	        if (largura <= 0 || altura <= 0) return;

	        // Escala a imagem mantendo a proporção
	        Image scaled = imagemOriginal.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);

	     

}
	public void ajustarFonte(int largura, int altura) {
		// TODO Auto-generated method stub
		
	}}
