package sgacad.controller;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sgacad.model.Academia;


public class AcademiaController {
    
    public Academia criarAcademia(String nomeAcademia, String enderecoAcademia) {
                Date currentDate = Calendar.getInstance().getTime();
                Academia academia = new Academia(1, nomeAcademia, enderecoAcademia, currentDate, currentDate);
                return academia;

    }

    private String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }
}
