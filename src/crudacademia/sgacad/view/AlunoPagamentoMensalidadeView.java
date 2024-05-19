package sgacad.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import sgacad.controller.AlunoPagamentoMensalidadeController;
import sgacad.controller.MensalidadeVigenteController;
import sgacad.controller.PessoaController;
import sgacad.model.AlunoPagamentoMensalidade;


public class AlunoPagamentoMensalidadeView {
    public static AlunoPagamentoMensalidade[] alunosPagamentosMensalidades = new AlunoPagamentoMensalidade[100];
    public static int numPagamentos = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void gerarAlunoPagamentoMensalidade(){
        PessoaView.exibirTodosAlunos();
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
        if (confirmacao.equals("S") || confirmacao.equals("s")) {
        double valorPago = mesesPagos * MensalidadeVigenteController.getMensalidadeVigente().getValor();
        AlunoPagamentoMensalidadeController.gerarAlunoPagamentoMensalidade(valorPago, idAluno, modalidade);

        }
        else {
            System.out.println("Pagamento cancelado");
            return;
        }
    }

    public static void listarPagamentos() {
        for (int i = 0; i < numPagamentos; i++) {
            System.out.println("\n\nID: " + alunosPagamentosMensalidades[i].getId());
            System.out.println("Mensalidade vigente: " + String.format("%.2f", alunosPagamentosMensalidades[i].getMensalidadeVigente()));
            System.out.println("Data de pagamento: " + formatarData(alunosPagamentosMensalidades[i].getDataPagamento()));
            System.out.println("Valor pago: " + String.format("%.2f", alunosPagamentosMensalidades[i].getValorPago()));
            System.out.println("Nome do Aluno: " + PessoaController.getAlunoById(alunosPagamentosMensalidades[i].getIdAluno()).getNome());
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
            System.out.println("Nome do Aluno: " + PessoaController.getAlunoById(alunoPagamentoMensalidade.getIdAluno()).getNome());
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

        if(AlunoPagamentoMensalidadeController.getPorId(id) == null){
            System.out.println("Pagamento nao encontrado");
            return;
        } 
            AlunoPagamentoMensalidadeController.removePagamento(id);
            System.out.println("\nPagamento removido com sucesso\n");
    }

    public static String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }

}
