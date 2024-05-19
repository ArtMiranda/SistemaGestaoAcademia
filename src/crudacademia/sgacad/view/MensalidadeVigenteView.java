package sgacad.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import sgacad.controller.MensalidadeVigenteController;
import sgacad.model.MensalidadeVigente;

public class MensalidadeVigenteView {
    public static MensalidadeVigente[] mensalidades = new MensalidadeVigente[100];
    public static int numMensalidades = 0;
    private static Scanner scanner = new Scanner(System.in);


    public static void criaMensalidadeVigente(){
        System.out.print("\nDigite o valor da mensalidade: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

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
                                + ", Inicio: " + mensalidades[i].getInicio() + ", Termino: " + mensalidades[i].getTermino());
            }
        }
    }

    public static void exibirMensalidadeVigente(){
        if (numMensalidades == 0) {
            System.out.println("\n\nNenhuma mensalidade cadastrada ainda.");
        } else {
            System.out.println("\n\nMensalidade Vigente:");
            for (int i = 0; i < numMensalidades; i++) {
                if (mensalidades[i].getTermino().after(Calendar.getInstance().getTime())) {
                    System.out.println(
                            "ID: " + mensalidades[i].getId() + ", Valor: " + mensalidades[i].getValor()
                                    + ", Inicio: " + mensalidades[i].getInicio() + ", Termino: " + mensalidades[i].getTermino());
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
                System.out.println("\nMensalidade não encontrada.");
            }
        }
    }
}
