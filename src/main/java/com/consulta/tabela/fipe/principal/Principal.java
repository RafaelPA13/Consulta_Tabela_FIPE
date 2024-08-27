package com.consulta.tabela.fipe.principal;

import com.consulta.tabela.fipe.services.ConsultaAPI;
import com.consulta.tabela.fipe.services.RemoverAcento;

import java.util.Scanner;

public class Principal {
    Scanner input = new Scanner(System.in);
    ConsultaAPI consulta = new ConsultaAPI();
    RemoverAcento removerAcento = new RemoverAcento();

    private final String URL = "https://parallelum.com.br/fipe/api/v1/";

    public void menu() {
        System.out.println("Digite qual veículo você gostaria de pesquisar [carros | motos | caminhões]: ");
        String veiculo = input.nextLine();
        var json = consulta.consultarAPI(URL + removerAcento.removedor(veiculo) + "/marcas");

        System.out.println(json);
    }
}
