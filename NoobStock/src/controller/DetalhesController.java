package controller;

import java.awt.event.MouseAdapter;

import view.Principal;
import view.TelaControleEstoque;
import view.TelaDetalhesProduto;

public class DetalhesController extends MouseAdapter {

	private TelaDetalhesProduto detalhes;
	private TelaControleEstoque estoque;
	private Navegador nav;

	public DetalhesController(TelaDetalhesProduto detalhes, TelaControleEstoque estoque, Navegador nav) {
		this.detalhes = detalhes;
		this.estoque = estoque;
		this.nav = nav;
		
		detalhes.acaoVoltar(() -> {
			nav.navegarPara(Principal.ESTOQUE);
		});
	}

}
