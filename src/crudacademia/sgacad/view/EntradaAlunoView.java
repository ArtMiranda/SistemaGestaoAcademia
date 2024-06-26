package sgacad.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import sgacad.controller.EntradaAlunoController;
import sgacad.controller.PessoaController;
import sgacad.model.EntradaAluno;

public class EntradaAlunoView {

    public static Scanner scanner = new Scanner(System.in);

    public static void visualizarTodasEntradas() {
        // Buscar todas as entradas do banco de dados
        List<EntradaAluno> entradas = EntradaAlunoController.buscarTodasEntradas();
        
        // Exibir todas as entradas encontradas
        for (EntradaAluno entrada : entradas) {
            System.out.println("\n\nID: " + entrada.getId());
            System.out.println("ID Aluno: " + entrada.getIdAluno());
            System.out.println("Data e Hora: " + formatarDataHora(entrada.getDataHora()));
            System.out.println("Data de Criacao: " + formatarData(entrada.getDataCriacao()));
            System.out.println("Data de Modificacao: " + formatarData(entrada.getDataModificacao()));
        }
    }

    public static void visualizarEntradaPorAluno(){
        PessoaView.exibirTodos("Aluno");
        System.out.print("\nDigite o ID do aluno: ");
        int idAluno = scanner.nextInt();
        if(PessoaController.getPessoaById(idAluno, "Aluno") == null){
            System.out.println("Aluno nao encontrado.");
            return;
        }

        List<EntradaAluno> entradas = EntradaAlunoController.buscarEntradasPorAluno(idAluno);
        
        if (entradas.size() > 0) {
            for (EntradaAluno entrada : entradas) {
                System.out.println("\n\nID: " + entrada.getId());
                System.out.println("ID Aluno: " + entrada.getIdAluno());
                System.out.println("Data e Hora: " + formatarDataHora(entrada.getDataHora()));
                System.out.println("Data de Criacao: " + formatarData(entrada.getDataCriacao()));
                System.out.println("Data de Modificacao: " + formatarData(entrada.getDataModificacao()));
            }
        } else {
            System.out.println("Nenhuma entrada encontrada para o aluno com ID " + idAluno);
        }
    }

    public static void removerEntradaAluno(){
        System.out.print("Digite o ID da entrada que deseja remover: ");
        int idEntrada = scanner.nextInt();
        
        // Remover a entrada do banco de dados com o ID especificado
        boolean removido = EntradaAlunoController.removerEntrada(idEntrada);
        
        if (removido) {
            System.out.println("Entrada removida com sucesso.");
        } else {
            System.out.println("Entrada nao encontrada.");
        }
    }
    
    private static String formatarDataHora(LocalDateTime data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return data.format(formatter);
    }

    private static String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }
}
