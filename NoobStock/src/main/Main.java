package main;

import model.Usuario;
import model.UsuarioDAO;

public class Main {

	public static void main(String[] args) {
		// 1. Recebe os dados da tela e cria o objeto Cliente
        Usuario novoUsuario = new Usuario(null, null, null, null);

        // 2. Chama a classe DAO para salvar no banco
        UsuarioDAO dao = new UsuarioDAO();
        dao.cadastrarUsuario(novoUsuario);
    }
	}


