package sgacad.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import sgacad.controller.MensalidadeVigenteController;
import sgacad.model.MensalidadeVigente;

public class MensalidadeVigenteView {

    private static Scanner scanner = new Scanner(System.in);
    private static MensalidadeVigenteController mensalidadeVigenteController = new MensalidadeVigenteController();

    public static void criaMensalidadeVigente() {

        if(MensalidadeVigenteController.getMensalidadeVigente() != null) {
            System.out.println("\n\nJa existe uma mensalidade vigente cadastrada.");
            return;
        }

        System.out.print("\nDigite o valor da mensalidade: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        LocalDate dataInicio = null;
        boolean dataValida = false;
        while (!dataValida) {
            try {
                System.out.print("\nInforme a data de Inicio (dd/MM/yyyy): ");
                String dataInicioStr = scanner.nextLine().trim();
                dataInicio = LocalDate.parse(dataInicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                if (dataInicio.isBefore(LocalDate.now())) {
                    System.out.print("\nData de inicio nao pode ser no passado. Informe novamente.");
                } else {
                    MensalidadeVigente vigente = MensalidadeVigenteController.getMensalidadeVigente();
                    if (vigente != null && dataInicio.isBefore(vigente.getTermino())) {
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
                dataTermino = LocalDate.parse(dataTerminoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                if (dataTermino.isBefore(dataInicio)) {
                    System.out.print("\nData de termino deve ser posterior à data de inicio. Informe novamente.");
                } else {
                    MensalidadeVigente vigente = MensalidadeVigenteController.getMensalidadeVigente();
                    if (vigente != null && dataTermino.isBefore(vigente.getTermino())) {
                        System.out.println("\nRemova a mensalidade vigente antes de cadastrar uma nova.");
                        return;
                    }
                    dataValida = true;
                }
            } catch (Exception e) {
                System.out.print("\nFormato de data invalido. Use o formato dd/MM/yyyy.");
            }
        }

        MensalidadeVigente mensalidadeVigente = MensalidadeVigenteController.cadastrarOuAtualizar(0, valor, dataInicio, dataTermino);
        if (mensalidadeVigente != null) {
            System.out.println("Mensalidade cadastrada ou atualizada com sucesso.");
        } else {
            System.out.println("Erro ao cadastrar ou atualizar mensalidade.");
        }
    }

    public static void exibirHistoricoMensalidade() {
        List<MensalidadeVigente> mensalidades = mensalidadeVigenteController.getAllMensalidades();
        if (mensalidades.isEmpty()) {
            System.out.println("\n\nNenhuma mensalidade cadastrada ainda.");
        } else {
            System.out.println("\n\nLista de Mensalidades:");
            for (MensalidadeVigente mensalidade : mensalidades) {
                System.out.println(
                        "ID: " + mensalidade.getId() + ", Valor: " + mensalidade.getValor()
                                + ", Inicio: " + formatarData(mensalidade.getInicio()) + ", Termino: " + formatarData(mensalidade.getTermino()));
            }
        }
    }

    public static void exibirMensalidadeVigente() {
        MensalidadeVigente vigente = MensalidadeVigenteController.getMensalidadeVigente();
        if (vigente == null) {
            System.out.println("\n\nNenhuma mensalidade vigente cadastrada ainda.");
        } else {
            System.out.println("\n\nMensalidade Vigente:");
            System.out.println(
                    "ID: " + vigente.getId() + ", Valor: " + vigente.getValor()
                            + ", Inicio: " + formatarData(vigente.getInicio()) + ", Termino: " + formatarData(vigente.getTermino()));
        }
    }

    public static void removerMensalidade() {
        System.out.print("\nInforme o ID da mensalidade a ser removida: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        MensalidadeVigente vigente = MensalidadeVigenteController.getMensalidadeVigente();
        if (vigente != null && vigente.getId() == id) {
            mensalidadeVigenteController.deleteMensalidadeVigente(id);
            System.out.println("\nMensalidade removida com sucesso.");
        } else {
            System.out.println("\nMensalidade nao encontrada.");
        }
    }

    private static String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }
}
