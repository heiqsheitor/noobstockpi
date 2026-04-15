package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private static final String URL     = "jdbc:mysql://localhost:3306/db_noobstock";
    private static final String USUARIO = "root";
    private static final String SENHA   = "admin";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    // ── CADASTRAR ─────────────────────────────────────────────────────────────
    public boolean cadastrarProduto(Produto produto) {
    	String sql = "INSERT INTO produto (nome, numeroserie, qtdestoque, estoque_minimo, localizacao, fornecedor_id, categoria_id) "
    	           + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getSKU());
            stmt.setInt   (3, Integer.parseInt(produto.getQtd()));
            stmt.setInt   (4, produto.getEstoqueMinimo());
            stmt.setString(5, produto.getLocalização());
            stmt.setString(6, produto.getFornecedor());
            stmt.setString(7, produto.getCategoria());

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

    // ── LISTAR TODOS ──────────────────────────────────────────────────────────
    public List<Produto> listarProdutos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT p.idproduto, p.nome, p.SKU, p.qtdestoque, p.estoque_minimo, "
                   + "       p.localizacao, f.nome AS fornecedor, c.nome AS categoria "
                   + "FROM produto p "
                   + "LEFT JOIN fornecedor f ON p.fornecedor_id = f.idfornecedor "
                   + "LEFT JOIN categoria  c ON p.categoria_id  = c.idcategoria";

        try (Connection con = conectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto(
                    String.valueOf(rs.getInt("idproduto")),
                    rs.getString("SKU"),
                    rs.getString("nome"),
                    String.valueOf(rs.getInt("qtdestoque")),
                    rs.getString("localizacao"),
                    rs.getString("fornecedor"),
                    rs.getString("categoria")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        }
        return lista;
    }

    // ── BUSCAR POR ID ─────────────────────────────────────────────────────────
    public Produto buscarPorId(int id) {
        String sql = "SELECT p.idproduto, p.nome, p.SKU, p.qtdestoque, p.estoque_minimo, "
                   + "       p.localizacao, f.nome AS fornecedor, c.nome AS categoria "
                   + "FROM produto p "
                   + "LEFT JOIN fornecedor f ON p.fornecedor_id = f.idfornecedor "
                   + "LEFT JOIN categoria  c ON p.categoria_id  = c.idcategoria "
                   + "WHERE p.idproduto = ?";

        try (Connection con = conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Produto(
                        String.valueOf(rs.getInt("idproduto")),
                        rs.getString("SKU"),
                        rs.getString("nome"),
                        String.valueOf(rs.getInt("qtdestoque")),
                        rs.getString("localizacao"),
                        rs.getString("fornecedor"),
                        rs.getString("categoria")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto: " + e.getMessage());
        }
        return null;
    }

    // ── REGISTRAR ENTRADA ─────────────────────────────────────────────────────
    public boolean registrarEntrada(int idProduto, int quantidade, int idUsuario) {
        String sqlEstoque     = "UPDATE produto SET qtdestoque = qtdestoque + ? WHERE idproduto = ?";
        String sqlMovimentacao = "INSERT INTO movimentacao (produto_id, usuario_id, tipo, quantidade) "
                               + "VALUES (?, ?, 'entrada', ?)";

        try (Connection con = conectar()) {
            con.setAutoCommit(false); // transação

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
        String sqlVerifica    = "SELECT qtdestoque FROM produto WHERE idproduto = ?";
        String sqlEstoque     = "UPDATE produto SET qtdestoque = qtdestoque - ? WHERE idproduto = ?";
        String sqlMovimentacao = "INSERT INTO movimentacao (produto_id, usuario_id, tipo, quantidade) "
                               + "VALUES (?, ?, 'saida', ?)";

        try (Connection con = conectar()) {
            con.setAutoCommit(false);

            // Verifica se há estoque suficiente antes de baixar
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

    // ── AJUSTE DE INVENTÁRIO (RF13) ───────────────────────────────────────────
    public boolean ajustarEstoque(int idProduto, int novaQtd, String motivo, int idUsuario) {
        String sqlEstoque      = "UPDATE produto SET qtdestoque = ? WHERE idproduto = ?";
        String sqlMovimentacao = "INSERT INTO movimentacao (produto_id, usuario_id, tipo, quantidade, motivo) "
                               + "VALUES (?, ?, 'ajuste', ?, ?)";

        try (Connection con = conectar()) {
            con.setAutoCommit(false);

            try (PreparedStatement stmtE = con.prepareStatement(sqlEstoque);
                 PreparedStatement stmtM = con.prepareStatement(sqlMovimentacao)) {

                stmtE.setInt(1, novaQtd);
                stmtE.setInt(2, idProduto);
                stmtE.executeUpdate();

                stmtM.setInt   (1, idProduto);
                stmtM.setInt   (2, idUsuario);
                stmtM.setInt   (3, novaQtd);
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

    // ── VERIFICAR ALERTAS DE ESTOQUE BAIXO (RF12) ────────────────────────────
    public List<Produto> listarEstoqueBaixo() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT p.idproduto, p.nome, p.SKU, p.qtdestoque, p.estoque_minimo, "
                   + "       p.localizacao, f.nome AS fornecedor, c.nome AS categoria "
                   + "FROM produto p "
                   + "LEFT JOIN fornecedor f ON p.fornecedor_id = f.idfornecedor "
                   + "LEFT JOIN categoria  c ON p.categoria_id  = c.idcategoria "
                   + "WHERE p.qtdestoque <= p.estoque_minimo";

        try (Connection con = conectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto(
                    String.valueOf(rs.getInt("idproduto")),
                    rs.getString("SKU"),
                    rs.getString("nome"),
                    String.valueOf(rs.getInt("qtdestoque")),
                    rs.getString("localizacao"),
                    rs.getString("fornecedor"),
                    rs.getString("categoria")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao verificar estoque baixo: " + e.getMessage());
        }
        return lista;
    }

    // ── DELETAR ───────────────────────────────────────────────────────────────
    public boolean deletarProduto(int id) {
        String sql = "DELETE FROM produto WHERE idproduto = ?";

        try (Connection con = conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao deletar produto: " + e.getMessage());
            return false;
        }
    }
}
