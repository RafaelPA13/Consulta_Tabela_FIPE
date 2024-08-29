package com.consulta.tabela.fipe.principal;

import com.consulta.tabela.fipe.models.Dados;
import com.consulta.tabela.fipe.services.ConsultaAPI;
import com.consulta.tabela.fipe.services.ConverterDados;
import com.consulta.tabela.fipe.services.RemoverAcento;

import java.util.Comparator;
import java.util.Scanner;

public class Principal {
    Scanner input = new Scanner(System.in);
    ConsultaAPI consulta = new ConsultaAPI();
    RemoverAcento removerAcento = new RemoverAcento();
    ConverterDados conversor = new ConverterDados();

    private final String URL = "https://parallelum.com.br/fipe/api/v1/";

    public void menu() {
        System.out.println("Digite qual veículo você gostaria de pesquisar [carros | motos | caminhões]: ");
        String veiculo = input.nextLine();
        var json = consulta.consultarAPI(URL + removerAcento.removedor(veiculo) + "/marcas");

        var dadosMarcas = conversor.converterLista(json, Dados.class);
        dadosMarcas.sort(Comparator.comparing(Dados::codigo));
        dadosMarcas.forEach(System.out::println);

        System.out.println("Digite o código da marca de " + veiculo + "que você deseja pesquisar: ");
        Integer marca = input.nextInt();
        input.nextLine();
        json = consulta.consultarAPI(URL + removerAcento.removedor(veiculo) + "/marcas/" + marca + "/modelos");

        var dadosModelos = conversor.converterDados(json, Dados.class);
        dadosModelos.modelos().forEach(m-> System.out.println(m.nome().toUpperCase() + "\n"));

    }
}
