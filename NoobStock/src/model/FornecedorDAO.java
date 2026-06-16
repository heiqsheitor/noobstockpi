package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/db_noobstock";
	private static final String USUARIO = "root";
	private static final String SENHA = "admin";

	private Connection conectar() throws SQLException {
		return DriverManager.getConnection(URL, USUARIO, SENHA);
	}

	public boolean adicionar(Fornecedor fornecedor) {
		String sql = "INSERT INTO fornecedor (nome, cnpj, contato, duracao_contrato) VALUES (?, ?, ?, ?)";

		try (Connection con = conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, fornecedor.getNome());
			stmt.setString(2, fornecedor.getCnpj());
			stmt.setString(3, fornecedor.getContato());
			stmt.setString(4, fornecedor.getDuracaoContrato());
			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.err.println("Erro ao cadastrar fornecedor: " + e.getMessage());
			return false;
		}
	}

	public boolean atualizar(Fornecedor fornecedor) {
		String sql = "UPDATE fornecedor SET nome = ?, cnpj = ?, contato = ?, duracao_contrato = ? "
				+ "WHERE idfornecedor = ?";

		try (Connection con = conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, fornecedor.getNome());
			stmt.setString(2, fornecedor.getCnpj());
			stmt.setString(3, fornecedor.getContato());
			stmt.setString(4, fornecedor.getDuracaoContrato());
			stmt.setInt(5, fornecedor.getIdfornecedor());

			int linhasAfetadas = stmt.executeUpdate();
			if (linhasAfetadas > 0) {
				System.out.println("Fornecedor '" + fornecedor.getNome() + "' atualizado com sucesso!");
				return true;
			} else {
				System.err.println("Nenhum fornecedor encontrado com ID: " + fornecedor.getIdfornecedor());
				return false;
			}

		} catch (SQLException e) {
			System.err.println("Erro ao atualizar fornecedor: " + e.getMessage());
			return false;
		}
	}

	public boolean deletar(int id) {
		String sql = "DELETE FROM fornecedor WHERE idfornecedor = ?";

		try (Connection con = conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, id);
			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.err.println("Erro ao deletar fornecedor: " + e.getMessage());
			return false;
		}
	}

	public List<Fornecedor> listar() {
		List<Fornecedor> lista = new ArrayList<>();
		String sql = "SELECT * FROM fornecedor";

		try (Connection con = conectar();
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Fornecedor f = new Fornecedor();
				f.setIdfornecedor(rs.getInt("idfornecedor"));
				f.setNome(rs.getString("nome"));
				f.setCnpj(rs.getString("cnpj"));
				f.setContato(rs.getString("contato"));
				f.setDuracaoContrato(rs.getString("duracao_contrato"));
				lista.add(f);
			}

		} catch (SQLException e) {
			System.err.println("Erro ao listar fornecedores: " + e.getMessage());
		}
		return lista;
	}
}