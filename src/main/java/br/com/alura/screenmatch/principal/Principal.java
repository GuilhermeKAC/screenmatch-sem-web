package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.model.enums.Categoria;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";
    private List<DadosSerie> dadosSeries = new ArrayList<>();

    private SerieRepository repository;

    private List<Serie> series = new ArrayList<>();

    private Optional<Serie> serieBusca;

    public Principal(SerieRepository repository) {
        this.repository = repository;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                    4 - Buscar série por titulo
                    5 - Buscar series por ator
                    6 - Top 5 séries
                    7 - Buscar séries por categoria
                    8 - Buscar séries por temporadas e avaliação
                    9 - Buscar episódio por trecho
                    10 - Top episódios por serie
                    11 - Buscar episódios a partir de uma data

                    0 - Sair
                    """;

            System.out.println(menu);

            opcao = Integer.parseInt(leitura.nextLine());

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 4:
                    buscarSeriePorTitulo();
                    break;
                case 5:
                    buscarSeriesPorAtor();
                    break;
                case 6:
                    buscarTop5Series();
                    break;
                case 7:
                    buscarSeriesPorCategoria();
                    break;
                case 8:
                    buscarSeriesPorTemporadasEAvaliacao();
                    break;
                case 9:
                    buscarEpisodioPorTrecho();
                    break;
                case 10:
                    topEpisodiosPorSerie();
                    break;
                case 11:
                    buscarEpisodiosPorData();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        Serie serie = new Serie(dados);
        repository.save(serie);
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie() {
        listarSeriesBuscadas();
        System.out.println("Escolha uma série pelo nome");
        var nomeSerie = leitura.nextLine();

        Optional<Serie> serie = series.stream()
                .filter(s -> s.getTitulo().equalsIgnoreCase(nomeSerie))
                .findFirst();

        if (serie.isPresent()) {
            var serieEncontrada = serie.get();

            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumo.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream().map(e -> new Episodio(d.numero(), e)))
                    .toList();

            serieEncontrada.setEpisodios(episodios);
            repository.save(serieEncontrada);
        } else {
            System.out.println("Série não encontrada!");
        }
    }

    private void listarSeriesBuscadas() {
        series = repository.findAll().stream().toList();
        series.stream().sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    private void buscarSeriePorTitulo() {
        System.out.println("Escolha uma série pelo nome");
        var nomeSerie = leitura.nextLine();

        serieBusca = repository.findByTituloContainingIgnoreCase(nomeSerie);

        if (serieBusca.isPresent()) {
            System.out.println("Dados da série: " + serieBusca.get());
        } else {
            System.out.println("Série não encontrada!");
        }
    }

    private void buscarSeriesPorAtor() {
        System.out.println("Qual o nome para busca?");
        var nomeAtor = leitura.nextLine();
        System.out.println("Avaliações a partir de qual valor? ");
        var avaliacao = Double.parseDouble(leitura.nextLine().replace(",", "."));
        List<Serie> seriesEncontradas = repository.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor, avaliacao);
        System.out.println("Séries em que " + nomeAtor + " participou:");
        seriesEncontradas.forEach(s -> System.out.println(s.getTitulo() + " avaliação " + s.getAvaliacao()));
    }

    private void buscarTop5Series() {
        List<Serie> serieTop = repository.findTop5ByOrderByAvaliacaoDesc();
        serieTop.forEach(s -> System.out.println(s.getTitulo() + " avaliação " + s.getAvaliacao()));
    }

    private void buscarSeriesPorCategoria() {
        System.out.println("Qual a categoria?");
        var nomeGenero = leitura.nextLine();
        Categoria categoria = Categoria.fromPortugues(nomeGenero);

        List<Serie> seriesPorCategoria = repository.findByGenero(categoria);
        System.out.println("Séries da categoria " + nomeGenero + ":");
        seriesPorCategoria.forEach(s -> System.out.println(s.getTitulo()));
    }

    private void buscarSeriesPorTemporadasEAvaliacao() {
        System.out.println("Séries com até quantas temporadas?");
        var totalTemporadas = Integer.parseInt(leitura.nextLine());
        System.out.println("Avaliação a partir de qual valor?");
        var avaliacao = Double.parseDouble(leitura.nextLine().replace(",", "."));

        List<Serie> series = repository.seriesPorTemporadaEAvaliacao(totalTemporadas, avaliacao);
        System.out.println("Séries com até " + totalTemporadas + " temporadas e avaliação >= " + avaliacao + ":");
        series.forEach(s -> System.out.println(s.getTitulo() + " (" + s.getTotalTemporadas() + " temporadas) - avaliação " + s.getAvaliacao()));
    }


    private void buscarEpisodioPorTrecho() {
        System.out.println("Qual o nome do episódio para busca?");
        var trechoEpisodio = leitura.nextLine();
        List<Episodio> episodiosEncontrados = repository.episodiosPorTrecho(trechoEpisodio);
        episodiosEncontrados.forEach(e ->
                System.out.printf("Série: %s Temporada %s - Episódio %s - %s\n",
                        e.getSerie().getTitulo(), e.getTemporada(),
                        e.getNumero(), e.getTitulo()));
    }

    private void topEpisodiosPorSerie() {
        buscarSeriePorTitulo();

        if (serieBusca.isPresent()) {
            Serie serie = serieBusca.get();
            List<Episodio> topEpisodios = repository.topEpisodiosPorSerie(serie);
            topEpisodios.forEach(e ->
                    System.out.printf("Série: %s Temporada %s - Episódio %s - %s - Avaliação: %s\n",
                            e.getSerie().getTitulo(), e.getTemporada(),
                            e.getNumero(), e.getTitulo(), e.getAvaliacao()));
        }
    }


    private void buscarEpisodiosPorData() {
        buscarSeriePorTitulo();

        if (serieBusca.isPresent()) {
            Serie serie = serieBusca.get();
            System.out.println("Digite a data para busca");
            var data = leitura.nextInt();
            leitura.nextLine();

            List<Episodio> dataEpisodio = repository.episodiosPorData(serie, data);
            dataEpisodio.forEach(System.out::println);
        }
    }
}