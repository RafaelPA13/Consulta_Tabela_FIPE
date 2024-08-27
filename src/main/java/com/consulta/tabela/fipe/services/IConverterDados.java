package com.consulta.tabela.fipe.services;

import java.util.List;

public interface IConverterDados {
    <T> T converterDados(String json, Class<T> classe);

    <T> List<T> converterLista(String json, Class<T> classe);
}
