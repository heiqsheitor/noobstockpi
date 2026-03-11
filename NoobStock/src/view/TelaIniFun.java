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
import javax.swing.SwingConstants;

public class TelaIniFun extends JPanel {

	private static final long serialVersionUID = 1L;
	private TelaLogin login;
	private BufferedImage imagemOriginal;

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public TelaIniFun() throws IOException {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[][grow 10][][][grow 3][][grow][]", "[][][grow 1][grow 1][grow 1][grow 1][grow 50][][]"));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaIniFun.class.getResource("/img/image8.png")));
		add(lblNewLabel, "cell 1 0,alignx left,aligny center");
		
				JLabel lblNewLabel_1 = new JLabel("Descubra");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
				add(lblNewLabel_1, "cell 0 1");

		JLabel lblInicio = new JLabel("");
		lblInicio.setIcon(new ImageIcon(TelaIniFun.class.getResource("/img/home.png")));
		add(lblInicio, "cell 0 2,alignx center");

		JLabel LInicio = new JLabel("Inicio");
		LInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		LInicio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LInicio, "cell 1 2,alignx left");

		JLabel lblNewLabel_11 = new JLabel("Início");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblNewLabel_11, "cell 5 2");

		JLabel lblcontroleEstoq = new JLabel("");
		lblcontroleEstoq.setIcon(new ImageIcon(TelaIniFun.class.getResource("/img/caixa(1)1.png")));
		add(lblcontroleEstoq, "cell 0 3,alignx center");

		JLabel LControleEstoq = new JLabel("Controle de estoque");
		LControleEstoq.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LControleEstoq, "cell 1 3,alignx left");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(0, 0, 0));
		add(separator, "cell 3 0 1 9, gapx 10 10, growy");

		JLabel lblNewLabel_12 = new JLabel("Bem vindo(a), (Nome de usuário)");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblNewLabel_12, "cell 5 3");

		JLabel lblLogo = new JLabel("");
		imagemOriginal = ImageIO.read(getClass().getResource("/img/LOGO1.png"));
		lblLogo.setIcon(new ImageIcon(TelaLogin.class.getResource("/img/LOGO1.png")));
        Image scaled = imagemOriginal.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLogo.setIcon(new ImageIcon(TelaIniFun.class.getResource("/img/LOGO1.png")));
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
