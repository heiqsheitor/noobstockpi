package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class TelaRedefinirSenha extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TelaRedefinirSenha() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBackground(new Color(255, 255, 255));
		lblLogo.setIcon(new ImageIcon(TelaRedefinirSenha.class.getResource("/img/logopng.png")));
		lblLogo.setBounds(468, 11, 518, 233);
		add(lblLogo);
		
		JLabel lblRedefinirSenha = new JLabel("Redefinição de senha");
		lblRedefinirSenha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRedefinirSenha.setBounds(451, 271, 185, 14);
		add(lblRedefinirSenha);
		
		JLabel lblInstrucao = new JLabel("Crie uma senha nova para acessar sua conta");
		lblInstrucao.setBounds(421, 295, 223, 14);
		add(lblInstrucao);
		
		JLabel lblNewLabel = new JLabel("Nova senha");
		lblNewLabel.setBounds(138, 344, 69, 14);
		add(lblNewLabel);

	}
}
