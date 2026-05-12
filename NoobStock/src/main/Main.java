package main;

import javax.swing.SwingUtilities;

import controller.EstoqueController;
import controller.FornecedorController;
import controller.InicioController;
import controller.LoginController;
import controller.UsuarioController;
import controller.RedefinirSenhaController;
import controller.InicioController; // IMPORTANTE: Importe o seu novo controller aqui
import controller.Navegador;
import controller.PerfilController;
import controller.ProdutoController;
import model.FornecedorDAO;
import model.ProdutoDAO;
import model.UsuarioDAO;
import view.Principal;

public class Main {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			try {
				// Instancia a janela principal que contém o CardLayout e todas as telas
				Principal principal = new Principal();

				// DAOs
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				ProdutoDAO produtoDAO = new ProdutoDAO();
				FornecedorDAO fornecedorDAO = new FornecedorDAO();

				// Navegador utilitário
				Navegador navegador = new Navegador(principal);

				// Inicializa os Controllers
				new LoginController(principal.getLogin(), usuarioDAO, navegador);
				new UsuarioController(principal.getCadastro(), usuarioDAO, navegador);
				new ProdutoController(principal.getAdicionar(), produtoDAO, navegador);

				// EstoqueController agora recebe produtoDAO e telaAdicionar
				// para poder fazer edição e exclusão
				// referência
				// à
				// tela
				// de
				// adicionar/editar
				new EstoqueController(principal.getControle(), navegador, produtoDAO, principal.getAdicionar());

				new InicioController(principal.getInicio(), navegador);
				new PerfilController(principal.getPerfil(), usuarioDAO, navegador);
				new FornecedorController(principal.getFornecedor(), navegador, fornecedorDAO,
						principal.getAdicionarFor());
				new RedefinirSenhaController(principal.getRedefinirSenha(), usuarioDAO, navegador);

				principal.mostrarTela(Principal.INICIO);
				principal.setVisible(true);
				principal.setLocationRelativeTo(null);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}