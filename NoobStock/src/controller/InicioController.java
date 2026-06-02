package controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.Principal;
import view.TelaDeInicio;

public class InicioController extends ComponentAdapter {
	private TelaDeInicio view;
    private Navegador navegador;

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
        
        view.setFornecedorAcao(() -> {
            navegador.navegarPara(Principal.FORNECEDOR);
        });
        
        view.setEntradaSaidaAcao(() -> {
        	navegador.navegarPara(Principal.SAIDA);
        });

       
    }
	

}
