package sgacad.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import sgacad.controller.MensalidadeVigenteController;
import sgacad.controller.PagamentoRecorrenteController;
import sgacad.controller.PessoaController;
import sgacad.model.PagamentoRecorrente;

public class PagamentoRecorrenteView {

    private static Scanner scanner = new Scanner(System.in);
    public static void cadastrarPagamentoRecorrente() {
        PessoaView.exibirTodos("Aluno");
        System.out.print("\nInforme o ID do aluno: ");
        int idPessoa = scanner.nextInt();
        scanner.nextLine();

        if (PessoaController.getPessoaById(idPessoa, "Aluno") == null) {
            System.out.println("Aluno nao encontrado.");
            return;
        }

        List<PagamentoRecorrente> pagamentos = PagamentoRecorrenteController.getAllPagamentosRecorrentes();
        for (PagamentoRecorrente pagamento : pagamentos) {
            if (pagamento.getIdPessoa() == idPessoa) {
                System.out.println(
                        "Ja existe um pagamento recorrente cadastrado para esse aluno. Efetue o cancelamento desse pagamento para cadastrar um novo.");
                return;
            }
        }

        System.out.print("\nInforme o numero do cartao de credito: ");
        String cartaoCredito = scanner.nextLine();

        System.out.print("\nInforme o numero de meses autorizados: ");
        int numeroMesesAutorizados = scanner.nextInt();
        scanner.nextLine();

        if (numeroMesesAutorizados <= 1) {
            System.out.println("O numero de meses autorizados deve ser maior que 1.");
            return;
        }

        System.out.println("\n\nConfirme os dados: ");
        System.out.println("Aluno de ID: " + idPessoa);
        System.out.println("Nome do Aluno: " + PessoaController.getPessoaById(idPessoa, "Aluno").getNome());
        System.out.println("Nº do Cartao de Credito: " + cartaoCredito);
        System.out.println("Nº de Meses Restantes: " + numeroMesesAutorizados);
        System.out.println("Valor Mensalidade: "
                + String.format("%.2f", MensalidadeVigenteController.getMensalidadeVigente().getValor()));
        System.out.println("Valor Total: " + String.format("%.2f",
                MensalidadeVigenteController.getMensalidadeVigente().getValor() * numeroMesesAutorizados));
        System.out.println("-----------------");
        System.out.print("\nO credito sera realizado no cartao de credito informado em " + numeroMesesAutorizados
                + " parcelas de R$"
                + String.format("%.2f", MensalidadeVigenteController.getMensalidadeVigente().getValor())
                + ". Deseja continuar? (S/N): ");
        String confirmacao = scanner.nextLine();
        if (confirmacao.equalsIgnoreCase("S")) {
            PagamentoRecorrenteController.cadastrarPagamentoRecorrente(idPessoa, cartaoCredito, numeroMesesAutorizados);
            System.out.println("Pagamento recorrente cadastrado com sucesso!");
        } else {
            System.out.println("Operacao cancelada.");
        }
    }

    public static void listarPagamentosRecorrentes() {
        List<PagamentoRecorrente> pagamentos = PagamentoRecorrenteController.getAllPagamentosRecorrentes();
        for (PagamentoRecorrente pagamento : pagamentos) {
            System.out.println("\nID: " + pagamento.getId());
            System.out.println("ID Pessoa: " + pagamento.getIdPessoa());
            System.out.println("Cartao de Credito: " + pagamento.getCartaoCredito());
            System.out.println("Numero de Meses Restantes: " + pagamento.getNumeroMesesAutorizados());
            System.out.println("Valor: " + String.format("%.2f", pagamento.getValor()));
            System.out.println("Data de Criacao: " + formatarData(pagamento.getDataCriacao()));
            System.out.println("Data de Modificacao: " + formatarData(pagamento.getDataModificacao()));
        }
    }

    public static void listarPagamentosRecorrentesPorIdPessoa() {
        PessoaView.exibirTodos("Aluno");
        System.out.print("\nInforme o ID do aluno: ");
        int idPessoa = scanner.nextInt();
        scanner.nextLine();

        if (PessoaController.getPessoaById(idPessoa, "Aluno") == null) {
            System.out.println("Aluno nao encontrado.");
            return;
        }

        boolean pagamentoEncontrado = false;
        List<PagamentoRecorrente> pagamentos = PagamentoRecorrenteController.getAllPagamentosRecorrentes();
        for (PagamentoRecorrente pagamento : pagamentos) {
            if (pagamento.getIdPessoa() == idPessoa) {
                pagamentoEncontrado = true;
                System.out.println("\n\nID: " + pagamento.getId());
                System.out.println("ID Pessoa: " + pagamento.getIdPessoa());
                System.out.println("Cartao de Credito: " + pagamento.getCartaoCredito());
                System.out.println("Numero de Meses Restantes: " + pagamento.getNumeroMesesAutorizados());
                System.out.println("Valor: " + String.format("%.2f", pagamento.getValor()));
                System.out.println("Data de Criacao: " + formatarData(pagamento.getDataCriacao()));
                System.out.println("Data de Modificacao: " + formatarData(pagamento.getDataModificacao()));
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

        PagamentoRecorrente pagamento = PagamentoRecorrenteController.getPagamentoRecorrenteById(idPagamentoRecorrente);

        if (pagamento != null) {
            System.out.println("Pagamento recorrente encontrado:");
            System.out.println("\n\nID: " + pagamento.getId());
            System.out.println("ID Pessoa: " + pagamento.getIdPessoa());
            System.out.println("Cartao de Credito: " + pagamento.getCartaoCredito());
            System.out.println("Numero de Meses Restantes: " + pagamento.getNumeroMesesAutorizados());
            System.out.println("Valor: " + String.format("%.2f", pagamento.getValor()));
            System.out.println("Data de Criacao: " + formatarData(pagamento.getDataCriacao()));
            System.out.println("Data de Modificacao: " + formatarData(pagamento.getDataModificacao()));

            System.out.print("\nDeseja remover o pagamento recorrente? (S/N): ");
            String confirmacao = scanner.nextLine();
            if (confirmacao.equalsIgnoreCase("S")) {
                PagamentoRecorrenteController.removerPagamentoRecorrente(idPagamentoRecorrente);
                System.out.println("Pagamento recorrente removido com sucesso!");
            } else {
                System.out.println("Operacao cancelada.");
            }
        } else {
            System.out.println("Pagamento recorrente nao encontrado.");
        }
    }

    private static String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

    public static void simularAvancoTempo() {
        System.out.print("\nInforme o numero de meses a avancar: ");
        int mesesAvancar = scanner.nextInt();
        scanner.nextLine();

        LocalDate dataAvancada = LocalDate.now().plusMonths(mesesAvancar);

        System.out.println("\nData avancada: " + formatarData(dataAvancada));

        PagamentoRecorrenteController.checarPagamentos(dataAvancada);
    }
}
