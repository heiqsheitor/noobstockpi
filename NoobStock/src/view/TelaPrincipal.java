package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private TelaLogin Login;
	private TelaCadastro Cadastro;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 407);
		Cadastro = new TelaCadastro();
		Cadastro.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Cadastro);
		
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

		      		        
		        Cadastro.ajustarFonte(largura, altura);
		        repaint();
		        revalidate();
				
			}
}
