package controller;

import view.Principal;
import view.TelaDeInicio;
import view.TelaFornecedor;

public class FornecedorController {
	private TelaFornecedor view;
    private Navegador navegador;

    public FornecedorController(TelaFornecedor view, Navegador navegador) {
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

}
}
