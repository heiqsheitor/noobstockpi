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

public class TelaLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tFUsuario;
	private JTextField textField;
	private JLabel lblLogo;
	private JLabel lblFacaLogin;
	private JLabel lblDigiteCredenciais;
	private BufferedImage  imagemOriginal;

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public TelaLogin() throws IOException {
		setToolTipText("");
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[grow 30][grow 2][grow 5][grow 30]", "[grow 60][][grow 10][grow 30][grow 5][grow 5][grow 5][grow 15][grow 15][grow][]"));
		
		lblLogo = new JLabel("");
		imagemOriginal = ImageIO.read(getClass().getResource("/img/logopng.png"));
		lblLogo.setIcon(new ImageIcon(TelaLogin.class.getResource("/img/logopng.png")));
        Image scaled = imagemOriginal.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        lblLogo.setIcon(new ImageIcon(scaled));

		//lblLogo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		add(lblLogo, "cell 2 0,alignx center,aligny top");
		
		lblFacaLogin = new JLabel("Faça seu Login");
		add(lblFacaLogin, "flowy,cell 2 2,alignx center,aligny bottom");
		lblFacaLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblDigiteCredenciais = new JLabel("Digite suas credenciais para entrar no aplicativo");
		add(lblDigiteCredenciais, "cell 2 3,alignx center,aligny top");
		
		JLabel lblUsuario = new JLabel("Usuário:");
		add(lblUsuario, "cell 1 4,alignx right,growy");
		
		tFUsuario = new JTextField();
		add(tFUsuario, "cell 2 4,grow");
		tFUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		add(lblSenha, "cell 1 6,alignx right,aligny top");
		
		textField = new JTextField();
		add(textField, "flowx,cell 2 6,grow");
		textField.setColumns(10);
		
		JButton btnEsqueciASenha = new JButton("Esqueci a senha");
		add(btnEsqueciASenha, "cell 2 7,alignx center,aligny bottom");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 2 8,grow");
		
		JButton btnEntrar = new JButton("Entrar");
		panel.add(btnEntrar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		panel.add(btnCadastrar);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblTermos = new JLabel("Ao continuar, você concorda com os nossos termos e Serviços e Política de Privacidade");
		lblTermos.setForeground(new Color(192, 192, 192));
		lblTermos.setFont(new Font("Tahoma", Font.ITALIC, 11));
		add(lblTermos, "cell 2 10,alignx center");
		
		
		

	}

	public void ajustarFonte(int largura, int altura) {
        // Calcula a escala com base na mudança de tamanho da janela
		int tamAjuste = 8;
		//ajustarFonteContainer(largura, altura, lblLogo, tamAjuste);
		tamAjuste = 45;
		ajustarFonteContainer(largura, altura, lblFacaLogin, tamAjuste);
		tamAjuste = 70;
		ajustarFonteContainer(largura, altura, lblDigiteCredenciais, tamAjuste);
		redimensionarImagem(largura, altura);
		
		
				
		
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
