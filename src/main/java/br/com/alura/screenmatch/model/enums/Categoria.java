package br.com.alura.screenmatch.model.enums;

public enum Categoria {
    ACAO("Action"),
    AVENTURA("Adventure"),
    ANIMACAO("Animation"),
    COMEDIA("Comedy"),
    CRIME("Crime"),
    DOCUMENTARIO("Documentary"),
    DRAMA("Drama"),
    FANTASIA("Fantasy"),
    ROMANCE("Romance"),
    FICCAO_CIENTIFICA("Sci-Fi"),
    TERROR("Horror"),
    MISTERIO("Mystery"),
    BIOGRAFIA("Biography"),
    MUSICAL("Music"),
    GUERRA("War"),
    WESTERN("Western");

    private final String categoriaOmdb;

    Categoria(String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
