package main;

import javax.swing.SwingUtilities;

import controller.DetalhesController;
import controller.EstoqueController;
import controller.FornecedorController;
import controller.InicioController;
import controller.LoginController;
import controller.UsuarioController;
import controller.RedefinirSenhaController;
import controller.SaidaController;
import controller.Navegador;
import controller.PerfilController;
import controller.ProdutoController;
import model.CategoriaDAO;
import model.FornecedorDAO;
import model.ProdutoDAO;
import model.UsuarioDAO;
import view.Principal;

public class Main {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			try {
				// Janela principal 
				Principal principal = new Principal();

				// DAOs
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				ProdutoDAO produtoDAO = new ProdutoDAO();
				FornecedorDAO fornecedorDAO = new FornecedorDAO();
				CategoriaDAO categoriaDAO = new CategoriaDAO();
				
				categoriaDAO.inicializarCategoriasPadrao();

				// Navegador 
				Navegador navegador = new Navegador(principal);

                // Controllers
				new LoginController(principal.getLogin(), usuarioDAO, navegador);
				new UsuarioController(principal.getCadastro(), usuarioDAO, navegador);
				new ProdutoController(principal.getAdicionar(), produtoDAO, navegador, principal.getControle());
				new EstoqueController(principal.getControle(), navegador, produtoDAO, principal.getAdicionar(),principal.getTelaDetalhesProduto());
				new DetalhesController(principal.getTelaDetalhesProduto(), principal.getControle(), navegador);
				new InicioController(principal.getInicio(), navegador);
				new PerfilController(principal.getPerfil(), usuarioDAO, navegador);
				new FornecedorController(principal.getFornecedor(), navegador, fornecedorDAO,principal.getAdicionarFor());
				new RedefinirSenhaController(principal.getRedefinirSenha(), usuarioDAO, navegador);
				new SaidaController(principal.getTelaSaida(), navegador);

				// Inicialição
				principal.mostrarTela(Principal.INICIO);
				principal.setVisible(true);
				principal.setLocationRelativeTo(null);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
