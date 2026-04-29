package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.UsuarioDAO;
import view.TelaPerfil;
import view.TelaLogin; 

public class PerfilController {
	private final TelaPerfil view;
	private final UsuarioDAO model;
	
	public PerfilController(TelaPerfil view, UsuarioDAO model) {
		this.model = model;
		this.view = view;
		
		// Evento de deslogar
		this.view.adicionarDeslogar(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				deslogar();
			}
		});
		
		// Evento de excluir conta
		this.view.adicionarExcluirContaListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				excluirConta();
			}
		});
	}
	
	private void excluirConta() {
		// 1. Pega o email que o usuário digitou lá no campo da View
		String emailDigitado = view.getEmail();
		
		// 2. Verifica se o usuário deixou o campo vazio
		if (emailDigitado.isEmpty()) {
			JOptionPane.showMessageDialog(view, 
					"Por favor, digite seu email no campo 'Email' para confirmar a exclusão da conta.", 
					"Aviso", 
					JOptionPane.WARNING_MESSAGE);
			return; // Para a execução do método aqui
		}
		
		// 3. Pede confirmação ao usuário informando o email que será excluído
		int resposta = JOptionPane.showConfirmDialog(
				view, 
				"Tem certeza que deseja excluir permanentemente a conta vinculada ao email: " + emailDigitado + "?\nEsta ação não pode ser desfeita.", 
				"Confirmar Exclusão", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.WARNING_MESSAGE
		);

		// 4. Se ele disser sim, vai para o banco de dados
		if (resposta == JOptionPane.YES_OPTION) {
			try {
				// Chama o DAO passando o email que o usuário digitou
				boolean sucesso = model.excluirUsuario(emailDigitado);
				
				if (sucesso) {
					JOptionPane.showMessageDialog(view, "Conta excluída com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
					
					// Volta para a tela de login
					deslogar();
				} else {
					// Se o sucesso for 'false', significa que o banco não achou esse email
					JOptionPane.showMessageDialog(view, 
							"Não foi possível excluir a conta. Verifique se o email digitado está correto.", 
							"Erro", 
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(view, "Erro ao tentar excluir a conta.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void deslogar() {
		JFrame framePrincipal = (JFrame) SwingUtilities.getWindowAncestor(view);
		
		if (framePrincipal != null) {
			try {
				TelaLogin telaLogin = new TelaLogin();
				framePrincipal.setContentPane(telaLogin);
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