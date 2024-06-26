package sgacad.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import sgacad.controller.AlunoPagamentoMensalidadeController;
import sgacad.controller.MovimentacaoFinanceiraController;
import sgacad.model.MovimentacaoFinanceira;
import sgacad.model.Pessoa;

public class MovimentacaoFinanceiraView {

    private static Scanner scanner = new Scanner(System.in);

    public static void listarMovimentacoesFinanceiras() {
        List<MovimentacaoFinanceira> movimentacoes = MovimentacaoFinanceiraController.listarTodas();
        for (MovimentacaoFinanceira movimentacao : movimentacoes) {
            System.out.println("\n\nID: " + movimentacao.getId());
            System.out.println("Valor: " + String.format("%.2f", movimentacao.getValor()));
            System.out.println("Tipo: " + movimentacao.getTipo());
            System.out.println("Descricao: " + movimentacao.getDescricao());
            System.out.println("Data de Criacao: " + formatarData(movimentacao.getDataCriacao()));
            System.out.println("Data de Modificacao: " + formatarData(movimentacao.getDataModificacao()));
        }
    }

    public static void cadastrarMovimentacaoFinanceira() {
        System.out.print("\nDigite o valor da movimentacao: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("\n1 - Saida\n2 - Entrada");
        System.out.print("\nDigite o tipo da movimentacao: ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a linha nova após o próximo int

        System.out.print("Descricao da movimentacao: ");
        String descricao = scanner.nextLine().trim();

        while (descricao.isEmpty()) {
            System.out.print("\nDescricao nao pode estar vazia: ");
            descricao = scanner.nextLine().trim();
        }

        MovimentacaoFinanceiraController.cadastrar(valor, tipo, descricao);
    }

    public static void listarMovimentacoesEntrada() {
        List<MovimentacaoFinanceira> movimentacoes = MovimentacaoFinanceiraController.listarTodas();
        boolean entradaEncontrada = false;
        for (MovimentacaoFinanceira movimentacao : movimentacoes) {
            if (movimentacao.getTipo().equals("Entrada")) {
                System.out.println("\nID: " + movimentacao.getId());
                System.out.println("Valor: " + String.format("%.2f", movimentacao.getValor()));
                System.out.println("Tipo: " + movimentacao.getTipo());
                System.out.println("Descricao: " + movimentacao.getDescricao());
                System.out.println("Data de Criacao: " + formatarData(movimentacao.getDataCriacao()));
                System.out.println("Data de Modificacao: " + formatarData(movimentacao.getDataModificacao()));
                entradaEncontrada = true;
            }
        }
        if (!entradaEncontrada) {
            System.out.println("Nenhuma movimentacao de entrada encontrada.");
        }
    }

    public static void listarMovimentacoesSaida() {
        List<MovimentacaoFinanceira> movimentacoes = MovimentacaoFinanceiraController.listarTodas();
        boolean saidaEncontrada = false;
        for (MovimentacaoFinanceira movimentacao : movimentacoes) {
            if (movimentacao.getTipo().equals("Saida")) {
                System.out.println("\nID: " + movimentacao.getId());
                System.out.println("Valor: " + String.format("%.2f", movimentacao.getValor()));
                System.out.println("Tipo: " + movimentacao.getTipo());
                System.out.println("Descricao: " + movimentacao.getDescricao());
                System.out.println("Data de Criacao: " + formatarData(movimentacao.getDataCriacao()));
                System.out.println("Data de Modificacao: " + formatarData(movimentacao.getDataModificacao()));
                saidaEncontrada = true;
            }
        }
        if (!saidaEncontrada) {
            System.out.println("Nenhuma movimentacao de saida encontrada.");
        }
    }

    public static void relatorioMovimentacoesFinanceiras() {
        List<MovimentacaoFinanceira> movimentacoes = MovimentacaoFinanceiraController.listarTodas();
        double totalEntrada = 0;
        double totalSaida = 0;
        for (MovimentacaoFinanceira movimentacao : movimentacoes) {
            if (movimentacao.getTipo().equals("Entrada")) {
                totalEntrada += movimentacao.getValor();
            } else {
                totalSaida += movimentacao.getValor();
            }
        }
        System.out.println("\n---- Relatorio de Movimentacoes Financeiras ----");

        System.out.println("\n---- Entradas ----");
        listarMovimentacoesEntrada();
        System.out.print("Total de Entradas: " + String.format("%.2f", totalEntrada));

        System.out.println("\n---- Saidas ----");
        listarMovimentacoesSaida();
        System.out.print("Total de Saidas: " + String.format("%.2f", totalSaida));

        System.out.println("\n\n---- Relacao final ----");
        System.out.println("Saldo: " + String.format("%.2f", totalEntrada - totalSaida));
    }

    public static void removerMovimentacaoFinanceiraPorId() {
        System.out.print("\nDigite o ID da movimentacao que deseja remover: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a linha nova após o próximo int

        MovimentacaoFinanceiraController.removerMovimentacaoFinanceiraPorId(id);
        System.out.println("\nMovimentacao removida com sucesso.");
    }

    public static void exibirAlunosAdimplentes() {
        System.out.println("\n--- Alunos adimplentes ---");
        List<Pessoa> alunosAdimplentes = AlunoPagamentoMensalidadeController.getAlunosAdimplentes();
        if (alunosAdimplentes.isEmpty()) {
            System.out.println("Nenhum aluno adimplente encontrado.");
        } else {
            for (Pessoa aluno : alunosAdimplentes) {
                System.out.println("ID: " + aluno.getId() + ", Nome: " + aluno.getNome());
            }
        }
    }

    private static String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }
}
