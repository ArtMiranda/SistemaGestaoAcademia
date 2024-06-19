package sgacad.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import sgacad.controller.PessoaController;
import sgacad.model.Pessoa;

public class PessoaView {
    public static Pessoa[] alunos = new Pessoa[100]; // Array de alunos
    public static Pessoa[] administradores = new Pessoa[100]; // Array de adms
    public static Pessoa[] professoresInstrutores = new Pessoa[100]; // Array de profs

    public static int numAdministradores = 0;
    public static int numProfessoresInstrutores = 0;
    public static int numAlunos = 0;

    private static Scanner scanner = new Scanner(System.in);

    public static Pessoa criarPessoa(String tipousuario) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        while (nome.isEmpty()) {
            System.out.print("Nome nao pode estar vazio. Informe novamente: ");
            nome = scanner.nextLine().trim();
        }

        System.out.print("Sexo (M/F): ");
        char sexoAdmin;
        String sexoInput = scanner.nextLine();
        while (!(sexoInput.equalsIgnoreCase("M") || sexoInput.equalsIgnoreCase("F"))) {
            System.out.print("Sexo invalido. Digite M para Masculino ou F para Feminino: ");
            sexoInput = scanner.nextLine();
        }
        sexoAdmin = sexoInput.toUpperCase().charAt(0);

        LocalDate dtNascimento = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        boolean dataValida = false;
        while (!dataValida) {
            try {
                System.out.print("Data de nascimento (dd/MM/yyyy): ");
                String dtNascimentoStr = scanner.nextLine();
                dtNascimento = LocalDate.parse(dtNascimentoStr, dtf);
                if (dtNascimento.isAfter(LocalDate.now())) {
                    System.out.println("Data de nascimento invalida! Informe uma data anterior à data atual.");
                } else {
                    dataValida = true;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data invalido! Use o formato dd/MM/yyyy.");
            }
        }

        System.out.print("Login: ");
        String login = scanner.nextLine().trim();
        while (login.isEmpty()) {
            System.out.print("Login nao pode estar vazio. Informe novamente: ");
            login = scanner.nextLine().trim();
        }
        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim();
        while (senha.isEmpty()) {
            System.out.print("Senha nao pode estar vazia. Informe novamente: ");
            senha = scanner.nextLine().trim();
        }

        PessoaController pessoaController = new PessoaController();

        if (tipousuario.equals("Administrador")) {
            return pessoaController.criarPessoa(numAdministradores, nome, sexoAdmin, dtNascimento, login, senha, tipousuario);
        } else if (tipousuario.equals("Professor/Instrutor")) {
            return pessoaController.criarPessoa(numProfessoresInstrutores, nome, sexoAdmin, dtNascimento, login, senha, tipousuario);
        }
        return pessoaController.criarPessoa(numAlunos, nome, sexoAdmin, dtNascimento, login, senha, tipousuario);
    }

    public static void atualizarProfessorInstrutor() {
        int idProf = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do professor/instrutor que deseja atualizar: ");
            if (scanner.hasNextInt()) {
                idProf = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas numeros inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numProfessoresInstrutores; i++) {
            if (PessoaView.professoresInstrutores[i].getId() == idProf) {
                System.out.println("Professor/Instrutor encontrado. Insira os novos dados:");

                // Nome do professor/instrutor
                System.out.print("Novo nome: ");
                String novoNome = scanner.nextLine().trim();
                while (novoNome.isEmpty()) {
                    System.out.print("Nome nao pode estar vazio. Informe novamente: ");
                    novoNome = scanner.nextLine().trim();
                }
                PessoaView.professoresInstrutores[i].setNome(novoNome);

                // Sexo do professor/instrutor
                System.out.print("Novo sexo (M/F): ");
                char novoSexo;
                String novoSexoInput = scanner.nextLine();
                while (!(novoSexoInput.equalsIgnoreCase("M") || novoSexoInput.equalsIgnoreCase("F"))) {
                    System.out.print("Sexo invalido. Digite M para Masculino ou F para Feminino: ");
                    novoSexoInput = scanner.nextLine();
                }
                novoSexo = novoSexoInput.toUpperCase().charAt(0);
                PessoaView.professoresInstrutores[i].setSexo(novoSexo);

                // Data de nascimento do professor/instrutor
                LocalDate novaDtNascimento = null;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                boolean dataValida = false;
                while (!dataValida) {
                    try {
                        System.out.print("Nova data de nascimento (dd/MM/yyyy): ");
                        String novaDtNascimentoStr = scanner.nextLine();
                        novaDtNascimento = LocalDate.parse(novaDtNascimentoStr, dtf);
                        if (novaDtNascimento.isAfter(LocalDate.now())) {
                            System.out.println("Data de nascimento invalida! Informe uma data anterior à data atual.");
                        } else {
                            dataValida = true;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de data invalido! Use o formato dd/MM/yyyy.");
                    }
                }
                PessoaView.professoresInstrutores[i].setNascimento(novaDtNascimento);

                // Login do professor/instrutor
                System.out.print("Novo login: ");
                String novoLogin = scanner.nextLine().trim();
                while (novoLogin.isEmpty()) {
                    System.out.print("Login nao pode estar vazio. Informe novamente: ");
                    novoLogin = scanner.nextLine().trim();
                }
                PessoaView.professoresInstrutores[i].setLogin(novoLogin);

                // Senha do professor/instrutor
                System.out.print("Nova senha: ");
                String novaSenha = scanner.nextLine().trim();
                while (novaSenha.isEmpty()) {
                    System.out.print("Senha nao pode estar vazia. Informe novamente: ");
                    novaSenha = scanner.nextLine().trim();
                }
                PessoaView.professoresInstrutores[i].setSenha(novaSenha);

                // Data de modificacao
                PessoaView.professoresInstrutores[i].setDataModificacao(LocalDate.now());

                System.out.println("Professor/Instrutor atualizado com sucesso!");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nProfessor/Instrutor com ID " + idProf + " nao encontrado.");
        }
    }

    public static void atualizarAdministrador() {
        int idAdmin = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do administrador que deseja atualizar: ");
            if (scanner.hasNextInt()) {
                idAdmin = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas numeros inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numAdministradores; i++) {
            if (PessoaView.administradores[i].getId() == idAdmin) {
                System.out.println("Administrador encontrado. Insira os novos dados:");

                // Nome do administrador
                System.out.print("Novo nome: ");
                String novoNome = scanner.nextLine().trim();
                while (novoNome.isEmpty()) {
                    System.out.print("Nome nao pode estar vazio. Informe novamente: ");
                    novoNome = scanner.nextLine().trim();
                }
                PessoaView.administradores[i].setNome(novoNome);

                // Sexo do administrador
                System.out.print("Novo sexo (M/F): ");
                char novoSexo;
                String novoSexoInput = scanner.nextLine();
                while (!(novoSexoInput.equalsIgnoreCase("M") || novoSexoInput.equalsIgnoreCase("F"))) {
                    System.out.print("Sexo invalido. Digite M para Masculino ou F para Feminino: ");
                    novoSexoInput = scanner.nextLine();
                }
                novoSexo = novoSexoInput.toUpperCase().charAt(0);
                PessoaView.administradores[i].setSexo(novoSexo);

                // Data de nascimento do administrador
                LocalDate novaDtNascimento = null;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                boolean dataValida = false;
                while (!dataValida) {
                    try {
                        System.out.print("Nova data de nascimento (dd/MM/yyyy): ");
                        String novaDtNascimentoStr = scanner.nextLine();
                        novaDtNascimento = LocalDate.parse(novaDtNascimentoStr, dtf);
                        if (novaDtNascimento.isAfter(LocalDate.now())) {
                            System.out.println("Data de nascimento invalida! Informe uma data anterior à data atual.");
                        } else {
                            dataValida = true;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de data invalido! Use o formato dd/MM/yyyy.");
                    }
                }
                PessoaView.administradores[i].setNascimento(novaDtNascimento);

                // Login do administrador
                System.out.print("Novo login: ");
                String novoLogin = scanner.nextLine().trim();
                while (novoLogin.isEmpty()) {
                    System.out.print("Login nao pode estar vazio. Informe novamente: ");
                    novoLogin = scanner.nextLine().trim();
                }
                PessoaView.administradores[i].setLogin(novoLogin);

                // Senha do administrador
                System.out.print("Nova senha: ");
                String novaSenha = scanner.nextLine().trim();
                while (novaSenha.isEmpty()) {
                    System.out.print("Senha nao pode estar vazia. Informe novamente: ");
                    novaSenha = scanner.nextLine().trim();
                }
                PessoaView.administradores[i].setSenha(novaSenha);

                // Data de modificacao
                PessoaView.administradores[i].setDataModificacao(LocalDate.now());

                System.out.println("Administrador atualizado com sucesso!");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nAdministrador com ID " + idAdmin + " nao encontrado.");
        }
    }

    public static void atualizarAluno() {
        int idAluno = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do aluno que deseja atualizar: ");
            if (scanner.hasNextInt()) {
                idAluno = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas numeros inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numAlunos; i++) {
            if (PessoaView.alunos[i].getId() == idAluno) {
                System.out.println("Aluno encontrado. Insira os novos dados:");

                // Nome do aluno
                System.out.print("Novo nome: ");
                String novoNome = scanner.nextLine().trim();
                while (novoNome.isEmpty()) {
                    System.out.print("Nome nao pode estar vazio. Informe novamente: ");
                    novoNome = scanner.nextLine().trim();
                }
                PessoaView.alunos[i].setNome(novoNome);

                // Sexo do aluno
                System.out.print("Novo sexo (M/F): ");
                char novoSexo;
                String novoSexoInput = scanner.nextLine();
                while (!(novoSexoInput.equalsIgnoreCase("M") || novoSexoInput.equalsIgnoreCase("F"))) {
                    System.out.print("Sexo invalido. Digite M para Masculino ou F para Feminino: ");
                    novoSexoInput = scanner.nextLine();
                }
                novoSexo = novoSexoInput.toUpperCase().charAt(0);
                PessoaView.alunos[i].setSexo(novoSexo);

                // Data de nascimento do aluno
                LocalDate novaDtNascimento = null;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                boolean dataValida = false;
                while (!dataValida) {
                    try {
                        System.out.print("Nova data de nascimento (dd/MM/yyyy): ");
                        String novaDtNascimentoStr = scanner.nextLine();
                        novaDtNascimento = LocalDate.parse(novaDtNascimentoStr, dtf);
                        if (novaDtNascimento.isAfter(LocalDate.now())) {
                            System.out.println("Data de nascimento invalida! Informe uma data anterior à data atual.");
                        } else {
                            dataValida = true;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de data invalido! Use o formato dd/MM/yyyy.");
                    }
                }
                PessoaView.alunos[i].setNascimento(novaDtNascimento);

                // Login do aluno
                System.out.print("Novo login: ");
                String novoLogin = scanner.nextLine().trim();
                while (novoLogin.isEmpty()) {
                    System.out.print("Login nao pode estar vazio. Informe novamente: ");
                    novoLogin = scanner.nextLine().trim();
                }
                PessoaView.alunos[i].setLogin(novoLogin);

                // Senha do aluno
                System.out.print("Nova senha: ");
                String novaSenha = scanner.nextLine().trim();
                while (novaSenha.isEmpty()) {
                    System.out.print("Senha nao pode estar vazia. Informe novamente: ");
                    novaSenha = scanner.nextLine().trim();
                }
                PessoaView.alunos[i].setSenha(novaSenha);

                // Data de modificacao
                PessoaView.alunos[i].setDataModificacao(LocalDate.now());

                System.out.println("Aluno atualizado com sucesso!");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nAluno com ID " + idAluno + " nao encontrado.");
        }
    }

    public static void exibirTodosAlunos() {
        if (numAlunos == 0) {
            System.out.println("Nao ha alunos cadastrados.");
            return;
        }

        System.out.println("\n\nLista de todos os alunos:");
        for (int i = 0; i < numAlunos; i++) {
            Pessoa aluno = alunos[i];
            System.out.println("ID: " + aluno.getId());
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Sexo: " + aluno.getSexo());
            System.out.println("Data de nascimento: " + aluno.getNascimento());
            System.out.println("Login: " + aluno.getLogin());
            System.out.println("Data de Modificacao: " + aluno.getDataModificacao());
            System.out.println("----------------------------");
        }
    }

    public static void exibirTodosAdministradores() {
        if (numAdministradores == 0) {
            System.out.println("Nao ha administradores cadastrados.");
            return;
        }

        System.out.println("\n\nLista de todos os administradores:");
        for (int i = 0; i < numAdministradores; i++) {
            Pessoa administrador = administradores[i];
            System.out.println("ID: " + administrador.getId());
            System.out.println("Nome: " + administrador.getNome());
            System.out.println("Sexo: " + administrador.getSexo());
            System.out.println("Data de nascimento: " + administrador.getNascimento());
            System.out.println("Login: " + administrador.getLogin());
            System.out.println("Data de Modificacao: " + administrador.getDataModificacao());
            System.out.println("----------------------------");
        }
    }

    public static void exibirTodosProfessoresInstrutores() {
        if (numProfessoresInstrutores == 0) {
            System.out.println("Nao ha professores/instrutores cadastrados.");
            return;
        }

        System.out.println("\n\nLista de todos os professores/instrutores:");
        for (int i = 0; i < numProfessoresInstrutores; i++) {
            Pessoa professorInstrutor = professoresInstrutores[i];
            System.out.println("ID: " + professorInstrutor.getId());
            System.out.println("Nome: " + professorInstrutor.getNome());
            System.out.println("Sexo: " + professorInstrutor.getSexo());
            System.out.println("Data de nascimento: " + professorInstrutor.getNascimento());
            System.out.println("Login: " + professorInstrutor.getLogin());
            System.out.println("Data de Modificacao: " + professorInstrutor.getDataModificacao());
            System.out.println("----------------------------");
        }
    }

    public static void removerAdministradorPorId() {
        int idAdmin = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do administrador que deseja remover: ");
            if (scanner.hasNextInt()) {
                idAdmin = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas numeros inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < numAdministradores; i++) {
            if (administradores[i].getId() == idAdmin) {
                System.out.println("Administrador encontrado e removido.");
                administradores[i] = administradores[numAdministradores - 1];
                administradores[numAdministradores - 1] = null;
                numAdministradores--;
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nAdministrador com ID " + idAdmin + " nao encontrado.");
        }
    }

    public static void exibirDadosAdministradorPorId() {
        int idBusca = solicitarId("administrador");

        boolean encontrado = false;
        for (int i = 0; i < numAdministradores; i++) {
            if (administradores[i].getId() == idBusca) {
                System.out.println("\n\n----- Dados do Administrador -----\n\n");
                System.out.println(administradores[i].exibirDetalhes());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nAdministrador nao encontrado.");
        }
    }

    private static int solicitarId(String tipo) {
        int idBusca = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do " + tipo + ": ");
            if (scanner.hasNextInt()) {
                idBusca = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas numeros inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }
        return idBusca;
    }

    public static void removerAlunoPorId() {
        int idAluno = solicitarId("aluno");

        boolean encontrado = false;
        for (int i = 0; i < numAlunos; i++) {
            if (alunos[i].getId() == idAluno) {
                System.out.println("Aluno encontrado e removido.");
                alunos[i] = alunos[numAlunos - 1];
                alunos[numAlunos - 1] = null;
                numAlunos--;
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nAluno com ID " + idAluno + " nao encontrado.");
        }
    }

    public static void exibirDadosAlunoPorId() {
        int idBusca = solicitarId("aluno");

        boolean encontrado = false;
        for (int i = 0; i < numAlunos; i++) {
            if (alunos[i].getId() == idBusca) {
                System.out.println("\n\n----- Dados do Aluno -----\n\n");
                System.out.println(alunos[i].exibirDetalhes());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nAluno nao encontrado.");
        }
    }

    public static void exibirDadosProfessorPorId() {
        int idBusca = solicitarId("professor/instrutor");

        boolean encontrado = false;
        for (int i = 0; i < numProfessoresInstrutores; i++) {
            if (professoresInstrutores[i].getId() == idBusca) {
                System.out.println("\n\n----- Dados do Professor/Instrutor -----\n\n");
                System.out.println(professoresInstrutores[i].exibirDetalhes());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nProfessor/Instrutor nao encontrado.");
        }
    }


    public static void removerProfessorPorId() {
        int idProf = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do professor/instrutor que deseja remover: ");
            if (scanner.hasNextInt()) {
                idProf = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas numeros inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numProfessoresInstrutores; i++) {
            if (PessoaView.professoresInstrutores[i].getId() == idProf) {
                System.out.println("Professor/Instrutor encontrado e removido.");
                PessoaView.professoresInstrutores[i] = PessoaView.professoresInstrutores[numProfessoresInstrutores - 1];
                PessoaView.professoresInstrutores[numProfessoresInstrutores - 1] = null;
                PessoaView.numProfessoresInstrutores--;
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nProfessor/Instrutor com ID " + idProf + " nao encontrado.");
        }
    }
}
