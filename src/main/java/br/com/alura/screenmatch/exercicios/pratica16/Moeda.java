package br.com.alura.screenmatch.exercicios.pratica16;

public enum Moeda {
    DOLAR(0.196), EURO(0.182), REAL(1.0);

    private final double taxaConversao;

    Moeda(double taxaConversao) {
        this.taxaConversao = taxaConversao;
    }

    public double converterPara(double valorEmReais) {
        return valorEmReais * taxaConversao;
    }
}
