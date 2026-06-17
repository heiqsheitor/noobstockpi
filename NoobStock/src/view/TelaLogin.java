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

import controller.ComponentUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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

	public TelaLogin() throws IOException {
		setToolTipText("");
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("insets 20, gap 10px 15px", "[grow 30][grow 0][grow][grow 30]",
				"[grow 60][][grow 10][][][][][][][grow][]"));

		lblLogo = new JLabel("");
		imagemOriginal = ImageIO.read(getClass().getResource("/img/noobstocklogo.png"));
		Image scaled = imagemOriginal.getScaledInstance(500, 250, Image.SCALE_SMOOTH);
		lblLogo.setIcon(new ImageIcon(scaled));
		add(lblLogo, "cell 2 0,alignx center,aligny top, gapy 20px 10px");

		lblFacaLogin = new JLabel("Faça seu Login");
		lblFacaLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(lblFacaLogin, "cell 2 2,alignx center,aligny bottom");

		lblDigiteCredenciais = new JLabel("Digite suas credenciais para entrar no aplicativo");
		lblDigiteCredenciais.setForeground(new Color(120, 120, 120));
		add(lblDigiteCredenciais, "cell 2 3,alignx center,aligny top");

		JLabel lblUsuario = new JLabel("Usuário:");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblUsuario, "cell 1 4,alignx right");

		tFUsuario = new JTextField();
		add(tFUsuario, "cell 2 4,growx, h 35!");
		tFUsuario.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblSenha, "cell 1 6,alignx right");

		pFSenha = new JPasswordField();
		add(pFSenha, "cell 2 6,growx, h 35!");

		btnEsqueciASenha = new JButton("Esqueci a senha");
		btnEsqueciASenha.setForeground(new Color(100, 100, 100));
		btnEsqueciASenha.setBackground(new Color(255, 255, 255));
		btnEsqueciASenha.setBorderPainted(false);
		btnEsqueciASenha.setContentAreaFilled(false);
		add(btnEsqueciASenha, "cell 2 7,alignx right");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(new MigLayout("insets 0, gap 15px", "[grow][grow]", "[]"));
		add(panel, "cell 2 8,growx");

		btnEntrar = new JButton("Entrar");
		btnEntrar.setBackground(new Color(0, 0, 0));
		btnEntrar.setForeground(new Color(255, 255, 255));
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnEntrar, "cell 0 0, growx, h 35!");

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBackground(new Color(220, 220, 220));
		btnCadastrar.setForeground(new Color(0, 0, 0));
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnCadastrar, "cell 1 0, growx, h 35!");
		
		ComponentUtils.associarTeclaEnter(this, btnEntrar);
	}

	public String getUsuario() {
		return tFUsuario.getText().trim();
	}

	public String getSenha() {
		return new String(pFSenha.getPassword());
	}

	public void mostrarMensagem(String titulo, String msg, String tipo) {
		TelaMensagem tm = new TelaMensagem(titulo, msg, tipo);
		tm.setVisible(true);
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
	}
}