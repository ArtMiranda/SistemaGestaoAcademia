package sgacad.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
            System.out.print("Nome não pode estar vazio. Informe novamente: ");
            nome = scanner.nextLine().trim();
        }

        System.out.print("Sexo (M/F): ");
        char sexoAdmin;
        String sexoInput = scanner.nextLine();
        while (!(sexoInput.equalsIgnoreCase("M") || sexoInput.equalsIgnoreCase("F"))) {
            System.out.print("Sexo inválido. Digite M para Masculino ou F para Feminino: ");
            sexoInput = scanner.nextLine();
        }
        sexoAdmin = sexoInput.toUpperCase().charAt(0);

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

        System.out.print("Login: ");
        String login = scanner.nextLine().trim();
        while (login.isEmpty()) {
            System.out.print("Login não pode estar vazio. Informe novamente: ");
            login = scanner.nextLine().trim();
        }
        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim();
        while (senha.isEmpty()) {
            System.out.print("Senha não pode estar vazia. Informe novamente: ");
            senha = scanner.nextLine().trim();
        }

        // Data de criação e modificação
        PessoaController pessoaController = new PessoaController();

        if (tipousuario.equals("Administrador")) {

            Pessoa pessoa = pessoaController.criarPessoa(numAdministradores, nome, sexoAdmin, dtNascimento,
                    login,
                    senha, tipousuario);
            return pessoa;
        } else if (tipousuario.equals("Professor/Instrutor")) {
            Pessoa pessoa = pessoaController.criarPessoa(numProfessoresInstrutores, nome, sexoAdmin,
                    dtNascimento, login,
                    senha, tipousuario);
            return pessoa;
        }

        Pessoa pessoa = pessoaController.criarPessoa(numAlunos, nome, sexoAdmin, dtNascimento, login,
                senha, tipousuario);
        return pessoa;
    }

    public static void atualizarProfessorInstrutor(){
        int idProf = 0;

        // Loop de validação
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do professor/instrutor que deseja atualizar: ");
            if (scanner.hasNextInt()) {
                idProf = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas números inteiros.");
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
                    System.out.print("Nome não pode estar vazio. Informe novamente: ");
                    novoNome = scanner.nextLine().trim();
                }
                PessoaView.professoresInstrutores[i].setNome(novoNome);

                // Sexo do professor/instrutor
                System.out.print("Novo sexo (M/F): ");
                char novoSexo;
                String novoSexoInput = scanner.nextLine();
                while (!(novoSexoInput.equalsIgnoreCase("M") || novoSexoInput.equalsIgnoreCase("F"))) {
                    System.out.print("Sexo inválido. Digite M para Masculino ou F para Feminino: ");
                    novoSexoInput = scanner.nextLine();
                }
                novoSexo = novoSexoInput.toUpperCase().charAt(0);
                PessoaView.professoresInstrutores[i].setSexo(novoSexo);

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
                PessoaView.professoresInstrutores[i].setNascimento(novaDtNascimento);

                // Login do professor/instrutor
                System.out.print("Novo login: ");
                String novoLogin = scanner.nextLine().trim();
                while (novoLogin.isEmpty()) {
                    System.out.print("Login não pode estar vazio. Informe novamente: ");
                    novoLogin = scanner.nextLine().trim();
                }
                PessoaView.professoresInstrutores[i].setLogin(novoLogin);

                // Senha do professor/instrutor
                System.out.print("Nova senha: ");
                String novaSenha = scanner.nextLine().trim();
                while (novaSenha.isEmpty()) {
                    System.out.print("Senha não pode estar vazia. Informe novamente: ");
                    novaSenha = scanner.nextLine().trim();
                }
                PessoaView.professoresInstrutores[i].setSenha(novaSenha);

                // Data de modificação
                PessoaView.professoresInstrutores[i].setDataModificacao(Calendar.getInstance().getTime());

                System.out.println("Professor/Instrutor atualizado com sucesso!");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nProfessor/Instrutor com ID " + idProf + " não encontrado.");
        }
    }

    public static void atualizarAdministrador() {
        int idAdmin = 0;
    
        // Loop de validação
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do administrador que deseja atualizar: ");
            if (scanner.hasNextInt()) {
                idAdmin = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas números inteiros.");
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
                    System.out.print("Nome não pode estar vazio. Informe novamente: ");
                    novoNome = scanner.nextLine().trim();
                }
                PessoaView.administradores[i].setNome(novoNome);
    
                // Sexo do administrador
                System.out.print("Novo sexo (M/F): ");
                char novoSexo;
                String novoSexoInput = scanner.nextLine();
                while (!(novoSexoInput.equalsIgnoreCase("M") || novoSexoInput.equalsIgnoreCase("F"))) {
                    System.out.print("Sexo inválido. Digite M para Masculino ou F para Feminino: ");
                    novoSexoInput = scanner.nextLine();
                }
                novoSexo = novoSexoInput.toUpperCase().charAt(0);
                PessoaView.administradores[i].setSexo(novoSexo);
    
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
                PessoaView.administradores[i].setNascimento(novaDtNascimento);
    
                // Login do administrador
                System.out.print("Novo login: ");
                String novoLogin = scanner.nextLine().trim();
                while (novoLogin.isEmpty()) {
                    System.out.print("Login não pode estar vazio. Informe novamente: ");
                    novoLogin = scanner.nextLine().trim();
                }
                PessoaView.administradores[i].setLogin(novoLogin);
    
                // Senha do administrador
                System.out.print("Nova senha: ");
                String novaSenha = scanner.nextLine().trim();
                while (novaSenha.isEmpty()) {
                    System.out.print("Senha não pode estar vazia. Informe novamente: ");
                    novaSenha = scanner.nextLine().trim();
                }
                PessoaView.administradores[i].setSenha(novaSenha);
    
                // Data de modificação
                PessoaView.administradores[i].setDataModificacao(Calendar.getInstance().getTime());
    
                System.out.println("Administrador atualizado com sucesso!");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nAdministrador com ID " + idAdmin + " não encontrado.");
        }
    }
    

    public static void atualizarAluno(){
        int idAluno = 0;

        // Loop de validação
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do aluno que deseja atualizar: ");
            if (scanner.hasNextInt()) {
                idAluno = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas números inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numAlunos; i++) {
            if (PessoaView.alunos[i].getId() == idAluno) {
                encontrado = true;
                System.out.println("\nDados atuais do Aluno:");
                System.out.println(PessoaView.alunos[i].exibirDetalhes());

                System.out.println("\nInforme os novos dados do aluno:");

                // Nome do aluno
                System.out.print("Novo Nome: ");
                String novoNome = scanner.nextLine().trim();
                if (!novoNome.isEmpty()) {
                    PessoaView.alunos[i].setNome(novoNome);
                }

                // Sexo do aluno
                System.out.print("Novo Sexo (M/F): ");
                String novoSexoInput = scanner.nextLine();
                if (!novoSexoInput.isEmpty()
                        && (novoSexoInput.equalsIgnoreCase("M") || novoSexoInput.equalsIgnoreCase("F"))) {
                    PessoaView.alunos[i].setSexo(novoSexoInput.toUpperCase().charAt(0));
                }

                // Data de nascimento do aluno
                System.out.print("Nova Data de Nascimento (dd/MM/yyyy): ");
                String novaDataNascimentoStr = scanner.nextLine();
                if (!novaDataNascimentoStr.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date novaDataNascimento = sdf.parse(novaDataNascimentoStr);
                        PessoaView.alunos[i].setNascimento(novaDataNascimento);
                    } catch (ParseException e) {
                        System.out.println("Formato de data inválido! Os dados de data não foram atualizados.");
                    }
                }

                // Login do aluno
                System.out.print("Novo Login: ");
                String novoLogin = scanner.nextLine().trim();
                if (!novoLogin.isEmpty()) {
                    PessoaView.alunos[i].setLogin(novoLogin);
                }

                // Senha do aluno
                System.out.print("Nova Senha: ");
                String novaSenha = scanner.nextLine().trim();
                if (!novaSenha.isEmpty()) {
                    PessoaView.alunos[i].setSenha(novaSenha);
                }

                // Data de modificação
                PessoaView.alunos[i].setDataModificacao(Calendar.getInstance().getTime());

                System.out.println("\nDados do Aluno atualizados com sucesso:");
                System.out.println(PessoaView.alunos[i].exibirDetalhes());
                break;
            }
        }

        if (!encontrado) {
            System.out.println("\nAluno com ID " + idAluno + " não encontrado.");
        }
         
    }



    public static void exibirDadosAdministradorPorId() {
        int idBusca = 0;

        // Loop de validação
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do administrador: ");
            if (scanner.hasNextInt()) {
                idBusca = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas números inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numAdministradores; i++) {
            if (PessoaView.administradores[i].getId() == idBusca) {
                System.out.println("\n\n----- Dados do Administrador -----\n\n");
                System.out.println(PessoaView.administradores[i].exibirDetalhes());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nAdministrador não encontrado.");
        }
         
    }


    public static void exibirDadosProfessorPorId() {
        int idBusca = 0;

        // Loop de validação
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do professor/instrutor: ");
            if (scanner.hasNextInt()) {
                idBusca = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas números inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numProfessoresInstrutores; i++) {
            if (PessoaView.professoresInstrutores[i].getId() == idBusca) {
                System.out.println("\n\n----- Dados do Professor/Instrutor -----\n\n");
                System.out.println(PessoaView.professoresInstrutores[i].exibirDetalhes());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nProfessor/Instrutor não encontrado.");
        }
         
    }

    public static void exibirDadosAlunoPorId() {
        int idBusca = 0;

        // Loop de validação
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do aluno: ");
            if (scanner.hasNextInt()) {
                idBusca = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas números inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numAlunos; i++) {
            if (PessoaView.alunos[i].getId() == idBusca) {
                System.out.println("\n\n----- Dados do Aluno -----\n\n");
                System.out.println(PessoaView.alunos[i].exibirDetalhes());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nAluno não encontrado.");
        }
         
    }

    public static void exibirTodosProfessoresInstrutores() {
        if (PessoaView.numProfessoresInstrutores == 0) {
            System.out.println("\n\nNenhum professor/instrutor cadastrado ainda.");
        } else {
            System.out.println("\n\nLista de Professores/Instrutores:");
            for (int i = 0; i < PessoaView.numProfessoresInstrutores; i++) {
                System.out.println(
                        "ID: " + PessoaView.professoresInstrutores[i].getId() + ", Nome: " + PessoaView.professoresInstrutores[i].getNome());
            }
        }
    }

    public static void exibirTodosAlunos() {
        if (PessoaView.numAlunos == 0) {
            System.out.println("\n\nNenhum aluno cadastrado ainda.");
        } else {
            System.out.println("\n\nLista de Alunos:");
            for (int i = 0; i < PessoaView.numAlunos; i++) {
                System.out.println("ID: " + PessoaView.alunos[i].getId() + ", Nome: " + PessoaView.alunos[i].getNome());
            }
        }
    }

    public static void exibirTodosAdministradores() {
        if (PessoaView.numAdministradores == 0) {
            System.out.println("\n\nNenhum administrador cadastrado ainda.");
        } else {
            System.out.println("\n\nLista de Administradores:");
            for (int i = 0; i < PessoaView.numAdministradores; i++) {
                System.out.println("ID: " + PessoaView.administradores[i].getId() + ", Nome: " + PessoaView.administradores[i].getNome());
            }
        }
    }

    public static void removerAlunoPorId() {
        int idAluno = 0;

        // Loop de validação
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do administrador que deseja remover: ");
            if (scanner.hasNextInt()) {
                idAluno = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas números inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numAlunos; i++) {
            if (PessoaView.alunos[i].getId() == idAluno) {
                for (int j = i; j < PessoaView.numAlunos - 1; j++) {
                    PessoaView.alunos[j] = PessoaView.alunos[j + 1];
                }
                PessoaView.alunos[PessoaView.numAlunos - 1] = null;
                PessoaView.numAlunos--;
                System.out.println("\n\nAluno removido com sucesso.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nAluno com ID " + idAluno + " não encontrado.");
        }
        
    }

    public static void removerAdministradorPorId() {
        int idAdmin = 0;

        // Loop de validação
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do administrador que deseja remover: ");
            if (scanner.hasNextInt()) {
                idAdmin = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas números inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numAdministradores; i++) {
            if (PessoaView.administradores[i].getId() == idAdmin) {
                for (int j = i; j < PessoaView.numAdministradores - 1; j++) {
                    PessoaView.administradores[j] = PessoaView.administradores[j + 1];
                }
                PessoaView.administradores[PessoaView.numAdministradores - 1] = null;
                PessoaView.numAdministradores--;
                System.out.println("\n\nAdministrador removido com sucesso.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nAdministrador com ID " + idAdmin + " não encontrado.");
        }
        
    }

    public static void removerProfessorPorId() {
        int idProf = 0;

        // Loop de validação
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do professor/instrutor que deseja remover: ");
            if (scanner.hasNextInt()) {
                idProf = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas números inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < PessoaView.numProfessoresInstrutores; i++) {
            if (PessoaView.professoresInstrutores[i].getId() == idProf) {
                for (int j = i; j < PessoaView.numProfessoresInstrutores - 1; j++) {
                    PessoaView.professoresInstrutores[j] = PessoaView.professoresInstrutores[j + 1];
                }
                PessoaView.professoresInstrutores[PessoaView.numProfessoresInstrutores - 1] = null;
                PessoaView.numProfessoresInstrutores--;
                System.out.println("\n\nProfessor/Instrutor removido com sucesso.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\nProfessor/Instrutor com ID " + idProf + " não encontrado.");
        }
        
    }
    

}
