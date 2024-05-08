package sgacad.view;

import java.util.Scanner;

import sgacad.controller.ExercicioController;
import sgacad.model.Exercicio;

public class ExercicioView {
    public static int numExercicios = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static Exercicio criaExercicio() {
        // Nome do Exercicio
        System.out.print("Nome do exercício: ");
        String nome = scanner.nextLine().trim();
        while (nome.isEmpty()) {
            System.out.print("Nome não pode estar vazio. Informe novamente: ");
            nome = scanner.nextLine().trim();
        }

        // Descricao do exercicio
        System.out.print("Descricao do exercício: ");
        String descricao = scanner.nextLine().trim();
        while (descricao.isEmpty()) {
            System.out.print("Descricao não pode estar vazio. Informe novamente: ");
            descricao = scanner.nextLine().trim();
        }

        ExercicioController exercicioController = new ExercicioController();

        Exercicio exercicio = exercicioController.geraExercicio(numExercicios, nome, descricao);
        return exercicio;
    }

}
