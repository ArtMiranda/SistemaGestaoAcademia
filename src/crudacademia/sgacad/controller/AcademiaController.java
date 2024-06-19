package sgacad.controller;
import java.time.LocalDate;
import sgacad.model.Academia;


public class AcademiaController {
    
    public Academia criarAcademia(String nomeAcademia, String enderecoAcademia) {
                LocalDate currentDate = LocalDate.now();
                Academia academia = new Academia(1, nomeAcademia, enderecoAcademia, currentDate, currentDate);
                return academia;
    }
}
