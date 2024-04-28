import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class MenuAcademia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Academia academia = null;
        Pessoa[] alunos = new Pessoa[100]; // Array para armazenar os alunos
        int numAlunos = 0; // Número atual de alunos

        boolean sair = false;
        while (!sair) {
            if (academia == null) {
                System.out.println("----- Criação da Academia -----");
                academia = criarAcademia(scanner);
                if (academia == null) {
                    System.out.println("Não foi possível criar a academia. Encerrando o programa.");
                    return;
                }
            } else {
                System.out.println("----- Menu da Academia -----");
                System.out.println("1. Exibir Detalhes da Academia");
                System.out.println("2. Criar Aluno");
                System.out.println("3. Exibir Dados do Aluno por ID");
                System.out.println("4. Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado

                switch (opcao) {
                    case 1:
                        exibirDetalhesAcademia(academia);
                        break;
                    case 2:
                        criarAluno(scanner, alunos, numAlunos);
                        numAlunos++;
                        break;
                    case 3:
                        exibirDadosAlunoPorId(scanner, alunos, numAlunos);
                        break;
                    case 4:
                        sair = true;
                        System.out.println("Saindo do programa...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }

        scanner.close();
    }

    private static Academia criarAcademia(Scanner scanner) {
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
        return new Academia(idAcademia, nomeAcademia, enderecoAcademia, dataCriacao, dataModificacao);
    }

    private static void exibirDetalhesAcademia(Academia academia) {
        if (academia != null) {
            System.out.println("Detalhes da Academia:");
            academia.exibirDetalhes();
        } else {
            System.out.println("Nenhuma academia criada ainda.");
        }
    }

    private static void criarAluno(Scanner scanner, Pessoa[] alunos, int numAlunos) {
        System.out.println("Informe os detalhes do aluno:");
        System.out.print("ID: ");
        int idAluno = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado
        System.out.print("Nome: ");
        String nomeAluno = scanner.nextLine();
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
        // Criar um novo aluno
        Pessoa aluno = new Pessoa(idAluno, nomeAluno, sexoAluno, dtNascimento, null, null, tipoUsuario, dataCriacao, dataModificacao);
        alunos[numAlunos] = aluno;
        System.out.println("Aluno criado com sucesso!");
    }

    private static void exibirDadosAlunoPorId(Scanner scanner, Pessoa[] alunos, int numAlunos) {
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
}
