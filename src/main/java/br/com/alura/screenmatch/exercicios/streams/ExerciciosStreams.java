package br.com.alura.screenmatch.exercicios.streams;

import java.util.*;
import java.util.stream.Collectors;

public class ExerciciosStreams {
    public static void main(String[] args) {

        // 1 - Maior número da lista
        List<Integer> numeros = Arrays.asList(10, 20, 30, 40, 50);
        int maior = numeros.stream()
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();
        System.out.println("1) Maior número: " + maior);

        // 2 - Agrupar palavras pelo tamanho
        List<String> palavras = Arrays.asList("java", "stream", "lambda", "code");
        Map<Integer, List<String>> porTamanho = palavras.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("2) Agrupadas por tamanho: " + porTamanho);

        // 3 - Concatenar nomes separados por vírgula
        List<String> nomes = Arrays.asList("Alice", "Bob", "Charlie");
        String concatenados = nomes.stream()
                .collect(Collectors.joining(", "));
        System.out.println("3) Nomes: " + concatenados);

        // 4 - Soma dos quadrados dos números pares
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);
        int somaQuadrados = nums.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(n -> n * n)
                .sum();
        System.out.println("4) Soma dos quadrados dos pares: " + somaQuadrados);

        // 5 - Separar pares de ímpares
        Map<Boolean, List<Integer>> particionados = nums.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("5) Pares: " + particionados.get(true));
        System.out.println("5) Ímpares: " + particionados.get(false));

        // Base dos exercícios 6-9
        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 800.0, "Eletrônicos"),
                new Produto("Notebook", 1500.0, "Eletrônicos"),
                new Produto("Teclado", 200.0, "Eletrônicos"),
                new Produto("Cadeira", 300.0, "Móveis"),
                new Produto("Monitor", 900.0, "Eletrônicos"),
                new Produto("Mesa", 700.0, "Móveis")
        );

        // 6 - Agrupar produtos por categoria
        Map<String, List<Produto>> porCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria));
        System.out.println("6) Por categoria: " + porCategoria);

        // 7 - Contar produtos por categoria
        Map<String, Long> contagemPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.counting()));
        System.out.println("7) Contagem por categoria: " + contagemPorCategoria);

        // 8 - Produto mais caro por categoria
        Map<String, Optional<Produto>> maisCaro = produtos.stream()
                .collect(Collectors.groupingBy(
                        Produto::getCategoria,
                        Collectors.maxBy(Comparator.comparingDouble(Produto::getPreco))
                ));
        System.out.println("8) Mais caro por categoria: " + maisCaro);

        // 9 - Total de preços por categoria
        Map<String, Double> totalPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(
                        Produto::getCategoria,
                        Collectors.summingDouble(Produto::getPreco)
                ));
        System.out.println("9) Total por categoria: " + totalPorCategoria);
    }
}
