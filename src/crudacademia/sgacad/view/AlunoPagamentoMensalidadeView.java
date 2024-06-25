package sgacad.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import sgacad.controller.AlunoPagamentoMensalidadeController;
import sgacad.controller.DatabaseUtil;
import sgacad.controller.MensalidadeVigenteController;
import sgacad.controller.PessoaController;
import sgacad.model.AlunoPagamentoMensalidade;

public class AlunoPagamentoMensalidadeView {
    private static Scanner scanner = new Scanner(System.in);

    public static void gerarAlunoPagamentoMensalidade() {
        PessoaView.exibirTodos("Aluno");
        System.out.print("\n\nInforme o ID do Aluno: ");
        int idAluno = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n1 - Dinheiro");
        System.out.println("2 - PIX");
        System.out.println("3 - Debito automatico");
        System.out.println("4 - Pagamento recorrente");
        System.out.print("\nInforme a modalidade de pagamento: ");
        int idPagamento = scanner.nextInt();
        scanner.nextLine();
        String modalidade = "";
        switch (idPagamento) {
            case 1:
                modalidade = "Dinheiro";
                break;
            case 2:
                modalidade = "PIX";
                break;
            case 3:
                modalidade = "Debito automatico";
                break;
            case 4:
                modalidade = "Pagamento recorrente";
                break;
            default:
                System.out.println("Opcao invalida");
                break;
        }
        System.out.println("Valor da mensalidade vigente: " + String.format("%.2f", MensalidadeVigenteController.getMensalidadeVigente().getValor()));
        System.out.print("\nQuantos meses serao pagos: ");
        int mesesPagos = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Valor total: " + String.format("%.2f", mesesPagos * MensalidadeVigenteController.getMensalidadeVigente().getValor()));
        System.out.print("\n\nConfirma pagamento? (S/N): ");
        String confirmacao = scanner.nextLine();
        if (confirmacao.equalsIgnoreCase("S")) {
            double valorPago = mesesPagos * MensalidadeVigenteController.getMensalidadeVigente().getValor();
            AlunoPagamentoMensalidadeController.gerarAlunoPagamentoMensalidade(valorPago, idAluno, modalidade);
        } else {
            System.out.println("Pagamento cancelado");
        }
    }

    public static void listarPagamentos() {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM aluno_pagamento_mensalidade";
            try (PreparedStatement stmt = connection.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("\n\nID: " + rs.getInt("id"));
                    System.out.println("Mensalidade vigente: " + String.format("%.2f", rs.getDouble("mensalidade_vigente")));
                    System.out.println("Data de vencimento: " + formatarData(rs.getDate("data_vencimento").toLocalDate()));
                    System.out.println("Data de pagamento: " + formatarData(rs.getDate("data_pagamento").toLocalDate()));
                    System.out.println("Valor pago: " + String.format("%.2f", rs.getDouble("valor_pago")));
                    System.out.println("Nome do Aluno: " + PessoaController.getPessoaById(rs.getInt("id_aluno"), "Aluno").getNome());
                    System.out.println("Modalidade: " + rs.getString("modalidade"));
                    System.out.println("Data de criacao: " + formatarData(rs.getDate("data_criacao").toLocalDate()));
                    System.out.println("Data de modificacao: " + formatarData(rs.getDate("data_modificacao").toLocalDate()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarPagamento() {
        System.out.print("\n\nInforme o ID do pagamento: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        AlunoPagamentoMensalidade alunoPagamentoMensalidade = AlunoPagamentoMensalidadeController.getPorId(id);
        if (alunoPagamentoMensalidade != null) {
            System.out.println("ID: " + alunoPagamentoMensalidade.getId());
            System.out.println("Mensalidade vigente: " + alunoPagamentoMensalidade.getMensalidadeVigente());
            System.out.println("Data de vencimento: " + formatarData(alunoPagamentoMensalidade.getDataVencimento()));
            System.out.println("Data de pagamento: " + formatarData(alunoPagamentoMensalidade.getDataPagamento()));
            System.out.println("Valor pago: " + String.format("%.2f", alunoPagamentoMensalidade.getValorPago()));
            System.out.println("Nome do Aluno: " + PessoaController.getPessoaById(alunoPagamentoMensalidade.getIdAluno(), "Aluno").getNome());
            System.out.println("Modalidade: " + alunoPagamentoMensalidade.getModalidade());
            System.out.println("Data de criacao: " + formatarData(alunoPagamentoMensalidade.getDataCriacao()));
            System.out.println("Data de modificacao: " + formatarData(alunoPagamentoMensalidade.getDataModificacao()));
        } else {
            System.out.println("Pagamento nao encontrado");
        }
    }

    public static void removePagamentoPorId() {
        System.out.print("\nInforme o ID do pagamento: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (AlunoPagamentoMensalidadeController.getPorId(id) == null) {
            System.out.println("Pagamento nao encontrado");
            return;
        }
        AlunoPagamentoMensalidadeController.removePagamento(id);
        System.out.println("\nPagamento removido com sucesso\n");
    }

    private static String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }
}
