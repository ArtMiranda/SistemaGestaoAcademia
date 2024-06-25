package sgacad.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import sgacad.controller.PessoaController;
import sgacad.model.Pessoa;

public class PessoaView {
    private static Scanner scanner = new Scanner(System.in);
    private static PessoaController pessoaController = new PessoaController();

    public static void criarPessoa(String tipousuario) {
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

        PessoaController.criarPessoa(nome, sexoAdmin, dtNascimento, login, senha, tipousuario);
    }

    public static void atualizarPessoa(String tipo){
        int id = solicitarId(tipo);
        Pessoa pessoa = pessoaController.buscarPessoaPorId(id, tipo);
        if (pessoa != null) {
            System.out.println(tipo + " encontrado. Insira os novos dados:");

            // Nome do usuario
            System.out.print("Novo Nome: ");
            String novoNome = scanner.nextLine().trim();
            if (!novoNome.isEmpty()) {
                pessoa.setNome(novoNome);
            }

            // Sexo do usuario
            char novoSexo;
            String novoSexoInput = "";
            while (!(novoSexoInput.equalsIgnoreCase("M") || novoSexoInput.equalsIgnoreCase("F"))) {
            System.out.print("Novo Sexo (M/F): ");
            novoSexoInput = scanner.nextLine();
            novoSexo = novoSexoInput.toUpperCase().charAt(0);
            pessoa.setSexo(novoSexo);
        }

        

        // Data de nascimento do usuario
        LocalDate novaDataNascimento = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        boolean dataValida = false;
        while (!dataValida) {
            try {
                System.out.print("Nova Data de nascimento (dd/MM/yyyy): ");
                String novaDataNascimentoStr = scanner.nextLine();
                novaDataNascimento = LocalDate.parse(novaDataNascimentoStr, dtf);
                if (novaDataNascimento.isAfter(LocalDate.now())) {
                    System.out.println("Data de nascimento invalida! Informe uma data anterior à data atual.");
                } else {
                    dataValida = true;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data invalido! Use o formato dd/MM/yyyy.");
            }
        }
        pessoa.setNascimento(novaDataNascimento);

        System.out.print("Novo Login: ");
        String novoLogin = scanner.nextLine().trim();
        if (!novoLogin.isEmpty()) {
            pessoa.setLogin(novoLogin);
        }

        System.out.print("Nova Senha: ");
        String novaSenha = scanner.nextLine().trim();
        if (!novaSenha.isEmpty()) {
            pessoa.setSenha(novaSenha);
        }

        pessoaController.atualizarPessoa(pessoa);
        System.out.println(tipo + " atualizado com sucesso!");
    }


    }

    public static void exibirTodos(String tipo) {
        Pessoa[] pessoas = pessoaController.buscarTodos(tipo);
        if (pessoas.length == 0) {
            System.out.println("Nao ha " + tipo.toLowerCase() + " cadastrados.");
            return;
        }

        System.out.println("\n\nLista de todos os " + tipo.toLowerCase() + "s:");
        for (Pessoa pessoa : pessoas) {
            System.out.println("ID: " + pessoa.getId());
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("Sexo: " + pessoa.getSexo());
            System.out.println("Data de nascimento: " + pessoa.getNascimento());
            System.out.println("Login: " + pessoa.getLogin());
            System.out.println("Data de Modificacao: " + pessoa.getDataModificacao());
            System.out.println("----------------------------");
        }
    }

    public static void removerPessoaPorId(String tipo) {
        int id = solicitarId(tipo);
        boolean removido = pessoaController.removerPessoaPorId(id, tipo);
        if (removido) {
            System.out.println(tipo + " removido com sucesso.");
        } else {
            System.out.println("\n\n" + tipo + " com ID " + id + " nao encontrado.");
        }
    }

    public static void exibirDadosPorId(String tipo) {
        int id = solicitarId(tipo);
        Pessoa pessoa = PessoaController.getPessoaById(id, tipo);
        if (pessoa != null) {
            System.out.println("\n\n----- Dados do " + tipo + " -----\n\n");
            System.out.println(pessoa.exibirDetalhes());
        } else {
            System.out.println("\n\n" + tipo + " nao encontrado.");
        }
    }

    private static int solicitarId(String tipo) {
        int id = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do " + tipo + ": ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.println("Por favor, insira apenas numeros inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }
        return id;
    }
}
