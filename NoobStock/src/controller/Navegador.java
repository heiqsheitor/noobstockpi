package controller;

import javax.swing.JPanel;

import view.Principal;

public class Navegador {
	private Principal principal;

	public Navegador(Principal principal) {
		this.principal = principal;
	}

	public void navegarPara(String tela) {
		principal.mostrarTela(tela);
	}
}