package sgacad.view;

import java.util.Scanner;
import sgacad.controller.ExercicioController;
import sgacad.model.Exercicio;

public class ExercicioView {
    private static Scanner scanner = new Scanner(System.in);
    private static ExercicioController exercicioController = new ExercicioController();

    public static void criaExercicio() {
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

        ExercicioController.adicionarExercicio(nome, descricao);
    }

    public static void exibirTodosExercicios() {
        Exercicio[] exercicios = ExercicioController.listarExercicios();
        if (exercicios.length == 0) {
            System.out.println("\n\nNenhum exercicio cadastrado ainda.");
        } else {
            System.out.println("\n\nLista de Exercicios:");
            for (Exercicio exercicio : exercicios) {
                System.out.println("ID: " + exercicio.getId() + ", Nome: " + exercicio.getNome());
            }
        }
    }

    public static void exibirDadosExercicioPorId() {
        int idBusca = 0;
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

        Exercicio exercicio = ExercicioController.getExercicioById(idBusca);
        if (exercicio != null) {
            System.out.println("\n\n----- Dados do Exercicio -----\n\n");
            System.out.println(exercicio.exibirDetalhes());
        } else {
            System.out.println("\n\nExercicio nao encontrado.");
        }
    }

    public static void atualizarExercicio() {
        int idExercicio = 0;
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

        Exercicio exercicio = ExercicioController.getExercicioById(idExercicio);
        if (exercicio != null) {
            System.out.println("\nDados atuais do Exercicio:");
            System.out.println(exercicio.exibirDetalhes());

            System.out.println("\nInforme os novos dados do exercicio:");

            // Nome do exercicio
            System.out.print("Novo Nome: ");
            String novoNome = scanner.nextLine().trim();
            if (!novoNome.isEmpty()) {
                exercicio.setNome(novoNome);
            }

            // Descricao/Foto do exercicio
            System.out.print("Nova Descricao/Foto: ");
            String novaDescricaoFoto = scanner.nextLine().trim();
            if (!novaDescricaoFoto.isEmpty()) {
                exercicio.setDescricaoFoto(novaDescricaoFoto);
            }

            exercicioController.atualizarExercicio(idExercicio, novoNome, novaDescricaoFoto);
            System.out.println("\nDados do Exercicio atualizados com sucesso:");
            System.out.println(exercicio.exibirDetalhes());
        } else {
            System.out.println("\nExercicio com ID " + idExercicio + " nao encontrado.");
        }
    }

    public static void removerExercicio() {
        int idExercicio = 0;
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

        Exercicio exercicio = ExercicioController.getExercicioById(idExercicio);
        if (exercicio != null) {
            exercicioController.removerExercicio(idExercicio);
            System.out.println("\nExercicio removido com sucesso!");
        } else {
            System.out.println("\nExercicio com ID " + idExercicio + " nao encontrado.");
        }
    }
}
