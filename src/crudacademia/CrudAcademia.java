import java.util.Date;

public class CrudAcademia {

    public static void main(String[] args) {

        Academia academia = Academia.criarAcademia(1, "Academia TopFit", "Rua das Academias, 123");

        // Exibindo os detalhes da academia
        System.out.println("Detalhes da Academia:");
        academia.exibirDetalhes();
        System.out.println();

        // Criando uma pessoa
        Pessoa pessoa = new Pessoa(1, "João", 'M', new Date(), "joao123", "senha", "aluno", new Date(), new Date());

        // Matriculando a pessoa na academia
        academia.matricularAluno(pessoa);

        // Exibindo os detalhes da academia após a matrícula
        System.out.println("Detalhes da Academia após a matrícula:");
        academia.exibirDetalhes();

    }

}


