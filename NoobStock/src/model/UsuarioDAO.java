package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/db_noobstock";
    private static final String USUARIO = "root";
    private static final String SENHA = "sua_senha";
	private PreparedStatement stmt;
    
    public void cadastrarUsuario(Usuario usuario) {
    	// O Controller empacota os dados soltos da tela no Modelo (Cliente)
    	String sql = "INSERT INTO clientes (id_usuario, nome, email, senha) VALUES (?, ?, ?, ?)";;

    	// Try-with-resources: Garante que a conexão será fechada ao final
        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            // Trocando os '?' pelos dados reais do cliente
            stmt.setString(1, usuario.getId_usuario());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());

            // Executa o comando no banco de dados
            stmt.executeUpdate();
            
            System.out.println("Usuário " + usuario.getNome() + " cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar o Usuário no banco de dados: " + e.getMessage());
        }
    }
//    public Usuario autenticar(String email, String senha) {
//        String sql = "SELECT * FROM usuário WHERE email = ? AND senha = ?";
//        
//        // ... (abre a conexão e prepara o statement) ...
//        
//        stmt.setString(1, email);
//        stmt.setString(2, senha);
//        
//        ResultSet rs = stmt.executeQuery(); // Executa a busca
//        
//        if (rs.next()) {
//            // Encontrou! Pega os dados do banco e monta o objeto Cliente
//            Usuario UsuarioLogado = new Usuario(
//                rs.getString("nome_completo"), 
//                rs.getString("email"),
//                rs.getString("senha")
//            );
//            return UsuarioLogado; // Devolve o cliente para o Controller
//        }
//        
//        return null; // Não encontrou ninguém com essa senha/email
//    }
 
    }
