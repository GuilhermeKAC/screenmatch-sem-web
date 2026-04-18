package br.com.alura.screenmatch.exercicios.ex4e5;

import br.com.alura.screenmatch.exercicios.ex3.Tarefa;
import tools.jackson.databind.ObjectMapper;

import java.io.File;

public class SerializacaoApp {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Exercício 4 — Serialização: objeto → arquivo JSON
        Tarefa tarefa = new Tarefa("Estudar Spring Boot", false, "Guilherme");
        mapper.writeValue(new File("tarefa.json"), tarefa);
        System.out.println("Tarefa serializada em tarefa.json");

        // Exercício 5 — Desserialização: arquivo JSON → objeto
        Tarefa tarefaLida = mapper.readValue(new File("tarefa.json"), Tarefa.class);
        System.out.println("Tarefa desserializada: " + tarefaLida);
    }
}