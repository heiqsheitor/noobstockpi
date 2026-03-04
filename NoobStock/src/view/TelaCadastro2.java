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

public class TelaCadastro2 extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tFUsuario;
	private JLabel lblLogo;
	private JLabel lblUsuario;
	private JLabel lblFacaCadastro;
	private JLabel lblDigiteCredenciais;
	private BufferedImage  imagemOriginal;
	private JTextField tFEmail;
	private JPasswordField pFSenhaNova;
	private Container btnNewButton;

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public TelaCadastro2() throws IOException {
		setToolTipText("");
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[grow][][][][grow][grow][]", "[grow 60][][][grow 10][grow 3][grow 3][grow 3][grow 50][][][][grow 10][][]"));
		
		lblLogo = new JLabel("");
		imagemOriginal = ImageIO.read(getClass().getResource("/img/logopng.png"));
		lblLogo.setIcon(new ImageIcon(TelaCadastro2.class.getResource("/img/logopng.png")));
        Image scaled = imagemOriginal.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        lblLogo.setIcon(new ImageIcon(scaled));

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
		
		JLabel lblEmail = new JLabel("Email:");
		add(lblEmail, "cell 3 5,alignx left");
		
		tFEmail = new JTextField();
		add(tFEmail, "cell 4 5,grow");
		tFEmail.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		add(lblSenha, "cell 3 6,alignx left");
		
		pFSenhaNova = new JPasswordField();
		add(pFSenhaNova, "cell 4 6,grow");
		
		JButton btnCadastrar = new JButton("Cadastrar");
		add(btnCadastrar, "cell 3 9 2 2,alignx center,growy");
		
		JLabel lblTermos = new JLabel("Ao continuar, você concorda com os nossos termos e Serviços e Política de Privacidade");
		lblTermos.setForeground(new Color(192, 192, 192));
		lblTermos.setFont(new Font("Tahoma", Font.ITALIC, 11));
		add(lblTermos, "cell 4 13,alignx center");
		
		
	}

	public void ajustarFonte(int largura, int altura) {
        // Calcula a escala com base na mudança de tamanho da janela
		int tamAjuste = 8;
		//ajustarFonteContainer(largura, altura, lblLogo, tamAjuste);
		tamAjuste = 45;
		ajustarFonteContainer(largura, altura, lblFacaCadastro, tamAjuste);
		tamAjuste = 70;
		ajustarFonteContainer(largura, altura, lblDigiteCredenciais, tamAjuste);
		tamAjuste = 45;
//		ajustarFonteContainer(largura, altura, lblUsuario, tamAjuste);
		redimensionarImagem(largura, altura);
//		tamAjuste = 35;
		ajustarFonteContainer(largura, altura, btnNewButton, tamAjuste);
		
		
				
		
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
