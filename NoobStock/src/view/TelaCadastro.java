package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Container;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class TelaCadastro extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfUsuario;
	private JTextField tfEmail;
	private JPasswordField pfSenha;
	private JLabel lblLogo;
	private BufferedImage  imagemOriginal;
	private JLabel lblAbraConta;
	private JLabel lblDigiteCredenciais;
	private JLabel lblUsuario;
	private JButton btnCadastrar;
	

	/**
	 * Create the panel.
	 */
	public TelaCadastro() throws IOException{
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[grow 29][grow 0][grow 2][grow 3][grow 2][grow 29]", "[grow 40][grow 10][grow 3][grow 2][grow 1][grow 2][grow 1][grow 2][grow 2][grow 2][grow 35][grow 1]"));
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaCadastro.class.getResource("/img/logopng.png")));
		add(lblLogo, "cell 1 0 4 1,alignx center,aligny top");
		imagemOriginal = ImageIO.read(getClass().getResource("/img/logopng.png"));
        Image scaled = imagemOriginal.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        
        lblLogo.setIcon(new ImageIcon(scaled));
		
		lblAbraConta = new JLabel("Abra uma conta");
		lblAbraConta.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblAbraConta, "cell 1 1 4 1,alignx center,aligny bottom");
		
		JLabel lblDigiteCredenciais = new JLabel("Digite as credenciais e inscreva-se neste aplicativo");
		add(lblDigiteCredenciais, "cell 1 2 4 1,alignx center,aligny top");
		
		JLabel lblUsuario = new JLabel("Usuário:");
		add(lblUsuario, "cell 1 3,alignx right,aligny center");
		
		tfUsuario = new JTextField();
		add(tfUsuario, "cell 2 3 3 1,grow");
		tfUsuario.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		add(lblEmail, "cell 1 5,alignx right,aligny center");
		
		tfEmail = new JTextField();
		add(tfEmail, "cell 2 5 3 1,grow");
		tfEmail.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		add(lblSenha, "cell 1 7,alignx right,aligny center");
		
		pfSenha = new JPasswordField();
		add(pfSenha, "cell 2 7 3 1,grow");
		
		btnCadastrar = new JButton("Cadastrar-se");
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setBackground(new Color(0, 0, 0));
		add(btnCadastrar, "cell 3 9,grow");
		
		JLabel lblNewLabel = new JLabel("Ao continuar, você concorda com os nossos Termos de Serviço e Política de Privacidade");
		lblNewLabel.setForeground(new Color(192, 192, 192));
		add(lblNewLabel, "cell 1 11 4 1,alignx center,aligny bottom");
		
	}
	public void ajustarFonte(int largura, int altura) {
        //Calcula a escala com base na mudança de tamanho da janela
		int tamAjuste = 8;
		ajustarFonteContainer(largura, altura, lblLogo, tamAjuste);
		tamAjuste = 45;
		ajustarFonteContainer(largura, altura, lblAbraConta, tamAjuste);
		tamAjuste = 70;
		ajustarFonteContainer(largura, altura, lblDigiteCredenciais, tamAjuste);
		tamAjuste = 45;
		ajustarFonteContainer(largura, altura, lblUsuario, tamAjuste);
		redimensionarImagem(largura, altura);
		tamAjuste = 35;
		ajustarFonteContainer(largura, altura, btnCadastrar, tamAjuste);
		
	}
	private void redimensionarImagem(int largura, int altura) {
		   largura /=4;
	       altura /=4;
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
