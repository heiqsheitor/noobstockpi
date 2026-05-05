package controller;

import java.util.List;

import model.Fornecedor;
import model.FornecedorDAO;
import view.Principal;
import view.TelaDeInicio;
import view.TelaFornecedor;

public class FornecedorController {
	private TelaFornecedor view;
    private Navegador navegador;
    private FornecedorDAO dao;

    public FornecedorController(TelaFornecedor view, Navegador navegador,FornecedorDAO dao) {
        this.view = view;
        this.navegador = navegador;
        this.dao = dao;
        
        

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
    public boolean salvarFornecedor(String nome, String cnpj, String contato) {
        if (nome.isEmpty() || cnpj.isEmpty() || contato.isEmpty()) {
            return false;
        }
        Fornecedor fornecedor = new Fornecedor(nome, cnpj, contato);
        return dao.adicionar(fornecedor);
    }

    public List<Fornecedor> buscarTodos() {
        return dao.listar();
    }
}
