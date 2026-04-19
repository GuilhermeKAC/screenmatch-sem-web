package br.com.alura.screenmatch.exercicios.pratica16;

public enum CodigoErro {
    OK(200, "Requisição bem-sucedida"),
    CREATED(201, "Recurso criado com sucesso"),
    BAD_REQUEST(400, "Requisição inválida"),
    UNAUTHORIZED(401, "Não autorizado"),
    FORBIDDEN(403, "Acesso proibido"),
    NOT_FOUND(404, "Recurso não encontrado"),
    METHOD_NOT_ALLOWED(405, "Método não permitido"),
    CONFLICT(409, "Conflito de dados"),
    UNPROCESSABLE_ENTITY(422, "Entidade não processável"),
    INTERNAL_SERVER_ERROR(500, "Erro interno do servidor"),
    BAD_GATEWAY(502, "Gateway inválido"),
    SERVICE_UNAVAILABLE(503, "Serviço indisponível");

    private final int codigo;
    private final String descricao;

    CodigoErro(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
