package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SaidaDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/db_noobstock";
	private static final String USUARIO = "root";
	private static final String SENHA = "admin";

	private Connection conectar() throws Exception {
		return DriverManager.getConnection(URL, USUARIO, SENHA);
	}

	public int registrarSaida(SaidaEstoque saida) {
		Connection con = null;
		try {
			con = conectar();
			con.setAutoCommit(false);
			String sqlSaida = "INSERT INTO saida_estoque (responsavel, observacao) VALUES (?, ?)";
			PreparedStatement stmtSaida = con.prepareStatement(sqlSaida, Statement.RETURN_GENERATED_KEYS);
			stmtSaida.setString(1, saida.getResponsavel());
			stmtSaida.setString(2, saida.getObservacao());
			stmtSaida.executeUpdate();

			ResultSet rsKeys = stmtSaida.getGeneratedKeys();
			int idSaida = -1;
			if (rsKeys.next())
				idSaida = rsKeys.getInt(1);
			if (idSaida == -1) {
				con.rollback();
				return -1;
			}
			String sqlItem = "INSERT INTO item_saida (saida_id, produto_id, quantidade) VALUES (?, ?, ?)";
			String sqlUpdate = "UPDATE produto SET qtdestoque = qtdestoque - ? WHERE idproduto = ?";

			PreparedStatement stmtItem = con.prepareStatement(sqlItem);
			PreparedStatement stmtUpdate = con.prepareStatement(sqlUpdate);

			for (ItemSaida item : saida.getItens()) {
				int idProduto = Integer.parseInt(item.getProduto().getId_produto());

				stmtItem.setInt(1, idSaida);
				stmtItem.setInt(2, idProduto);
				stmtItem.setInt(3, item.getQuantidade());
				stmtItem.executeUpdate();

				stmtUpdate.setInt(1, item.getQuantidade());
				stmtUpdate.setInt(2, idProduto);
				stmtUpdate.executeUpdate();
			}

			con.commit();
			return idSaida;

		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (con != null)
					con.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return -1;
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<HistoricoMovimentacao> buscarHistoricoPorProduto(int idProduto) {
		List<HistoricoMovimentacao> lista = new ArrayList<>();

		String sql = "SELECT DATE_FORMAT(se.datahora, '%d/%m/%Y %H:%i') AS datahora, " + "       it.quantidade, "
				+ "       se.responsavel, " + "       COALESCE(se.observacao, '') AS observacao "
				+ "FROM item_saida it " + "JOIN saida_estoque se ON it.saida_id = se.idsaida "
				+ "WHERE it.produto_id = ? " + "ORDER BY se.datahora DESC " + "LIMIT 10";

		try (Connection con = conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, idProduto);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					lista.add(new HistoricoMovimentacao(rs.getString("datahora"), rs.getInt("quantidade"),
							rs.getString("responsavel"), rs.getString("observacao")));
				}
			}

		} catch (Exception e) {
			System.err.println("Erro ao buscar histórico de saídas do produto " + idProduto + ": " + e.getMessage());
		}

		return lista;
	}
}
