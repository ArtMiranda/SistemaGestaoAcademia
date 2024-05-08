package sgacad.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import sgacad.model.*;
import sgacad.controller.*;

public class MenuAcademia {
    private static Scanner scanner = new Scanner(System.in);
    private static Pessoa[] alunos = new Pessoa[100]; // Array de alunos
    private static Pessoa[] administradores = new Pessoa[100]; // Array de adms
    private static Pessoa[] professoresInstrutores = new Pessoa[100]; // Array de profs

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
        System.out.println("\n\n----- Criando Administrador Padrão -----");

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
        administradores[PessoaView.numAdministradores++] = administradorPadrao;

        System.out.println("Administrador padrão criado com sucesso!");
    }

    private static void criarContaAdministrador() {
        System.out.println("\n\n----- Criação da Conta do Administrador -----");
        administradores[PessoaView.numAdministradores++] = criarAdministrador();
    }

    private static void criarContaProfessor() {
        System.out.println("\n\n----- Criação da Conta do Professor/Instrutor -----");
        professoresInstrutores[PessoaView.numProfessoresInstrutores++] = criarProfessorInstrutor();
    }

    private static Pessoa criarAdministrador() {
        System.out.println("Informe os dados para criar o Administrador:");

        Pessoa admin = PessoaView.criarPessoa("Administrador");

        return admin;
    }

    private static Pessoa criarProfessorInstrutor() {
        System.out.println("Informe os dados para criar o Professor/Instrutor:");

        Pessoa prof = PessoaView.criarPessoa("Administrador");

        return prof;
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
            if (login.equals(administradores[i].getLogin()) && senha.equals(administradores[i].getSenha())) {
                System.out.println("Login de Administrador bem-sucedido!");
                return true;
            }
        }
        for (int i = 0; i < PessoaView.numProfessoresInstrutores; i++) {
            if (login.equals(professoresInstrutores[i].getLogin())
                    && senha.equals(professoresInstrutores[i].getSenha())) {
                System.out.println("Login de Professor/Instrutor bem-sucedido!");
                return true;
            }
        }
        for (int i = 0; i < PessoaView.numAlunos; i++) {
            if (login.equals(alunos[i].getLogin()) && senha.equals(alunos[i].getSenha())) {
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
                // Restante das opções do menu...
                case 0:
                    System.out.println("Deslogando...");
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void atualizarProfessorInstrutor() {
        System.out.print("\n\nInforme o ID do professor/instrutor que deseja atualizar: ");
        int idProf = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numProfessoresInstrutores; i++) {
            if (professoresInstrutores[i].getId() == idProf) {
                System.out.println("Professor/Instrutor encontrado. Insira os novos dados:");

                // Nome do professor/instrutor
                System.out.print("Novo nome: ");
                String novoNome = scanner.nextLine().trim();
                while (novoNome.isEmpty()) {
                    System.out.print("Nome não pode estar vazio. Informe novamente: ");
                    novoNome = scanner.nextLine().trim();
                }
                professoresInstrutores[i].setNome(novoNome);

                // Sexo do professor/instrutor
                System.out.print("Novo sexo (M/F): ");
                char novoSexo;
                String novoSexoInput = scanner.nextLine();
                while (!(novoSexoInput.equalsIgnoreCase("M") || novoSexoInput.equalsIgnoreCase("F"))) {
                    System.out.print("Sexo inválido. Digite M para Masculino ou F para Feminino: ");
                    novoSexoInput = scanner.nextLine();
                }
                novoSexo = novoSexoInput.toUpperCase().charAt(0);
                professoresInstrutores[i].setSexo(novoSexo);

                // Data de nascimento do professor/instrutor
                Date novaDtNascimento = null;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                boolean dataValida = false;
                while (!dataValida) {
                    try {
                        System.out.print("Nova data de nascimento (dd/MM/yyyy): ");
                        String novaDtNascimentoStr = scanner.nextLine();
                        novaDtNascimento = sdf.parse(novaDtNascimentoStr);
                        if (novaDtNascimento.after(Calendar.getInstance().getTime())) {
                            System.out.println("Data de nascimento inválida! Informe uma data anterior à data atual.");
                        } else {
                            dataValida = true;
                        }
                    } catch (ParseException e) {
                        System.out.println("Formato de data inválido! Use o formato dd/MM/yyyy.");
                    }
                }
                professoresInstrutores[i].setNascimento(novaDtNascimento);

                // Login do professor/instrutor
                System.out.print("Novo login: ");
                String novoLogin = scanner.nextLine().trim();
                while (novoLogin.isEmpty()) {
                    System.out.print("Login não pode estar vazio. Informe novamente: ");
                    novoLogin = scanner.nextLine().trim();
                }
                professoresInstrutores[i].setLogin(novoLogin);

                // Senha do professor/instrutor
                System.out.print("Nova senha: ");
                String novaSenha = scanner.nextLine().trim();
                while (novaSenha.isEmpty()) {
                    System.out.print("Senha não pode estar vazia. Informe novamente: ");
                    novaSenha = scanner.nextLine().trim();
                }
                professoresInstrutores[i].setSenha(novaSenha);

                // Data de modificação
                professoresInstrutores[i].setDataModificacao(Calendar.getInstance().getTime());

                System.out.println("Professor/Instrutor atualizado com sucesso!");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nProfessor/Instrutor com ID " + idProf + " não encontrado.");
        }
    }

    private static void atualizarAdministrador() {
        System.out.print("\n\nInforme o ID do administrador que deseja atualizar: ");
        int idAdmin = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numAdministradores; i++) {
            if (administradores[i].getId() == idAdmin) {
                System.out.println("Administrador encontrado. Insira os novos dados:");

                // Nome do administrador
                System.out.print("Novo nome: ");
                String novoNome = scanner.nextLine().trim();
                while (novoNome.isEmpty()) {
                    System.out.print("Nome não pode estar vazio. Informe novamente: ");
                    novoNome = scanner.nextLine().trim();
                }
                administradores[i].setNome(novoNome);

                // Sexo do administrador
                System.out.print("Novo sexo (M/F): ");
                char novoSexo;
                String novoSexoInput = scanner.nextLine();
                while (!(novoSexoInput.equalsIgnoreCase("M") || novoSexoInput.equalsIgnoreCase("F"))) {
                    System.out.print("Sexo inválido. Digite M para Masculino ou F para Feminino: ");
                    novoSexoInput = scanner.nextLine();
                }
                novoSexo = novoSexoInput.toUpperCase().charAt(0);
                administradores[i].setSexo(novoSexo);

                // Data de nascimento do administrador
                Date novaDtNascimento = null;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                boolean dataValida = false;
                while (!dataValida) {
                    try {
                        System.out.print("Nova data de nascimento (dd/MM/yyyy): ");
                        String novaDtNascimentoStr = scanner.nextLine();
                        novaDtNascimento = sdf.parse(novaDtNascimentoStr);
                        if (novaDtNascimento.after(Calendar.getInstance().getTime())) {
                            System.out.println("Data de nascimento inválida! Informe uma data anterior à data atual.");
                        } else {
                            dataValida = true;
                        }
                    } catch (ParseException e) {
                        System.out.println("Formato de data inválido! Use o formato dd/MM/yyyy.");
                    }
                }
                administradores[i].setNascimento(novaDtNascimento);

                // Login do administrador
                System.out.print("Novo login: ");
                String novoLogin = scanner.nextLine().trim();
                while (novoLogin.isEmpty()) {
                    System.out.print("Login não pode estar vazio. Informe novamente: ");
                    novoLogin = scanner.nextLine().trim();
                }
                administradores[i].setLogin(novoLogin);

                // Senha do administrador
                System.out.print("Nova senha: ");
                String novaSenha = scanner.nextLine().trim();
                while (novaSenha.isEmpty()) {
                    System.out.print("Senha não pode estar vazia. Informe novamente: ");
                    novaSenha = scanner.nextLine().trim();
                }
                administradores[i].setSenha(novaSenha);

                // Data de modificação
                administradores[i].setDataModificacao(Calendar.getInstance().getTime());

                System.out.println("Administrador atualizado com sucesso!");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nAdministrador com ID " + idAdmin + " não encontrado.");
        }
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
                    exibirTodosProfessoresInstrutores();
                    break;
                case 3:
                    exibirDadosProfessorPorId();
                    break;
                case 4:
                    atualizarProfessorInstrutor();
                    break;
                case 5:
                    removerProfessor();
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
                    exibirTodosAdministradores();
                    break;
                case 3:
                    exibirDadosAdministradorPorId();
                    break;
                case 4:
                    atualizarAdministrador();
                    break;
                case 5:
                    removerAdministrador();
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
                    exibirTodosAlunos();
                    break;
                case 3:
                    exibirDadosAlunoPorId();
                    break;
                case 4:
                    atualizarAluno();
                    break;
                case 5:
                    removerAluno();
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void atualizarAluno() {
        System.out.print("\n\nInforme o ID do aluno que deseja atualizar: ");
        int idAluno = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numAlunos; i++) {
            if (alunos[i].getId() == idAluno) {
                encontrado = true;
                System.out.println("\nDados atuais do Aluno:");
                System.out.println(alunos[i].exibirDetalhes());

                System.out.println("\nInforme os novos dados do aluno:");

                // Nome do aluno
                System.out.print("Novo Nome: ");
                String novoNome = scanner.nextLine().trim();
                if (!novoNome.isEmpty()) {
                    alunos[i].setNome(novoNome);
                }

                // Sexo do aluno
                System.out.print("Novo Sexo (M/F): ");
                String novoSexoInput = scanner.nextLine();
                if (!novoSexoInput.isEmpty()
                        && (novoSexoInput.equalsIgnoreCase("M") || novoSexoInput.equalsIgnoreCase("F"))) {
                    alunos[i].setSexo(novoSexoInput.toUpperCase().charAt(0));
                }

                // Data de nascimento do aluno
                System.out.print("Nova Data de Nascimento (dd/MM/yyyy): ");
                String novaDataNascimentoStr = scanner.nextLine();
                if (!novaDataNascimentoStr.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date novaDataNascimento = sdf.parse(novaDataNascimentoStr);
                        alunos[i].setNascimento(novaDataNascimento);
                    } catch (ParseException e) {
                        System.out.println("Formato de data inválido! Os dados de data não foram atualizados.");
                    }
                }

                // Login do aluno
                System.out.print("Novo Login: ");
                String novoLogin = scanner.nextLine().trim();
                if (!novoLogin.isEmpty()) {
                    alunos[i].setLogin(novoLogin);
                }

                // Senha do aluno
                System.out.print("Nova Senha: ");
                String novaSenha = scanner.nextLine().trim();
                if (!novaSenha.isEmpty()) {
                    alunos[i].setSenha(novaSenha);
                }

                // Data de modificação
                alunos[i].setDataModificacao(Calendar.getInstance().getTime());

                System.out.println("\nDados do Aluno atualizados com sucesso:");
                System.out.println(alunos[i].exibirDetalhes());
                break;
            }
        }

        if (!encontrado) {
            System.out.println("\nAluno com ID " + idAluno + " não encontrado.");
        }
    }

    public static Pessoa criarAluno() {
        System.out.println("\n\n----- Criação da Conta de Aluno -----");
        System.out.println("\nInforme os detalhes do aluno:");

        // Nome do aluno
        System.out.print("Nome: ");
        String nomeAluno = scanner.nextLine().trim();
        while (nomeAluno.isEmpty()) {
            System.out.print("Nome não pode estar vazio. Informe novamente: ");
            nomeAluno = scanner.nextLine().trim();
        }

        // Sexo do aluno
        System.out.print("Sexo (M/F): ");
        char sexoAluno;
        String sexoInput = scanner.nextLine();
        while (!(sexoInput.equalsIgnoreCase("M") || sexoInput.equalsIgnoreCase("F"))) {
            System.out.print("Sexo inválido. Digite M para Masculino ou F para Feminino: ");
            sexoInput = scanner.nextLine();
        }
        sexoAluno = sexoInput.toUpperCase().charAt(0);

        // Data de nascimento do aluno
        Date dtNascimento = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean dataValida = false;
        while (!dataValida) {
            try {
                System.out.print("Data de nascimento (dd/MM/yyyy): ");
                String dtNascimentoStr = scanner.nextLine();
                dtNascimento = sdf.parse(dtNascimentoStr);
                if (dtNascimento.after(Calendar.getInstance().getTime())) {
                    System.out.println("Data de nascimento inválida! Informe uma data anterior à data atual.");
                } else {
                    dataValida = true;
                }
            } catch (ParseException e) {
                System.out.println("Formato de data inválido! Use o formato dd/MM/yyyy.");
            }
        }

        // Tipo de usuário
        String tipoUsuario = "Aluno";

        // Login do aluno
        System.out.print("Criar login para o aluno: ");
        String loginAluno = scanner.nextLine().trim();
        while (loginAluno.isEmpty()) {
            System.out.print("Login não pode estar vazio. Informe novamente: ");
            loginAluno = scanner.nextLine().trim();
        }

        // Senha do aluno
        System.out.print("Criar senha para o aluno: ");
        String senhaAluno = scanner.nextLine().trim();
        while (senhaAluno.isEmpty()) {
            System.out.print("Senha não pode estar vazia. Informe novamente: ");
            senhaAluno = scanner.nextLine().trim();
        }

        PessoaController alunoController = new PessoaController();
        Pessoa aluno = alunoController.criarPessoa(PessoaView.numAlunos, nomeAluno, sexoAluno, dtNascimento, loginAluno,
                senhaAluno,
                tipoUsuario);

        alunos[PessoaView.numAlunos] = aluno;
        System.out.println("Aluno criado com sucesso!");
        PessoaView.numAlunos++;

        return aluno;
    }

    private static void exibirTodosAlunos() {
        if (PessoaView.numAlunos == 0) {
            System.out.println("\n\nNenhum aluno cadastrado ainda.");
        } else {
            System.out.println("\n\nLista de Alunos:");
            for (int i = 0; i < PessoaView.numAlunos; i++) {
                System.out.println("ID: " + alunos[i].getId() + ", Nome: " + alunos[i].getNome());
            }
        }
    }

    private static void exibirDadosAdministradorPorId() {
        System.out.print("\n\nInforme o ID do administrador: ");
        int idBusca = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numAdministradores; i++) {
            if (administradores[i].getId() == idBusca) {
                System.out.println("\n\n----- Dados do Administrador -----\n\n");
                System.out.println(administradores[i].exibirDetalhes());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nAdministrador não encontrado.");
        }
    }

    private static void exibirDadosProfessorPorId() {
        System.out.print("\n\nInforme o ID do professor/instrutor: ");
        int idBusca = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numProfessoresInstrutores; i++) {
            if (professoresInstrutores[i].getId() == idBusca) {
                System.out.println("\n\n----- Dados do Professor/Instrutor -----\n\n");
                System.out.println(professoresInstrutores[i].exibirDetalhes());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nProfessor/Instrutor não encontrado.");
        }
    }

    private static void exibirTodosAdministradores() {
        if (PessoaView.numAdministradores == 0) {
            System.out.println("\n\nNenhum administrador cadastrado ainda.");
        } else {
            System.out.println("\n\nLista de Administradores:");
            for (int i = 0; i < PessoaView.numAdministradores; i++) {
                System.out.println("ID: " + administradores[i].getId() + ", Nome: " + administradores[i].getNome());
            }
        }
    }

    private static void exibirTodosProfessoresInstrutores() {
        if (PessoaView.numProfessoresInstrutores == 0) {
            System.out.println("\n\nNenhum professor/instrutor cadastrado ainda.");
        } else {
            System.out.println("\n\nLista de Professores/Instrutores:");
            for (int i = 0; i < PessoaView.numProfessoresInstrutores; i++) {
                System.out.println(
                        "ID: " + professoresInstrutores[i].getId() + ", Nome: " + professoresInstrutores[i].getNome());
            }
        }
    }

    private static void exibirDadosAlunoPorId() {
        System.out.print("\n\nInforme o ID do aluno: ");
        int idBusca = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numAlunos; i++) {
            if (alunos[i].getId() == idBusca) {
                System.out.println("\n\n----- Dados do Aluno -----\n\n");
                System.out.println(alunos[i].exibirDetalhes());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nAluno não encontrado.");
        }
    }

    private static void removerAlunoPorId(int id) {
        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numAlunos; i++) {
            if (alunos[i].getId() == id) {
                for (int j = i; j < PessoaView.numAlunos - 1; j++) {
                    alunos[j] = alunos[j + 1];
                }
                alunos[PessoaView.numAlunos - 1] = null;
                PessoaView.numAlunos--;
                System.out.println("\n\nAluno removido com sucesso.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nAluno com ID " + id + " não encontrado.");
        }
    }

    private static void removerAluno() {
        System.out.print("\n\nInforme o ID do aluno que deseja remover: ");
        int idAluno = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado
        removerAlunoPorId(idAluno);
    }

    private static void removerAdministradorPorId(int id) {
        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numAdministradores; i++) {
            if (administradores[i].getId() == id) {
                for (int j = i; j < PessoaView.numAdministradores - 1; j++) {
                    administradores[j] = administradores[j + 1];
                }
                administradores[PessoaView.numAdministradores - 1] = null;
                PessoaView.numAdministradores--;
                System.out.println("\n\nAdministrador removido com sucesso.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nAdministrador com ID " + id + " não encontrado.");
        }
    }

    private static void removerProfessorPorId(int id) {
        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numProfessoresInstrutores; i++) {
            if (professoresInstrutores[i].getId() == id) {
                for (int j = i; j < PessoaView.numProfessoresInstrutores - 1; j++) {
                    professoresInstrutores[j] = professoresInstrutores[j + 1];
                }
                professoresInstrutores[PessoaView.numProfessoresInstrutores - 1] = null;
                PessoaView.numProfessoresInstrutores--;
                System.out.println("\n\nProfessor/Instrutor removido com sucesso.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nProfessor/Instrutor com ID " + id + " não encontrado.");
        }
    }

    private static void removerAdministrador() {
        System.out.print("\n\nInforme o ID do administrador que deseja remover: ");
        int idAdmin = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado
        removerAdministradorPorId(idAdmin);
    }

    private static void removerProfessor() {
        System.out.print("\n\nInforme o ID do professor/instrutor que deseja remover: ");
        int idProf = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado
        removerProfessorPorId(idProf);
    }

}
