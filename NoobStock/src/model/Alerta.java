package model;

import java.time.LocalDateTime;

public class Alerta {
	private String idAlerta;
	private String mensagem;
	private LocalDateTime dataHora;
	private String Status;

	public Alerta(String idAlerta, String mensagem, LocalDateTime dataHora, String status) {
		super();
		this.idAlerta = idAlerta;
		this.mensagem = mensagem;
		this.dataHora = dataHora;
		Status = status;
	}

	public String getIdAlerta() {
		return idAlerta;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public String getStatus() {
		return Status;
	}

}
