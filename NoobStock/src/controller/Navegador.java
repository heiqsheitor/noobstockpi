package controller;

import model.Usuario;
import view.Principal;

public class Navegador {
	private Principal principal;
	private Usuario usuarioLogado; // Aqui fica guardada a "sessão"

	public Navegador(Principal principal) {
		this.principal = principal;
	}

	public void navegarPara(String tela) {
		principal.mostrarTela(tela);
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}