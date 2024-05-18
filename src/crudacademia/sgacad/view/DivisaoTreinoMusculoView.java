package sgacad.view;

import java.util.Calendar;
import java.util.Scanner;
import sgacad.controller.DivisaoTreinoController;
import sgacad.controller.DivisaoTreinoMusculoController;
import sgacad.controller.TreinoController;
import sgacad.model.DivisaoTreino;
import sgacad.model.DivisaoTreinoMusculo;

public class DivisaoTreinoMusculoView {
    public static DivisaoTreinoMusculo[] divisoesTreinoMusculo = new DivisaoTreinoMusculo[100];
    public static int numDivisoesTreinoMusculo = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static DivisaoTreinoMusculo criaDivisaoTreinoMusculo() {
        DivisaoTreinoView.exibirTodasDivisoesTreino();
        System.out.print("\n\nInforme o ID da Divisao de Treino: ");
        int divisaoTreinoId = scanner.nextInt();
        scanner.nextLine(); // Consumir a linha nova apos o proximo int

        DivisaoTreino divisaoTreinoSelecionada = DivisaoTreinoController.getDivisaoTreinoById(divisaoTreinoId);
        while (divisaoTreinoSelecionada == null) {
            System.out.print("Divisao de Treino invalida. Informe novamente: ");
            divisaoTreinoId = scanner.nextInt();
            scanner.nextLine(); // Consumir a linha nova apos o proximo int
            divisaoTreinoSelecionada = DivisaoTreinoController.getDivisaoTreinoById(divisaoTreinoId);
        }

        System.out.print("Descricao da Divisao de Treino-musculo: ");
        String descricao = scanner.nextLine().trim();
        while (descricao.isEmpty()) {
            System.out.print("Descricao nao pode estar vazio. Informe novamente: ");
            descricao = scanner.nextLine().trim();
        }

        DivisaoTreinoMusculoController divisaoTreinoMusculoController = new DivisaoTreinoMusculoController();
        DivisaoTreinoMusculo divisaoTreinoMusculo = divisaoTreinoMusculoController
                .geraDivisaoTreinoMusculo(divisaoTreinoId,
                        TreinoController.getTreinoById(divisaoTreinoId).getObjetivo(),
                        descricao, descricao);

        divisoesTreinoMusculo[numDivisoesTreinoMusculo] = divisaoTreinoMusculo;
        numDivisoesTreinoMusculo++;
        return divisaoTreinoMusculo;
    }

    public static void exibirTodasDivisoesTreinoMusculo() {
        if (numDivisoesTreinoMusculo == 0) {
            System.out.println("\n\nNenhuma divisao de treino-musculo cadastrada ainda.");
        } else {
            System.out.println("\n\nLista de Divisoes de Treino-musculo:");
            for (int i = 0; i < numDivisoesTreinoMusculo; i++) {
                System.out.println(
                        "ID: " + divisoesTreinoMusculo[i].getId() +
                                ", Descriçao: " + divisoesTreinoMusculo[i].getDescricao() +
                                ", Nome do Treino: " + divisoesTreinoMusculo[i].getNomeTreino());
            }
        }
    }

    public static void exibirDadosDivisaoTreinoMusculoPorId() {
        System.out.print("\n\nInforme o ID da divisao de treino-musculo: ");
        int idBusca = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        DivisaoTreinoMusculo divisaoTreinoMusculo = DivisaoTreinoMusculoController.getDivisaoTreinoMusculoById(idBusca);
        if (divisaoTreinoMusculo != null) {
            System.out.println(divisaoTreinoMusculo.exibirDetalhes());
        } else {
            System.out.println("Divisao de treino-musculo nao encontrada.");
        }
    }

    public static DivisaoTreinoMusculo atualizarDivisaoTreinoMusculo() {
        System.out.print("\n\nInforme o ID da divisao de treino-musculo que deseja atualizar: ");
        int idDivisaoTreinoMusculo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        DivisaoTreinoMusculo divisaoTreinoMusculo = DivisaoTreinoMusculoController
                .getDivisaoTreinoMusculoById(idDivisaoTreinoMusculo);
        if (divisaoTreinoMusculo != null) {
            System.out.println("\nDados atuais da Divisao de Treino-musculo:");
            System.out.println(divisaoTreinoMusculo.exibirDetalhes());

            System.out.print("Nova Descriçao: ");
            String novaDescricao = scanner.nextLine().trim();
            if (!novaDescricao.isEmpty()) {
                divisaoTreinoMusculo.setDescricao(novaDescricao);
            }

            divisaoTreinoMusculo.setDataModificacao(Calendar.getInstance().getTime());

            System.out.println("\nDados da Divisao de Treino-musculo atualizados com sucesso:");
            System.out.println(divisaoTreinoMusculo.exibirDetalhes());
        } else {
            System.out.println("\nDivisao de Treino-musculo com ID " + idDivisaoTreinoMusculo + " nao encontrada.");
        }

        return divisaoTreinoMusculo;
    }

    public static void removerDivisaoTreinoMusculo() {
        System.out.print("\n\nInforme o ID da divisao de treino-musculo que deseja remover: ");
        int idDivisaoTreinoMusculo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        boolean encontrado = false;
        for (int i = 0; i < numDivisoesTreinoMusculo; i++) {
            if (divisoesTreinoMusculo[i].getId() == idDivisaoTreinoMusculo) {
                for (int j = i; j < numDivisoesTreinoMusculo - 1; j++) {
                    divisoesTreinoMusculo[j] = divisoesTreinoMusculo[j + 1];
                }
                divisoesTreinoMusculo[numDivisoesTreinoMusculo - 1] = null;
                numDivisoesTreinoMusculo--;
                System.out.println("\n\nDivisao de treino-musculo removida com sucesso.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nDivisao de treino-musculo com ID " + idDivisaoTreinoMusculo + " nao encontrada.");
        }
    }
}
