package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

public class TelaLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tFUsuario;
	private JLabel lblLogo;
	private JLabel lblFacaLogin;
	private JLabel lblDigiteCredenciais;
	private JButton btnEntrar, btnCadastrar, btnEsqueciASenha;
	private BufferedImage imagemOriginal;
	private JPasswordField pFSenha;

	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 */
	public TelaLogin() throws IOException {
		setToolTipText("");
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[grow 30][grow 0][grow][grow 30]", "[grow 60][][grow 10][grow 30][grow 5][grow 5][grow 5][grow 15][grow 15][grow][]"));

		lblLogo = new JLabel("");
		imagemOriginal = ImageIO.read(getClass().getResource("/img/logopng.png"));
		lblLogo.setIcon(new ImageIcon(TelaLogin.class.getResource("/img/logopng.png")));
		Image scaled = imagemOriginal.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

		lblLogo.setIcon(new ImageIcon(scaled));

		// lblLogo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		add(lblLogo, "cell 2 0,alignx center,aligny top");

		lblFacaLogin = new JLabel("Faça seu Login");
		add(lblFacaLogin, "flowy,cell 2 2,alignx center,aligny bottom");
		lblFacaLogin.setFont(new Font("Tahoma", Font.BOLD, 11));

		lblDigiteCredenciais = new JLabel("Digite suas credenciais para entrar no aplicativo");
		add(lblDigiteCredenciais, "cell 2 3,alignx center,aligny top");

		JLabel lblUsuario = new JLabel("Usuário:");
		add(lblUsuario, "cell 1 4,alignx right,growy");

		tFUsuario = new JTextField();
		add(tFUsuario, "cell 2 4,growx");
		tFUsuario.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		add(lblSenha, "cell 1 6,alignx trailing,aligny top");
		
		pFSenha = new JPasswordField();
		add(pFSenha, "cell 2 6,growx,aligny top");

		btnEsqueciASenha = new JButton("Esqueci a senha");
		btnEsqueciASenha.setForeground(new Color(0, 0, 0));
		btnEsqueciASenha.setBackground(new Color(255, 255, 255));
		add(btnEsqueciASenha, "cell 2 7,alignx center,aligny bottom");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 2 8,grow");

		btnEntrar = new JButton("Entrar");
		btnEntrar.setBackground(new Color(255, 255, 255));
		btnEntrar.setForeground(new Color(0, 0, 0));
		panel.add(btnEntrar);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setForeground(new Color(0, 0, 0));
		btnCadastrar.setBackground(new Color(255, 255, 255));
		panel.add(btnCadastrar);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

	public String getUsuario() {
		return tFUsuario.getText().trim();
	}

	public String getSenha() {
		return new String(pFSenha.getPassword());
	}

	public void mostrarMensagem(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public void adicionarListenerLogin(ActionListener listener) {
		btnEntrar.addActionListener(listener);
	}

	public void adicionarListenerCadastro(ActionListener Cadastro) {
		btnCadastrar.addActionListener(Cadastro);
	}
	
	public void adicionarListenerEsqueciSenha(ActionListener EsqueciSenha) {
		btnEsqueciASenha.addActionListener(EsqueciSenha);
	}

	public void ajustarFonte(int largura, int altura) {
		// Calcula a escala com base na mudança de tamanho da janela
		int tamAjuste = 8;
		// ajustarFonteContainer(largura, altura, lblLogo, tamAjuste);
		tamAjuste = 45;
		ajustarFonteContainer(largura, altura, lblFacaLogin, tamAjuste);
		tamAjuste = 70;
		ajustarFonteContainer(largura, altura, lblDigiteCredenciais, tamAjuste);
		redimensionarImagem(largura, altura);

	}

	private void redimensionarImagem(int largura, int altura) {
		largura /= 4;
		altura /= 4;
		System.out.println(largura);
		if (largura <= 0 || altura <= 0)
			return;

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
