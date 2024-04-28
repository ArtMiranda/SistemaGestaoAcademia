public class CrudAcademia {

    public static void main(String[] args) {

       AcademiaDAO academiaDAO = new AcademiaDAO();
       academiaDAO.criaAcademia(1, "Academia XYZ", "Rua ABC, 123");

        Academia[] academias = academiaDAO.listarAcademias();

        // Exibe as informações da academia criada
        for (Academia academia : academias) {
            System.out.println("ID: " + academia.getId());
            System.out.println("Nome: " + academia.getNome());
            System.out.println("Endereço: " + academia.getEndereco());
            System.out.println("Data de Criação: " + academia.getDataCriacao());
            System.out.println("Data de Modificação: " + academia.getDataModificacao());
        }


    }

}
