package br.com.alura.screenmatch.service;

import tools.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        return objectMapper.readValue(json, classe);
    }
}
