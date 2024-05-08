package sgacad.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import sgacad.controller.PessoaController;
import sgacad.model.Pessoa;

public class PessoaView {
    public static int numAdministradores = 0;
    public static int numProfessoresInstrutores = 0;
    public static int numAlunos = 0;

    private static Scanner scanner = new Scanner(System.in);

    public static Pessoa criarPessoa(String tipousuario) {
        // Nome do administradornumAdministradores
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
}
