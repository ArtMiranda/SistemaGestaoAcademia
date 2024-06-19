package sgacad.controller;

import java.time.LocalDate;

import sgacad.model.MensalidadeVigente;
import sgacad.view.MensalidadeVigenteView;

public class MensalidadeVigenteController {

    public static void cadastrar(int id, double valor, LocalDate inicio, LocalDate termino) {
        LocalDate currentDate = LocalDate.now();
        MensalidadeVigenteView.mensalidades[MensalidadeVigenteView.numMensalidades] = new MensalidadeVigente(id, valor, inicio, termino, currentDate, currentDate);
        MensalidadeVigenteView.numMensalidades++;
    }

    public static MensalidadeVigente getMensalidadeVigente() {
        MensalidadeVigente mensalidadeVigente = null;
        LocalDate currentDate = LocalDate.now();

        for (int i = 0; i < MensalidadeVigenteView.numMensalidades; i++) {
            MensalidadeVigente mensalidade = MensalidadeVigenteView.mensalidades[i];
            if (mensalidade.getInicio().isBefore(currentDate) && mensalidade.getTermino().isAfter(currentDate)) {
                mensalidadeVigente = mensalidade;
                break;
            }
        }

        return mensalidadeVigente;
    }
}
 