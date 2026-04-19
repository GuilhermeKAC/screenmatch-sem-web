package br.com.alura.screenmatch.exercicios.pratica16;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // Ex 1
        List<String> input = Arrays.asList("10", "abc", "20", "30x");
        System.out.println(converterNumeros(input)); // [10, 20]

        // Ex 2
        System.out.println(processaNumero(Optional.of(5)));    // Optional[25]
        System.out.println(processaNumero(Optional.of(-3)));   // Optional.empty
        System.out.println(processaNumero(Optional.empty()));  // Optional.empty

        // Ex 3
        System.out.println(obterPrimeiroEUltimoNome("  João Carlos Silva   ")); // João Silva
        System.out.println(obterPrimeiroEUltimoNome("Maria   "));               // Maria

        // Ex 4
        System.out.println(ehPalindromo("socorram me subi no onibus em marrocos")); // true
        System.out.println(ehPalindromo("Java"));                                   // false

        // Ex 5
        List<String> emails = Arrays.asList("TESTE@EXEMPLO.COM", "exemplo@Java.com ", "Usuario@teste.Com");
        System.out.println(converterEmails(emails));
    }

    // Ex 1 — converte strings para inteiros, ignorando inválidos
    public static List<Integer> converterNumeros(List<String> input) {
        return input.stream()
                .flatMap(s -> {
                    try {
                        return java.util.stream.Stream.of(Integer.parseInt(s));
                    } catch (NumberFormatException e) {
                        return java.util.stream.Stream.empty();
                    }
                })
                .collect(Collectors.toList());
    }

    // Ex 2 — quadrado se positivo, empty caso contrário
    public static Optional<Integer> processaNumero(Optional<Integer> numero) {
        return numero.filter(n -> n > 0).map(n -> n * n);
    }

    // Ex 3 — primeiro e último nome
    public static String obterPrimeiroEUltimoNome(String nomeCompleto) {
        String[] partes = nomeCompleto.trim().split("\\s+");
        if (partes.length == 1) return partes[0];
        return partes[0] + " " + partes[partes.length - 1];
    }

    // Ex 4 — palíndromo ignorando espaços
    public static boolean ehPalindromo(String palavra) {
        String limpa = palavra.replaceAll("\\s+", "").toLowerCase();
        return limpa.equals(new StringBuilder(limpa).reverse().toString());
    }

    // Ex 5 — e-mails em lowercase sem espaços
    public static List<String> converterEmails(List<String> emails) {
        return emails.stream()
                .map(e -> e.trim().toLowerCase())
                .collect(Collectors.toList());
    }
}
