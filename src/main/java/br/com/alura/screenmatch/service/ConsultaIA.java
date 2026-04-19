package br.com.alura.screenmatch.service;

import io.github.cdimascio.dotenv.Dotenv;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaIA {

    private static final String API_KEY = Dotenv.load().get("DEEPSEEK_API_KEY");
    private static final String URL = "https://api.deepseek.com/chat/completions";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public static String obterTraducao(String texto) {
        if (texto == null || texto.isBlank()) return "";

        var body = """
                {"model":"deepseek-chat","messages":[{"role":"user","content":"traduza para o português o texto: %s"}],"max_tokens":1000,"temperature":0.7}
                """.formatted(texto.replace("\"", "\\\""));

        var request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        try {
            var json = CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
            int start = json.indexOf("\"content\":") + 10;
            start = json.indexOf("\"", start) + 1;
            return json.substring(start, json.indexOf("\"", start));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar IA: " + e.getMessage());
        }
    }
}
