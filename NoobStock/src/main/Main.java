package main;

import javax.swing.SwingUtilities;
import controller.LoginController;
import controller.UsuarioController;
import controller.Navegador;
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
                
                // Navegador utilitário
                Navegador navegador = new Navegador(principal);

                // Inicializa os Controllers passando as views contidas em Principal
                new LoginController(principal.getLogin(), usuarioDAO, navegador);
                new UsuarioController(principal.getCadastro(), usuarioDAO, navegador);
                
                // Exibe a janela principal (que começa na TelaLogin por padrão)
                principal.setVisible(true);
                principal.setLocationRelativeTo(null);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
