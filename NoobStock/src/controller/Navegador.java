package controller;

import javax.swing.JPanel;

import view.Principal;

public class Navegador {
	private Principal principal;
	
	
	public void adicionarPainel(String nome, JPanel tela) {
		this.principal.adicionarTela(nome, tela);
		}
	
	
	public Navegador(Principal principal) {
        this.principal = principal;
    }

    public void navegarPara(String tela) {
        principal.mostrarTela(tela);
    }
	
	public void sair() {
		this.principal.dispose();
	}
	
	

}
 