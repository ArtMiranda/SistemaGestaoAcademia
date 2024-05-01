import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class MenuAcademia {
    private static Scanner scanner = new Scanner(System.in);
    private static Academia academia = null;
    private static Pessoa[] alunos = new Pessoa[100];
    private static int numAlunos = 0;
    private static Pessoa[] administradores = new Pessoa[10]; // Array para administradores
    private static int numAdministradores = 0;
    private static Pessoa[] professoresInstrutores = new Pessoa[10]; // Array para professores/instrutores
    private static int numProfessoresInstrutores = 0;

    public static void main(String[] args) {
        criarAdministradorPadrao();

        while (true) {
            if (academia == null) {
                criarAcademia();
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataNascimentoPadrao = sdf.format(dataAtual);

        // Criar o objeto Pessoa para representar o administrador padrão
        Pessoa administradorPadrao = new Pessoa(0, nomePadrao, sexoPadrao, dataAtual, loginPadrao, senhaPadrao,
                "Administrador", dataAtual, dataAtual);

        // Adicionar o administrador padrão ao array de administradores
        administradores[numAdministradores++] = administradorPadrao;

        System.out.println("Administrador padrão criado com sucesso!");
    }

    private static void criarContaAdministrador() {
        System.out.println("\n\n----- Criação da Conta do Administrador -----");
        administradores[numAdministradores++] = criarAdministrador();
    }

    private static void criarContaProfessor() {
        System.out.println("\n\n----- Criação da Conta do Professor/Instrutor -----");
        professoresInstrutores[numProfessoresInstrutores++] = criarProfessorInstrutor();
    }

    private static Pessoa criarAdministrador() {
        System.out.println("Informe os dados para criar o Administrador:");

        // Nome do administrador
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        while (nome.isEmpty()) {
            System.out.print("Nome não pode estar vazio. Informe novamente: ");
            nome = scanner.nextLine().trim();
        }

        // Sexo do administrador
        System.out.print("Sexo (M/F): ");
        char sexoAdmin;
        String sexoInput = scanner.nextLine();
        while (!(sexoInput.equalsIgnoreCase("M") || sexoInput.equalsIgnoreCase("F"))) {
            System.out.print("Sexo inválido. Digite M para Masculino ou F para Feminino: ");
            sexoInput = scanner.nextLine();
        }
        sexoAdmin = sexoInput.toUpperCase().charAt(0);

        // Data de nascimento do administrador
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

        // Login do administrador
        System.out.print("Login: ");
        String login = scanner.nextLine().trim();
        while (login.isEmpty()) {
            System.out.print("Login não pode estar vazio. Informe novamente: ");
            login = scanner.nextLine().trim();
        }

        // Senha do administrador
        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim();
        while (senha.isEmpty()) {
            System.out.print("Senha não pode estar vazia. Informe novamente: ");
            senha = scanner.nextLine().trim();
        }

        // Data de criação e modificação
        Date dataCriacao = Calendar.getInstance().getTime();
        Date dataModificacao = Calendar.getInstance().getTime();

        // Criar o objeto Pessoa para representar o administrador
        return new Pessoa(numAdministradores, nome, sexoAdmin, dtNascimento, login, senha, "Administrador", dataCriacao,
                dataModificacao);
    }

    private static Pessoa criarProfessorInstrutor() {
        System.out.println("Informe os dados para criar o Professor/Instrutor:");

        // Nome do professor/instrutor
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        while (nome.isEmpty()) {
            System.out.print("Nome não pode estar vazio. Informe novamente: ");
            nome = scanner.nextLine().trim();
        }

        // Sexo do professor/instrutor
        System.out.print("Sexo (M/F): ");
        char sexoProf;
        String sexoInput = scanner.nextLine();
        while (!(sexoInput.equalsIgnoreCase("M") || sexoInput.equalsIgnoreCase("F"))) {
            System.out.print("Sexo inválido. Digite M para Masculino ou F para Feminino: ");
            sexoInput = scanner.nextLine();
        }
        sexoProf = sexoInput.toUpperCase().charAt(0);

        // Data de nascimento do professor/instrutor
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

        // Login do professor/instrutor
        System.out.print("Login: ");
        String login = scanner.nextLine().trim();
        while (login.isEmpty()) {
            System.out.print("Login não pode estar vazio. Informe novamente: ");
            login = scanner.nextLine().trim();
        }

        // Senha do professor/instrutorf
        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim();
        while (senha.isEmpty()) {
            System.out.print("Senha não pode estar vazia. Informe novamente: ");
            senha = scanner.nextLine().trim();
        }

        // Data de criação e modificação
        Date dataCriacao = Calendar.getInstance().getTime();
        Date dataModificacao = Calendar.getInstance().getTime();

        // Criar o objeto Pessoa para representar o professor/instrutor
        return new Pessoa(numProfessoresInstrutores, nome, sexoProf, dtNascimento, login, senha, "Professor/Instrutor",
                dataCriacao,
                dataModificacao);
    }

    private static boolean efetuarLogin() {
        System.out.println("\n\n----- Login -----");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        if (login.equalsIgnoreCase("sair")) {
            System.out.println("Saindo do programa...");
            System.exit(0); // Terminar o programa
        }
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (int i = 0; i < numAdministradores; i++) {
            if (login.equals(administradores[i].getLogin()) && senha.equals(administradores[i].getSenha())) {
                System.out.println("Login de Administrador bem-sucedido!");
                return true;
            }
        }
        for (int i = 0; i < numProfessoresInstrutores; i++) {
            if (login.equals(professoresInstrutores[i].getLogin())
                    && senha.equals(professoresInstrutores[i].getSenha())) {
                System.out.println("Login de Professor/Instrutor bem-sucedido!");
                return true;
            }
        }
        for (int i = 0; i < numAlunos; i++) {
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
            System.out.println("2. Criar Aluno");
            System.out.println("3. Exibir Todos os Alunos");
            System.out.println("4. Exibir Dados do Aluno por ID");
            System.out.println("5. Remover um aluno por ID");
            System.out.println("6. Criar Administrador");
            System.out.println("7. Criar Professor/Instrutor");
            System.out.println("8. Exibir Todos os Administradores");
            System.out.println("9. Exibir Todos os Professores/Instrutores");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    exibirDetalhesAcademia(academia);
                    break;
                case 2:
                    criarAluno();
                    break;
                case 3:
                    exibirTodosAlunos();
                    break;
                case 4:
                    exibirDadosAlunoPorId();
                    break;
                case 5:
                    removerAluno();
                    break;
                case 6:
                    criarContaProfessor();
                    break;
                case 7:
                    criarContaAdministrador();

                    break;
                case 8:
                    exibirTodosAdministradores();
                    break;
                case 9:
                    exibirTodosProfessoresInstrutores();
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

    private static Academia criarAcademia() {
        System.out.println("\n\n----- Criação da Academia -----");
        System.out.println("Informe os detalhes da academia:");
        System.out.print("Nome da Academia: ");
        String nomeAcademia = scanner.nextLine();
        System.out.print("Endereço da Academia: ");
        String enderecoAcademia = scanner.nextLine();

        Date dataAtual = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = sdf.format(dataAtual);

        academia = new Academia(1, nomeAcademia, enderecoAcademia, dataAtual, dataAtual);
        return academia;
    }

    private static void exibirDetalhesAcademia(Academia academia) {
        if (academia != null) {
            System.out.println("\n\n----- Detalhes da Academia -----");
            System.out.println(academia.exibirDetalhes());
        } else {
            System.out.println("Nenhuma academia criada ainda.");
        }
    }

    private static void criarAluno() {
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

        // Data de criação e modificação
        Date dataCriacao = Calendar.getInstance().getTime();
        Date dataModificacao = Calendar.getInstance().getTime();

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

        // Criar o objeto Pessoa para representar o aluno
        Pessoa aluno = new Pessoa(numAlunos, nomeAluno, sexoAluno, dtNascimento, loginAluno, senhaAluno, tipoUsuario,
                dataCriacao, dataModificacao);
        alunos[numAlunos] = aluno;
        System.out.println("Aluno criado com sucesso!");
        numAlunos++;
    }

    private static void exibirTodosAlunos() {
        if (numAlunos == 0) {
            System.out.println("\n\nNenhum aluno cadastrado ainda.");
        } else {
            System.out.println("\n\nLista de Alunos:");
            for (int i = 0; i < numAlunos; i++) {
                System.out.println("ID: " + alunos[i].getId() + ", Nome: " + alunos[i].getNome());
            }
        }
    }

    private static void exibirTodosAdministradores() {
        if (numAdministradores == 0) {
            System.out.println("\n\nNenhum administrador cadastrado ainda.");
        } else {
            System.out.println("\n\nLista de Administradores:");
            for (int i = 0; i < numAdministradores; i++) {
                System.out.println("ID: " + administradores[i].getId() + ", Nome: " + administradores[i].getNome());
            }
        }
    }

    private static void exibirTodosProfessoresInstrutores() {
        if (numProfessoresInstrutores == 0) {
            System.out.println("\n\nNenhum professor/instrutor cadastrado ainda.");
        } else {
            System.out.println("\n\nLista de Professores/Instrutores:");
            for (int i = 0; i < numProfessoresInstrutores; i++) {
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
        for (int i = 0; i < numAlunos; i++) {
            if (alunos[i].getId() == idBusca) {
                System.out.println("Dados do Aluno:");
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
        for (int i = 0; i < numAlunos; i++) {
            if (alunos[i].getId() == id) {
                for (int j = i; j < numAlunos - 1; j++) {
                    alunos[j] = alunos[j + 1];
                }
                alunos[numAlunos - 1] = null;
                numAlunos--;
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
}
