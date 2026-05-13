package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SaidaDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/db_noobstock";
    private static final String USUARIO = "root";
    private static final String SENHA = "admin";

    private Connection conectar() throws Exception {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public boolean registrarSaida(SaidaEstoque saida) {
		Connection con = null;
        try {
            con = conectar();
            // Desativa o autocommit para iniciar a transação
            con.setAutoCommit(false); 

            // 1. Salva o cabeçalho da Saída
            String sqlSaida = "INSERT INTO saida_estoque (responsavel, observacao) VALUES (?, ?)";
            PreparedStatement stmtSaida = con.prepareStatement(sqlSaida, Statement.RETURN_GENERATED_KEYS);
            stmtSaida.setString(1, saida.getResponsavel());
            stmtSaida.setString(2, saida.getObservacao());
            stmtSaida.executeUpdate();

            // Recupera o ID gerado para a saída
            ResultSet rsKeys = stmtSaida.getGeneratedKeys();
            int idSaida = 0;
            if (rsKeys.next()) {
                idSaida = rsKeys.getInt(1);
            }

            // 2. Salva os Itens e Atualiza o Estoque
            String sqlItem = "INSERT INTO item_saida (saida_id, produto_id, quantidade) VALUES (?, ?, ?)";
            String sqlUpdateEstoque = "UPDATE produto SET qtdestoque = qtdestoque - ? WHERE idproduto = ?";
            
            PreparedStatement stmtItem = con.prepareStatement(sqlItem);
            PreparedStatement stmtUpdate = con.prepareStatement(sqlUpdateEstoque);

            for (ItemSaida item : saida.getItens()) {
                int idProduto = Integer.parseInt(item.getProduto().getId_produto());
                
                // Insere na tabela item_saida
                stmtItem.setInt(1, idSaida);
                stmtItem.setInt(2, idProduto);
                stmtItem.setInt(3, item.getQuantidade());
                stmtItem.executeUpdate();

                // Subtrai do estoque atual
                stmtUpdate.setInt(1, item.getQuantidade());
                stmtUpdate.setInt(2, idProduto);
                stmtUpdate.executeUpdate();
            }

            // Se chegou até aqui sem erros, confirma tudo no banco!
            con.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                // Em caso de qualquer erro, desfaz TUDO (rollback)
                if (con != null) con.rollback(); 
            } catch (Exception ex) { ex.printStackTrace(); }
            return false;
        } finally {
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }
}