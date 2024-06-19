package sgacad.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import sgacad.controller.MensalidadeVigenteController;
import sgacad.model.MensalidadeVigente;

public class MensalidadeVigenteView {

    public static MensalidadeVigente[] mensalidades = new MensalidadeVigente[100];
    public static int numMensalidades = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void criaMensalidadeVigente() {

        System.out.print("\nDigite o valor da mensalidade: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        LocalDate dataInicio = null;
        boolean dataValida = false;
        while (!dataValida) {
            try {
                System.out.print("\nInforme a data de Inicio (dd/MM/yyyy): ");
                String dataInicioStr = scanner.nextLine().trim();
                dataInicio = LocalDate.parse(dataInicioStr);

                if (dataInicio.isBefore(LocalDate.now())) {
                    System.out.print("\nData de inicio nao pode ser no passado. Informe novamente.");
                } else {
                    if (dataInicio.isBefore(MensalidadeVigenteController.getMensalidadeVigente().getTermino())) {
                        System.out.println("\nRemova a data de inicio da mensalidade vigente antes de cadastrar uma nova.");
                        return;
                    }
                    dataValida = true;
                }
            } catch (Exception e) {
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
                dataTermino = LocalDate.parse(dataTerminoStr);

                if (dataTermino.isBefore(dataInicio)) {
                    System.out.print("\nData de termino deve ser posterior à data de inicio. Informe novamente.");
                } else {
                    if (dataTermino.isBefore(MensalidadeVigenteController.getMensalidadeVigente().getTermino())) {
                        System.out.println("\nRemova a mensalidade vigente antes de cadastrar uma nova.");
                        return;
                    }
                    dataValida = true;
                }
            } catch (Exception e) {
                System.out.print("\nFormato de data invalido. Use o formato dd/MM/yyyy.");
            }
        }

        MensalidadeVigenteController.cadastrar(numMensalidades, valor, dataInicio, dataTermino);
    }

    public static void exibirHistoricoMensalidade() {
        if (numMensalidades == 0) {
            System.out.println("\n\nNenhuma mensalidade cadastrada ainda.");
        } else {
            System.out.println("\n\nLista de Mensalidades:");
            for (int i = 0; i < numMensalidades; i++) {
                System.out.println(
                        "ID: " + mensalidades[i].getId() + ", Valor: " + mensalidades[i].getValor()
                                + ", Inicio: " + formatarData(mensalidades[i].getInicio()) + ", Termino: " + formatarData(mensalidades[i].getTermino()));
            }
        }
    }

    public static void exibirMensalidadeVigente() {
        if (numMensalidades == 0) {
            System.out.println("\n\nNenhuma mensalidade cadastrada ainda.");
        } else {
            System.out.println("\n\nMensalidade Vigente:");
            for (int i = 0; i < numMensalidades; i++) {
                if (mensalidades[i].getTermino().isAfter(LocalDate.now()) && mensalidades[i].getInicio().isBefore(LocalDate.now())) {
                    System.out.println(
                            "ID: " + mensalidades[i].getId() + ", Valor: " + mensalidades[i].getValor()
                            + ", Inicio: " + formatarData(mensalidades[i].getInicio()) + ", Termino: " + formatarData(mensalidades[i].getTermino()));
            }
        }
    }
}

public static void removerMensalidade() {
    if (numMensalidades == 0) {
        System.out.println("\n\nNenhuma mensalidade cadastrada ainda.");
    } else {
        System.out.print("\nInforme o ID da mensalidade a ser removida: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean encontrada = false;
        for (int i = 0; i < numMensalidades; i++) {
            if (mensalidades[i].getId() == id) {
                encontrada = true;
                for (int j = i; j < numMensalidades - 1; j++) {
                    mensalidades[j] = mensalidades[j + 1];
                }
                numMensalidades--;
                System.out.println("\nMensalidade removida com sucesso.");
                break;
            }
        }

        if (!encontrada) {
            System.out.println("\nMensalidade nao encontrada.");
        }
    }
}

private static String formatarData(LocalDate data) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return data.format(formatter);
}
}

