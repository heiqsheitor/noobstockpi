package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SaidaDAO {

    private static final String URL     = "jdbc:mysql://localhost:3306/db_noobstock";
    private static final String USUARIO = "root";
    private static final String SENHA   = "admin";

    private Connection conectar() throws Exception {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    /**
     * Registra uma saída de estoque de forma transacional:
     *   1. Insere o cabeçalho em saida_estoque
     *   2. Para cada item: insere em item_saida e subtrai de produto.qtdestoque
     *
     * @return O ID gerado para a saída (> 0) em caso de sucesso,
     *         ou -1 em caso de falha (rollback é executado).
     */
    public int registrarSaida(SaidaEstoque saida) {
        Connection con = null;
        try {
            con = conectar();
            con.setAutoCommit(false);   // inicia a transação

            // ── 1. Insere o cabeçalho da saída ──────────────────────────────
            String sqlSaida = "INSERT INTO saida_estoque (responsavel, observacao) VALUES (?, ?)";
            PreparedStatement stmtSaida = con.prepareStatement(sqlSaida, Statement.RETURN_GENERATED_KEYS);
            stmtSaida.setString(1, saida.getResponsavel());
            stmtSaida.setString(2, saida.getObservacao());
            stmtSaida.executeUpdate();

            // Recupera o ID gerado pelo banco
            ResultSet rsKeys = stmtSaida.getGeneratedKeys();
            int idSaida = -1;
            if (rsKeys.next()) {
                idSaida = rsKeys.getInt(1);
            }
            if (idSaida == -1) {
                con.rollback();
                return -1;
            }

            // ── 2. Insere os itens e baixa o estoque ─────────────────────────
            String sqlItem          = "INSERT INTO item_saida (saida_id, produto_id, quantidade) VALUES (?, ?, ?)";
            String sqlUpdateEstoque = "UPDATE produto SET qtdestoque = qtdestoque - ? WHERE idproduto = ?";

            PreparedStatement stmtItem   = con.prepareStatement(sqlItem);
            PreparedStatement stmtUpdate = con.prepareStatement(sqlUpdateEstoque);

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

            con.commit();   // confirma tudo no banco
            return idSaida;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (con != null) con.rollback();    // desfaz em caso de erro
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return -1;
        } finally {
            try {
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
