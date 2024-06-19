package sgacad.view;

import java.time.LocalDate;
import java.util.Scanner;
import sgacad.controller.TreinoController;
import sgacad.controller.DivisaoTreinoController;
import sgacad.model.DivisaoTreino;
import sgacad.model.Treino;

public class DivisaoTreinoView {
    public static DivisaoTreino[] divisoesTreinos = new DivisaoTreino[100]; // Array de exercicios
    public static int numDivisoesTreino = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static DivisaoTreino criaDivisaoTreino() {
        TreinoView.exibirTodosTreinos();
        System.out.print("\n\nInforme o Treino: ");
        int treino = scanner.nextInt();
        scanner.nextLine(); // Consumir a linha nova apos o proximo int

        Treino treinoSelecionado = TreinoController.getTreinoById(treino);
        while (treinoSelecionado == null) {
            System.out.print("Treino invalido. Informe novamente: ");
            treino = scanner.nextInt();
            scanner.nextLine(); // Consumir a linha nova apos o proximo int
            treinoSelecionado = TreinoController.getTreinoById(treino);
        }

        // Descricao do exercicio
        System.out.print("Descricao da Divisao de Treino: ");
        String descricao = scanner.nextLine().trim();
        while (descricao.isEmpty()) {
            System.out.print("Descricao nao pode estar vazio. Informe novamente: ");
            descricao = scanner.nextLine().trim();
        }

        String nome = treinoSelecionado.getObjetivo();

        DivisaoTreinoController divisaoTreinoController = new DivisaoTreinoController();

        DivisaoTreino divisaoTreino = divisaoTreinoController.geraDivisaoTreino(numDivisoesTreino, nome, descricao);
        divisoesTreinos[numDivisoesTreino] = divisaoTreino;
        numDivisoesTreino++;
        return divisaoTreino;
    }

    public static void exibirTodasDivisoesTreino() {
        if (DivisaoTreinoView.numDivisoesTreino == 0) {
            System.out.println("\n\nNenhuma divisao de treino cadastrada ainda.");
        } else {
            System.out.println("\n\nLista de Divisoes de Treino:");
            for (int i = 0; i < DivisaoTreinoView.numDivisoesTreino; i++) {
                System.out.println(
                        "ID: " + divisoesTreinos[i].getId() + ", Nome do Treino: " + divisoesTreinos[i].getNome()
                                + ", Descricao: " + divisoesTreinos[i].getDescricao());
            }
        }
    }

    public static void exibirDadosDivisaoTreinoPorId() {
        int idBusca = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID da divisao de treino: ");
            if (scanner.hasNextInt()) {
                idBusca = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("ID invalido. Informe novamente.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        DivisaoTreino divisaoTreino = DivisaoTreinoController.getDivisaoTreinoById(idBusca);
        if (divisaoTreino != null) {
            System.out.println(divisaoTreino.exibirDetalhes());
        } else {
            System.out.println("Divisao de treino nao encontrada.");
        }
    }

    public static DivisaoTreino atualizarDivisaoTreino() {
        int idDivisaoTreino = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID da divisao de treino que deseja atualizar: ");
            if (scanner.hasNextInt()) {
                idDivisaoTreino = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas numeros inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        DivisaoTreino divisaoTreino = DivisaoTreinoController.getDivisaoTreinoById(idDivisaoTreino);
        if (divisaoTreino != null) {
            System.out.println("\nDados atuais da Divisao de Treino:");
            System.out.println(divisaoTreino.exibirDetalhes());

            System.out.println("\nInforme os novos dados da divisao de treino:");

            // Descricao da divisao de treino
            System.out.print("Nova Descricao: ");
            String novaDescricao = scanner.nextLine().trim();
            if (!novaDescricao.isEmpty()) {
                divisaoTreino.setDescricao(novaDescricao);
            }

            // Data de modificacao
            divisaoTreino.setDataModificacao(LocalDate.now());

            System.out.println("\nDados da Divisao de Treino atualizados com sucesso:");
            System.out.println(divisaoTreino.exibirDetalhes());
        } else {
            System.out.println("\nDivisao de Treino com ID " + idDivisaoTreino + " nao encontrada.");
        }

        return divisaoTreino;
    }

    public static void removerDivisaoTreino() {
        int idDivisaoTreino = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID da divisao de treino que deseja remover: ");
            if (scanner.hasNextInt()) {
                idDivisaoTreino = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas numeros inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < DivisaoTreinoView.numDivisoesTreino; i++) {
            if (divisoesTreinos[i].getId() == idDivisaoTreino) {
                for (int j = i; j < DivisaoTreinoView.numDivisoesTreino - 1; j++) {
                    divisoesTreinos[j] = divisoesTreinos[j + 1];
                }
                divisoesTreinos[DivisaoTreinoView.numDivisoesTreino - 1] = null;
                DivisaoTreinoView.numDivisoesTreino--;
                System.out.println("\n\nDivisao de treino removida com sucesso.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nDivisao de treino com ID " + idDivisaoTreino + " nao encontrada.");
        }
    }
}
