package sgacad.view;

import java.util.Scanner;

import sgacad.controller.ExercicioAplicacaoController;
import sgacad.model.ExercicioAplicacao;

public class ExercicioAplicacaoView {
    public static int numExerciciosAplicacao = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static ExercicioAplicacao criarExercicioAplicacao() {

        System.out.print("\n\nInforme o ID do exercício que deseja detalhar: ");
        int idExercicio = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        boolean encontrado = false;
        for (int i = 0; i < ExercicioView.numExercicios; i++) {
            if (MenuAcademia.exercicios[i].getId() == idExercicio) {
                for (int j = i; j < ExercicioView.numExercicios - 1; j++) {
                    MenuAcademia.exercicios[j] = MenuAcademia.exercicios[j + 1];
                }
                int exercicioID = MenuAcademia.exercicios[ExercicioView.numExercicios - 1].getId();
                String exercicioNome = MenuAcademia.exercicios[ExercicioView.numExercicios - 1].getNome();

                // Descricao do exercicio
                System.out.print("Descricao detalhada do exercício: ");
                String descricao = scanner.nextLine().trim();
                while (descricao.isEmpty()) {
                    System.out.print("Descricao não pode estar vazio. Informe novamente: ");
                    descricao = scanner.nextLine().trim();
                }
                ExercicioAplicacaoController exercicioAplicacao = new ExercicioAplicacaoController();
                ExercicioAplicacao exercicio = exercicioAplicacao.geraExercicioAplicacao(exercicioID, exercicioNome,
                        descricao);
                return exercicio;
            }

            encontrado = true;
        }

        if (!encontrado) {
            System.out.println("\n\nExercício com ID " + idExercicio + " não encontrado.");
        }

        return null;
    }
}