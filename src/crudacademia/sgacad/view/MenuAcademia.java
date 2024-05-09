package sgacad.view;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import sgacad.model.*;
import sgacad.controller.*;

public class MenuAcademia {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        criarAdministradorPadrao();

        while (true) {
            if (AcademiaView.academia == null) {
                AcademiaView.criarAcademia();
            } else {
                if (efetuarLogin()) {
                    exibirMenu();
                } else {
                    System.out.println("Login ou senha incorretos. Tente novamente.");
                }
            }
        }
    }

    private static void criarAdministradorPadrao() {
        // Definir os dados do administrador padrão
        String nomePadrao = "Administrador Padrão";
        char sexoPadrao = 'F';
        String loginPadrao = "adm";
        String senhaPadrao = "adm";

        // Definir a data de nascimento como a data atual
        Date dataAtual = Calendar.getInstance().getTime();

        PessoaController administradorController = new PessoaController();
        // Criar o objeto Pessoa para representar o administrador padrão
        Pessoa administradorPadrao = administradorController.criarPessoa(PessoaView.numAdministradores,
                nomePadrao,
                sexoPadrao,
                dataAtual, loginPadrao, senhaPadrao, "Administrador");

        // Adicionar o administrador padrão ao array de administradores
        PessoaView.administradores[PessoaView.numAdministradores] = administradorPadrao;
        PessoaView.numAdministradores++;
        System.out.println("Administrador padrão criado com sucesso!");
    }

    private static boolean efetuarLogin() {
        System.out.println("\n\n----- Faça login ou digite 'sair' para sair do programa -----");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        if (login.equalsIgnoreCase("sair")) {
            System.out.println("Saindo do programa...");
            System.exit(0); // Terminar o programa
        }
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (int i = 0; i < PessoaView.numAdministradores; i++) {
            if (login.equals(PessoaView.administradores[i].getLogin())
                    && senha.equals(PessoaView.administradores[i].getSenha())) {
                System.out.println("Login de Administrador bem-sucedido!");
                return true;
            }
        }
        for (int i = 0; i < PessoaView.numProfessoresInstrutores; i++) {
            if (login.equals(PessoaView.professoresInstrutores[i].getLogin())
                    && senha.equals(PessoaView.professoresInstrutores[i].getSenha())) {
                System.out.println("Login de Professor/Instrutor bem-sucedido!");
                return true;
            }
        }
        for (int i = 0; i < PessoaView.numAlunos; i++) {
            if (login.equals(PessoaView.alunos[i].getLogin()) && senha.equals(PessoaView.alunos[i].getSenha())) {
                System.out.println("Login de Aluno bem-sucedido!");
                return true;
            }
        }
        return false;
    }

    private static void exibirMenu() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu da Academia -----");
            System.out.println("1. Exibir Detalhes da Academia");
            System.out.println("2. CRUD Aluno");
            System.out.println("3. CRUD Administrador");
            System.out.println("4. CRUD Professor/Instrutor");
            System.out.println("5. CRUD Exercicios");
            System.out.println("6. CRUD Exercicios Aplicacao");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    AcademiaView.exibirDetalhesAcademia(AcademiaView.academia);
                    break;
                case 2:
                    exibirMenuCRUDAluno();
                    break;
                case 3:
                    exibirMenuCRUDAdministrador();
                    break;
                case 4:
                    exibirMenuCRUDProfessor();
                    break;
                case 5:
                    exibirMenuCRUDExercicios();
                    break;
                case 6:
                    exibirMenuCRUDExerciciosAplicacao();
                    break;
                case 0:
                    System.out.println("Deslogando...");
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void exibirMenuCRUDAluno() {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n\n----- Menu CRUD Aluno -----");
            System.out.println("1. Criar Aluno");
            System.out.println("2. Exibir Todos os Alunos");
            System.out.println("3. Exibir Dados do Aluno por ID");
            System.out.println("4. Atualizar Aluno");
            System.out.println("5. Remover Aluno por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    criarAluno();
                    break;
                case 2:
                    PessoaView.exibirTodosAlunos();
                    break;
                case 3:
                    PessoaView.exibirTodosAlunos();
                    PessoaView.exibirDadosAlunoPorId();
                    break;
                case 4:
                    PessoaView.exibirTodosAlunos();
                    PessoaView.atualizarAluno();
                    break;
                case 5:
                    PessoaView.exibirTodosAlunos();

                    PessoaView.removerAlunoPorId();
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    public static Pessoa criarAluno() {
        System.out.println("\n\n----- Criação da Conta de Aluno -----");
        Pessoa aluno = PessoaView.criarPessoa("Aluno");

        PessoaView.alunos[PessoaView.numAlunos] = aluno;
        PessoaView.numAlunos++;
        return aluno;
    }

    private static void exibirMenuCRUDAdministrador() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu do Administrador -----");
            System.out.println("1. Criar conta de Administrador");
            System.out.println("2. Exibir Todos os Administradores");
            System.out.println("3. Exibir Dados do Administrador por ID");
            System.out.println("4. Atualizar Administrador");
            System.out.println("5. Remover Administrador por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    criarContaAdministrador();
                    break;
                case 2:
                    PessoaView.exibirTodosAdministradores();
                    break;
                case 3:
                    PessoaView.exibirTodosAdministradores();
                    PessoaView.exibirDadosAdministradorPorId();
                    break;
                case 4:
                    PessoaView.exibirTodosAdministradores();
                    PessoaView.atualizarAdministrador();
                    break;
                case 5:
                    PessoaView.exibirTodosAdministradores();
                    PessoaView.removerAdministradorPorId();
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal...");
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void criarContaAdministrador() {
        System.out.println("\n\n----- Criação da Conta do Administrador -----");
        PessoaView.administradores[PessoaView.numAdministradores] = criarAdministrador();
        PessoaView.numAdministradores++;
    }

    private static Pessoa criarAdministrador() {
        System.out.println("Informe os dados para criar o Administrador:");

        Pessoa admin = PessoaView.criarPessoa("Administrador");

        return admin;
    }

    private static void exibirMenuCRUDProfessor() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu do Professor/Instrutor -----");
            System.out.println("1. Criar conta de Professor/Instrutor");
            System.out.println("2. Exibir Todos os Professor/Instrutor");
            System.out.println("3. Exibir Dados do Professor/Instrutor por ID");
            System.out.println("4. Atualizar Professor/Instrutor");
            System.out.println("5. Remover Professor/Instrutor por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    criarContaProfessor();
                    break;
                case 2:
                    PessoaView.exibirTodosProfessoresInstrutores();
                    break;
                case 3:
                    PessoaView.exibirTodosProfessoresInstrutores();
                    PessoaView.exibirDadosProfessorPorId();
                    break;
                case 4:
                    PessoaView.exibirTodosProfessoresInstrutores();

                    PessoaView.atualizarProfessorInstrutor();
                    break;
                case 5:
                    PessoaView.exibirTodosProfessoresInstrutores();
                    PessoaView.removerProfessorPorId();
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal...");
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void criarContaProfessor() {
        System.out.println("\n\n----- Criação da Conta do Professor/Instrutor -----");
        PessoaView.professoresInstrutores[PessoaView.numProfessoresInstrutores] = criarProfessorInstrutor();
        PessoaView.numProfessoresInstrutores++;
    }

    private static Pessoa criarProfessorInstrutor() {
        System.out.println("Informe os dados para criar o Professor/Instrutor:");

        Pessoa prof = PessoaView.criarPessoa("Professor/Instrutor");

        return prof;
    }

    private static void exibirMenuCRUDExercicios() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu de Exercícios -----");
            System.out.println("1. Criar Exercício");
            System.out.println("2. Exibir Todos os Exercícios");
            System.out.println("3. Exibir Dados do Exercício por ID");
            System.out.println("4. Atualizar Exercício");
            System.out.println("5. Remover Exercício por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    criaExercicio();
                    break;
                case 2:
                    ExercicioView.exibirTodosExercicios();
                    break;
                case 3:
                    ExercicioView.exibirTodosExercicios();
                    ExercicioView.exibirDadosExercicioPorId();
                    break;
                case 4:
                    ExercicioView.exibirTodosExercicios();
                    ExercicioView.atualizarExercicio();
                    break;
                case 5:
                    ExercicioView.exibirTodosExercicios();
                    ExercicioView.removerExercicio();
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal...");
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static Exercicio criaExercicio() {
        System.out.println("Informe os dados para criar o Administrador:");

        Exercicio exercicio = ExercicioView.criaExercicio();

        ExercicioView.exercicios[ExercicioView.numExercicios++] = exercicio;

        return exercicio;
    }

    private static void exibirMenuCRUDExerciciosAplicacao() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu de Exercícios Aplicação -----");
            System.out.println("1. Definir Aplicação do Exercicio");
            System.out.println("2. Exibir todas as Aplicações de Exercícios");
            System.out.println("3. Exibir Dados das Aplicações de Exercício por ID");
            System.out.println("4. Atualizar Aplicação de Exercício");
            System.out.println("5. Remover Aplicação de Exercício por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    ExercicioView.exibirTodosExercicios();
                    criaExercicioAplicacao();
                    break;
                case 2:
                    ExercicioAplicacaoView.exibirTodosExerciciosAplicacao();
                    break;
                case 3:
                    ExercicioAplicacaoView.exibirTodosExerciciosAplicacao();
                    ExercicioAplicacaoView.exibirDadosExercicioAplicacaoPorId();
                    break;
                case 4:
                    ExercicioAplicacaoView.exibirTodosExerciciosAplicacao();
                    ExercicioAplicacaoView.atualizarExercicioAplicacao();
                    break;
                case 5:
                    ExercicioAplicacaoView.exibirTodosExerciciosAplicacao();

                    ExercicioAplicacaoView.removerExercicioAplicacao();
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal...");
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void criaExercicioAplicacao() {
        System.out.println("\n\n----- Criação da Aplicação de Exercício -----");
        ExercicioAplicacao exercicioAplicacao = ExercicioAplicacaoView.criarExercicioAplicacao();

        ExercicioAplicacaoView.exerciciosAplicacao[ExercicioAplicacaoView.numExerciciosAplicacao++] = exercicioAplicacao;
    }

}
