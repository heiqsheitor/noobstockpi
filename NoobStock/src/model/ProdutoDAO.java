package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/db_noobstock";
	private static final String USUARIO = "root";
	private static final String SENHA = "admin";

	private Connection conectar() throws SQLException {
		return DriverManager.getConnection(URL, USUARIO, SENHA);
	}

	// ── CADASTRAR ─────────────────────────────────────────────────────────────
	// ── CADASTRAR ─────────────────────────────────────────────────────────────
	public boolean cadastrarProduto(Produto produto) {
	    // SQL ajustado para incluir a coluna 'preco'
	    String sql = "INSERT INTO produto (nome, SKU, numeroserie, qtdestoque, estoque_minimo, localizacao, preco, fornecedor_id, categoria_id) "
	            + "VALUES (?, ?, ?, ?, ?, ?, ?, " + "(SELECT idfornecedor FROM fornecedor WHERE nome = ? LIMIT 1), "
	            + "(SELECT idcategoria FROM categoria WHERE nome = ? LIMIT 1))";

	    try (Connection con = conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setString(1, produto.getNome());
	        stmt.setString(2, produto.getSKU());
	        stmt.setString(3, produto.getSKU()); 
	        stmt.setInt(4, Integer.parseInt(produto.getQtd()));
	        stmt.setInt(5, produto.getEstoqueMinimo());
	        stmt.setString(6, produto.getLocalização());
	        
	        // Novo parâmetro de preço (7)
	        stmt.setDouble(7, produto.getPreco()); 
	        
	        // Fornecedor e Categoria agora são os parâmetros 8 e 9
	        stmt.setString(8, produto.getFornecedor()); 
	        stmt.setString(9, produto.getCategoria()); 

	        stmt.executeUpdate();
	        System.out.println("Produto '" + produto.getNome() + "' cadastrado com sucesso!");
	        return true;

	    } catch (NumberFormatException e) {
	        System.err.println("Quantidade inválida: " + e.getMessage());
	        return false;
	    } catch (SQLException e) {
	        System.err.println("Erro ao cadastrar produto: " + e.getMessage());
	        return false;
	    }
	}

	// ── ATUALIZAR ─────────────────────────────────────────────────────────────
	public boolean atualizarProduto(Produto produto) {
	    // SQL ajustado para atualizar a coluna 'preco'
	    String sql = "UPDATE produto SET nome = ?, SKU = ?, qtdestoque = ?, localizacao = ?, preco = ?, "
	            + "    fornecedor_id = (SELECT idfornecedor FROM fornecedor WHERE nome = ? LIMIT 1), "
	            + "    categoria_id  = (SELECT idcategoria FROM categoria WHERE nome = ? LIMIT 1) "
	            + "WHERE idproduto = ?";

	    try (Connection con = conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setString(1, produto.getNome());
	        stmt.setString(2, produto.getSKU());

	        try {
	            stmt.setInt(3, Integer.parseInt(produto.getQtd()));
	        } catch (NumberFormatException e) {
	            stmt.setInt(3, 0);
	        }

	        stmt.setString(4, produto.getLocalização());
	        
	        // Inserção do preço no PreparedStatement (5)
	        stmt.setDouble(5, produto.getPreco());
	        
	        // Ajustando os índices seguintes (6, 7 e 8)
	        stmt.setString(6, produto.getFornecedor()); 
	        stmt.setString(7, produto.getCategoria()); 
	        stmt.setInt(8, Integer.parseInt(produto.getId_produto()));

	        int linhasAfetadas = stmt.executeUpdate();
	        if (linhasAfetadas > 0) {
	            System.out.println("Produto '" + produto.getNome() + "' atualizado com sucesso!");
	            return true;
	        } else {
	            System.err.println("Nenhum produto encontrado com ID: " + produto.getId_produto());
	            return false;
	        }

	    } catch (SQLException e) {
	        System.err.println("Erro ao atualizar produto: " + e.getMessage());
	        return false;
	    }
	}

	// ── BUSCAR POR ID ─────────────────────────────────────────────────────────
	public Produto buscarPorId(int id) {
		String sql = "SELECT p.idproduto, p.nome, p.SKU, p.qtdestoque, p.estoque_minimo, "
				+ "       p.localizacao, p.preco, "
				+ "       DATE_FORMAT(p.data_criacao, '%d/%m/%Y') AS data_formatada, "
				+ "       f.nome AS fornecedor, c.nome AS categoria " + "FROM produto p "
				+ "LEFT JOIN fornecedor f ON p.fornecedor_id = f.idfornecedor "
				+ "LEFT JOIN categoria  c ON p.categoria_id  = c.idcategoria " + "WHERE p.idproduto = ?";

		try (Connection con = conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Produto(String.valueOf(rs.getInt("idproduto")), rs.getString("SKU"),
							rs.getString("nome"), String.valueOf(rs.getInt("qtdestoque")), rs.getInt("estoque_minimo"),
							rs.getString("localizacao"), rs.getString("fornecedor"), rs.getString("categoria"),
							rs.getString("data_formatada"), rs.getDouble("preco"));
				}
			}

		} catch (SQLException e) {
			System.err.println("Erro ao buscar produto: " + e.getMessage());
		}
		return null;
	}

	public List<Produto> listarProdutos() {

		List<Produto> lista = new ArrayList<>();

		// SQL atualizado para buscar a data formatada

		String sql = "SELECT p.*, f.nome AS fornecedor, c.nome AS categoria, "

				+ "DATE_FORMAT(p.data_criacao, '%d/%m/%Y') as data_formatada "

				+ "FROM produto p "

				+ "LEFT JOIN fornecedor f ON p.fornecedor_id = f.idfornecedor "

				+ "LEFT JOIN categoria c ON p.categoria_id  = c.idcategoria";

		try (Connection con = conectar();

				PreparedStatement stmt = con.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {

				Produto p = new Produto(

						String.valueOf(rs.getInt("idproduto")),

						rs.getString("SKU"),

						rs.getString("nome"),

						String.valueOf(rs.getInt("qtdestoque")),

						rs.getInt("estoque_minimo"),

						rs.getString("localizacao"),

						rs.getString("fornecedor"),

						rs.getString("categoria"),

						rs.getString("data_formatada"), rs.getDouble("preco")

				);

				lista.add(p);

			}

		} catch (SQLException e) {

			System.err.println("Erro ao listar produtos: " + e.getMessage());

		}

		return lista;

	}

	// ── REGISTRAR ENTRADA ─────────────────────────────────────────────────────
	public boolean registrarEntrada(int idProduto, int quantidade, int idUsuario) {
		String sqlEstoque = "UPDATE produto SET qtdestoque = qtdestoque + ? WHERE idproduto = ?";
		String sqlMovimentacao = "INSERT INTO movimentacao (produto_id, usuario_id, tipo, quantidade) "
				+ "VALUES (?, ?, 'entrada', ?)";

		try (Connection con = conectar()) {
			con.setAutoCommit(false);

			try (PreparedStatement stmtE = con.prepareStatement(sqlEstoque);
					PreparedStatement stmtM = con.prepareStatement(sqlMovimentacao)) {

				stmtE.setInt(1, quantidade);
				stmtE.setInt(2, idProduto);
				stmtE.executeUpdate();

				stmtM.setInt(1, idProduto);
				stmtM.setInt(2, idUsuario);
				stmtM.setInt(3, quantidade);
				stmtM.executeUpdate();

				con.commit();
				return true;
			} catch (SQLException e) {
				con.rollback();
				System.err.println("Erro na entrada — rollback: " + e.getMessage());
				return false;
			}
		} catch (SQLException e) {
			System.err.println("Erro de conexão: " + e.getMessage());
			return false;
		}
	}

	// ── REGISTRAR SAÍDA ───────────────────────────────────────────────────────
	public boolean registrarSaida(int idProduto, int quantidade, int idUsuario) {
		String sqlVerifica = "SELECT qtdestoque FROM produto WHERE idproduto = ?";
		String sqlEstoque = "UPDATE produto SET qtdestoque = qtdestoque - ? WHERE idproduto = ?";
		String sqlMovimentacao = "INSERT INTO movimentacao (produto_id, usuario_id, tipo, quantidade) "
				+ "VALUES (?, ?, 'saida', ?)";

		try (Connection con = conectar()) {
			con.setAutoCommit(false);

			try (PreparedStatement stmtV = con.prepareStatement(sqlVerifica)) {
				stmtV.setInt(1, idProduto);
				ResultSet rs = stmtV.executeQuery();
				if (rs.next() && rs.getInt("qtdestoque") < quantidade) {
					System.err.println("Estoque insuficiente para saída.");
					con.rollback();
					return false;
				}
			}

			try (PreparedStatement stmtE = con.prepareStatement(sqlEstoque);
					PreparedStatement stmtM = con.prepareStatement(sqlMovimentacao)) {

				stmtE.setInt(1, quantidade);
				stmtE.setInt(2, idProduto);
				stmtE.executeUpdate();

				stmtM.setInt(1, idProduto);
				stmtM.setInt(2, idUsuario);
				stmtM.setInt(3, quantidade);
				stmtM.executeUpdate();

				con.commit();
				return true;
			} catch (SQLException e) {
				con.rollback();
				System.err.println("Erro na saída — rollback: " + e.getMessage());
				return false;
			}
		} catch (SQLException e) {
			System.err.println("Erro de conexão: " + e.getMessage());
			return false;
		}
	}

	// ── AJUSTE DE INVENTÁRIO ──────────────────────────────────────────────────
	public boolean ajustarEstoque(int idProduto, int novaQtd, String motivo, int idUsuario) {
		String sqlEstoque = "UPDATE produto SET qtdestoque = ? WHERE idproduto = ?";
		String sqlMovimentacao = "INSERT INTO movimentacao (produto_id, usuario_id, tipo, quantidade, motivo) "
				+ "VALUES (?, ?, 'ajuste', ?, ?)";

		try (Connection con = conectar()) {
			con.setAutoCommit(false);

			try (PreparedStatement stmtE = con.prepareStatement(sqlEstoque);
					PreparedStatement stmtM = con.prepareStatement(sqlMovimentacao)) {

				stmtE.setInt(1, novaQtd);
				stmtE.setInt(2, idProduto);
				stmtE.executeUpdate();

				stmtM.setInt(1, idProduto);
				stmtM.setInt(2, idUsuario);
				stmtM.setInt(3, novaQtd);
				stmtM.setString(4, motivo);
				stmtM.executeUpdate();

				con.commit();
				return true;
			} catch (SQLException e) {
				con.rollback();
				System.err.println("Erro no ajuste — rollback: " + e.getMessage());
				return false;
			}
		} catch (SQLException e) {
			System.err.println("Erro de conexão: " + e.getMessage());
			return false;
		}
	}

	// ── VERIFICAR ALERTAS DE ESTOQUE BAIXO ───────────────────────────────────
	public List<Produto> listarEstoqueBaixo() {
		List<Produto> lista = new ArrayList<>();
		String sql = "SELECT p.idproduto, p.nome, p.SKU, p.qtdestoque, p.estoque_minimo, "
				+ "       p.localizacao, p.preco, f.nome AS fornecedor, c.nome AS categoria " + "FROM produto p "
				+ "LEFT JOIN fornecedor f ON p.fornecedor_id = f.idfornecedor "
				+ "LEFT JOIN categoria  c ON p.categoria_id  = c.idcategoria "
				+ "WHERE p.qtdestoque <= p.estoque_minimo";

		try (Connection con = conectar();
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Produto p = new Produto(String.valueOf(rs.getInt("idproduto")), rs.getString("SKU"),
						rs.getString("nome"), String.valueOf(rs.getInt("qtdestoque")), rs.getInt("estoque_minimo"),
						rs.getString("localizacao"), rs.getString("fornecedor"), rs.getString("categoria"), null,
						rs.getDouble("preco"));
				lista.add(p);
			}

		} catch (SQLException e) {
			System.err.println("Erro ao verificar estoque baixo: " + e.getMessage());
		}
		return lista;
	}

	// ── DELETAR ───────────────────────────────────────────────────────────────
	/**
	 * BUG CORRIGIDO: antes executava um DELETE simples e falhava silenciosamente
	 * quando o produto possuía registros em item_saida (FK constraint).
	 * Agora exclui os itens de saída relacionados primeiro (dentro de transação)
	 * e só então remove o produto.
	 */
	public boolean deletarProduto(int id) {
		String sqlItens   = "DELETE FROM item_saida WHERE produto_id = ?";
		String sqlProduto = "DELETE FROM produto WHERE idproduto = ?";

		try (Connection con = conectar()) {
			con.setAutoCommit(false);

			try (PreparedStatement stmtItens   = con.prepareStatement(sqlItens);
			     PreparedStatement stmtProduto = con.prepareStatement(sqlProduto)) {

				// 1. Remove itens de saída vinculados
				stmtItens.setInt(1, id);
				stmtItens.executeUpdate();

				// 2. Remove o produto
				stmtProduto.setInt(1, id);
				stmtProduto.executeUpdate();

				con.commit();
				return true;

			} catch (SQLException e) {
				con.rollback();
				System.err.println("Erro ao deletar produto (rollback): " + e.getMessage());
				return false;
			}

		} catch (SQLException e) {
			System.err.println("Erro de conexão ao deletar produto: " + e.getMessage());
			return false;
		}
	}
}