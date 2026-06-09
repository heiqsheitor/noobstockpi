package model;

public class HistoricoMovimentacao {

    private String datahora;   // já formatada: "dd/MM/yyyy HH:mm"
    private int    quantidade;
    private String responsavel;
    private String observacao;

    public HistoricoMovimentacao(String datahora, int quantidade,
                                  String responsavel, String observacao) {
        this.datahora   = datahora;
        this.quantidade = quantidade;
        this.responsavel = responsavel;
        this.observacao  = (observacao != null) ? observacao : "";
    }

    public String getDatahora()    { return datahora;   }
    public int    getQuantidade()  { return quantidade;  }
    public String getResponsavel() { return responsavel; }
    public String getObservacao()  { return observacao;  }
}
