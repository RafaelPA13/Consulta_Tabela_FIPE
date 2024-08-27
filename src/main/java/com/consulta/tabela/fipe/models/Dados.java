package com.consulta.tabela.fipe.models;

public record Dados(Integer codigo, String nome) {
    @Override
    public String toString() {
        return """
                Código: %d
                Nome: %s
                """.formatted(codigo, nome);
    }
}
