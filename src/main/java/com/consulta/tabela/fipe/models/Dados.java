package com.consulta.tabela.fipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados(Integer codigo, String nome, List<Dados> modelos) {
    @Override
    public String toString() {
        return """
                Código: %d
                Nome: %s
                """.formatted(codigo, nome);
    }
}
