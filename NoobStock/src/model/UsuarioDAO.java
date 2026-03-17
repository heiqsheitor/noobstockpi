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
public Usuario autenticar(Usuario usuario) {
        
        // Comando SQL buscando a combinação exata no banco
        String sql = "SELECT * FROM clientes WHERE email = ? AND senha = ?";
        
        // Começamos com o cliente nulo. Se a busca falhar, ele continua nulo.
        Usuario usuarioLogado = null;

        // Try-with-resources: Conecta e prepara o comando de forma segura
        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            // Substitui os '?' pelos dados que o Controller mandou
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());

            // O ResultSet é onde o Java guarda a "tabela" de resultados que o banco devolve
            try (ResultSet rs = stmt.executeQuery()) {
                
                // rs.next() move o cursor para a primeira linha. Se for true, achou alguém!
                if (rs.next()) {
                    
                    // Show de bola! Achou o usuário. Agora empacotamos os dados do banco no objeto
                    usuarioLogado = new Usuario(
                        rs.getString("nome_completo"),
                        rs.getString("Senha"),
                        rs.getString("email"), sql
                    );
                    
                    // OBS: Se você criou o atributo 'senha' dentro da classe Cliente, 
                    // não esqueça de adicionar ele no construtor aqui também!
                }
            }

        } catch (SQLException e) {
            // Se der algum pau na conexão ou no SQL, avisa aqui
            System.err.println("Eita, deu ruim na hora de consultar o banco: " + e.getMessage());
        }

        // Devolve o resultado para o Controller (o objeto cheio ou null)
        return usuarioLogado;
    }
 
    }
