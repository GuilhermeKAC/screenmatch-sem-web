package br.com.alura.screenmatch.exercicios.ex6;

import java.util.List;

public class Avaliacao<T> {
    private T item;
    private double nota;
    private String comentario;

    public Avaliacao(T item, double nota, String comentario) {
        this.item = item;
        this.nota = nota;
        this.comentario = comentario;
    }

    public T getItem() { return item; }
    public double getNota() { return nota; }
    public String getComentario() { return comentario; }

    public static <T> double calcularMedia(List<Avaliacao<T>> avaliacoes) {
        return avaliacoes.stream()
                .mapToDouble(Avaliacao::getNota)
                .average()
                .orElse(0);
    }

    @Override
    public String toString() {
        return "Avaliacao{item=" + item + ", nota=" + nota + ", comentario='" + comentario + "'}";
    }
}