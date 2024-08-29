package com.consulta.tabela.fipe.models;

public record Dados(String codigo, String nome) {
    @Override
    public String toString() {
        return """
                Código: %s
                Nome: %s
                """.formatted(codigo, nome);
    }
}
