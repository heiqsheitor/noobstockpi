package main;

import java.io.IOException;

import javax.swing.JFrame;

import controller.LoginController;
import controller.Navegador;
import controller.ProdutoController;
import model.ProdutoDAO;
import model.Usuario;
import model.UsuarioDAO;
import view.Principal;
import view.TelaAdicionarProduto;
import view.TelaCadastro2;
import view.TelaLogin;

public class Main {

	public static void main(String[] args) {

		Principal principal = new Principal();
		Navegador navegador = new Navegador(principal);

		TelaLogin telalogin;
		try {
			telalogin = new TelaLogin();
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			LoginController loginController = new LoginController(telalogin, usuarioDAO, null);
			
			TelaCadastro2 telaCadastro = new TelaCadastro2();
			
	
			TelaAdicionarProduto telaAdicionar = new TelaAdicionarProduto();
			ProdutoDAO daoProduto = new ProdutoDAO();
			ProdutoController controllerProduto = new ProdutoController(telaAdicionar, daoProduto);
	
	
			principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			principal.setSize(800, 600); // Tamanho da janela
	
			
			principal.setContentPane(telalogin);
			principal.setLocationRelativeTo(null); 
			principal.setVisible(true);
			
			navegador.adicionarPainel("LOGIN", telalogin);
			navegador.adicionarPainel("ADICIONAR_PRODUTOS", telaAdicionar);
			navegador.adicionarPainel("CADASTRO", telaCadastro);
			
			navegador.navegarPara("LOGIN");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
