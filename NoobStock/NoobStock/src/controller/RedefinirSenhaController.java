package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.UsuarioDAO;
import view.TelaRedefinirSenha;

public class RedefinirSenhaController {

    private final TelaRedefinirSenha view;
    private final UsuarioDAO dao;
    private final Navegador navegador;

    public RedefinirSenhaController(TelaRedefinirSenha view, UsuarioDAO dao, Navegador navegador) {
        this.view = view;
        this.dao = dao;
        this.navegador = navegador;

        this.view.adicionarListenerSalvar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redefinirSenha();
            }
        });
    }

    private void redefinirSenha() {
        String email = view.getEmail();
        String novaSenha = view.getNovaSenha();
        String confirmarSenha = view.getConfirmarNovaSenha();

        // Validação de campos vazios
        if (email.isEmpty() || novaSenha.isEmpty() || confirmarSenha.isEmpty()) {
            view.mostrarMensagem("Favor digitar nos campos!");
            return;
        }

        // Validação de coincidência de senhas
        if (!novaSenha.equals(confirmarSenha)) {
            view.mostrarMensagem("As senhas não coincidem!");
            return;
        }

        // Atualização no banco de dados
        if (dao.atualizarSenha(email, novaSenha)) {
            view.mostrarMensagem("Senha alterada com sucesso!");
            view.limparCampos();
            
            // Volta para a tela de login após sucesso
            if (navegador != null) {
                navegador.navegarPara("LOGIN");
            }
        } else {
            view.mostrarMensagem("Erro: E-mail não encontrado ou falha no banco de dados.");
        }
    }
}
