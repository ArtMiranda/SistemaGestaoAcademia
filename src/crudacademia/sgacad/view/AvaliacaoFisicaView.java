package sgacad.view;

import java.util.Scanner;

import sgacad.controller.AvaliacaoFisicaController;
import sgacad.model.AvaliacaoFisica;

public class AvaliacaoFisicaView {
    public static AvaliacaoFisica[] avaliacoesFisicas;
    public static int numAvaliacoes;

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

        criaAvaliaçaoFisica(id, ultimoTreino, peso, altura, avFisicaResultado);
    }

    public static AvaliacaoFisica criaAvaliaçaoFisica(int id, int ultimoTreino, double peso, double altura, double imc) {
        int indiceSatisfacao;
        do {
            System.out.println("Informe o indice de satisfaçao do resultado da avaliaçao fisica (1 a 10): ");
            Scanner scanner = new Scanner(System.in);
            while (!scanner.hasNextInt()) {
            System.out.println("Entrada invalida. Por favor, informe um numero valido.");
            scanner.next();
            }
            indiceSatisfacao = scanner.nextInt();
            scanner.nextLine(); // consume the newline character
            scanner.close();
        } while (indiceSatisfacao < 1 || indiceSatisfacao > 10);

        AvaliacaoFisica avFisica = AvaliacaoFisicaController.geraAvaliacaoFisica(id, ultimoTreino, peso, altura, indiceSatisfacao);
        return avFisica;
    }

    public void listarAvaliacoesFisicas() {
        for (int i = 0; i < numAvaliacoes; i++) {
            System.out.println("ID: " + avaliacoesFisicas[i].getId());
            System.out.println("Nome: " + avaliacoesFisicas[i].getPessoa());
            System.out.println("ultimo treino: " + avaliacoesFisicas[i].getUltimoTreino());
            System.out.println("Peso: " + avaliacoesFisicas[i].getPeso());
            System.out.println("Altura: " + avaliacoesFisicas[i].getAltura());
            System.out.println("IMC: " + avaliacoesFisicas[i].getImc());
            System.out.println("indice de satisfaçao: " + avaliacoesFisicas[i].getIndiceSatisfacaoResultado());
            System.out.println("Data de criaçao: " + avaliacoesFisicas[i].getDataCriacao());
            System.out.println("Data de modificaçao: " + avaliacoesFisicas[i].getDataModificacao());
        }
    }

    public void listarAvaliacaoFisica(int id) {
        AvaliacaoFisica avaliacaoFisica = AvaliacaoFisicaController.getAvaliacaoFisicaById(id);
        if (avaliacaoFisica != null) {
            System.out.println("ID: " + avaliacaoFisica.getId());
            System.out.println("Nome: " + avaliacaoFisica.getPessoa());
            System.out.println("ultimo treino: " + avaliacaoFisica.getUltimoTreino());
            System.out.println("Peso: " + avaliacaoFisica.getPeso());
            System.out.println("Altura: " + avaliacaoFisica.getAltura());
            System.out.println("IMC: " + avaliacaoFisica.getImc());
            System.out.println("indice de satisfaçao: " + avaliacaoFisica.getIndiceSatisfacaoResultado());
            System.out.println("Data de criaçao: " + avaliacaoFisica.getDataCriacao());
            System.out.println("Data de modificaçao: " + avaliacaoFisica.getDataModificacao());
        } else {
            System.out.println("Avaliaçao fisica nao encontrada");
        }
    }

    public void removeAvaliacoesFisicas() {
        AvaliacaoFisicaController.removeAvaliacoesFisicas();
    }
    
}
