package view;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.TelaCadastro2;
import view.TelaLogin;
import view.TelaPerfil;
import view.TelaRedefinirSenha;

import java.awt.CardLayout;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TelaLogin login;
	private TelaPerfil perfil;
	private TelaRedefinirSenha redfSenha;
	private TelaCadastro2 cadastro;
	private CardLayout cardLayout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.

	 */
	public Principal() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		cadastro = new TelaCadastro2();
		cadastro.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cadastro);
//		login.setLayout(new CardLayout(0, 0));

	}

	public void adicionarTela(String nome, JPanel tela) {
		this.contentPane.add(tela, nome);
	}

	public void mostrarTela(String nome) {
		this.cardLayout.show(this.contentPane, nome);
		this.pack();
		
	}
}