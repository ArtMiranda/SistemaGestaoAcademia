package sgacad.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import sgacad.controller.AvaliacaoFisicaController;
import sgacad.model.AvaliacaoFisica;

public class AvaliacaoFisicaView {
    public static AvaliacaoFisica[] avaliacoesFisicas = new AvaliacaoFisica[100];
    public static int numAvaliacoes = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void calcularIMC(int id, int ultimoTreino, double peso, double altura){

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

        System.out.println("O IMC e: " + avFisicaResultado);

        criaAvaliacaoFisica(id, ultimoTreino, peso, altura, avFisicaResultado);
    }

    public static AvaliacaoFisica criaAvaliacaoFisica(int id, int ultimoTreino, double peso, double altura, double imc) {
        System.out.print("\n\nInforme o indice de Satisfacao com o resultado: ");
        int indiceSatisfacao = scanner.nextInt();
        scanner.nextLine(); 

        AvaliacaoFisica avFisica = AvaliacaoFisicaController.geraAvaliacaoFisica(id, ultimoTreino, peso, altura, indiceSatisfacao);
        return avFisica;
    }

    public static void listarAvaliacoesFisicas() {
        for (int i = 0; i < numAvaliacoes; i++) {
            System.out.println("\n\nID: " + avaliacoesFisicas[i].getId());
            System.out.println("Nome: " + avaliacoesFisicas[i].getPessoa());
            System.out.println("ultimo treino: " + avaliacoesFisicas[i].getUltimoTreino());
            System.out.println("Peso: " + avaliacoesFisicas[i].getPeso());
            System.out.println("Altura: " + avaliacoesFisicas[i].getAltura());
            System.out.println("IMC: " + avaliacoesFisicas[i].getImc());
            System.out.println("indice de satisfacao: " + avaliacoesFisicas[i].getIndiceSatisfacaoResultado());
            System.out.println("Data de criacao: " + formatarData(avaliacoesFisicas[i].getDataCriacao()));
            System.out.println("Data de modificacao: " + formatarData(avaliacoesFisicas[i].getDataModificacao()));
        }
    }

    public static void listarAvaliacaoFisica() {
        System.out.print("\n\nInforme o ID da avaliacao fisica: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        AvaliacaoFisica avaliacaoFisica = AvaliacaoFisicaController.getAvaliacaoFisicaById(id);
        if (avaliacaoFisica != null) {
            System.out.println("ID: " + avaliacaoFisica.getId());
            System.out.println("Nome: " + avaliacaoFisica.getPessoa());
            System.out.println("ultimo treino: " + avaliacaoFisica.getUltimoTreino());
            System.out.println("Peso: " + avaliacaoFisica.getPeso());
            System.out.println("Altura: " + avaliacaoFisica.getAltura());
            System.out.println("IMC: " + avaliacaoFisica.getImc());
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

        if(AvaliacaoFisicaController.getAvaliacaoFisicaById(id) == null){
            System.out.println("Avaliacao fisica nao encontrada");
            return;
        } 
            AvaliacaoFisicaController.removeAvaliacaoFisica(id);
            System.out.println("\nAvaliacao fisica removida com sucesso\n");
    }

    private static String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }
    
}
