package br.com.alura.screenmatch.exercicios.ex6;

public class Produto {
    private String nome;

    public Produto(String nome) { this.nome = nome; }
    public String getNome() { return nome; }

    @Override
    public String toString() { return "Produto{nome='" + nome + "'}"; }
}