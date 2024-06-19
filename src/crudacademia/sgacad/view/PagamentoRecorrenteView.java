package sgacad.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import sgacad.controller.MensalidadeVigenteController;
import sgacad.controller.PagamentoRecorrenteController;
import sgacad.controller.PessoaController;
import sgacad.model.PagamentoRecorrente;

public class PagamentoRecorrenteView {

    public static PagamentoRecorrente[] pagamentoRecorrente = new PagamentoRecorrente[100];
    public static int numPagamentosRecorrentes;
    private static Scanner scanner = new Scanner(System.in);

    public static void cadastrarPagamentoRecorrente() {
        PessoaView.exibirTodosAlunos();
        System.out.print("\nInforme o ID do aluno: ");
        int idPessoa = scanner.nextInt();
        scanner.nextLine();

        if (PessoaController.getAlunoById(idPessoa) == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        for (int i = 0; i < numPagamentosRecorrentes; i++) {
            if (pagamentoRecorrente[i].getIdPessoa() == idPessoa) {
                System.out.println(
                        "Já existe um pagamento recorrente cadastrado para esse aluno. Efetue o cancelamento desse pagamento para cadastrar um novo.");
                return;
            }
        }

        System.out.print("\nInforme o número do cartão de crédito: ");
        String cartaoCredito = scanner.nextLine();

        System.out.print("\nInforme o número de meses autorizados: ");
        int numeroMesesAutorizados = scanner.nextInt();
        scanner.nextLine();

        if (numeroMesesAutorizados <= 1) {
            System.out.println("O número de meses autorizados deve ser maior que 1.");
            return;
        }

        System.out.println("\n\nConfirme os dados: ");
        System.out.println("Aluno de ID: " + idPessoa);
        System.out.println("Nome do Aluno: " + PessoaController.getAlunoById(idPessoa).getNome());
        System.out.println("Nº do Cartão de Crédito: " + cartaoCredito);
        System.out.println("Nº de Meses Restantes: " + numeroMesesAutorizados);
        System.out.println("Valor Mensalidade: "
                + String.format("%.2f", MensalidadeVigenteController.getMensalidadeVigente().getValor()));
        System.out.println("Valor Total: " + String.format("%.2f",
                MensalidadeVigenteController.getMensalidadeVigente().getValor() * numeroMesesAutorizados));
        System.out.println("-----------------");
        System.out.print("\nO crédito será realizado no cartão de crédito informado em " + numeroMesesAutorizados
                + " parcelas de R$"
                + String.format("%.2f", MensalidadeVigenteController.getMensalidadeVigente().getValor())
                + ". Deseja continuar? (S/N): ");
        String confirmacao = scanner.nextLine();
        if (confirmacao.equalsIgnoreCase("S")) {
            System.out.println("Pagamento recorrente cadastrado com sucesso!");
        } else {
            System.out.println("Operação cancelada.");
            return;
        }

        PagamentoRecorrenteController.cadastrarPagamentoRecorrente(idPessoa, cartaoCredito, numeroMesesAutorizados);
    }

    public static void listarPagamentosRecorrentes() {
        for (int i = 0; i < numPagamentosRecorrentes; i++) {
            System.out.println("\nID: " + pagamentoRecorrente[i].getId());
            System.out.println("ID Pessoa: " + pagamentoRecorrente[i].getIdPessoa());
            System.out.println("Cartão de Crédito: " + pagamentoRecorrente[i].getCartaoCredito());
            System.out.println("Número de Meses Restantes: " + pagamentoRecorrente[i].getNumeroMesesAutorizados());
            System.out.println("Valor: " + String.format("%.2f", pagamentoRecorrente[i].getValor()));
            System.out.println("Data de Criação: " + formatarData(pagamentoRecorrente[i].getData()));
            System.out.println("Data de Modificação: " + formatarData(pagamentoRecorrente[i].getDataModificacao()));
        }
    }

    public static void listarPagamentosRecorrentesPorIdPessoa() {
        PessoaView.exibirTodosAlunos();
        System.out.print("\nInforme o ID do aluno: ");
        int idPessoa = scanner.nextInt();
        scanner.nextLine();

        if (PessoaController.getAlunoById(idPessoa) == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        boolean pagamentoEncontrado = false;
        for (int i = 0; i < numPagamentosRecorrentes; i++) {
            if (pagamentoRecorrente[i].getIdPessoa() == idPessoa) {
                pagamentoEncontrado = true;
                System.out.println("\n\nID: " + pagamentoRecorrente[i].getId());
                System.out.println("ID Pessoa: " + pagamentoRecorrente[i].getIdPessoa());
                System.out.println("Cartão de Crédito: " + pagamentoRecorrente[i].getCartaoCredito());
                System.out.println("Número de Meses Restantes: " + pagamentoRecorrente[i].getNumeroMesesAutorizados());
                System.out.println("Valor: " + String.format("%.2f", pagamentoRecorrente[i].getValor()));
                System.out.println("Data de Criação: " + formatarData(pagamentoRecorrente[i].getDataCriacao()));
                System.out.println("Data de Modificação: " + formatarData(pagamentoRecorrente[i].getDataModificacao()));
            }
        }

        if (!pagamentoEncontrado) {
            System.out.println("Nenhum pagamento recorrente encontrado para essa pessoa.");
        }
    }

    public static void removerPagamentoRecorrente() {
        listarPagamentosRecorrentes();
        System.out.print("\nInforme o ID do pagamento recorrente: ");
        int idPagamentoRecorrente = scanner.nextInt();
        scanner.nextLine();

        int index = -1;
        for (int i = 0; i < numPagamentosRecorrentes; i++) {
            if (pagamentoRecorrente[i].getId() == idPagamentoRecorrente) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            System.out.println("Pagamento recorrente encontrado:");
            System.out.println("\n\nID: " + pagamentoRecorrente[index].getId());
            System.out.println("ID Pessoa: " + pagamentoRecorrente[index].getIdPessoa());
            System.out.println("Cartão de Crédito: " + pagamentoRecorrente[index].getCartaoCredito());
            System.out.println("Número de Meses Restantes: " + pagamentoRecorrente[index].getNumeroMesesAutorizados());
            System.out.println("Valor: " + String.format("%.2f", pagamentoRecorrente[index].getValor()));
            System.out.println("Data de Criação: " + formatarData(pagamentoRecorrente[index].getDataCriacao()));
            System.out.println("Data de Modificação: " + formatarData(pagamentoRecorrente[index].getDataModificacao()));

            System.out.print("\nDeseja remover o pagamento recorrente? (S/N): ");
            String confirmacao = scanner.nextLine();
            if (confirmacao.equalsIgnoreCase("S")) {
                for (int i = index; i < numPagamentosRecorrentes - 1; i++) {
                    pagamentoRecorrente[i] = pagamentoRecorrente[i + 1];
                }
                numPagamentosRecorrentes--;
                System.out.println("Pagamento recorrente removido com sucesso!");
            } else {
                System.out.println("Operação cancelada.");
            }
        } else {
            System.out.println("Pagamento recorrente não encontrado.");
        }
    }

    private static String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

    public static void simularAvancoTempo() {
        System.out.print("\nInforme o número de meses a avançar: ");
        int mesesAvancar = scanner.nextInt();
        scanner.nextLine();

        // Removed the Calendar usage and replaced with LocalDate
        LocalDate dataAvancada = LocalDate.now().plusMonths(mesesAvancar);

        System.out.println("\nData avançada: " + formatarData(dataAvancada));

        PagamentoRecorrenteController.checarPagamentos(dataAvancada);
    }
}
