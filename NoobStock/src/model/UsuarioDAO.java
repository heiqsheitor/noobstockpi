package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/db_noobstock";
	private static final String USUARIO = "root";
	private static final String SENHA = "aluno";

	public void cadastrarUsuario(Usuario usuario) {
		String sql = "insert into usuarios (nome_usuario, email, senha) values (?, ?, ?)";
		try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
				PreparedStatement stmt = conexao.prepareStatement(sql)) {

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
		String sql = "select * from usuarios where nome_usuario = ? and senha = ?";
		Usuario usuarioLogado = null;

		try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
				PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSenha());

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					usuarioLogado = new Usuario(String.valueOf(rs.getInt("id_usuario")), rs.getString("nome_usuario"),
							rs.getString("email"), rs.getString("senha"));
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro na autenticação: " + e.getMessage());
		}

		return usuarioLogado;
	}

	public boolean atualizarUsuario(String idUsuarioStr, String nome, String email, String senha) {
		String sql = "update usuarios set nome_usuario = ?, email = ?, senha = ? where id_usuario = ?";

		try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
				PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setString(1, nome);
			stmt.setString(2, email);
			stmt.setString(3, senha);
			stmt.setInt(4, Integer.parseInt(idUsuarioStr));

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException | NumberFormatException e) {
			System.err.println("Erro ao atualizar perfil do usuário: " + e.getMessage());
			return false;
		}
	}

	public boolean atualizarSenha(String email, String novaSenha) {
		String sql = "update usuarios set senha = ? where email = ?";

		try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
				PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setString(1, novaSenha);
			stmt.setString(2, email);

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			System.err.println("Erro ao atualizar senha: " + e.getMessage());
			return false;
		}
	}

	public boolean excluirUsuario(String email) {
		String sql = "delete from usuarios where email = ?";

		try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
				PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setString(1, email);

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			System.err.println("Erro ao excluir usuário: " + e.getMessage());
			return false;
		}
	}
}