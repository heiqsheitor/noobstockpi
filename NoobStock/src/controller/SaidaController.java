package controller;

import view.TelaSaida;

public class SaidaController {

    private TelaSaida telasaida;
    private Navegador navegador;

    public SaidaController(TelaSaida telasaida, Navegador navegador) {
        this.telasaida = telasaida;
        this.navegador = navegador;
    }
}