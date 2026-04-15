package controller;

import javax.swing.JOptionPane;

import model.Usuario;
import model.UsuarioDAO;
import view.TelaCadastro;

public class UsuarioController {
	
	private final TelaCadastro view;
	private final UsuarioDAO model;
	private final Navegador navegador;
	
	public UsuarioController(TelaCadastro view, UsuarioDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;
	this.view.Cadastrar(e -> {
		String nome = view.getNome();
		String email = view.getEmail();
		String senha = view.getSenha();
		
		if(!nome.equals("") &&
				!email.equals("") &&
				!senha.equals("")) {
			Usuario u = new Usuario(nome, email, senha);
			this.model.cadastrarUsuario(u);
			
			this.view.limparFormulario();
			JOptionPane.showMessageDialog(null, "Candidato salvo");
			this.navegador.navegarPara("INIGESTOR");
		}else {
			JOptionPane.showMessageDialog(null, "Erro, Preencha todos os campos!");
		}
	});
}
//	// Método que a View (Tela) vai chamar quando o usuário clicar em "Salvar"
//        public void processarCadastro(String id_usuario,String nome,String email,String senha) {
//        
//        // 1. O Controller empacota os dados soltos da tela no Modelo (Cliente)
//        Usuario UsuarioEmpacotado = new Usuario(id_usuario, nome, email, senha);
//
//        // 2. O Controller chama o DAO e passa o pacote (AQUI ESTÁ O PARÂMETRO!)
//        UsuarioDAO dao = new UsuarioDAO();
//        dao.cadastrarUsuario(UsuarioEmpacotado); 
//        
//        // 3. Avisar a View que a operação terminou
//        System.out.println("Cadastro processado pelo Controller com sucesso!");
    //}
}
