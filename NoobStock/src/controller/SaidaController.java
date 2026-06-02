package controller;

import view.Principal;
import view.TelaSaida;

public class SaidaController {

	private TelaSaida view;
	private Navegador navegador;

	public SaidaController(TelaSaida view, Navegador navegador) {
		this.view = view;
		this.navegador = navegador;

		view.setPerfil(() -> {
			navegador.navegarPara(Principal.PERFIL);
		});

		view.setInicio(() -> {
			navegador.navegarPara(Principal.INICIO);
		});

		view.setEstoque(() -> {
			navegador.navegarPara(Principal.ESTOQUE);
		});

		view.setFornecedor(() -> {
			navegador.navegarPara(Principal.FORNECEDOR);
		});

	}
}