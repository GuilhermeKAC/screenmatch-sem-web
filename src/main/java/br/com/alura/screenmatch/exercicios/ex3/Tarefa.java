package br.com.alura.screenmatch.exercicios.ex3;

public class Tarefa {
    private String descricao;
    private boolean concluida;
    private String pessoaResponsavel;

    public Tarefa() {}

    public Tarefa(String descricao, boolean concluida, String pessoaResponsavel) {
        this.descricao = descricao;
        this.concluida = concluida;
        this.pessoaResponsavel = pessoaResponsavel;
    }

    public String getDescricao() { return descricao; }
    public boolean isConcluida() { return concluida; }
    public String getPessoaResponsavel() { return pessoaResponsavel; }

    @Override
    public String toString() {
        return "Tarefa{descricao='" + descricao + "', concluida=" + concluida + ", pessoaResponsavel='" + pessoaResponsavel + "'}";
    }
}