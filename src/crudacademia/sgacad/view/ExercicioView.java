package sgacad.view;

import java.util.Calendar;
import java.util.Scanner;

import sgacad.controller.ExercicioController;
import sgacad.model.Exercicio;

public class ExercicioView {
    public static Exercicio[] exercicios = new Exercicio[100]; // Array de exercicios
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

    public static void exibirTodosExercicios() {
        if (ExercicioView.numExercicios == 0) {
            System.out.println("\n\nNenhum exercício cadastrado ainda.");
        } else {
            System.out.println("\n\nLista de Exercícios:");
            for (int i = 0; i < ExercicioView.numExercicios; i++) {
                System.out.println("ID: " + exercicios[i].getId() + ", Nome: " + exercicios[i].getNome());
            }
        }
    }

    public static void exibirDadosExercicioPorId() {
        
        int idBusca = 0;

        // Loop de validacão
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do exercício: ");
            if (scanner.hasNextInt()) {
                idBusca = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas números inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < ExercicioView.numExercicios; i++) {
            if (exercicios[i].getId() == idBusca) {
                System.out.println("\n\n----- Dados do Exercício -----\n\n");
                System.out.println(exercicios[i].exibirDetalhes());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nExercício não encontrado.");
        }
    }


    public static void atualizarExercicio(){

        int idExercicio = 0;

        // Loop de validacão
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do exercício que deseja atualizar: ");
            if (scanner.hasNextInt()) {
                idExercicio = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas números inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < ExercicioView.numExercicios; i++) {
            if (exercicios[i].getId() == idExercicio) {
                System.out.println("\nDados atuais do Exercício:");
                System.out.println(exercicios[i].exibirDetalhes());

                System.out.println("\nInforme os novos dados do exercício:");

                // Nome do exercício
                System.out.print("Novo Nome: ");
                String novoNome = scanner.nextLine().trim();
                if (!novoNome.isEmpty()) {
                    exercicios[i].setNome(novoNome);
                }

                // Descricão/Foto do exercício
                System.out.print("Nova Descricão/Foto: ");
                String novaDescricaoFoto = scanner.nextLine().trim();
                if (!novaDescricaoFoto.isEmpty()) {
                    exercicios[i].setDescricaoFoto(novaDescricaoFoto);
                }

                // Data de modificacão
                exercicios[i].setDataModificacao(Calendar.getInstance().getTime());

                System.out.println("\nDados do Exercício atualizados com sucesso:");
                System.out.println(exercicios[i].exibirDetalhes());
                break;
            }
        }

        if (!encontrado) {
            System.out.println("\nExercício com ID " + idExercicio + " não encontrado.");
        }
    }

    public static void removerExercicio() {
        
        int idExercicio = 0;

        // Loop de validacão
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do exercício que deseja remover: ");
            if (scanner.hasNextInt()) {
                idExercicio = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas números inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < ExercicioView.numExercicios; i++) {
            if (exercicios[i].getId() == idExercicio) {
                for (int j = i; j < ExercicioView.numExercicios - 1; j++) {
                    exercicios[j] = exercicios[j + 1];
                }
                exercicios[ExercicioView.numExercicios - 1] = null;
                ExercicioView.numExercicios--;
                System.out.println("\n\nExercício removido com sucesso.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nExercício com ID " + idExercicio + " não encontrado.");
        }
    }
}
