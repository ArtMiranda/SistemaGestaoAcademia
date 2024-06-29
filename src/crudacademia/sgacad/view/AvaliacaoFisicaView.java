package sgacad.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import sgacad.controller.AvaliacaoFisicaController;
import sgacad.model.AvaliacaoFisica;

public class AvaliacaoFisicaView {
    private static Scanner scanner = new Scanner(System.in);

    public static void calcularIMC(int id, int ultimoTreino, double peso, double altura) {
        System.out.println(AvaliacaoFisicaController.calculaAvaliacaoFisica(id, ultimoTreino, peso, altura));

        double avFisicaResultado = Double.parseDouble(AvaliacaoFisicaController.calculaAvaliacaoFisica(id, ultimoTreino, peso, altura));

        if (avFisicaResultado < 18.5) {
            System.out.println("Abaixo do peso");
        } else if (avFisicaResultado >= 18.5 && avFisicaResultado < 25) {
            System.out.println("Peso normal");
        } else if (avFisicaResultado >= 25 && avFisicaResultado < 30) {
            System.out.println("Sobrepeso");
        } else if (avFisicaResultado >= 30 && avFisicaResultado < 35) {
            System.out.println("Obesidade grau 1");
        } else if (avFisicaResultado >= 35 && avFisicaResultado < 40) {
            System.out.println("Obesidade grau 2");
        } else {
            System.out.println("Obesidade grau 3");
        }

        System.out.printf("O IMC e: %.2f\n", avFisicaResultado);

        criaAvaliacaoFisica(id, ultimoTreino, peso, altura, avFisicaResultado);
    }

    public static AvaliacaoFisica criaAvaliacaoFisica(int id, int ultimoTreino, double peso, double altura, double imc) {
        int indiceSatisfacao = 0;
        do{
            System.out.print("\nInforme o indice de Satisfacao com o resultado (0 a 10): ");
            indiceSatisfacao = scanner.nextInt();
            scanner.nextLine(); 
        } while(indiceSatisfacao < 0 || indiceSatisfacao > 10);

        AvaliacaoFisica avFisica = AvaliacaoFisicaController.geraAvaliacaoFisica(id, ultimoTreino, peso, altura, indiceSatisfacao);
        return avFisica;
    }

    public static void listarAvaliacoesFisicas() {
        List<AvaliacaoFisica> avaliacoesFisicas = AvaliacaoFisicaController.listarTodasAvaliacoesFisicas();
        for (AvaliacaoFisica avaliacao : avaliacoesFisicas) {
            System.out.println("\n\nID: " + avaliacao.getId());
            System.out.println("Nome: " + avaliacao.getPessoa());
            System.out.println("Ultimo treino: " + avaliacao.getUltimoTreino());
            System.out.println("Peso: " + avaliacao.getPeso());
            System.out.println("Altura: " + avaliacao.getAltura());
            System.out.printf("IMC: %.2f\n", avaliacao.getImc());
            System.out.println("Indice de satisfacao: " + avaliacao.getIndiceSatisfacaoResultado());
            System.out.println("Data de criacao: " + formatarData(avaliacao.getDataCriacao()));
            System.out.println("Data de modificacao: " + formatarData(avaliacao.getDataModificacao()));
        }
    }

    public static void listarAvaliacaoFisica() {
        System.out.print("\n\nInforme o ID da avaliacao fisica: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        AvaliacaoFisica avaliacaoFisica = AvaliacaoFisicaController.buscarAvaliacaoFisicaPorId(id);
        if (avaliacaoFisica != null) {
            System.out.println("ID: " + avaliacaoFisica.getId());
            System.out.println("Nome: " + avaliacaoFisica.getPessoa());
            System.out.println("Ultimo treino: " + avaliacaoFisica.getUltimoTreino());
            System.out.println("Peso: " + avaliacaoFisica.getPeso());
            System.out.println("Altura: " + avaliacaoFisica.getAltura());
            System.out.printf("IMC: %.2f\n", avaliacaoFisica.getImc());
            System.out.println("indice de satisfacao: " + avaliacaoFisica.getIndiceSatisfacaoResultado());
            System.out.println("Data de criacao: " + formatarData(avaliacaoFisica.getDataCriacao()));
            System.out.println("Data de modificacao: " + formatarData(avaliacaoFisica.getDataModificacao()));
        } else {
            System.out.println("Avaliacao fisica nao encontrada");
        }
    }

    public static void removeAvaliacoesFisicasPorId() {
        System.out.print("\nInforme o ID da avaliacao fisica: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (AvaliacaoFisicaController.buscarAvaliacaoFisicaPorId(id) == null) {
            System.out.println("Avaliacao fisica nao encontrada");
            return;
        }
        AvaliacaoFisicaController.deletarAvaliacaoFisica(id);
        System.out.println("\nAvaliacao fisica removida com sucesso\n");
    }

    private static String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }
}
