package sgacad.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import sgacad.controller.TreinoController;
import sgacad.model.Treino;

public class TreinoView {
    public static Treino[] treinos = new Treino[100]; // Array de treinos
    public static int numTreinos = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static Treino criaTreino() {

        System.out.print("\nInforme o objetivo: ");
        String objetivo = scanner.nextLine().trim();
        while (objetivo.isEmpty()) {
            System.out.print("\nObjetivo nao pode estar vazio. Informe novamente: ");
            objetivo = scanner.nextLine().trim();
        }

        // Data de Inicio
        Date dataInicio = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean dataValida = false;
        while (!dataValida) {
            try {
                System.out.print("\nInforme a data de Inicio (dd/MM/yyyy): ");
                String dataInicioStr = scanner.nextLine().trim();
                dataInicio = sdf.parse(dataInicioStr);
                if (dataInicio.before(Calendar.getInstance().getTime())) {
                    System.out.print("\nData de inicio nao pode ser no passado. Informe novamente.");
                } else {
                    dataValida = true;
                }
            } catch (ParseException e) {
                System.out.print("\nFormato de data invalido. Use o formato dd/MM/yyyy.");
            }
        }

        // Data de Termino
        Date dataTermino = null;
        dataValida = false;
        while (!dataValida) {
            try {
                System.out.print("\nInforme a data de Termino (dd/MM/yyyy): ");
                String dataTerminoStr = scanner.nextLine().trim();
                dataTermino = sdf.parse(dataTerminoStr);
                if (dataTermino.before(dataInicio)) {
                    System.out.print("\nData de termino deve ser posterior à data de inicio. Informe novamente.");
                } else {
                    dataValida = true;
                }
            } catch (ParseException e) {
                System.out.print("\nFormato de data invalido. Use o formato dd/MM/yyyy.");
            }
        }

        TreinoController treinoController = new TreinoController();
        Treino treino = treinoController.geraTreino(numTreinos, objetivo, dataInicio, dataTermino);
        treinos[numTreinos] = treino;
        numTreinos++;
        return treino;
    }

    public static void exibirTodosTreinos() {
        if (TreinoView.numTreinos == 0) {
            System.out.print("\n\nNenhum treino cadastrado ainda.");
        } else {
            System.out.print("\n\nLista de Treinos:");
            for (int i = 0; i < TreinoView.numTreinos; i++) {
                System.out.print("\nID: " + treinos[i].getId() + ", Nome: " + treinos[i].getObjetivo());
            }
        }
    }

    public static void exibirDadosTreinoPorId() {
        int idBusca = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do treino: ");
            if (scanner.hasNextInt()) {
                idBusca = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.print("\nPor favor, insira apenas numeros inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < TreinoView.numTreinos; i++) {
            if (treinos[i].getId() == idBusca) {
                System.out.print("\n\n----- Dados do Treino -----\n\n");
                System.out.print("\n\n" + treinos[i].exibirDetalhes());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.print("\n\nTreino nao encontrado.");
        }
    }

    public static void atualizarTreino() {
        int idTreino = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do treino que deseja atualizar: ");
            if (scanner.hasNextInt()) {
                idTreino = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.print("Por favor, insira apenas numeros inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        Treino treino = TreinoController.getTreinoById(idTreino);
        if (treino != null) {
            System.out.print("\nDados atuais do Treino:");
            System.out.print(treino.exibirDetalhes());

            System.out.print("\nInforme os novos dados do treino:");

            // Objetivo do treino
            System.out.print("\n\nNovo Objetivo: ");
            String novoObjetivo = scanner.nextLine().trim();
            if (!novoObjetivo.isEmpty()) {
                treino.setObjetivo(novoObjetivo);
            }

            // Data de Inicio
            Date novaDataInicio = null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            boolean dataValida = false;
            while (!dataValida) {
                try {
                    System.out.print("\nNova data de Inicio (dd/MM/yyyy): ");
                    String dataInicioStr = scanner.nextLine().trim();
                    if (dataInicioStr.isEmpty()) {
                        break;
                    }
                    novaDataInicio = sdf.parse(dataInicioStr);
                    if (novaDataInicio.before(Calendar.getInstance().getTime())) {
                        System.out.print("\nData de inicio nao pode ser no passado. Informe novamente.");
                    } else {
                        dataValida = true;
                    }
                } catch (ParseException e) {
                    System.out.print("\nFormato de data invalido. Use o formato dd/MM/yyyy.");
                }
            }
            if (novaDataInicio != null) {
                treino.setDataInicio(novaDataInicio);
            }

            // Data de Termino
            Date novaDataTermino = null;
            dataValida = false;
            while (!dataValida) {
                try {
                    System.out.print("\nNova data de Termino (dd/MM/yyyy): ");
                    String dataTerminoStr = scanner.nextLine().trim();
                    if (dataTerminoStr.isEmpty()) {
                        break;
                    }
                    novaDataTermino = sdf.parse(dataTerminoStr);
                    if (novaDataTermino.before(novaDataInicio != null ? novaDataInicio : treino.getDataInicio())) {
                        System.out.print("\nData de termino deve ser posterior à data de inicio. Informe novamente.");
                    } else {
                        dataValida = true;
                    }
                } catch (ParseException e) {
                    System.out.print("\nFormato de data invalido. Use o formato dd/MM/yyyy.");
                }
            }
            if (novaDataTermino != null) {
                treino.setDataTermino(novaDataTermino);
            }

            // Data de modificacao
            treino.setDataModificacao(Calendar.getInstance().getTime());

            System.out.print("\nDados do Treino atualizados com sucesso:");
            System.out.print("\n" + treino.exibirDetalhes());
        } else {
            System.out.print("\nTreino com ID " + idTreino + " nao encontrado.");
        }
    }

    public static void removerTreino() {
        int idTreino = 0;

        // Loop de validacao
        boolean inputValido = false;
        while (!inputValido) {
            System.out.print("\n\nInforme o ID do treino que deseja remover: ");
            if (scanner.hasNextInt()) {
                idTreino = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do teclado
                inputValido = true;
            } else {
                System.out.print("\nPor favor, insira apenas numeros inteiros.");
                scanner.nextLine(); // Limpar o buffer do teclado
            }
        }

        boolean encontrado = false;
        for (int i = 0; i < TreinoView.numTreinos; i++) {
            if (treinos[i].getId() == idTreino) {
                for (int j = i; j < TreinoView.numTreinos - 1; j++) {
                    treinos[j] = treinos[j + 1];
                }
                treinos[TreinoView.numTreinos - 1] = null;
                TreinoView.numTreinos--;
                System.out.print("\n\nTreino removido com sucesso.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.print("\n\nTreino com ID " + idTreino + " nao encontrado.");
        }
    }
}
