package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SaidaDAO {

    private static final String URL     = "jdbc:mysql://localhost:3306/db_noobstock";
    private static final String USUARIO = "root";
    private static final String SENHA   = "admin";

    // BUG CORRIGIDO: conectar() agora passa as credenciais USUARIO e SENHA.
    // Antes: DriverManager.getConnection("jdbc:...") → ConnectException (sem auth).
    private Connection conectar() throws Exception {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    // ── REGISTRAR SAÍDA ───────────────────────────────────────────────────────
    /**
     * Registra uma saída de estoque de forma transacional:
     *   1. Insere o cabeçalho em saida_estoque
     *   2. Para cada item: insere em item_saida e subtrai de produto.qtdestoque
     *
     * @return ID gerado para a saída (> 0) em caso de sucesso, -1 em falha.
     */
    public int registrarSaida(SaidaEstoque saida) {
        Connection con = null;
        try {
            con = conectar();
            con.setAutoCommit(false);

            // 1. Cabeçalho
            String sqlSaida = "INSERT INTO saida_estoque (responsavel, observacao) VALUES (?, ?)";
            PreparedStatement stmtSaida = con.prepareStatement(sqlSaida, Statement.RETURN_GENERATED_KEYS);
            stmtSaida.setString(1, saida.getResponsavel());
            stmtSaida.setString(2, saida.getObservacao());
            stmtSaida.executeUpdate();

            ResultSet rsKeys = stmtSaida.getGeneratedKeys();
            int idSaida = -1;
            if (rsKeys.next()) idSaida = rsKeys.getInt(1);
            if (idSaida == -1) { con.rollback(); return -1; }

            // 2. Itens + baixa de estoque
            String sqlItem   = "INSERT INTO item_saida (saida_id, produto_id, quantidade) VALUES (?, ?, ?)";
            String sqlUpdate = "UPDATE produto SET qtdestoque = qtdestoque - ? WHERE idproduto = ?";

            PreparedStatement stmtItem   = con.prepareStatement(sqlItem);
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
            try { if (con != null) con.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
            return -1;
        } finally {
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    // ── HISTÓRICO POR PRODUTO ─────────────────────────────────────────────────
    /**
     * Retorna as últimas saídas que envolveram o produto informado,
     * buscando nas tabelas item_saida (alias: it) e saida_estoque (alias: se).
     * Resultado ordenado do mais recente para o mais antigo, limitado a 10 registros.
     *
     * @param idProduto ID do produto a pesquisar.
     * @return Lista de HistoricoMovimentacao, possivelmente vazia.
     */
    public List<HistoricoMovimentacao> buscarHistoricoPorProduto(int idProduto) {
        List<HistoricoMovimentacao> lista = new ArrayList<>();

        String sql =
            "SELECT DATE_FORMAT(se.datahora, '%d/%m/%Y %H:%i') AS datahora, " +
            "       it.quantidade, " +
            "       se.responsavel, " +
            "       COALESCE(se.observacao, '') AS observacao " +
            "FROM item_saida it " +
            "JOIN saida_estoque se ON it.saida_id = se.idsaida " +
            "WHERE it.produto_id = ? " +
            "ORDER BY se.datahora DESC " +
            "LIMIT 10";

        try (Connection con = conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new HistoricoMovimentacao(
                        rs.getString("datahora"),
                        rs.getInt("quantidade"),
                        rs.getString("responsavel"),
                        rs.getString("observacao")
                    ));
                }
            }

        } catch (Exception e) {
            System.err.println("Erro ao buscar histórico de saídas do produto " + idProduto + ": " + e.getMessage());
        }

        return lista;
    }
}
