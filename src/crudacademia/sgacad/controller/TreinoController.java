package sgacad.controller;

import java.time.LocalDate;
import sgacad.model.Treino;
import sgacad.view.TreinoView;

public class TreinoController {

    // Metodo para criar um novo treino
    public Treino geraTreino(int id, String objetivo, LocalDate dataInicio, LocalDate dataTermino) {
        LocalDate currentDate = LocalDate.now();
        Treino treino = new Treino(id, objetivo, dataInicio, dataTermino, currentDate, currentDate);
        return treino;
    }

    // Metodo para buscar um treino pelo ID
    public static Treino getTreinoById(int id) {
        for (int i = 0; i < TreinoView.numTreinos; i++) {
            if (TreinoView.treinos[i].getId() == id) {
                return TreinoView.treinos[i];
            }
        }
        return null;
    }

}
