package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/db_noobstock";
    private static final String USUARIO = "root";
    private static final String SENHA = "sua_senha";
    
    public void cadastrarUsuario(Usuario usuario) {
    	
    }

}
