package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaRedefinirSenha extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPasswordField pFNovaSenha;
	private JPasswordField pFConfirmarNovaSenha;
	private JTextField tFEmail; // Adicionado campo de e-mail para saber qual senha alterar
	private JButton btnSalvar;

	/**
	 * Create the panel.
	 */
	public TelaRedefinirSenha() {
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[grow 80][grow 1][grow 1][grow 1][grow 80]", "[grow 5][grow 5][grow 20][grow 20][grow 5][grow 3][grow 2][grow 3][grow 3][grow 20][grow 3][grow 50][grow]"));
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaRedefinirSenha.class.getResource("/img/logopng.png")));
		add(lblLogo, "cell 1 0 3 1,alignx center,growy");
		
		JLabel lblRedefinicaoSenha = new JLabel("Redefinição de Senha");
		lblRedefinicaoSenha.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(lblRedefinicaoSenha, "cell 1 2 3 1,alignx center,aligny bottom");
		
		JLabel lblAvisoCrieSenha = new JLabel("Crie uma senha nova para acessar sua conta");
		lblAvisoCrieSenha.setForeground(new Color(128, 128, 128));
		add(lblAvisoCrieSenha, "cell 1 3 3 1,alignx center,aligny top");
		
		JLabel lblEmail = new JLabel("E-mail da conta:");
		add(lblEmail, "cell 1 5,alignx trailing");
		
		tFEmail = new JTextField();
		add(tFEmail, "cell 2 5,grow");
		
		JLabel lblNovaSenha = new JLabel("Nova senha:");
		add(lblNovaSenha, "cell 1 7,alignx trailing");
		
		pFNovaSenha = new JPasswordField();
		add(pFNovaSenha, "cell 2 7,grow");
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar senha nova:");
		add(lblConfirmarSenha, "cell 1 8,alignx trailing");
		
		pFConfirmarNovaSenha = new JPasswordField();
		add(pFConfirmarNovaSenha, "cell 2 8,grow");
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setForeground(new Color(0, 0, 0));
		btnSalvar.setBackground(new Color(255, 255, 255));
		add(btnSalvar, "cell 2 10,grow");

	}

	public String getEmail() {
		return tFEmail.getText().trim();
	}

	public String getNovaSenha() {
		return new String(pFNovaSenha.getPassword());
	}

	public String getConfirmarNovaSenha() {
		return new String(pFConfirmarNovaSenha.getPassword());
	}

	public void adicionarListenerSalvar(ActionListener listener) {
		btnSalvar.addActionListener(listener);
	}

	public void mostrarMensagem(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public void limparCampos() {
		tFEmail.setText("");
		pFNovaSenha.setText("");
		pFConfirmarNovaSenha.setText("");
	}
}
