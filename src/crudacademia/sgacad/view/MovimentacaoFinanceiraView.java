package sgacad.view;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import sgacad.model.MovimentacaoFinanceira;
import sgacad.controller.AlunoPagamentoMensalidadeController;
import sgacad.controller.MovimentacaoFinanceiraController;

public class MovimentacaoFinanceiraView {
    public static MovimentacaoFinanceira[] movimentacaoFinanceiras = new MovimentacaoFinanceira[200];
    public static int movimentacoesFinanceiras = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void listarMovimentacoesFinanceiras() {
        for (int i = 0; i < movimentacoesFinanceiras; i++) {
            System.out.println("\n\nID: " + movimentacaoFinanceiras[i].getId());
            System.out.println("Valor: " + String.format("%.2f", movimentacaoFinanceiras[i].getValor()));
            System.out.println("Tipo: " + movimentacaoFinanceiras[i].getTipo());
            System.out.println("Descricao: " + movimentacaoFinanceiras[i].getDescricao());
            System.out.println("Data de Criacao: " + formatarData(movimentacaoFinanceiras[i].getDataCriacao()));
            System.out.println("Data de Modificacao: " + formatarData(movimentacaoFinanceiras[i].getDataModificacao()));
        }
    }

    public static void cadastrarMovimentacaoFinanceira() {
    System.out.print("\nDigite o valor da movimentacao: ");
    double valor = scanner.nextDouble();
    scanner.nextLine();
    System.out.println("\n1 - Saida\n2 - Entrada");
    System.out.print("\nDigite o tipo da movimentacao: ");
    int tipo = scanner.nextInt();

    System.out.print("Descricao da movimentacao");
    String descricao = scanner.nextLine().trim();

    while (descricao.isEmpty()) {
        System.out.print("\nDescricao nao pode estar vazia: ");
        descricao = scanner.nextLine();
    }

    MovimentacaoFinanceiraController.cadastrar(valor, tipo, descricao);
    }

    public static void listarMovimentacoesEntrada(){
        boolean entradaEncontrada = false;
        for (int i = 0; i < movimentacoesFinanceiras; i++) {
            if (movimentacaoFinanceiras[i].getTipo().equals("Entrada")) {
            System.out.println("\nID: " + movimentacaoFinanceiras[i].getId());
            System.out.println("Valor: " + String.format("%.2f", movimentacaoFinanceiras[i].getValor()));
            System.out.println("Tipo: " + movimentacaoFinanceiras[i].getTipo());
            System.out.println("Descricao: " + movimentacaoFinanceiras[i].getDescricao());
            System.out.println("Data de Criacao: " + formatarData(movimentacaoFinanceiras[i].getDataCriacao()));
            System.out.println("Data de Modificacao: " + formatarData(movimentacaoFinanceiras[i].getDataModificacao()));
            entradaEncontrada = true;
            }
        }
        if (!entradaEncontrada) {
            System.out.println("Nenhuma movimentacao de entrada encontrada.");
        }
    }
    
    public static void listarMovimentacoesSaida(){
        boolean saidaEncontrada = false;
        for (int i = 0; i < movimentacoesFinanceiras; i++) {
            if (movimentacaoFinanceiras[i].getTipo().equals("Saida")) {
            System.out.println("\nID: " + movimentacaoFinanceiras[i].getId());
            System.out.println("Valor: " + String.format("%.2f", movimentacaoFinanceiras[i].getValor()));
            System.out.println("Tipo: " + movimentacaoFinanceiras[i].getTipo());
            System.out.println("Descricao: " + movimentacaoFinanceiras[i].getDescricao());
            System.out.println("Data de Criacao: " + formatarData(movimentacaoFinanceiras[i].getDataCriacao()));
            System.out.println("Data de Modificacao: " + formatarData(movimentacaoFinanceiras[i].getDataModificacao()));
            saidaEncontrada = true;
            }
        }
        if (!saidaEncontrada) {
            System.out.println("Nenhuma movimentacao de saida encontrada.");
        }
    }

    public static void relatorioMovimentacoesFinaceiras(){
        double totalEntrada = 0;
        double totalSaida = 0;
        for (int i = 0; i < movimentacoesFinanceiras; i++) {
            if (movimentacaoFinanceiras[i].getTipo().equals("Entrada")) {
                totalEntrada += movimentacaoFinanceiras[i].getValor();
            } else {
                totalSaida += movimentacaoFinanceiras[i].getValor();
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
    
    public static void removerMovimentacaoFinaceiraPorId(){
        System.out.print("\nDigite o ID da movimentacao que deseja remover: ");
        int id = scanner.nextInt();
        boolean movimentacaoEncontrada = false;
        for (int i = 0; i < movimentacoesFinanceiras; i++) {
            if (movimentacaoFinanceiras[i].getId() == id) {
            movimentacaoFinanceiras[i] = null;
            movimentacoesFinanceiras--;
            movimentacaoEncontrada = true;
            break;
            }
        }
        if (!movimentacaoEncontrada) {
            System.out.println("Movimentacao nao encontrada.");
        }
    }

    public static void exibirAlunosAdimplentes(){
        System.out.println("\n--- Alunos adimplentes ---");
        for(int i = 0; i < PessoaView.numAlunos; i++){
            if(AlunoPagamentoMensalidadeController.getPorIdAluno(PessoaView.alunos[i].getId())){
               System.out.println("ID: " + PessoaView.alunos[i].getId() + ", Nome: " + PessoaView.alunos[i].getNome());
            }
        }
    
    }



 private static String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }
}
