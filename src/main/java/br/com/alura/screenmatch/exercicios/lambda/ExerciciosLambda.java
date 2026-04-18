package br.com.alura.screenmatch.exercicios.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ExerciciosLambda {
    public static void main(String[] args) {

        // 1 - Multiplicação de dois inteiros
        Multiplicacao multiplicar = (a, b) -> a * b;
        System.out.println("1) 4 x 5 = " + multiplicar.multiplicacao(4, 5));

        // 2 - Verificar se um número é primo
        Predicate<Integer> ehPrimo = n -> {
            if (n < 2) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        };
        System.out.println("2) 7 é primo? " + ehPrimo.test(7));
        System.out.println("2) 9 é primo? " + ehPrimo.test(9));

        // 3 - Converter string para maiúsculas
        Function<String, String> paraMaiusculas = String::toUpperCase;
        System.out.println("3) " + paraMaiusculas.apply("alura"));

        // 4 - Verificar palíndromo
        VerificadorPalindromo ehPalindromo = str -> {
            String invertida = new StringBuilder(str).reverse().toString();
            return str.equalsIgnoreCase(invertida);
        };
        System.out.println("4) 'arara' é palíndromo? " + ehPalindromo.verificarPalindromo("arara"));
        System.out.println("4) 'java' é palíndromo? " + ehPalindromo.verificarPalindromo("java"));

        // 5 - Multiplicar cada elemento de uma lista por 3
        List<Integer> numeros = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        numeros.replaceAll(n -> n * 3);
        System.out.println("5) " + numeros);

        // 6 - Ordenar lista de strings em ordem alfabética
        List<String> nomes = new ArrayList<>(Arrays.asList("Carlos", "Ana", "Bruno", "Diana"));
        nomes.sort((a, b) -> a.compareTo(b));
        System.out.println("6) " + nomes);

        // 7 - Dividir dois números lançando exceção se divisor for zero
        Multiplicacao dividir = (a, b) -> {
            if (b == 0) throw new ArithmeticException("Divisão por zero não permitida");
            return a / b;
        };
        System.out.println("7) 10 / 2 = " + dividir.multiplicacao(10, 2));
        try {
            dividir.multiplicacao(10, 0);
        } catch (ArithmeticException e) {
            System.out.println("7) Exceção capturada: " + e.getMessage());
        }
    }
}
