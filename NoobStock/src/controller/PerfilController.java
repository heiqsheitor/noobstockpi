package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.UsuarioDAO;
import view.TelaPerfil;
import view.TelaLogin; // Importando a sua tela de login

public class PerfilController {
	private final TelaPerfil view;
	private final UsuarioDAO model;
	
	public PerfilController(TelaPerfil view, UsuarioDAO model) {
		this.model = model;
		this.view = view;
		  
		this.view.adicionarDeslogar(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				deslogar();
			}
		});
	}
	
	private void deslogar() {
		// Captura o JFrame principal que está exibindo a TelaPerfil no momento
		JFrame framePrincipal = (JFrame) SwingUtilities.getWindowAncestor(view);
		
		if (framePrincipal != null) {
			try {
				// Instancia a TelaLogin (exige try-catch por causa do ImageIO.read na View)
				TelaLogin telaLogin = new TelaLogin();
				
				// Substitui o conteúdo do JFrame (tira o Perfil e coloca o Login)
				framePrincipal.setContentPane(telaLogin);
				
				// Atualiza a janela para renderizar o novo painel
				framePrincipal.revalidate();
				framePrincipal.repaint();
				
			} catch (IOException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(view, 
						"Erro ao carregar a interface de login.", 
						"Erro", 
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}