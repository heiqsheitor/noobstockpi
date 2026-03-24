package model;

public class Estoque {
	private int Qtd;
	private Disponibilidade disponiblidade;

	public Estoque(int qtd, Disponibilidade disponiblidade) {
		super();
		Qtd = qtd;
		this.disponiblidade = disponiblidade;
	}

	public int getQtd() {
		return Qtd;
	}

	public Disponibilidade getDisponiblidade() {
		return disponiblidade;
	}

}
