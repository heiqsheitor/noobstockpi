package controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import model.Usuario;
import view.Principal;
import view.TelaDeInicio;

public class InicioController {
    
    private TelaDeInicio view;
    private final Navegador navegador;

    public InicioController(TelaDeInicio view, Navegador navegador) {
        this.view = view;
        this.navegador = navegador;

        // Mantido: Suas navegações originais
        view.setInicioAcao(() -> {
            navegador.navegarPara(Principal.INICIO);
        });

        view.setControleEstoqueAcao(() -> {
            navegador.navegarPara(Principal.ESTOQUE);
        });

        view.setPerfilAcao(() -> {
            navegador.navegarPara(Principal.PERFIL);
        });
        
        // NOVO: Adiciona a verificação de quem logou toda vez que a tela abre
        configurarEventos();
    }

    private void configurarEventos() {
        // Escuta o momento exato em que a tela de Início aparece
        view.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                atualizarMensagemBoasVindas();
            }
        });
    }

    private void atualizarMensagemBoasVindas() {
        // Pega o usuário da sessão que o LoginController salvou
        Usuario logado = navegador.getUsuarioLogado();
        
        if (logado != null) {
            view.setNomeUsuario(logado.getNome()); // Joga o nome na tela
        }
    }
}