package sgacad.controller;

import java.util.Calendar;
import java.util.Date;
import sgacad.model.MensalidadeVigente;
import sgacad.view.MensalidadeVigenteView;

public class MensalidadeVigenteController {

    public static void cadastrar(int id, double valor, Date inicio, Date termino) {
        Date currentDate = Calendar.getInstance().getTime();
        MensalidadeVigenteView.mensalidades[MensalidadeVigenteView.numMensalidades] = new MensalidadeVigente(id, valor, inicio, termino, currentDate, currentDate);
        MensalidadeVigenteView.numMensalidades++;
    }

    public static MensalidadeVigente getMensalidadeVigente() {
        MensalidadeVigente mensalidadeVigente = null;
        Date currentDate = Calendar.getInstance().getTime();

        for (int i = 0; i < MensalidadeVigenteView.numMensalidades; i++) {
            MensalidadeVigente mensalidade = MensalidadeVigenteView.mensalidades[i];
            if (mensalidade.getInicio().before(currentDate) && mensalidade.getTermino().after(currentDate)) {
                mensalidadeVigente = mensalidade;
                break;
            }
        }

        return mensalidadeVigente;
    }
}
