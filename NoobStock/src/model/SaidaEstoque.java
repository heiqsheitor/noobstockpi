package model;

import java.util.List;

public class SaidaEstoque {
	private int id;
	private String responsavel;
	private String observacao;
	private List<ItemSaida> itens;

	public SaidaEstoque(int id, String responsavel, String observacao, List<ItemSaida> itens) {
		super();
		this.id = id;
		this.responsavel = responsavel;
		this.observacao = observacao;
		this.itens = itens;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public String getObservacao() {
		return observacao;
	}

	public List<ItemSaida> getItens() {
		return itens;
	}

}
