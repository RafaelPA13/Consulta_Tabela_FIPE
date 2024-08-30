package com.consulta.tabela.fipe.principal;

import com.consulta.tabela.fipe.models.Dados;
import com.consulta.tabela.fipe.models.DadosModelos;
import com.consulta.tabela.fipe.models.DadosVeiculo;
import com.consulta.tabela.fipe.services.ConsultaAPI;
import com.consulta.tabela.fipe.services.ConverterDados;
import com.consulta.tabela.fipe.services.RemoverAcento;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    Scanner input = new Scanner(System.in);
    ConsultaAPI consulta = new ConsultaAPI();
    RemoverAcento removerAcento = new RemoverAcento();
    ConverterDados conversor = new ConverterDados();

    private final String URL = "https://parallelum.com.br/fipe/api/v1/";

    public void menu() {
        System.out.println("Digite uma das opções [carros | motos | caminhões]: ");
        String veiculo = input.nextLine();
        var json = consulta.consultarAPI(URL + removerAcento.removedor(veiculo) + "/marcas");

        var dadosMarcas = conversor.converterLista(json, Dados.class);
        System.out.println("*** Marcas ***\n");
        dadosMarcas.stream().sorted(Comparator.comparing(Dados::nome)).forEach(System.out::println);

        System.out.println("Digite o código da marca de " + veiculo + " que você deseja pesquisar: ");
        Integer marca = input.nextInt();
        input.nextLine();
        json = consulta.consultarAPI(URL + removerAcento.removedor(veiculo) + "/marcas/" + marca + "/modelos");

        var dadosModelos = conversor.converterDados(json, DadosModelos.class);
        System.out.println("*** Modelos ***\n");
        dadosModelos.modelos().forEach(m-> System.out.println(m.nome() + "\n"));

        System.out.println("Digite o modelo de " + veiculo + " que você deseja pesquisar: ");
        String modelo = input.nextLine();

        List<Dados> modelosFiltrados = dadosModelos.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(modelo.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("*** Modelos " + modelo + " ***\n");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite o código do modelo de que você deseja pesquisar: ");
        Integer codigo = input.nextInt();
        input.nextLine();

        json = consulta.consultarAPI(URL + removerAcento.removedor(veiculo) + "/marcas/" + marca + "/modelos/" + codigo + "/anos");
        var dadosAnos = conversor.converterLista(json, Dados.class);
        System.out.println("*** Anos *** \n");
        dadosAnos.stream().sorted(Comparator.comparing(Dados::codigo)).forEach(System.out::println);

        System.out.println("Digite o código do ano do modelo que você busca: ");
        String ano = input.nextLine();
        json = consulta.consultarAPI(URL + removerAcento.removedor(veiculo) + "/marcas/" + marca + "/modelos/" + codigo + "/anos/" + ano);

        var dadosVeiculo = conversor.converterDados(json, DadosVeiculo.class);
        System.out.println(dadosVeiculo);
    }
}
