package main;

import javax.swing.SwingUtilities;

import controller.EstoqueController;
import controller.InicioController;
import controller.LoginController;
import controller.UsuarioController;
import controller.RedefinirSenhaController;
import controller.Navegador;
import controller.ProdutoController;
import model.ProdutoDAO;
import model.UsuarioDAO;
import view.Principal;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                // Instancia a janela principal que contém o CardLayout e todas as telas
                Principal principal = new Principal();
                
                // DAO
                UsuarioDAO usuarioDAO = new UsuarioDAO();
				ProdutoDAO produtoDAO = new ProdutoDAO();
                
                // Navegador utilitário
                Navegador navegador = new Navegador(principal);

                // Inicializa os Controllers passando as views contidas em Principal
                new LoginController(principal.getLogin(), usuarioDAO, navegador);
                new UsuarioController(principal.getCadastro(), usuarioDAO, navegador);
                new ProdutoController(principal.getAdicionar(), produtoDAO);
                new EstoqueController(principal.getControle(), navegador);
                new InicioController(principal.getInicio(), navegador);  
                new controller.PerfilController(principal.getPerfil(), usuarioDAO, navegador);                
                // CORREÇÃO: Inicializando o controller de redefinição de senha
                new RedefinirSenhaController(principal.getRedefinirSenha(), usuarioDAO, navegador);
                
             // Define qual tela será aberta por padrão usando as constantes da classe Principal
                
                
                // Exibe a janela principal (que começa na TelaLogin por padrão)
                principal.mostrarTela(Principal.LOGIN);
                principal.setVisible(true);
                principal.setLocationRelativeTo(null);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
