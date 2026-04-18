package br.com.alura.screenmatch.exercicios.ex6;

public class Assinatura {
    private String plano;

    public Assinatura(String plano) { this.plano = plano; }
    public String getPlano() { return plano; }

    @Override
    public String toString() { return "Assinatura{plano='" + plano + "'}"; }
}