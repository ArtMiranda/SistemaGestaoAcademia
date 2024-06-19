package sgacad.controller;

import java.time.LocalDate;

import sgacad.model.DivisaoTreino;
import sgacad.view.DivisaoTreinoView;

public class DivisaoTreinoController {
    public DivisaoTreino geraDivisaoTreino(int id, String nome, String descricao) {
        LocalDate currentDate = LocalDate.now();

        DivisaoTreino divisaoTreino = new DivisaoTreino(id, nome, descricao, currentDate, currentDate);
        return divisaoTreino;
    }

    public static DivisaoTreino getDivisaoTreinoById(int id) {
        for (int i = 0; i < sgacad.view.DivisaoTreinoView.numDivisoesTreino; i++) {
            if (DivisaoTreinoView.divisoesTreinos[i].getId() == id) {
                return DivisaoTreinoView.divisoesTreinos[i];
            }
        }
        return null;
    }
}
