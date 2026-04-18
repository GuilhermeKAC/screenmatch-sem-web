package br.com.alura.screenmatch.exercicios.ex6;

public class Servico {
    private String descricao;

    public Servico(String descricao) { this.descricao = descricao; }
    public String getDescricao() { return descricao; }

    @Override
    public String toString() { return "Servico{descricao='" + descricao + "'}"; }
}