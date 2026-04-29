package controller;

import view.TelaDeInicio;
import java.awt.event.ActionListener;

public class InicioController {
    
    private TelaDeInicio view;
    private final Navegador navegador;

    public InicioController(TelaDeInicio view, Navegador navegador) {
        this.view = view;
        this.navegador = navegador;

        // Avisa que o botão agora tem uma função
        configurarEventos();
    }

    private void configurarEventos() {
        // Conecta o método deslogar() ao botão da View
        view.addDeslogarListener(e -> deslogar());
    }

    private void deslogar() {
        System.out.println("Botão deslogar clicado!"); // Para teste no console
        if (navegador != null) {
            // "LOGIN" deve ser o nome que você deu para a tela de login no seu Navegador
            navegador.navegarPara("LOGIN"); 
        }
    }
}