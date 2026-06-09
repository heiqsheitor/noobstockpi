package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaRedefinirSenha extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfEmail;
	private JPasswordField pfNovaSenha;
	private JPasswordField pfConfirmarSenha;
	private JLabel lblLogo;
	private BufferedImage imagemOriginal;
	private JButton btnSalvar, btnCancelar;

	public TelaRedefinirSenha() throws IOException {
		setBackground(new Color(255, 255, 255));
		// PADRÃO: Centralizado com insets e gaps uniformes
		setLayout(new MigLayout("insets 20, gap 10px 15px", "[grow 30][grow 0][grow 2][grow 30]", "[grow 40][][][][][][][][][grow 35]"));
		
		lblLogo = new JLabel("");
		imagemOriginal = ImageIO.read(getClass().getResource("/img/logopng.png"));
		// PADRÃO: Logo de formulário em 120x120
		Image scaled = imagemOriginal.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		lblLogo.setIcon(new ImageIcon(scaled));
		add(lblLogo, "cell 1 0 2 1,alignx center,aligny top, gapy 20px 10px");
		
		JLabel lblTitulo = new JLabel("Redefinir Senha");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(lblTitulo, "cell 1 1 2 1,alignx center,aligny bottom");
		
		JLabel lblSubtitulo = new JLabel("Informe seu e-mail e a nova senha desejada");
		lblSubtitulo.setForeground(new Color(120, 120, 120));
		add(lblSubtitulo, "cell 1 2 2 1,alignx center,aligny top");
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblEmail, "cell 1 3,alignx right");
		
		tfEmail = new JTextField();
		add(tfEmail, "cell 2 3,growx, h 35!");
		
		JLabel lblNovaSenha = new JLabel("Nova Senha:");
		lblNovaSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblNovaSenha, "cell 1 5,alignx right");
		
		pfNovaSenha = new JPasswordField();
		add(pfNovaSenha, "cell 2 5,growx, h 35!");

		JLabel lblConfirmar = new JLabel("Confirmar Senha:");
		lblConfirmar.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblConfirmar, "cell 1 7,alignx right");
		
		pfConfirmarSenha = new JPasswordField();
		add(pfConfirmarSenha, "cell 2 7,growx, h 35!");

		JPanel panelBotoes = new JPanel(new MigLayout("insets 0, gap 15px", "[grow][grow]", "[]"));
		panelBotoes.setBackground(Color.WHITE);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(220, 220, 220));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelBotoes.add(btnCancelar, "cell 0 0, growx, h 35!");
		
		btnSalvar = new JButton("Salvar Nova Senha");
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.setBackground(Color.BLACK);
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelBotoes.add(btnSalvar, "cell 1 0, growx, h 35!");

		add(panelBotoes, "cell 2 8,growx, gapy 10px");
	}
	
	public String getEmail() {
		return tfEmail.getText().trim();
	}

	public String getNovaSenha() {
		return new String(pfNovaSenha.getPassword());
	}

	public String getConfirmarNovaSenha() {
		return new String(pfConfirmarSenha.getPassword());
	}

	public void adicionarListenerSalvar(ActionListener listener) {
		btnSalvar.addActionListener(listener);
	}

	public void mostrarMensagem(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	
	public void adicionarListenerCancelar(ActionListener listener) {
	    btnCancelar.addActionListener(listener);
	}

	public void limparCampos() {
		tfEmail.setText("");
		pfNovaSenha.setText("");
		pfConfirmarSenha.setText("");
	}
}