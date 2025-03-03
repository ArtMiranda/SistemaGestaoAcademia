package sgacad.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import sgacad.controller.AcademiaController;
import sgacad.controller.ExercicioController;
import sgacad.controller.PessoaController;
import sgacad.controller.TreinoAplicacaoController;
import sgacad.controller.TreinoController;
import sgacad.model.Exercicio;
import sgacad.model.Treino;
import sgacad.model.TreinoAplicacao;

public class TreinoAplicacaoView {
    private static Scanner scanner = new Scanner(System.in);

    public static TreinoAplicacao criaTreinoAplicacao() {
        // Pedir ID de Treino e fazer a verificacao
        Treino treinoSelecionado = null;
        TreinoView.exibirTodosTreinos();
        while (treinoSelecionado == null) {
            System.out.print("\nInforme o ID do Treino: ");
            int idTreino = scanner.nextInt();
            scanner.nextLine(); // Consome a nova linha
            treinoSelecionado = TreinoController.getTreinoById(idTreino);
            if (treinoSelecionado == null) {
                System.out.println("Treino nao encontrado. Tente novamente.");
            }
        }

        // Pedir ID de Exercicio e fazer a verificacao
        Exercicio exercicioSelecionado = null;
        ExercicioView.exibirTodosExercicios();
        while (exercicioSelecionado == null) {
            System.out.print("\nInforme o ID do Exercicio: ");
            int idExercicio = scanner.nextInt();
            scanner.nextLine(); // Consome a nova linha
            exercicioSelecionado = ExercicioController.getExercicioById(idExercicio);
            if (exercicioSelecionado == null) {
                System.out.println("Exercicio nao encontrado. Tente novamente.");
            }
        }

        TreinoAplicacao treinoAplicacao = TreinoAplicacaoController.geraTreinoAplicacao(treinoSelecionado.getId(),
                exercicioSelecionado.getId());
        return treinoAplicacao;
    }

    public static void exibirTodosTreinosAplicacao() {
        List<TreinoAplicacao> treinosAplicacao = TreinoAplicacaoController.getAllTreinosAplicacao();
        if (treinosAplicacao.isEmpty()) {
            System.out.println("Nenhum treino aplicacao cadastrado.");
        } else {
            System.out.println("Lista de Treinos Aplicacao:");
            for (TreinoAplicacao treinoAplicacao : treinosAplicacao) {
                System.out.println("\n\n" + treinoAplicacao.exibirDetalhes());
            }
        }
    }

    public static void exibirTreinoAplicacaoPorId() {
        System.out.print("Informe o ID do treino aplicacao: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha

        TreinoAplicacao treinoAplicacao = TreinoAplicacaoController.getTreinoAplicacaoById(id);
        if (treinoAplicacao != null) {
            System.out.println(treinoAplicacao.exibirDetalhes());
        } else {
            System.out.println("Treino aplicacao nao encontrado.");
        }
    }

    public static void atualizarTreinoAplicacao() {
        System.out.print("Informe o ID do treino aplicacao a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha

        TreinoAplicacao treinoAplicacao = TreinoAplicacaoController.getTreinoAplicacaoById(id);
        if (treinoAplicacao == null) {
            System.out.println("Treino aplicacao nao encontrado.");
            return;
        }

        System.out.println("Dados atuais:");
        System.out.println(treinoAplicacao.exibirDetalhes());

        // Atualizar Treino
        Treino treinoSelecionado = null;
        while (treinoSelecionado == null) {
            System.out.print("Informe o novo ID do Treino (0 para nao alterar): ");
            int novoIdTreino = scanner.nextInt();
            scanner.nextLine(); // Consome a nova linha
            if (novoIdTreino == 0) {
                break; // Nao altera o treino
            }
            treinoSelecionado = TreinoController.getTreinoById(novoIdTreino);
            if (treinoSelecionado == null) {
                System.out.println("Treino nao encontrado. Tente novamente.");
            }
        }

        // Atualizar Exercicio
        Exercicio exercicioSelecionado = null;
        while (exercicioSelecionado == null) {
            System.out.print("Informe o novo ID do Exercicio (0 para nao alterar): ");
            int novoIdExercicio = scanner.nextInt();
            scanner.nextLine(); // Consome a nova linha
            if (novoIdExercicio == 0) {
                break; // Nao altera o exercicio
            }
            exercicioSelecionado = ExercicioController.getExercicioById(novoIdExercicio);
            if (exercicioSelecionado == null) {
                System.out.println("Exercicio nao encontrado. Tente novamente.");
            }
        }

        if (treinoSelecionado != null) {
            treinoAplicacao.setTreino(treinoSelecionado.getObjetivo());
        }

        if (exercicioSelecionado != null) {
            treinoAplicacao.setExercicio(exercicioSelecionado.getNome());
        }

        treinoAplicacao.setDataModificacao(LocalDate.now());
        TreinoAplicacaoController.atualizarTreinoAplicacao(treinoAplicacao);
        System.out.println("Treino aplicacao atualizado com sucesso.");
    }

    public static void removerTreinoAplicacao() {
        System.out.print("Informe o ID do treino aplicacao a ser removido: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha

        TreinoAplicacaoController.removerTreinoAplicacao(id);
        System.out.println("Treino aplicacao removido com sucesso.");
    }

    public static void exibirFichaTreino(int idAluno) {
        System.out.println("Informe o Treino que deseja visualizar a ficha: ");
        exibirTodosTreinosAplicacao();
        System.out.print("\nInforme o ID do treino: ");
        int idTreino = scanner.nextInt();
        scanner.nextLine();

        Treino treino = TreinoController.getTreinoById(idTreino);
        TreinoAplicacao treinoAplicacao = TreinoAplicacaoController.getTreinoAplicacaoById(idTreino);

        if (treinoAplicacao != null) {
            System.out.println("\n\n--- Ficha do Treino: ---");
            System.out.println("Academia: " + AcademiaController.buscarAcademiaUnica().getNome());
            System.out.println("Aluno(a): " + PessoaController.getPessoaById(idAluno, "Aluno").getNome());
            System.out.println("Divisao de Treino: " + treinoAplicacao.getDivisaoTreino());
            System.out.println("Divisao Treino Musculo: " + treinoAplicacao.getDivisaoTreinoAplicacao());

            long duracaoTreinoSemanas = ChronoUnit.WEEKS.between(treinoAplicacao.getDataInicio(), treinoAplicacao.getDataTermino());
            System.out.println("Data de Inicio: " + formatarData(treinoAplicacao.getDataInicio()) +
                    " Data de Termino: " + formatarData(treinoAplicacao.getDataTermino()) +
                    " (" + duracaoTreinoSemanas + " semanas)");

            System.out.println("Treino: " + treino.getObjetivo());
            System.out.println("Exercicios: " + treinoAplicacao.getNomeExercicio());
            System.out.println("Aplicacao de Exercicio: " + treinoAplicacao.getExercicioAplicacao());
        } else {
            System.out.println("\nTreino nao encontrado.");
        }
    }

    private static String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }
}
