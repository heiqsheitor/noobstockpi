package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/db_noobstock";
    private static final String USUARIO = "root";
    private static final String SENHA = "admin";
    
    public void cadastrarProduto(Produto produto) {
        // Ajustado para a tabela 'usuarios' e coluna 'nome_usuario'
        String sql = "INSERT INTO Produto (nome, SKU, qtdestoque, localizacao, fornecedor_nome, categoria_nome) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            // Como o ID é auto_increment, não precisamos enviar ele no INSERT
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getSKU());
            stmt.setString(3, produto.getQtd());
            stmt.setString(3, produto.getLocalização());
            stmt.setString(3, produto.getFornecedor());
            stmt.setString(3, produto.getCategoria());

            stmt.executeUpdate();
            System.out.println("Produto " + produto.getNome() + " cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }

 
}