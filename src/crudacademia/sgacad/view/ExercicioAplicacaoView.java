package sgacad.view;

import java.time.LocalDate;
import java.util.Scanner;

import sgacad.controller.ExercicioAplicacaoController;
import sgacad.model.ExercicioAplicacao;

public class ExercicioAplicacaoView {
    public static ExercicioAplicacao[] exerciciosAplicacao = new ExercicioAplicacao[100]; // Array de exercicios aplicaco
    public static int numExerciciosAplicacao = 0; // Contador de exercicios aplicacao
    private static Scanner scanner = new Scanner(System.in);

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
                for (int j = i; j < ExercicioView.numExercicios - 1; j++) {
                    ExercicioView.exercicios[j] = ExercicioView.exercicios[j + 1];
                }
                int exercicioID = ExercicioView.exercicios[ExercicioView.numExercicios - 1].getId();
                String exercicioNome = ExercicioView.exercicios[ExercicioView.numExercicios - 1].getNome();

                // Descricao do exercicio
                System.out.print("Descricao detalhada do exercicio: ");
                String descricao = scanner.nextLine().trim();
                while (descricao.isEmpty()) {
                    System.out.print("Descricao nao pode estar vazio. Informe novamente: ");
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
            System.out.println("\n\nExercicio com ID " + idExercicio + " nao encontrado.");
        }

        return null;
    }

    public static void exibirTodosExerciciosAplicacao() {
        if (ExercicioAplicacaoView.numExerciciosAplicacao == 0) {
            System.out.println("\n\nNenhuma aplicacao de exercicio cadastrada ainda.");
        } else {
            System.out.println("\n\nLista de Aplicacoes de Exercicios:");
            for (int i = 0; i < ExercicioAplicacaoView.numExerciciosAplicacao; i++) {
                System.out.println(
                        "ID: " + exerciciosAplicacao[i].getId() + ", Nome: " + exerciciosAplicacao[i].getNome()
                                + ", Descricao detalhada: " + exerciciosAplicacao[i].getNomeDetalhado());
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

        boolean encontrado = false;
        for (int i = 0; i < ExercicioAplicacaoView.numExerciciosAplicacao; i++) {
            if (exerciciosAplicacao[i].getId() == idBusca) {
                System.out.println("\n\n----- Dados da Aplicacao de Exercicio -----\n\n");
                System.out.println(exerciciosAplicacao[i].exibirDetalhes());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
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

        boolean encontrado = false;
        for (int i = 0; i < ExercicioAplicacaoView.numExerciciosAplicacao; i++) {
            if (exerciciosAplicacao[i].getId() == idExercicioAplicacao) {
                System.out.println("Aplicacao de Exercicio encontrada. Insira os novos dados:");

                // Descricao detalhada da aplicacao de exercicio
                System.out.print("Nova descricao detalhada: ");
                String novaDescricaoDetalhada = scanner.nextLine().trim();
                while (novaDescricaoDetalhada.isEmpty()) {
                    System.out.print("Descricao detalhada nao pode estar vazia. Informe novamente: ");
                    novaDescricaoDetalhada = scanner.nextLine().trim();
                }
                exerciciosAplicacao[i].setNomeDetalhado(novaDescricaoDetalhada);

                // Data de modificacao
                exerciciosAplicacao[i].setDataModificacao(LocalDate.now());

                System.out.println("Aplicacao de Exercicio atualizada com sucesso!");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
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

        boolean encontrado = false;
        for (int i = 0; i < ExercicioAplicacaoView.numExerciciosAplicacao; i++) {
            if (exerciciosAplicacao[i].getId() == idExercicioAplicacao) {
                for (int j = i; j < ExercicioAplicacaoView.numExerciciosAplicacao - 1; j++) {
                    exerciciosAplicacao[j] = exerciciosAplicacao[j + 1];
                }
                exerciciosAplicacao[ExercicioAplicacaoView.numExerciciosAplicacao - 1] = null;
                ExercicioAplicacaoView.numExerciciosAplicacao--;
                System.out.println("\n\nAplicacao de Exercicio removida com sucesso.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nAplicacao de Exercicio com ID " + idExercicioAplicacao + " nao encontrado.");
        }
    }


}