package com.consulta.tabela.fipe.services;

import java.text.Normalizer;

public class RemoverAcento {
    public String removedor(String palavra) {
        return Normalizer.normalize(palavra, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();
    }
}
