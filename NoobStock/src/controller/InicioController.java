package controller;

import view.TelaDeInicio;
import java.awt.event.ActionListener;
import view.Principal;

public class InicioController {
    
    private TelaDeInicio view;
    private final Navegador navegador;

    public InicioController(TelaDeInicio view, Navegador navegador) {
        this.view = view;
        this.navegador = navegador;

        view.setInicioAcao(() -> {
            navegador.navegarPara(Principal.INICIO);
        });

     
        view.setControleEstoqueAcao(() -> {
            navegador.navegarPara(Principal.ESTOQUE);
        });

        view.setPerfilAcao(() -> {
            navegador.navegarPara(Principal.PERFIL);
        });
        
//        configurarEventos();
    }

//    private void configurarEventos() {
//        view.addDeslogarListener(e -> deslogar());
//    }

//    private void deslogar() {
//        System.out.println("Botão deslogar clicado!");
//        if (navegador != null) {
//            navegador.navegarPara("LOGIN"); 
//        }
//    }
    
    
}