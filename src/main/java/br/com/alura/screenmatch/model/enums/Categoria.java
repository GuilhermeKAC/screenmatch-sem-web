package br.com.alura.screenmatch.model.enums;

public enum Categoria {
    ACAO("Action", "Ação"),
    AVENTURA("Adventure", "Aventura"),
    ANIMACAO("Animation", "Animação"),
    COMEDIA("Comedy", "Comédia"),
    CRIME("Crime", "Crime"),
    DOCUMENTARIO("Documentary", "Documentario"),
    DRAMA("Drama", "Drama"),
    FANTASIA("Fantasy", "Fantasia"),
    ROMANCE("Romance", "Romance"),
    FICCAO_CIENTIFICA("Sci-Fi", "Ficção Cientifica"),
    TERROR("Horror", "Terror"),
    MISTERIO("Mystery", "Misterio"),
    BIOGRAFIA("Biography", "Biografia"),
    MUSICAL("Music", "Música"),
    GUERRA("War", "Guerra"),
    WESTERN("Western", "Faroeste");

    private final String categoriaOmdb;
    private String categoriaPortugues;

    Categoria(String categoriaOmdb, String categoriaPortugues) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortugues = categoriaPortugues;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }


    public static Categoria fromPortugues(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaPortugues.equalsIgnoreCase(text) || categoria.name().equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
