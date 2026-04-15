package view;

import java.awt.EventQueue;
import java.io.IOException;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TelaLogin login;
	private TelaPerfil perfil;
	private TelaRedefinirSenha redfSenha;
	private TelaCadastro2 cadastro;
	private CardLayout cardLayout;
	private TelaAdicionarProduto addProduto;
	private TelaEntradaSaida entsai;

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
		
		// Correção: Inicializando o CardLayout e o contentPane principal
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		cardLayout = new CardLayout();
		contentPane.setLayout(cardLayout);
		setContentPane(contentPane);

		// Inicializando e adicionando a tela inicial ao gerenciador
		entsai = new TelaEntradaSaida();
		adicionarTela("EntradaSaida", entsai);
		
		mostrarTela("EntradaSaida");
	}

	public void adicionarTela(String nome, JPanel tela) {
		this.contentPane.add(tela, nome);
	}

	public void mostrarTela(String nome) {
		this.cardLayout.show(this.contentPane, nome);
		this.pack();
	}
}