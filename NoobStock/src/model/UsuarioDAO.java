package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/db_noobstock";
    private static final String USUARIO = "root";
    private static final String SENHA = "admin";

    public void cadastrarUsuario(Usuario usuario) {
        // Ajustado para a tabela 'usuarios' e coluna 'nome_usuario'
        String sql = "INSERT INTO usuarios (nome_usuario, email, senha) VALUES (?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            // Como o ID é auto_increment, não precisamos enviar ele no INSERT
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());

            stmt.executeUpdate();
            System.out.println("Usuário " + usuario.getNome() + " cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public Usuario autenticar(Usuario usuario) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        Usuario usuarioLogado = null;

        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Criando o objeto com as colunas corretas do banco
                    usuarioLogado = new Usuario(
                        String.valueOf(rs.getInt("id_usuario")), // Converte o ID int para String se mantiver seu modelo
                        rs.getString("nome_usuario"),
                        rs.getString("email"),
                        rs.getString("senha")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro na autenticação: " + e.getMessage());
        }
        return usuarioLogado;
    }
}