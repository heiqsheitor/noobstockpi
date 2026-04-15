package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.security.PublicKey;
import java.security.interfaces.DSAPublicKey;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class TelaJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private TelaLogin login;
	private TelaCadastro cadastro;
	private TelaRedefinirSenha RSenha;
	private TelaIniGestor gestor;
	private TelaControleEstoque Controle;
	public static final String LOGIN_PANEL = "Login";
	public static final String CADASTRO_PANEL = "Cadastro";
	private TelaEntradaSaida entsai;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaJFrame frame = new TelaJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public TelaJFrame() throws IOException {
		setTitle("NoobStock");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaJFrame.class.getResource("/img/logoNoobstock.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		setBounds(100, 100, 703, 407);
<<<<<<< HEAD
		Controle = new TelaControleEstoque();
		Controle.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Controle);
=======
		entsai = new TelaEntradaSaida();
		entsai.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(entsai);
>>>>>>> origin/branch2
		
		CardLayout cardLayout = new CardLayout();
		
		 JPanel contentPane = new JPanel(cardLayout);
		 
		 contentPane.setPreferredSize(new Dimension(816, 522));
		 
		 
		 contentPane.add(Login, LOGIN_PANEL);
		 contentPane.add(Cadastro, CADASTRO_PANEL);
		 setContentPane(contentPane);
		 
		
//		 public void mostrarTela(String panelName) {
//		        cardLayout.addLayoutComponent(contentPane, panelName);
//		    }
		 
//		 mostrarTela(LOGIN_PANEL);
		addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarFonte();
            }

			
        });

	}
	private void ajustarFonte() {
				   // Pega tamanho atual da janela
		        int largura = getWidth();
		        int altura = getHeight();

		      		        
		        gestor.ajustarFonte(largura, altura);
		        repaint();
		        revalidate();
				
			}
}
