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

        this.view.inicioListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navegador.navegarPara(Principal.INICIO);
            }
        });
    
        
        this.view.setControleEstoqueListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               System.out.println("deyrtdfthfghfghf");
                navegador.navegarPara(Principal.ESTOQUE);
            }
        });
        
//        this.view.setEntradaSaidaListener(new MouseAdapter() {
//        	public void mouseClicked(MouseEvent e) {
//        		navegador.navegarPara(Principal.);
//        	}
//        	
//		
//        });

       
    }
	

}
