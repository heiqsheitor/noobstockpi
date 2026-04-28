package main;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import controller.LoginController;
import controller.PerfilController;
import model.UsuarioDAO;
import view.TelaLogin;
import view.TelaPerfil;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("Sistema");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);

                // DAO
                UsuarioDAO usuarioDAO = new UsuarioDAO();

                // Telas
                TelaLogin telaLogin = new TelaLogin();
                TelaPerfil telaPerfil = new TelaPerfil();

                // Controllers
                LoginController loginController = new LoginController(telaLogin, usuarioDAO, null);
                PerfilController perfilController = new PerfilController(telaPerfil, usuarioDAO);

                // Tela inicial
                frame.setContentPane(telaLogin);
                frame.setVisible(true);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}