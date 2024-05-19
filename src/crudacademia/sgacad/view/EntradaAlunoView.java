package sgacad.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import sgacad.controller.PessoaController;
import sgacad.model.EntradaAluno;

public class EntradaAlunoView {

    public static EntradaAluno[] entradasAlunos = new EntradaAluno[100];
    public static int numEntradas;
    public static Scanner scanner = new Scanner(System.in);

    public static void visualizarTodasEntradas() {
        for (int i = 0; i < numEntradas; i++) {
            System.out.println("\n\nID: " + entradasAlunos[i].getId());
            System.out.println("ID Aluno: " + entradasAlunos[i].getIdAluno());
            System.out.println("Data e Hora: " + formatarDataHora(entradasAlunos[i].getDataHora()));
            System.out.println("Data de Criacao: " + formatarData(entradasAlunos[i].getDataCriacao()));
            System.out.println("Data de Modificacao: " + formatarData(entradasAlunos[i].getDataModificacao()));
        }
    }

    public static void visualizarEntradaPorAluno(){
        PessoaView.exibirTodosAlunos();
        System.out.print("\nDigite o ID do aluno: ");
        int idAluno = scanner.nextInt();
        if(PessoaController.getAlunoById(idAluno) == null){
            System.out.println("Aluno nao encontrado.");
            return;
        }

        boolean encontrouEntrada = false;
        for (int i = 0; i < numEntradas; i++) {
            if (entradasAlunos[i].getIdAluno() == idAluno) {
            encontrouEntrada = true;
            System.out.println("\n\nID: " + entradasAlunos[i].getId());
            System.out.println("ID Aluno: " + entradasAlunos[i].getIdAluno());
            System.out.println("Data e Hora: " + formatarDataHora(entradasAlunos[i].getDataHora()));
            System.out.println("Data de Criacao: " + formatarData(entradasAlunos[i].getDataCriacao()));
            System.out.println("Data de Modificacao: " + formatarData(entradasAlunos[i].getDataModificacao()));
            }
        }
        if (!encontrouEntrada) {
            System.out.println("Nenhuma entrada encontrada para o aluno com ID " + idAluno);
        }
    }

    public static void removerEntradaAluno(){
        System.out.print("Digite o ID da entrada que deseja remover: ");
        int idEntrada = scanner.nextInt();
        boolean encontrouEntrada = false;
        for (int i = 0; i < numEntradas; i++) {
            if (entradasAlunos[i].getId() == idEntrada) {
                encontrouEntrada = true;
                for (int j = i; j < numEntradas - 1; j++) {
                    entradasAlunos[j] = entradasAlunos[j + 1];
                }
                numEntradas--;
                System.out.println("Entrada removida com sucesso.");
                break;
            }
        }
        if (!encontrouEntrada) {
            System.out.println("Entrada nao encontrada.");
        }
    }
    
    private static String formatarDataHora(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(data);
    }
    private static String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }

}
