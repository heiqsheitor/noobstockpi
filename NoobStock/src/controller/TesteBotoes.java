package controller;

import view.Principal;
import view.TelaControleEstoque;
import view.TelaDeInicio;
import view.TelaPerfil;

public class TesteBotoes {
	
	private TelaDeInicio inicio;
	private TelaControleEstoque estoque;
	private TelaPerfil perfil;
	private Navegador navegador;
	
	public TesteBotoes(TelaDeInicio inicio, TelaControleEstoque estoque, TelaPerfil perfil, Navegador navegador) {
		this.inicio = inicio;
		this.estoque = estoque;
		this.perfil = perfil;
		this.navegador = navegador;
		
		
		estoque.setInicioAcao(() -> {
            navegador.navegarPara(Principal.INICIO);
        });
		
		perfil.setInicioAcao(() -> {
            navegador.navegarPara(Principal.INICIO);
        });


    
        
		inicio.setControleEstoqueAcao(() -> {
            navegador.navegarPara(Principal.ESTOQUE);
        });
		
		perfil.setControleEstoqueAcao(() -> {
            navegador.navegarPara(Principal.ESTOQUE);
        });
		
		
		
		
		inicio.setPerfilAcao(() -> {
            navegador.navegarPara(Principal.PERFIL);
        });
		
		estoque.setPerfilAcao(() -> {
            navegador.navegarPara(Principal.PERFIL);
        });
		

      
		
		
		
        
	}

}
