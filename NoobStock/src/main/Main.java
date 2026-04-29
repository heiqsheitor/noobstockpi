package main;

import javax.swing.SwingUtilities;
import controller.LoginController;
import controller.UsuarioController;
import controller.RedefinirSenhaController;
import controller.InicioController; // IMPORTANTE: Importe o seu novo controller aqui
import controller.Navegador;
import controller.ProdutoController;
import model.ProdutoDAO;
import model.UsuarioDAO;
import view.Principal;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                Principal principal = new Principal();
                
                UsuarioDAO usuarioDAO = new UsuarioDAO();          
                ProdutoDAO produtoDAO = new ProdutoDAO();
                
                Navegador navegador = new Navegador(principal);

                new LoginController(principal.getLogin(), usuarioDAO, navegador);
                new UsuarioController(principal.getCadastro(), usuarioDAO, navegador);
                new RedefinirSenhaController(principal.getRedefinirSenha(), usuarioDAO, navegador);
                new InicioController(principal.getInicio(), navegador);
                new ProdutoController(principal.getAdicionar(), produtoDAO);
                
                principal.setVisible(true);
                principal.setLocationRelativeTo(null);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}