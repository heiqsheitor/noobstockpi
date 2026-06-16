package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.UsuarioDAO;
import view.TelaMensagem;
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
            new TelaMensagem("Aviso", "Favor digitar nos campos!", "AVISO").setVisible(true);
            return;
        }

        // Validação de coincidência de senhas
        if (!novaSenha.equals(confirmarSenha)) {
            new TelaMensagem("Aviso", "As senhas não coincidem!", "AVISO").setVisible(true);
            return;
        }

        // Atualização no banco de dados
        if (dao.atualizarSenha(email, novaSenha)) {
            new TelaMensagem("Sucesso", "Senha alterada com sucesso!", "SUCESSO").setVisible(true);
            view.limparCampos();
            
            // Volta para a tela de login após sucesso
            if (navegador != null) {
                navegador.navegarPara("LOGIN");
            }
        } else {
            new TelaMensagem("Erro", "Erro: E-mail não encontrado ou falha no banco de dados.", "ERRO").setVisible(true);
        }
    }
}
