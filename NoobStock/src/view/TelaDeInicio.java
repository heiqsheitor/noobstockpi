package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;

public class TelaDeInicio extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage imagemOriginal;

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public TelaDeInicio() throws IOException {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[][grow 10][][][grow 3][][grow][]", "[][][grow 1][grow 1][grow 1][grow 1][grow 50][][]"));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaDeInicio.class.getResource("/img/image8.png")));
		add(lblNewLabel, "cell 1 0,alignx left,aligny center");
		
				JLabel lblNewLabel_1 = new JLabel("Descubra");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
				add(lblNewLabel_1, "cell 0 1");

		JLabel lblInicio = new JLabel("");
		lblInicio.setIcon(new ImageIcon(TelaDeInicio.class.getResource("/img/home.png")));
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
		lblcontroleEstoq.setIcon(new ImageIcon(TelaDeInicio.class.getResource("/img/caixa(1)1.png")));
		add(lblcontroleEstoq, "cell 0 3,alignx center");

		JLabel LControleEstoq = new JLabel("Controle de estoque");
		LControleEstoq.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LControleEstoq, "cell 1 3,alignx left");

		JLabel lblNewLabel_12 = new JLabel("Bem vindo(a), (Nome de usuário)");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblNewLabel_12, "cell 5 3");

		JLabel lblEstatis = new JLabel("");
		lblEstatis.setIcon(new ImageIcon(TelaDeInicio.class.getResource("/img/grafico.png")));
		add(lblEstatis, "cell 0 4,alignx center");

		JLabel LEstatis = new JLabel("Estatísticas");
		LEstatis.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LEstatis, "cell 1 4");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(255, 255, 255));
		separator.setForeground(new Color(0, 0, 0));
		add(separator, "cell 2 0 2 9, gapx 10 10, growy");

		JLabel lblEntraSai = new JLabel("");
		lblEntraSai.setIcon(new ImageIcon(TelaDeInicio.class.getResource("/img/entradaesaida(1)1.png")));
		add(lblEntraSai, "cell 0 5,alignx center");

		JLabel LEntraSai = new JLabel("Entrada e saída");
		LEntraSai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(LEntraSai, "cell 1 5");

		JLabel lblLogo = new JLabel("");
		// CORREÇÃO: Alterado de LOGO1.png para logopng.png
		URL logoUrl = getClass().getResource("/img/logopng.png");
		if (logoUrl != null) {
			imagemOriginal = ImageIO.read(logoUrl);
			lblLogo.setIcon(new ImageIcon(logoUrl));
			lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 16));
			add(lblLogo, "cell 0 8,growx");
		}
	}

	public void ajustarFonte(int largura, int altura) {
		// Implementação opcional
	}
}
