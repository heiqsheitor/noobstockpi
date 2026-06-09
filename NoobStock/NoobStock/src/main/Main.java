package main;

import javax.swing.SwingUtilities;

import controller.DetalhesController;
import controller.EstoqueController;
import controller.FornecedorController;
import controller.InicioController;
import controller.LoginController;
import controller.Navegador;
import controller.PerfilController;
import controller.ProdutoController;
import controller.RedefinirSenhaController;
import controller.SaidaController;
import controller.UsuarioController;
import model.CategoriaDAO;
import model.FornecedorDAO;
import model.ProdutoDAO;
import model.SaidaDAO;
import model.UsuarioDAO;
import view.Principal;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                // ── Janela principal ──────────────────────────────────────────
                Principal principal = new Principal();

                // ── DAOs ──────────────────────────────────────────────────────
                UsuarioDAO    usuarioDAO    = new UsuarioDAO();
                ProdutoDAO    produtoDAO    = new ProdutoDAO();
                FornecedorDAO fornecedorDAO = new FornecedorDAO();
                CategoriaDAO  categoriaDAO  = new CategoriaDAO();
                SaidaDAO      saidaDAO      = new SaidaDAO();

                categoriaDAO.inicializarCategoriasPadrao();

                // ── Navegador ─────────────────────────────────────────────────
                Navegador navegador = new Navegador(principal);

                // ── Controllers ───────────────────────────────────────────────
                new LoginController(principal.getLogin(), usuarioDAO, navegador);
                new UsuarioController(principal.getCadastro(), usuarioDAO, navegador);

                new ProdutoController(
                    principal.getAdicionar(), produtoDAO, navegador,
                    principal.getControle()
                );

                // CORREÇÃO: saidaDAO agora é passado ao EstoqueController para
                //           carregar o histórico real na TelaDetalhesProduto.
                new EstoqueController(
                    principal.getControle(), navegador, produtoDAO,
                    principal.getAdicionar(), principal.getTelaDetalhesProduto(),
                    saidaDAO    // ← novo argumento
                );

                new DetalhesController(
                    principal.getTelaDetalhesProduto(),
                    principal.getControle(),
                    navegador
                );

                new InicioController(principal.getInicio(), navegador);
                new PerfilController(principal.getPerfil(), usuarioDAO, navegador);

                new FornecedorController(
                    principal.getFornecedor(), navegador, fornecedorDAO,
                    principal.getAdicionarFor()
                );

                new RedefinirSenhaController(principal.getRedefinirSenha(), usuarioDAO, navegador);
                new SaidaController(principal.getTelaSaida(), navegador, produtoDAO, saidaDAO);

                // ── Inicialização ─────────────────────────────────────────────
                // BUG CORRIGIDO: iniciava em SAIDA (tela de teste).
                // Correto: sempre começa no LOGIN.
                principal.mostrarTela(Principal.LOGIN);
                principal.setVisible(true);
                principal.setLocationRelativeTo(null);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
