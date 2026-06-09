 package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/db_noobstock";
	private static final String USUARIO = "root";
	private static final String SENHA = "admin";

	// BUG CORRIGIDO: agora passa USUARIO e SENHA corretamente.
	private Connection conectar() throws SQLException {
		return DriverManager.getConnection(URL, USUARIO, SENHA);
	}

	public void inicializarCategoriasPadrao() {
		String sqlCheck = "SELECT COUNT(*) FROM categoria";
		String sqlInsert = "INSERT INTO categoria (nome) VALUES (?)";

		try (Connection con = conectar();
				PreparedStatement stmtCheck = con.prepareStatement(sqlCheck);
				ResultSet rs = stmtCheck.executeQuery()) {

			// Se o COUNT for 0, significa que a tabela está completamente vazia
			if (rs.next() && rs.getInt(1) == 0) {

				String[] categoriasPadrao = { "Processadores (CPUs)", "Placas-Mãe (Motherboards)",
						"Placas de Vídeo (GPUs)", "Memória RAM", "Armazenamento (SSDs e HDDs)",
						"Fontes de Alimentação (PSU)", "Refrigeração (Coolers/Ventoinhas)", "Gabinetes (Cases)",
						"Monitores", "Teclados", "Mouses e Mousepad", "Áudio", "Webcams",
						"Cabos e Adaptadores", "Equipamento de Rede (Wi-Fi)",
						"Energia (Filtros e UPS/Nobreaks)" };

				try (PreparedStatement stmtInsert = con.prepareStatement(sqlInsert)) {
					for (String nomeCategoria : categoriasPadrao) {
						stmtInsert.setString(1, nomeCategoria);
						stmtInsert.addBatch();
					}
					stmtInsert.executeBatch();
					System.out.println("[NoobStock] Categorias padrão inicializadas no banco de dados com sucesso!");
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao verificar ou preencher categorias padrão: " + e.getMessage());
		}
	}

	public java.util.List<String> listarCategorias() {
		java.util.List<String> lista = new java.util.ArrayList<>();
		String sql = "SELECT idcategoria, nome FROM categoria ORDER BY nome";

		try (Connection con = conectar();
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				// Formato idêntico ao do fornecedor: "ID - Nome"
				lista.add(rs.getInt("idcategoria") + " - " + rs.getString("nome"));
			}
		} catch (SQLException e) {
			System.err.println("Erro ao listar categorias: " + e.getMessage());
		}
		return lista;
	}
}
