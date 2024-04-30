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
    private static Pessoa administrador = null;
    private static Pessoa professorInstrutor = null;

    public static void main(String[] args) {
        criarContaAdministrador();
        criarContaProfessor();

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

    private static void criarContaAdministrador() {
        System.out.println("----- Criação da Conta do Administrador -----");
        administrador = criarAdministrador();
    }

    private static void criarContaProfessor() {
        System.out.println("----- Criação da Conta do Professor/Instrutor -----");
        professorInstrutor = criarProfessorInstrutor();
    }

    private static Pessoa criarAdministrador() {
        System.out.println("Informe os dados para criar o Administrador:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        while (nome.isEmpty()) {
            System.out.println("Nome não pode estar vazio. Informe novamente:");
            nome = scanner.nextLine().trim();
        }

        System.out.print("Login: ");
        String login = scanner.nextLine().trim();
        while (login.isEmpty()) {
            System.out.println("Login não pode estar vazio. Informe novamente:");
            login = scanner.nextLine().trim();
        }

        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim();
        while (senha.isEmpty()) {
            System.out.println("Senha não pode estar vazia. Informe novamente:");
            senha = scanner.nextLine().trim();
        }

        Date dataCriacao = Calendar.getInstance().getTime();
        Date dataModificacao = Calendar.getInstance().getTime();
        return new Pessoa(numAlunos++, nome, 'M', null, login, senha, "Administrador", dataCriacao, dataModificacao);
    }

    private static Pessoa criarProfessorInstrutor() {
        System.out.println("Informe os dados para criar o Professor/Instrutor:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        while (nome.isEmpty()) {
            System.out.println("Nome não pode estar vazio. Informe novamente:");
            nome = scanner.nextLine().trim();
        }

        System.out.print("Login: ");
        String login = scanner.nextLine().trim();
        while (login.isEmpty()) {
            System.out.println("Login não pode estar vazio. Informe novamente:");
            login = scanner.nextLine().trim();
        }

        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim();
        while (senha.isEmpty()) {
            System.out.println("Senha não pode estar vazia. Informe novamente:");
            senha = scanner.nextLine().trim();
        }

        Date dataCriacao = Calendar.getInstance().getTime();
        Date dataModificacao = Calendar.getInstance().getTime();
        return new Pessoa(numAlunos++, nome, 'M', null, login, senha, "Professor/Instrutor", dataCriacao,
                dataModificacao);
    }

    private static boolean efetuarLogin() {
        System.out.println("----- Login -----");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        if (login.equalsIgnoreCase("sair")) {
            System.out.println("Saindo do programa...");
            System.exit(0); // Terminar o programa
        }
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if (login.equals(administrador.getLogin()) && senha.equals(administrador.getSenha())) {
            System.out.println("Login de Administrador bem-sucedido!");
            return true;
        } else if (login.equals(professorInstrutor.getLogin()) && senha.equals(professorInstrutor.getSenha())) {
            System.out.println("Login de Professor/Instrutor bem-sucedido!");
            return true;
        } else {
            for (int i = 0; i < numAlunos; i++) {
                if (login.equals(alunos[i].getLogin()) && senha.equals(alunos[i].getSenha())) {
                    System.out.println("Login de Aluno bem-sucedido!");
                    return true;
                }
            }
        }
        return false;
    }

    private static void exibirMenu() {
        boolean loggedOut = false;
        while (!loggedOut) {
            System.out.println("----- Menu da Academia -----");
            System.out.println("1. Exibir Detalhes da Academia");
            System.out.println("2. Criar Aluno");
            System.out.println("3. Exibir Todos os Alunos");
            System.out.println("4. Exibir Dados do Aluno por ID");
            System.out.println("5. Criar outro Administrador");
            System.out.println("6. Criar outro Professor/Instrutor");
            System.out.println("7. Remover um aluno por ID");
            System.out.println("8. Sair");
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
                    criarContaAdministrador();
                    break;
                case 6:
                    criarContaProfessor();
                    break;
                case 7:
                    removerAluno();
                    break;
                case 8:
                    System.out.println("Deslogando...");
                    loggedOut = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static Academia criarAcademia() {
        System.out.println("----- Criação da Academia -----");
        System.out.println("Informe os detalhes da academia:");
        System.out.print("ID: ");
        int idAcademia = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado
        System.out.print("Nome: ");
        String nomeAcademia = scanner.nextLine();
        System.out.print("Endereço: ");
        String enderecoAcademia = scanner.nextLine();
        Date dataCriacao = Calendar.getInstance().getTime(); // Obter a data atual
        Date dataModificacao = Calendar.getInstance().getTime(); // Obter a data atual
        academia = new Academia(idAcademia, nomeAcademia, enderecoAcademia, dataCriacao, dataModificacao);
        return academia;
    }

    private static void exibirDetalhesAcademia(Academia academia) {
        if (academia != null) {
            System.out.println("Detalhes da Academia:");
            academia.exibirDetalhes();
        } else {
            System.out.println("Nenhuma academia criada ainda.");
        }
    }

    private static void criarAluno() {
        System.out.println("Informe os detalhes do aluno:");
        System.out.print("Nome: ");
        String nomeAluno = scanner.nextLine().trim();
        while (nomeAluno.isEmpty()) {
            System.out.println("Nome não pode estar vazio. Informe novamente:");
            nomeAluno = scanner.nextLine().trim();
        }
        System.out.print("Sexo (M/F): ");
        char sexoAluno = scanner.nextLine().charAt(0);

        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        String dtNascimentoStr = scanner.nextLine();

        Date dtNascimento = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dtNascimento = sdf.parse(dtNascimentoStr);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido! Use o formato dd/MM/yyyy.");
            return;
        }

        Date dataCriacao = Calendar.getInstance().getTime(); // Obter a data atual
        Date dataModificacao = Calendar.getInstance().getTime(); // Obter a data atual
        String tipoUsuario = "Aluno";

        // Solicitar ao professor/administrador para criar login e senha para o aluno
        System.out.print("Criar login para o aluno: ");
        String loginAluno = scanner.nextLine().trim();
        while (loginAluno.isEmpty()) {
            System.out.println("Login não pode estar vazio. Informe novamente:");
            loginAluno = scanner.nextLine().trim();
        }

        System.out.print("Criar senha para o aluno: ");
        String senhaAluno = scanner.nextLine().trim();
        while (senhaAluno.isEmpty()) {
            System.out.println("Senha não pode estar vazia. Informe novamente:");
            senhaAluno = scanner.nextLine().trim();
        }

        // Criar um novo aluno com login e senha
        Pessoa aluno = new Pessoa(numAlunos, nomeAluno, sexoAluno, dtNascimento, loginAluno, senhaAluno, tipoUsuario,
                dataCriacao, dataModificacao);
        alunos[numAlunos] = aluno;
        System.out.println("Aluno criado com sucesso!");
        numAlunos++;
    }

    private static void exibirTodosAlunos() {
        if (numAlunos == 0) {
            System.out.println("Nenhum aluno cadastrado ainda.");
        } else {
            System.out.println("Lista de Alunos:");
            for (int i = 0; i < numAlunos; i++) {
                System.out.println("ID: " + alunos[i].getId() + ", Nome: " + alunos[i].getNome());
            }
        }
    }

    private static void exibirDadosAlunoPorId() {
        System.out.print("Informe o ID do aluno: ");
        int idBusca = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        // Buscar e exibir os dados do aluno pelo ID
        boolean encontrado = false;
        for (int i = 0; i < numAlunos; i++) {
            if (alunos[i].getId() == idBusca) {
                System.out.println("Dados do Aluno:");
                alunos[i].exibirDetalhes();
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Aluno não encontrado.");
        }
    }

    private static void removerAlunoPorId(int id) {
        boolean encontrado = false;
        for (int i = 0; i < numAlunos; i++) {
            if (alunos[i].getId() == id) {
                // Remover o aluno encontrado movendo os alunos subsequentes para trás no array
                for (int j = i; j < numAlunos - 1; j++) {
                    alunos[j] = alunos[j + 1];
                }
                // Definir o último elemento como nulo e decrementar o contador de alunos
                alunos[numAlunos - 1] = null;
                numAlunos--;
                System.out.println("Aluno removido com sucesso.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Aluno com ID " + id + " não encontrado.");
        }
    }

    private static void removerAluno() {
        System.out.print("Informe o ID do aluno que deseja remover: ");
        int idAluno = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado
        removerAlunoPorId(idAluno);
    }
}
