package sgacad.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import sgacad.controller.ExercicioAplicacaoController;
import sgacad.model.ExercicioAplicacao;

public class ExercicioAplicacaoView {
    private static Scanner scanner = new Scanner(System.in);
    private static ExercicioAplicacaoController exercicioAplicacaoController = new ExercicioAplicacaoController();

    public static ExercicioAplicacao criarExercicioAplicacao() {
        int idExercicio = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do exercicio que deseja detalhar: ");
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
            if (ExercicioView.exercicios[i].getId() == idExercicio) {
                String exercicioNome = ExercicioView.exercicios[i].getNome();

                // Descricao do exercicio
                System.out.print("Descricao detalhada do exercicio: ");
                String descricao = scanner.nextLine().trim();
                while (descricao.isEmpty()) {
                    System.out.print("Descricao nao pode estar vazio. Informe novamente: ");
                    descricao = scanner.nextLine().trim();
                }
                ExercicioAplicacao exercicio = ExercicioAplicacaoController.geraExercicioAplicacao(idExercicio, exercicioNome, descricao);
                return exercicio;
            }
        }

        if (!encontrado) {
            System.out.println("\n\nExercicio com ID " + idExercicio + " nao encontrado.");
        }

        return null;
    }

    public static void exibirTodosExerciciosAplicacao() {
        List<ExercicioAplicacao> exercicios = exercicioAplicacaoController.listarTodosExerciciosAplicacao();
        if (exercicios.isEmpty()) {
            System.out.println("\n\nNenhuma aplicacao de exercicio cadastrada ainda.");
        } else {
            System.out.println("\n\nLista de Aplicacoes de Exercicios:");
            for (ExercicioAplicacao exercicio : exercicios) {
                System.out.println(
                        "ID: " + exercicio.getId() + ", Nome: " + exercicio.getNome()
                                + ", Descricao detalhada: " + exercicio.getNomeDetalhado());
            }
        }
    }

    public static void exibirDadosExercicioAplicacaoPorId() {
        int idBusca = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID da aplicacao de exercicio: ");
            if (scanner.hasNextInt()) {
                idBusca = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas numeros inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        ExercicioAplicacao exercicio = exercicioAplicacaoController.buscarExercicioAplicacaoPorId(idBusca);
        if (exercicio != null) {
            System.out.println("\n\n----- Dados da Aplicacao de Exercicio -----\n\n");
            System.out.println(exercicio.exibirDetalhes());
        } else {
            System.out.println("\n\nAplicacao de Exercicio nao encontrada.");
        }
    }

    public static void atualizarExercicioAplicacao() {
        int idExercicioAplicacao = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID da aplicacao de exercicio que deseja atualizar: ");
            if (scanner.hasNextInt()) {
                idExercicioAplicacao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas numeros inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        ExercicioAplicacao exercicio = exercicioAplicacaoController.buscarExercicioAplicacaoPorId(idExercicioAplicacao);
        if (exercicio != null) {
            System.out.println("Aplicacao de Exercicio encontrada. Insira os novos dados:");

            // Descricao detalhada da aplicacao de exercicio
            System.out.print("Nova descricao detalhada: ");
            String novaDescricaoDetalhada = scanner.nextLine().trim();
            while (novaDescricaoDetalhada.isEmpty()) {
                System.out.print("Descricao detalhada nao pode estar vazia. Informe novamente: ");
                novaDescricaoDetalhada = scanner.nextLine().trim();
            }
            exercicio.setNomeDetalhado(novaDescricaoDetalhada);
            exercicio.setDataModificacao(LocalDate.now());
            exercicioAplicacaoController.atualizarExercicioAplicacao(exercicio);
            System.out.println("Aplicacao de Exercicio atualizada com sucesso!");
        } else {
            System.out.println("\n\nAplicacao de Exercicio com ID " + idExercicioAplicacao + " nao encontrada.");
        }
    }

    public static void removerExercicioAplicacao() {
        int idExercicioAplicacao = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID da aplicacao de exercicio que deseja remover: ");
            if (scanner.hasNextInt()) {
                idExercicioAplicacao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas numeros inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        ExercicioAplicacao exercicio = exercicioAplicacaoController.buscarExercicioAplicacaoPorId(idExercicioAplicacao);
        if (exercicio != null) {
            exercicioAplicacaoController.deletarExercicioAplicacao(idExercicioAplicacao);
            System.out.println("\n\nAplicacao de Exercicio removida com sucesso.");
        } else {
            System.out.println("\n\nAplicacao de Exercicio com ID " + idExercicioAplicacao + " nao encontrada.");
        }
    }
}
