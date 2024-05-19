package sgacad.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import sgacad.controller.ExercicioController;
import sgacad.controller.PessoaController;
import sgacad.controller.TreinoAplicacaoController;
import sgacad.controller.TreinoController;
import sgacad.model.Exercicio;
import sgacad.model.Treino;
import sgacad.model.TreinoAplicacao;

public class TreinoAplicacaoView {
    public static TreinoAplicacao[] treinosAplicacao = new TreinoAplicacao[100];
    public static int numTreinosAplicacao = 0;
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

        TreinoAplicacaoController controller = new TreinoAplicacaoController();
        TreinoAplicacao treinoAplicacao = controller.geraTreinoAplicacao(treinoSelecionado.getId(), exercicioSelecionado.getId());
        treinosAplicacao[numTreinosAplicacao] = treinoAplicacao;
        numTreinosAplicacao++;
        return treinoAplicacao;
    }

    public static void exibirTodosTreinosAplicacao() {
        if (numTreinosAplicacao == 0) {
            System.out.println("Nenhum treino aplicacao cadastrado.");
        } else {
            System.out.println("Lista de Treinos Aplicacao:");
            for (int i = 0; i < numTreinosAplicacao; i++) {
                System.out.println("\n\n" + treinosAplicacao[i].exibirDetalhes());
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
            treinoAplicacao.setExercicio(exercicioSelecionado.getNome());;
        }

        treinoAplicacao.setDataModificacao(Calendar.getInstance().getTime());
        System.out.println("Treino aplicacao atualizado com sucesso.");
    }

    public static void removerTreinoAplicacao() {
        System.out.print("Informe o ID do treino aplicacao a ser removido: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha

        boolean encontrado = false;
        for (int i = 0; i < numTreinosAplicacao; i++) {
            if (treinosAplicacao[i].getId() == id) {
                for (int j = i; j < numTreinosAplicacao - 1; j++) {
                    treinosAplicacao[j] = treinosAplicacao[j + 1];
                }
                treinosAplicacao[numTreinosAplicacao - 1] = null;
                numTreinosAplicacao--;
                System.out.println("Treino aplicacao removido com sucesso.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Treino aplicacao nao encontrado.");
        }
    }

    public static void exibirFichaTreino(int idAluno){
        System.out.println("Informe o Treino que deseja visualizar a ficha: ");
        exibirTodosTreinosAplicacao();
        System.out.print("\nInforme o ID do treino: ");
        int idTreino = scanner.nextInt();
        scanner.nextLine();
        Treino treino = TreinoController.getTreinoById(idTreino);
        for (int i = 0; i < numTreinosAplicacao; i++) {
            if(TreinoAplicacaoController.getTreinoAplicacaoById(idTreino).getId() == idTreino){
                System.out.println("\n\n--- Ficha do Treino: ---");
                System.out.println("Academia: " + AcademiaView.academia.getNome());
                System.out.println("Aluno(a): " + PessoaController.getAlunoById(idAluno).getNome());
                System.out.println("Divisao de Treino: " + TreinoAplicacaoController.getTreinoAplicacaoById(idTreino).getDivisaoTreino());
                System.out.println("Divisao Treino Musculo: " + TreinoAplicacaoController.getTreinoAplicacaoById(idTreino).getDivisaoTreinoAplicacao());
                int duracaoTreinoSemanas = (int) ((TreinoAplicacaoController.getTreinoAplicacaoById(idTreino).getDataTermino().getTime() - TreinoAplicacaoController.getTreinoAplicacaoById(idTreino).getDataInicio().getTime()) / (1000 * 60 * 60 * 24 * 7));
                System.out.println("Data de Inicio: " + formatarData(TreinoAplicacaoController.getTreinoAplicacaoById(idTreino).getDataInicio()) + " Data de Termino: " + formatarData(TreinoAplicacaoController.getTreinoAplicacaoById(idTreino).getDataTermino()) + " (" + duracaoTreinoSemanas + " semanas)");    
                System.out.println("Treino: " + treino.getObjetivo());
                System.out.println("Exercicios: " + TreinoAplicacaoController.getTreinoAplicacaoById(idTreino).getNomeExercicio());
                System.out.println("Aplicacao de Exercicio: " + TreinoAplicacaoController.getTreinoAplicacaoById(idTreino).getExercicioAplicacao());    
            }
            
        } if(TreinoAplicacaoController.getTreinoAplicacaoById(idTreino) == null){
            System.out.println("\nTreino nao encontrado.");
        }
    }

    public static String formatarData(Date data){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }
}
