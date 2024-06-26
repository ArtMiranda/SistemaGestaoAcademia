package sgacad.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import sgacad.controller.TreinoController;
import sgacad.controller.DivisaoTreinoController;
import sgacad.model.DivisaoTreino;
import sgacad.model.Treino;

public class DivisaoTreinoView {
    private static Scanner scanner = new Scanner(System.in);
    private static DivisaoTreinoController divisaoTreinoController = new DivisaoTreinoController();

    public static DivisaoTreino criaOuAtualizaDivisaoTreino() {
        TreinoView.exibirTodosTreinos();
        System.out.print("\n\nInforme o Treino: ");
        int treinoId = scanner.nextInt();
        scanner.nextLine(); // Consumir a linha nova apos o proximo int

        Treino treinoSelecionado = TreinoController.getTreinoById(treinoId);
        while (treinoSelecionado == null) {
            System.out.print("Treino invalido. Informe novamente: ");
            treinoId = scanner.nextInt();
            scanner.nextLine(); // Consumir a linha nova apos o proximo int
            treinoSelecionado = TreinoController.getTreinoById(treinoId);
        }

        System.out.print("Descricao da Divisao de Treino: ");
        String descricao = scanner.nextLine().trim();
        while (descricao.isEmpty()) {
            System.out.print("Descricao nao pode estar vazia. Informe novamente: ");
            descricao = scanner.nextLine().trim();
        }

        String nome = treinoSelecionado.getObjetivo();

        DivisaoTreino divisaoTreino = DivisaoTreinoController.geraDivisaoTreino(treinoId, nome, descricao);
        if (divisaoTreino != null) {
            System.out.println("Divisao de treino criada ou atualizada com sucesso: " + divisaoTreino.exibirDetalhes());
        } else {
            System.out.println("Erro ao criar ou atualizar divisao de treino.");
        }
        return divisaoTreino;
    }

    public static void exibirTodasDivisoesTreino() {
        List<DivisaoTreino> divisoesTreino = divisaoTreinoController.getAllDivisoesTreino();
        if (divisoesTreino.isEmpty()) {
            System.out.println("\n\nNenhuma divisao de treino cadastrada ainda.");
        } else {
            System.out.println("\n\nLista de Divisoes de Treino:");
            for (DivisaoTreino divisaoTreino : divisoesTreino) {
                System.out.println(divisaoTreino.exibirDetalhes());
            }
        }
    }

    public static void exibirDadosDivisaoTreinoPorId() {
        System.out.print("\n\nInforme o ID da divisao de treino: ");
        int idBusca = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        DivisaoTreino divisaoTreino = DivisaoTreinoController.getDivisaoTreinoById(idBusca);
        if (divisaoTreino != null) {
            System.out.println(divisaoTreino.exibirDetalhes());
        } else {
            System.out.println("Divisao de treino nao encontrada.");
        }
    }

    public static DivisaoTreino atualizarDivisaoTreino() {
        System.out.print("\n\nInforme o ID da divisao de treino que deseja atualizar: ");
        int idDivisaoTreino = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        DivisaoTreino divisaoTreino = DivisaoTreinoController.getDivisaoTreinoById(idDivisaoTreino);
        if (divisaoTreino != null) {
            System.out.println("\nDados atuais da Divisao de Treino:");
            System.out.println(divisaoTreino.exibirDetalhes());

            System.out.println("\nInforme os novos dados da divisao de treino:");

            System.out.print("Nova Descricao: ");
            String novaDescricao = scanner.nextLine().trim();
            if (!novaDescricao.isEmpty()) {
                divisaoTreino.setDescricao(novaDescricao);
            }

            divisaoTreino.setDataModificacao(LocalDate.now());
            DivisaoTreinoController.updateDivisaoTreino(divisaoTreino);

            System.out.println("\nDados da Divisao de Treino atualizados com sucesso:");
            System.out.println(divisaoTreino.exibirDetalhes());
        } else {
            System.out.println("\nDivisao de Treino com ID " + idDivisaoTreino + " nao encontrada.");
        }
        return divisaoTreino;
    }

    public static void removerDivisaoTreino() {
        System.out.print("\n\nInforme o ID da divisao de treino que deseja remover: ");
        int idDivisaoTreino = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        DivisaoTreino divisaoTreino = DivisaoTreinoController.getDivisaoTreinoById(idDivisaoTreino);
        if (divisaoTreino != null) {
            divisaoTreinoController.deleteDivisaoTreino(idDivisaoTreino);
            System.out.println("\n\nDivisao de treino removida com sucesso.");
        } else {
            System.out.println("\n\nDivisao de treino com ID " + idDivisaoTreino + " nao encontrada.");
        }
    }
}
