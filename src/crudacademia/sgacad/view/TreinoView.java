package sgacad.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import sgacad.controller.TreinoController;
import sgacad.model.Treino;

public class TreinoView {

    private static TreinoController treinoController = new TreinoController();
    private static Scanner scanner = new Scanner(System.in);

    public static Treino criaTreino() {
        System.out.print("\nInforme o objetivo: ");
        String objetivo = scanner.nextLine().trim();
        while (objetivo.isEmpty()) {
            System.out.print("\nObjetivo nao pode estar vazio. Informe novamente: ");
            objetivo = scanner.nextLine().trim();
        }

        // Data de Inicio
        LocalDate dataInicio = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        boolean dataValida = false;
        while (!dataValida) {
            try {
                System.out.print("\nInforme a data de Inicio (dd/MM/yyyy): ");
                String dataInicioStr = scanner.nextLine().trim();
                dataInicio = LocalDate.parse(dataInicioStr, formatter);
                if (dataInicio.isBefore(LocalDate.now())) {
                    System.out.print("\nData de inicio nao pode ser no passado. Informe novamente.");
                } else {
                    dataValida = true;
                }
            } catch (DateTimeParseException e) {
                System.out.print("\nFormato de data invalido. Use o formato dd/MM/yyyy.");
            }
        }

        // Data de Termino
        LocalDate dataTermino = null;
        dataValida = false;
        while (!dataValida) {
            try {
                System.out.print("\nInforme a data de Termino (dd/MM/yyyy): ");
                String dataTerminoStr = scanner.nextLine().trim();
                dataTermino = LocalDate.parse(dataTerminoStr, formatter);
                if (dataTermino.isBefore(dataInicio)) {
                    System.out.print("\nData de termino deve ser posterior à data de inicio. Informe novamente.");
                } else {
                    dataValida = true;
                }
            } catch (DateTimeParseException e) {
                System.out.print("\nFormato de data invalido. Use o formato dd/MM/yyyy.");
            }
        }

        TreinoController.adicionarTreino(objetivo, dataInicio, dataTermino);
        System.out.println("\nTreino criado com sucesso!");
        return null; // Nao estamos mais retornando um treino aqui, apenas adicionando ao banco de dados
    }

    public static void exibirTodosTreinos() {
        System.out.println("\nLista de Treinos:");
        treinoController.listarTreinos().forEach(treino ->
                System.out.println("ID: " + treino.getId() + ", Objetivo: " + treino.getObjetivo()));
    }

    public static void exibirDadosTreinoPorId() {
        System.out.print("\nInforme o ID do treino: ");
        int idBusca = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        Treino treino = TreinoController.getTreinoById(idBusca);
        if (treino != null) {
            System.out.println("\n----- Dados do Treino -----");
            System.out.println(treino.exibirDetalhes());
        } else {
            System.out.println("\nTreino nao encontrado.");
        }
    }

    public static void atualizarTreino() {
        System.out.print("\nInforme o ID do treino que deseja atualizar: ");
        int idTreino = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        Treino treino = TreinoController.getTreinoById(idTreino);
        if (treino != null) {
            System.out.println("\nDados atuais do Treino:");
            System.out.println(treino.exibirDetalhes());

            System.out.println("\nInforme os novos dados do treino:");

            // Objetivo do treino
            System.out.print("\nNovo Objetivo: ");
            String novoObjetivo = scanner.nextLine().trim();

            // Data de Inicio
            LocalDate novaDataInicio = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            boolean dataValida = false;
            while (!dataValida) {
                try {
                    System.out.print("\nNova data de Inicio (dd/MM/yyyy): ");
                    String dataInicioStr = scanner.nextLine().trim();
                    if (!dataInicioStr.isEmpty()) {
                        novaDataInicio = LocalDate.parse(dataInicioStr, formatter);
                        if (novaDataInicio.isBefore(LocalDate.now())) {
                            System.out.print("\nData de inicio nao pode ser no passado. Informe novamente.");
                        } else {
                            dataValida = true;
                        }
                    } else {
                        break; // Sai do loop se a data for vazia
                    }
                } catch (DateTimeParseException e) {
                    System.out.print("\nFormato de data invalido. Use o formato dd/MM/yyyy.");
                }
            }

            // Data de Termino
            LocalDate novaDataTermino = null;
            dataValida = false;
            while (!dataValida) {
                try {
                    System.out.print("\nNova data de Termino (dd/MM/yyyy): ");
                    String dataTerminoStr = scanner.nextLine().trim();
                    if (!dataTerminoStr.isEmpty()) {
                        novaDataTermino = LocalDate.parse(dataTerminoStr, formatter);
                        if (novaDataTermino.isBefore(novaDataInicio != null ? novaDataInicio : treino.getDataInicio())) {
                            System.out.print("\nData de termino deve ser posterior à data de inicio. Informe novamente.");
                        } else {
                            dataValida = true;
                        }
                    } else {
                        break; // Sai do loop se a data for vazia
                    }
                } catch (DateTimeParseException e) {
                    System.out.print("\nFormato de data invalido. Use o formato dd/MM/yyyy.");
                }
            }

            treinoController.atualizarTreino(idTreino, novoObjetivo, novaDataInicio, novaDataTermino);

            System.out.println("\nDados do Treino atualizados com sucesso:");
            Treino treinoAtualizado = TreinoController.getTreinoById(idTreino);
            System.out.println(treinoAtualizado.exibirDetalhes());
        } else {
            System.out.println("\nTreino com ID " + idTreino + " nao encontrado.");
        }
    }


    public static void removerTreino() {
        System.out.print("\nInforme o ID do treino que deseja remover: ");
        int idTreino = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        treinoController.removerTreino(idTreino);
        System.out.println("\nTreino removido com sucesso.");
    }
}
