package sgacad.view;

import java.util.Scanner;
import sgacad.model.*;
import sgacad.controller.*;

public class MenuAcademia {
    private static Scanner scanner = new Scanner(System.in);
    private static int idAlunoLogado;

    public static void main(String[] args) {

        while (true) {

            if (AcademiaController.checarExistenciaAcademia() == false){
                AcademiaView.criarAcademia();
                PopularTabelasController.main(args);
            } else {
                if (!efetuarLogin()) {
                    System.out.println("Login ou senha incorretos. Tente novamente.");
                }
            }
        }
    }

    private static boolean efetuarLogin() {
        System.out.println("\n\n----- Faca login ou digite 'sair' para sair do programa -----");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        if (login.equalsIgnoreCase("sair")) {
            System.out.println("Saindo do programa...");
            System.exit(0); // Terminar o programa
        }
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
    
        // Tentativa de autenticar como administrador
        Pessoa administrador = PessoaController.autenticarPessoa(login, senha, "Administrador");
        if (administrador != null) {
            System.out.println("Login de Administrador bem-sucedido!");
            exibirMenuADM();
            return true;
        }
    
        // Tentativa de autenticar como professor/instrutor
        Pessoa professorInstrutor = PessoaController.autenticarPessoa(login, senha, "Professor/Instrutor");
        if (professorInstrutor != null) {
            System.out.println("Login de Professor/Instrutor bem-sucedido!");
            exibirMenuPRO();
            return true;
        }
    
        // Tentativa de autenticar como aluno
        Pessoa aluno = PessoaController.autenticarPessoa(login, senha, "Aluno");
        if (aluno != null) {
            if (AlunoPagamentoMensalidadeController.getPorIdAluno(aluno.getId())) {
                EntradaAlunoController.gerarEntradaAluno(aluno.getId());
                idAlunoLogado = aluno.getId();
                System.out.println("Login de Aluno bem-sucedido!");
                exibirMenuALUNO();
            } else {
                System.out.println("Mensalidade nao esta paga, procure alguem na secretaria e regularize sua matricula!");
            }
            return true;
        }
    
        return false;
    }
    

    private static void exibirMenuADM() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu da Academia -----");
            System.out.println("1. Exibir Detalhes da Academia");
            System.out.println("2. CRUD Aluno");
            System.out.println("3. CRUD Administrador");
            System.out.println("4. CRUD Professor/Instrutor");
            System.out.println("5. CRUD Exercicios");
            System.out.println("6. CRUD Exercicios Aplicacao");
            System.out.println("7. CRUD Treino");
            System.out.println("8. CRUD Divisao de Treino");
            System.out.println("9. CRUD Divisao de Treino Musculo");
            System.out.println("10. CRUD Treino Aplicacao");
            System.out.println("11. CRUD Avaliacao Fisica");
            System.out.println("12. CRUD Mensalidade");
            System.out.println("13. CRUD Pagamento de Mensalidade");
            System.out.println("14. CRUD Entrada de Aluno");
            System.out.println("15. Movimentacoes Financeiras");
            System.out.println("16. CRUD Pagamento Recorrente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    AcademiaView.exibirDetalhesAcademia(AcademiaController.buscarAcademiaUnica());
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
                case 7:
                    exibirMenuCRUDTreino();
                    break;
                case 8:
                    exibirMenuCRUDDivisaoTreino();
                    break;
                case 9:
                    exibirMenuCRUDDivisaoTreinoMusculo();
                    break;
                case 10:
                    exibirMenuCRUDTreinoAplicacao();
                    break;
                case 11:
                    exibirMenuCRUDAvaliacaoFisica();
                    break;
                case 12:
                    exibirMenuMensalidade();
                    break;
                case 13:
                    exibirMenuCRUDAlunoPagamentoMensalidade();
                    break;
                case 14:
                    exibirMenuCRUDEntradaAluno();
                    break;
                case 15:
                    exibirMenuCRUDMovimentacoesFinanceiras();
                    break;
                case 16:
                    exibirMenuCRUDPagamentoRecorrente();
                    break;
                case 0:
                    System.out.println("Deslogando...");
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        }
    }

    private static void exibirMenuPRO() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu da Academia -----");
            System.out.println("1. Exibir Detalhes da Academia");
            System.out.println("2. CRUD Aluno");
            System.out.println("3. CRUD Administrador");
            System.out.println("4. CRUD Professor/Instrutor");
            System.out.println("5. CRUD Exercicios");
            System.out.println("6. CRUD Exercicios Aplicacao");
            System.out.println("7. CRUD Treino");
            System.out.println("8. CRUD Divisao de Treino");
            System.out.println("9. CRUD Divisao de Treino Musculo");
            System.out.println("10. CRUD Treino Aplicacao");
            System.out.println("11. CRUD Avaliacao Fisica");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    AcademiaView.exibirDetalhesAcademia(AcademiaController.buscarAcademiaUnica());
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
                case 7:
                    exibirMenuCRUDTreino();
                    break;
                case 8:
                    exibirMenuCRUDDivisaoTreino();
                    break;
                case 9:
                    exibirMenuCRUDDivisaoTreinoMusculo();
                    break;
                case 10:
                    exibirMenuCRUDTreinoAplicacao();
                    break;
                case 11:
                    exibirMenuCRUDAvaliacaoFisica();
                    break;
                case 0:
                    System.out.println("Deslogando...");
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        }
    }

    public static void exibirMenuALUNO() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu da Academia -----");
            System.out.println("1. Exibir Detalhes da Academia");
            System.out.println("2. Visualizar ficha de Treino");
            System.out.println("3. CRUD Avaliacao Fisica");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    AcademiaView.exibirDetalhesAcademia(AcademiaController.buscarAcademiaUnica());
                    break;
                case 2:
                    TreinoAplicacaoView.exibirFichaTreino(idAlunoLogado);
                    break;
                case 3:
                    exibirMenuCRUDAvaliacaoFisica();
                    break;
                case 0:
                    System.out.println("Deslogando...");
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
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
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    criarAluno();
                    break;
                case 2:
                PessoaView.exibirTodos("Aluno");
                break;
                case 3:
                PessoaView.exibirTodos("Aluno");
                PessoaView.exibirDadosPorId("Aluno");
                    break;
                case 4:
                PessoaView.exibirTodos("Aluno");
                PessoaView.atualizarPessoa("Aluno");
                    break;
                case 5:
                PessoaView.exibirTodos("Aluno");
                PessoaView.removerPessoaPorId("Aluno");
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        }
    }

    public static void criarAluno() {
        System.out.println("\n\n----- Criacao da Conta de Aluno -----");
        PessoaView.criarPessoa("Aluno");
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
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    criarContaAdministrador();
                    break;
                case 2:
                    PessoaView.exibirTodos("Administrador");
                    break;
                case 3:
                PessoaView.exibirTodos("Administrador");
                PessoaView.exibirDadosPorId("Administrador");
                    break;
                case 4:
                    PessoaView.exibirTodos("Administrador");
                    PessoaView.atualizarPessoa("Administrador");
                    break;
                case 5:
                PessoaView.exibirTodos("Administrador");
                PessoaView.removerPessoaPorId("Administrador");
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal...");
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        }
    }

    private static void criarContaAdministrador() {
        System.out.println("\n\n----- Criacao da Conta do Administrador -----");
        criarAdministrador();
    }

    private static void criarAdministrador() {
        System.out.println("Informe os dados para criar o Administrador:");
        PessoaView.criarPessoa("Administrador");
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
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    criarContaProfessor();
                    break;
                case 2:
                PessoaView.exibirTodos("Professor/Instrutor");
                    break;
                case 3:
                PessoaView.exibirTodos("Professor/Instrutor");
                PessoaView.exibirDadosPorId("Professor/Instrutor");
                    break;
                case 4:
                PessoaView.exibirTodos("Professor/Instrutor");
                PessoaView.atualizarPessoa("Professor/Instrutor");
                    break;
                case 5:
                PessoaView.exibirTodos("Professor/Instrutor");
                PessoaView.removerPessoaPorId("Professor/Instrutor");
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal...");
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        }
    }

    private static void criarContaProfessor() {
        System.out.println("\n\n----- Criacao da Conta do Professor/Instrutor -----");
        criarProfessorInstrutor();
    }

    private static void criarProfessorInstrutor() {
        System.out.println("Informe os dados para criar o Professor/Instrutor:");
        PessoaView.criarPessoa("Professor/Instrutor");
    }

    private static void exibirMenuCRUDExercicios() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu de Exercicios -----");
            System.out.println("1. Criar Exercicio");
            System.out.println("2. Exibir Todos os Exercicios");
            System.out.println("3. Exibir Dados do Exercicio por ID");
            System.out.println("4. Atualizar Exercicio");
            System.out.println("5. Remover Exercicio por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

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
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        }
    }

    private static void criaExercicio() {
        System.out.println("Informe os dados para criar o Administrador:");

        ExercicioView.criaExercicio();

        // ExercicioView.exercicios[ExercicioView.numExercicios++] = exercicio;

    }

    private static void exibirMenuCRUDExerciciosAplicacao() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu de Exercicios Aplicacao -----");
            System.out.println("1. Definir Aplicacao do Exercicio");
            System.out.println("2. Exibir todas as Aplicacoes de Exercicios");
            System.out.println("3. Exibir Dados das Aplicacoes de Exercicio por ID");
            System.out.println("4. Atualizar Aplicacao de Exercicio");
            System.out.println("5. Remover Aplicacao de Exercicio por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

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
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        }
    }

    private static void criaExercicioAplicacao() {
        System.out.println("\n\n----- Criacao da Aplicacao de Exercicio -----");
        ExercicioAplicacaoView.criarExercicioAplicacao();
    }

    private static void exibirMenuCRUDTreino() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu de Treino -----");
            System.out.println("1. Criar Treino");
            System.out.println("2. Exibir todos os Treinos");
            System.out.println("3. Exibir Dados do Treino por ID");
            System.out.println("4. Atualizar Treino");
            System.out.println("5. Remover Treino por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    TreinoView.criaTreino();
                    break;
                case 2:
                    TreinoView.exibirTodosTreinos();
                    break;
                case 3:
                    TreinoView.exibirTodosTreinos();
                    TreinoView.exibirDadosTreinoPorId();
                    break;
                case 4:
                    TreinoView.exibirTodosTreinos();
                    TreinoView.atualizarTreino();
                    break;
                case 5:
                    TreinoView.exibirTodosTreinos();
                    TreinoView.removerTreino();
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal...");
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        }
    }

    private static void exibirMenuCRUDDivisaoTreino() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu de Divisao de Treino -----");
            System.out.println("1. Criar Divisao de Treino");
            System.out.println("2. Exibir todas as Divisoes de Treino");
            System.out.println("3. Exibir Dados da Divisao de Treino por ID");
            System.out.println("4. Atualizar Divisao de Treino");
            System.out.println("5. Remover Divisao de Treino por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    DivisaoTreinoView.criaOuAtualizaDivisaoTreino();
                    break;
                case 2:
                    DivisaoTreinoView.exibirTodasDivisoesTreino();
                    break;
                case 3:
                    DivisaoTreinoView.exibirTodasDivisoesTreino();
                    DivisaoTreinoView.exibirDadosDivisaoTreinoPorId();
                    break;
                case 4:
                    DivisaoTreinoView.exibirTodasDivisoesTreino();
                    DivisaoTreinoView.atualizarDivisaoTreino();
                    break;
                case 5:
                    DivisaoTreinoView.exibirTodasDivisoesTreino();
                    DivisaoTreinoView.removerDivisaoTreino();
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal...");
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        }
    }

    private static void exibirMenuCRUDDivisaoTreinoMusculo() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu de Divisao de Treino Musculo -----");
            System.out.println("1. Criar Divisao de Treino Musculo");
            System.out.println("2. Exibir todas as Divisoes de Treino Musculo");
            System.out.println("3. Exibir Dados da Divisao de Treino Musculo por ID");
            System.out.println("4. Atualizar Divisao de Treino Musculo");
            System.out.println("5. Remover Divisao de Treino Musculo por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    DivisaoTreinoMusculoView.criaOuAtualizaDivisaoTreinoMusculo();
                    break;
                case 2:
                    DivisaoTreinoMusculoView.exibirTodasDivisoesTreinoMusculo();
                    break;
                case 3:
                    DivisaoTreinoMusculoView.exibirTodasDivisoesTreinoMusculo();
                    DivisaoTreinoMusculoView.exibirDadosDivisaoTreinoMusculoPorId();
                    break;
                case 4:
                    DivisaoTreinoMusculoView.exibirTodasDivisoesTreinoMusculo();
                    DivisaoTreinoMusculoView.atualizarDivisaoTreinoMusculo();
                    break;
                case 5:
                    DivisaoTreinoMusculoView.exibirTodasDivisoesTreinoMusculo();
                    DivisaoTreinoMusculoView.removerDivisaoTreinoMusculo();
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal...");
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        }
    }

    private static void exibirMenuCRUDTreinoAplicacao() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu de Treino Aplicacao -----");
            System.out.println("1. Criar Treino Aplicacao");
            System.out.println("2. Exibir todos os Treinos Aplicacao");
            System.out.println("3. Atualizar Treino Aplicacao");
            System.out.println("4. Remover Treino Aplicacao por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    TreinoAplicacaoView.criaTreinoAplicacao();
                    break;
                case 2:
                    TreinoAplicacaoView.exibirTodosTreinosAplicacao();
                    break;
                case 3:
                    TreinoAplicacaoView.exibirTodosTreinosAplicacao();
                    TreinoAplicacaoView.atualizarTreinoAplicacao();
                    break;
                case 4:
                    TreinoAplicacaoView.exibirTodosTreinosAplicacao();
                    TreinoAplicacaoView.removerTreinoAplicacao();
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal...");
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        }
    }

    private static void exibirMenuCRUDAvaliacaoFisica() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu de Avaliacao Fisica -----");
            System.out.println("1. Criar Avaliacao Fisica");
            System.out.println("2. Exibir todas as Avaliacoes Fisicas");
            System.out.println("3. Exibir Dados da Avaliacao Fisica por ID");
            System.out.println("4. Remover Avaliacao Fisica por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    calcularIMC();
                    break;
                case 2:
                    AvaliacaoFisicaView.listarAvaliacoesFisicas();
                    break;
                case 3:
                    AvaliacaoFisicaView.listarAvaliacaoFisica();
                    break;
                case 4:
                    AvaliacaoFisicaView.listarAvaliacoesFisicas();
                    AvaliacaoFisicaView.removeAvaliacoesFisicasPorId();
                    break;
                case 0:
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
                    break;
            }
        }
    }

    private static void calcularIMC() {
        System.out.println("\n\n----- Calculo do Indice de Massa Corporal (IMC) -----");
        PessoaView.exibirTodos("Aluno");
        System.out.print("Informe o ID do aluno: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        TreinoView.exibirTodosTreinos();
        System.out.print("\nInforme o ID do ultimo treino: ");
        int ultimoTreino = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        System.out.print("Informe o peso (kg): ");
        double peso = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer do teclado

        System.out.print("Informe a altura (m): ");
        double altura = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer do teclado

        AvaliacaoFisicaView.calcularIMC(id, ultimoTreino, peso, altura);
    }

    private static void exibirMenuMensalidade() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu de Mensalidade -----");
            System.out.println("1. Cadastrar Mensalidade");
            System.out.println("2. Exibir Historico de Mensalidades");
            System.out.println("3. Exibir Mensalidade Vigente");
            System.out.println("4. Remover Mensalidade por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    MensalidadeVigenteView.criaMensalidadeVigente();
                    break;
                case 2:
                    MensalidadeVigenteView.exibirHistoricoMensalidade();
                    break;
                case 3:
                    MensalidadeVigenteView.exibirMensalidadeVigente();
                    break;
                case 4:
                    MensalidadeVigenteView.exibirHistoricoMensalidade();
                    MensalidadeVigenteView.removerMensalidade();
                    break;
                case 0:
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
                    break;
            }
        }
    }

    public static void exibirMenuCRUDAlunoPagamentoMensalidade() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu de Pagamento de Mensalidade -----");
            System.out.println("1. Gerar Pagamento de Mensalidade");
            System.out.println("2. Exibir Pagamentos de Mensalidades");
            System.out.println("3. Exibir Pagamento de Mensalidade por ID");
            System.out.println("4. Remover Pagamento de Mensalidade por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    AlunoPagamentoMensalidadeView.gerarAlunoPagamentoMensalidade();
                    break;
                case 2:
                    AlunoPagamentoMensalidadeView.listarPagamentos();
                    break;
                case 3:
                    AlunoPagamentoMensalidadeView.listarPagamento();
                    break;
                case 4:
                    AlunoPagamentoMensalidadeView.listarPagamentos();
                    AlunoPagamentoMensalidadeView.removePagamentoPorId();
                    break;
                case 0:
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
                    break;
            }
        }
    }

    public static void exibirMenuCRUDEntradaAluno() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu de Entrada de Aluno -----");
            System.out.println("1. Exibir Entradas de Alunos");
            System.out.println("2. Exibir Entrada por Alunos");
            System.out.println("3. Remover Entrada de Aluno por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    EntradaAlunoView.visualizarTodasEntradas();
                    break;
                case 2:
                    EntradaAlunoView.visualizarEntradaPorAluno();
                    break;
                case 3:
                    EntradaAlunoView.visualizarTodasEntradas();
                    EntradaAlunoView.removerEntradaAluno();
                    break;
                case 0:
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
                    break;
            }
        }
    }

    public static void exibirMenuCRUDMovimentacoesFinanceiras() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu de Movimentacoes Financeiras -----");
            System.out.println("1. Cadastrar Movimentacao Financeira");
            System.out.println("2. Exibir Movimentacoes Financeiras");
            System.out.println("3. Exibir Movimentacao Financeira de Entrada");
            System.out.println("4. Exibir Movimentacao Financeira de Saida");
            System.out.println("5. Remover Movimentacao Financeira por ID");
            System.out.println("6. Relatorio final de movimentacoes financeiras");
            System.out.println("7. Relatorio de alunos adimplentes");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    MovimentacaoFinanceiraView.cadastrarMovimentacaoFinanceira();
                    break;
                case 2:
                    MovimentacaoFinanceiraView.listarMovimentacoesFinanceiras();
                    break;
                case 3:
                    MovimentacaoFinanceiraView.listarMovimentacoesEntrada();
                    break;
                case 4:
                    MovimentacaoFinanceiraView.listarMovimentacoesSaida();
                    break;
                case 5:
                    MovimentacaoFinanceiraView.listarMovimentacoesFinanceiras();
                    MovimentacaoFinanceiraView.removerMovimentacaoFinanceiraPorId();
                    break;
                case 6:
                    MovimentacaoFinanceiraView.relatorioMovimentacoesFinanceiras();
                    break;
                case 7:
                    MovimentacaoFinanceiraView.exibirAlunosAdimplentes();
                    break;
                case 0:
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
                    break;
            }
        }
    }

    public static void exibirMenuCRUDPagamentoRecorrente() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("\n\n----- Menu de Pagamento Recorrente -----");
            System.out.println("1. Cadastrar Pagamento Recorrente");
            System.out.println("2. Exibir Pagamentos Recorrentes");
            System.out.println("3. Exibir Pagamento Recorrente por Aluno");
            System.out.println("4. Remover Pagamento Recorrente por ID");
            System.out.println("5. Simular avanco no tempo");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    PagamentoRecorrenteView.cadastrarPagamentoRecorrente();
                    break;
                case 2:
                    PagamentoRecorrenteView.listarPagamentosRecorrentes();
                    break;
                case 3:
                    PagamentoRecorrenteView.listarPagamentosRecorrentesPorIdPessoa();
                    break;
                case 4:
                    PagamentoRecorrenteView.removerPagamentoRecorrente();
                    break;
                case 5:
                    PagamentoRecorrenteView.simularAvancoTempo();
                    break;
                case 0:
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
                    break;
            }
        }
    }

}
