package sgacad.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import sgacad.controller.DivisaoTreinoController;
import sgacad.controller.DivisaoTreinoMusculoController;
import sgacad.model.DivisaoTreino;
import sgacad.model.DivisaoTreinoMusculo;

public class DivisaoTreinoMusculoView {
    private static Scanner scanner = new Scanner(System.in);
    private static DivisaoTreinoMusculoController divisaoTreinoMusculoController = new DivisaoTreinoMusculoController();

    public static DivisaoTreinoMusculo criaOuAtualizaDivisaoTreinoMusculo() {
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
        String divTreinoMusculo = scanner.nextLine().trim();
        while (divTreinoMusculo.isEmpty()) {
            System.out.print("Descricao nao pode estar vazia. Informe novamente: ");
            divTreinoMusculo = scanner.nextLine().trim();
        }

        String nomeTreino = divisaoTreinoSelecionada.getNome();
        String descricao = divisaoTreinoSelecionada.getDescricao();

        DivisaoTreinoMusculo divisaoTreinoMusculo = DivisaoTreinoMusculoController
                .geraOuAtualizaDivisaoTreinoMusculo(divisaoTreinoId, nomeTreino, descricao, divTreinoMusculo);
        if (divisaoTreinoMusculo != null) {
            System.out.println("Divisao de treino-musculo criada ou atualizada com sucesso: " + divisaoTreinoMusculo.exibirDetalhes());
        } else {
            System.out.println("Erro ao criar ou atualizar divisao de treino-musculo.");
        }
        return divisaoTreinoMusculo;
    }

    public static void exibirTodasDivisoesTreinoMusculo() {
        List<DivisaoTreinoMusculo> divisoesTreinoMusculo = divisaoTreinoMusculoController.getAllDivisoesTreinoMusculo();
        if (divisoesTreinoMusculo.isEmpty()) {
            System.out.println("\n\nNenhuma divisao de treino-musculo cadastrada ainda.");
        } else {
            System.out.println("\n\nLista de Divisoes de Treino-musculo:");
            for (DivisaoTreinoMusculo divisaoTreinoMusculo : divisoesTreinoMusculo) {
                System.out.println(divisaoTreinoMusculo.exibirDetalhes());
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

            System.out.print("Nova Descricao: ");
            String novaDescricao = scanner.nextLine().trim();
            if (!novaDescricao.isEmpty()) {
                divisaoTreinoMusculo.setDescricao(novaDescricao);
            }

            System.out.print("Novo Nome: ");
            String novoNome = scanner.nextLine().trim();
            if (!novoNome.isEmpty()) {
                divisaoTreinoMusculo.setNomeTreino(novoNome);
            }

            divisaoTreinoMusculo.setDataModificacao(LocalDate.now());
            DivisaoTreinoMusculoController.updateDivisaoTreinoMusculo(divisaoTreinoMusculo);

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

        DivisaoTreinoMusculo divisaoTreinoMusculo = DivisaoTreinoMusculoController.getDivisaoTreinoMusculoById(idDivisaoTreinoMusculo);
        if (divisaoTreinoMusculo != null) {
            divisaoTreinoMusculoController.deleteDivisaoTreinoMusculo(idDivisaoTreinoMusculo);
            System.out.println("\n\nDivisao de treino-musculo removida com sucesso.");
        } else {
            System.out.println("\n\nDivisao de treino-musculo com ID " + idDivisaoTreinoMusculo + " nao encontrada.");
        }
    }
}
