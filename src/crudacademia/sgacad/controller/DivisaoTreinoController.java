package sgacad.controller;

import java.util.Calendar;
import java.util.Date;

import sgacad.model.DivisaoTreino;
import sgacad.model.Exercicio;
import sgacad.model.Treino;
import sgacad.view.DivisaoTreinoView;

public class DivisaoTreinoController {
    public DivisaoTreino geraDivisaoTreino(int id, String nome, String descricao) {
        Date currentDate = Calendar.getInstance().getTime();

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
