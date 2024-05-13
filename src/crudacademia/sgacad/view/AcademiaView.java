package sgacad.view;

import sgacad.model.Academia;
import java.util.Scanner;
import sgacad.controller.AcademiaController;

public class AcademiaView {
    private static Scanner scanner = new Scanner(System.in);
    public static Academia academia = null;

    public static Academia criarAcademia() {
        System.out.println("\n\n----- Criacão da Academia -----");
        System.out.println("Informe os detalhes da academia:");
        System.out.print("Nome da Academia: ");
        String nomeAcademia = scanner.nextLine();
        System.out.print("Endereco da Academia: ");
        String enderecoAcademia = scanner.nextLine();

        AcademiaController academiaController = new AcademiaController();
        academia = academiaController.criarAcademia(nomeAcademia, enderecoAcademia);

        System.out.println("Academia criada com sucesso!");

        return academia;
    }

    public static void exibirDetalhesAcademia(Academia academia) {
        if (academia != null) {
            System.out.println("\n\n----- Detalhes da Academia -----");
            System.out.println(academia.exibirDetalhes());
        } else {
            System.out.println("Nenhuma academia criada ainda.");
        }
    }
}
