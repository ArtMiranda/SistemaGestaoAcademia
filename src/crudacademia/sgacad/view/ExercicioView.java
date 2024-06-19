package sgacad.view;

import java.util.Scanner;
import java.time.LocalDate;
import sgacad.controller.ExercicioController;
import sgacad.model.Exercicio;

public class ExercicioView {
    public static Exercicio[] exercicios = new Exercicio[100]; // Array de exercicios
    public static int numExercicios = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static Exercicio criaExercicio() {
        // Nome do Exercicio
        System.out.print("Nome do exercicio: ");
        String nome = scanner.nextLine().trim();
        while (nome.isEmpty()) {
            System.out.print("Nome nao pode estar vazio. Informe novamente: ");
            nome = scanner.nextLine().trim();
        }

        // Descricao do exercicio
        System.out.print("Descricao do exercicio: ");
        String descricao = scanner.nextLine().trim();
        while (descricao.isEmpty()) {
            System.out.print("Descricao nao pode estar vazio. Informe novamente: ");
            descricao = scanner.nextLine().trim();
        }

        ExercicioController exercicioController = new ExercicioController();

        Exercicio exercicio = exercicioController.geraExercicio(numExercicios, nome, descricao);
        return exercicio;
    }

    public static void exibirTodosExercicios() {
        if (ExercicioView.numExercicios == 0) {
            System.out.println("\n\nNenhum exercicio cadastrado ainda.");
        } else {
            System.out.println("\n\nLista de Exercicios:");
            for (int i = 0; i < ExercicioView.numExercicios; i++) {
                System.out.println("ID: " + exercicios[i].getId() + ", Nome: " + exercicios[i].getNome());
            }
        }
    }

    public static void exibirDadosExercicioPorId() {
        
        int idBusca = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do exercicio: ");
            if (scanner.hasNextInt()) {
                idBusca = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas numeros inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < ExercicioView.numExercicios; i++) {
            if (exercicios[i].getId() == idBusca) {
                System.out.println("\n\n----- Dados do Exercicio -----\n\n");
                System.out.println(exercicios[i].exibirDetalhes());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nExercicio nao encontrado.");
        }
    }


    public static void atualizarExercicio(){

        int idExercicio = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do exercicio que deseja atualizar: ");
            if (scanner.hasNextInt()) {
                idExercicio = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas numeros inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < ExercicioView.numExercicios; i++) {
            if (exercicios[i].getId() == idExercicio) {
                System.out.println("\nDados atuais do Exercicio:");
                System.out.println(exercicios[i].exibirDetalhes());

                System.out.println("\nInforme os novos dados do exercicio:");

                // Nome do exercicio
                System.out.print("Novo Nome: ");
                String novoNome = scanner.nextLine().trim();
                if (!novoNome.isEmpty()) {
                    exercicios[i].setNome(novoNome);
                }

                // Descricao/Foto do exercicio
                System.out.print("Nova Descricao/Foto: ");
                String novaDescricaoFoto = scanner.nextLine().trim();
                if (!novaDescricaoFoto.isEmpty()) {
                    exercicios[i].setDescricaoFoto(novaDescricaoFoto);
                }

                // Data de modificacao
                exercicios[i].setDataModificacao(LocalDate.now());

                System.out.println("\nDados do Exercicio atualizados com sucesso:");
                System.out.println(exercicios[i].exibirDetalhes());
                break;
            }
        }

        if (!encontrado) {
            System.out.println("\nExercicio com ID " + idExercicio + " nao encontrado.");
        }
    }

    public static void removerExercicio() {
        
        int idExercicio = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do exercicio que deseja remover: ");
            if (scanner.hasNextInt()) {
                idExercicio = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas numeros inteiros.");
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
                System.out.println("\n\nExercicio removido com sucesso.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nExercicio com ID " + idExercicio + " nao encontrado.");
        }
    }
}
