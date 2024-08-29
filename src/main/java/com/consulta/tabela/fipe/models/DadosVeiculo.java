package com.consulta.tabela.fipe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(String Valor, String Marca, String Modelo, Integer AnoModelo, String Combustivel) {
    @Override
    public String toString() {
        return """
                *** %s ***
                Marca: %s
                Ano: %d
                Combustível: %s
                Preço: %S
                """.formatted(Modelo, Marca, AnoModelo, Combustivel, Valor);
    }
}
