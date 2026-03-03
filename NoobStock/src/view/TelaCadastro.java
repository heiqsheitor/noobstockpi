package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class TelaCadastro extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tFUsuario;
	private JLabel lblLogo;
	private JLabel lblUsuario;
	private JLabel lblFacaCadastro;
	private JLabel lblDigiteCredenciais;
	private BufferedImage  imagemOriginal;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private Container btnNewButton;

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public TelaCadastro() throws IOException {
		setToolTipText("");
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[grow][][][][grow][grow][]", "[grow 60][][][grow 10][grow 3][grow 3][grow 3][grow 50][][][][grow 10][][]"));
		
		lblLogo = new JLabel("");
		imagemOriginal = ImageIO.read(getClass().getResource("/img/logopng.png"));
		lblLogo.setIcon(new ImageIcon(TelaCadastro.class.getResource("/img/logopng.png")));
        Image scaled = imagemOriginal.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        lblLogo.setIcon(new ImageIcon(scaled));

		//lblLogo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		add(lblLogo, "cell 3 0 2 1,alignx center,aligny top");
		
		lblFacaCadastro = new JLabel("Abra uma conta");
		add(lblFacaCadastro, "flowy,cell 3 1 2 1,alignx center,aligny bottom");
		lblFacaCadastro.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblDigiteCredenciais = new JLabel("Digite suas credenciais e inscreva-se no aplicativo");
		add(lblDigiteCredenciais, "cell 3 2 2 1,alignx center,aligny top");
		
		JLabel lblUsuario = new JLabel("Usuário:");
		add(lblUsuario, "cell 3 4,alignx left,growy");
		
		tFUsuario = new JTextField();
		add(tFUsuario, "cell 4 4,grow");
		tFUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Email:");
		add(lblNewLabel, "cell 3 5,alignx left");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 4 5,grow");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Senha:");
		add(lblNewLabel_1, "cell 3 6,alignx trailing");
		
		passwordField = new JPasswordField();
		add(passwordField, "cell 4 6,grow");
		
		JButton btnNewButton = new JButton("Cadastrar");
		add(btnNewButton, "cell 3 9 2 2,alignx center,growy");
		
		JLabel lblTermos = new JLabel("Ao continuar, você concorda com os nossos termos e Serviços e Política de Privacidade");
		lblTermos.setForeground(new Color(192, 192, 192));
		lblTermos.setFont(new Font("Tahoma", Font.ITALIC, 11));
		add(lblTermos, "cell 4 13,alignx center");
		
		
		

	}

	public void ajustarFonte(int largura, int altura) {
        // Calcula a escala com base na mudança de tamanho da janela
		int tamAjuste = 8;
		//ajustarFonteContainer(largura, altura, lblLogo, tamAjuste);
		tamAjuste = 25;
		ajustarFonteContainer(largura, altura, lblFacaCadastro, tamAjuste);
		tamAjuste = 50;
		ajustarFonteContainer(largura, altura, lblDigiteCredenciais, tamAjuste);
		tamAjuste = 45;
		ajustarFonteContainer(largura, altura, lblUsuario, tamAjuste);
		redimensionarImagem(largura, altura);
		tamAjuste = 35;
		ajustarFonteContainer(largura, altura, btnNewButton, tamAjuste);
		
		
				
		
	}
	private void redimensionarImagem(int largura, int altura) {
		 largura /=8;
	       altura /=8;
System.out.println(largura);
	        if (largura <= 0 || altura <= 0) return;

	        // Escala a imagem mantendo a proporção
	        Image scaled = imagemOriginal.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);

	        lblLogo.setIcon(new ImageIcon(scaled));
	}

	private void ajustarFonteContainer(int largura, int altura, Container lblLabel, int tamAjuste) {
		  int tamanhoFonte = Math.min(largura, altura) / tamAjuste;
	        tamanhoFonte = Math.max(tamanhoFonte, 8);
	        tamanhoFonte = Math.min(tamanhoFonte, 72);
	        
	      
	        // Aplica a nova fonte
	        lblLabel.setFont(lblLabel.getFont().deriveFont((float) tamanhoFonte));
		
	}

}
