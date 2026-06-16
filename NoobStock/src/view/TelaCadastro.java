package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JTextField;

import controller.ComponentUtils;

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
	private BufferedImage imagemOriginal;
	private JLabel lblAbraConta;
	private JLabel lblDigiteCredenciais;
	private JLabel lblUsuario;
	private JButton btnCadastrar;
	private JButton btnCancelar;

	public TelaCadastro() throws IOException {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("insets 20, gap 10px 15px", "[grow 29][grow 0][grow 2][grow 3][grow 2][grow 29]",
				"[grow 40][][][][][][][][][][grow 35][grow 1]"));

		lblLogo = new JLabel("");
		imagemOriginal = ImageIO.read(getClass().getResource("/img/noobstocklogo.png"));
		Image scaled = imagemOriginal.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		lblLogo.setIcon(new ImageIcon(scaled));
		add(lblLogo, "cell 1 0 4 1,alignx center,aligny top, gapy 20px 10px");

		lblAbraConta = new JLabel("Abra uma conta");
		lblAbraConta.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(lblAbraConta, "cell 1 1 4 1,alignx center,aligny bottom");

		lblDigiteCredenciais = new JLabel("Digite as credenciais e inscreva-se neste aplicativo");
		lblDigiteCredenciais.setForeground(new Color(120, 120, 120));
		add(lblDigiteCredenciais, "cell 1 2 4 1,alignx center,aligny top");

		lblUsuario = new JLabel("Usuário:");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblUsuario, "cell 1 3,alignx right");

		tfUsuario = new JTextField();
		add(tfUsuario, "cell 2 3 3 1,growx, h 35!");
		tfUsuario.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblEmail, "cell 1 5,alignx right");

		tfEmail = new JTextField();
		add(tfEmail, "cell 2 5 3 1,growx, h 35!");
		tfEmail.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblSenha, "cell 1 7,alignx right");

		pfSenha = new JPasswordField();
		add(pfSenha, "cell 2 7 3 1,growx, h 35!");

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(220, 220, 220));
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(btnCancelar, "cell 2 9,growx, h 35!");

		btnCadastrar = new JButton("Cadastrar-se");
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setBackground(new Color(0, 0, 0));
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(btnCadastrar, "cell 3 9,growx, h 35!");

		JLabel lblNewLabel = new JLabel("Ao continuar, você concorda com os nossos Termos de Serviço");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setForeground(new Color(150, 150, 150));
		add(lblNewLabel, "cell 1 11 4 1,alignx center,aligny bottom");
	}

	public void ajustarFonte(int largura, int altura) {
	}

	public String getNome() {
		return this.tfUsuario.getText();
	}

	public String getEmail() {
		return this.tfEmail.getText();
	}

	public String getSenha() {
		return new String(this.pfSenha.getPassword());
	}

	public void Cadastrar(ActionListener actionListener) {
		this.btnCadastrar.addActionListener(actionListener);
	}

	public void cancelar(ActionListener actionListener) {
		this.btnCancelar.addActionListener(actionListener);
	}

	public void limparFormulario() {
		this.tfUsuario.setText("");
		this.tfEmail.setText("");
		this.pfSenha.setText("");
	}

}