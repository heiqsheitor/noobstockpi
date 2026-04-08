package controller;

import javax.swing.JPanel;

import view.Principal;

public class Navegador {
	private Principal principal;
	
	public Navegador(Principal principal) {
		this.principal = principal;
	}
	
	public void adicionarPainel(String nome, JPanel tela) {
		this.principal.adicionarTela(nome, tela);
	}
	
	public void navegarPara(String nome) {
		this.principal.mostrarTela(nome);
	}
	
	public void sair() {
		this.principal.dispose();
	}
	
	

}
