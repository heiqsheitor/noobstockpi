package main;

import javax.swing.JFrame;

import controller.ProdutoController;
import model.ProdutoDAO;
import model.Usuario;
import model.UsuarioDAO;
import view.TelaAdicionarProduto;

public class Main {

public static void main(String[] args) {
        
        // 1. Instanciamos a tela, o DAO e o Controller (Isso você fez perfeitamente!)
        TelaAdicionarProduto telaAdicionar = new TelaAdicionarProduto();
        ProdutoDAO daoProduto = new ProdutoDAO();
        ProdutoController controllerProduto = new ProdutoController(telaAdicionar, daoProduto);
        
        // 2. Como a tela é um JPanel, precisamos de um JFrame provisório para testá-la
        JFrame janelaTeste = new JFrame("NoobStock - Teste Adicionar Produto");
        janelaTeste.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaTeste.setSize(800, 600); // Tamanho da janela
        
        // 3. Colocamos o seu painel dentro da janela e mandamos exibir
        janelaTeste.setContentPane(telaAdicionar);
        janelaTeste.setLocationRelativeTo(null); // Centraliza na tela
        janelaTeste.setVisible(true); 
    }
	}


