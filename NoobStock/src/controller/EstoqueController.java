package controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.Principal;
import view.TelaControleEstoque;

public class EstoqueController extends ComponentAdapter {
	private TelaControleEstoque view;
    private Navegador navegador;

    public EstoqueController(TelaControleEstoque view, Navegador navegador) {
        this.view = view;
        this.navegador = navegador;

        view.setInicioAcao(() -> {
            navegador.navegarPara(Principal.INICIO);
        });
    
        
        view.setControleEstoqueAcao(() -> {
            navegador.navegarPara(Principal.ESTOQUE);
        });

        view.setAdicionar(() -> {
            navegador.navegarPara(Principal.ADICIONAR);
        });
        
        view.setPerfilAcao(() -> {
            navegador.navegarPara(Principal.PERFIL);
        });

       
    }
	

}
