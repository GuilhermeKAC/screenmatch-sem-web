package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.IConverteDados;
import br.com.alura.screenmatch.service.converteDados;

import java.util.Scanner;

public class Principal {
    private static Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private IConverteDados conversor = new converteDados();

    private static final String ENDERECO = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=737ae697";

    public void exibeMenu() {
        System.out.println("Digite o nome da série");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);
    }
}
