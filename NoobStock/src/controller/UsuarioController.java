package controller;

import model.Usuario;
import model.UsuarioDAO;

public class UsuarioController {
	// Método que a View (Tela) vai chamar quando o usuário clicar em "Salvar"
        public void processarCadastro(String id_usuario,String nome,String email,String senha) {
        
        // 1. O Controller empacota os dados soltos da tela no Modelo (Cliente)
        Usuario UsuarioEmpacotado = new Usuario(id_usuario, nome, email, senha);

        // 2. O Controller chama o DAO e passa o pacote (AQUI ESTÁ O PARÂMETRO!)
        UsuarioDAO dao = new UsuarioDAO();
        dao.cadastrarUsuario(UsuarioEmpacotado); 
        
        // 3. Avisar a View que a operação terminou
        System.out.println("Cadastro processado pelo Controller com sucesso!");
    }
}
