package sgacad.controller;

import java.util.Calendar;
import sgacad.model.DivisaoTreinoMusculo;
import sgacad.view.DivisaoTreinoMusculoView;

public class DivisaoTreinoMusculoController {

    public DivisaoTreinoMusculo geraDivisaoTreinoMusculo(int id, String nomeTreino, String descricao,
            String divisaoTreinoMusculo) {
        DivisaoTreinoMusculo novo = new DivisaoTreinoMusculo(id, nomeTreino, descricao, divisaoTreinoMusculo,
                Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
        return novo;

    }

    public static DivisaoTreinoMusculo getDivisaoTreinoMusculoById(int id) {
        for (int i = 0; i < DivisaoTreinoMusculoView.numDivisoesTreinoMusculo; i++) {
            if (DivisaoTreinoMusculoView.divisoesTreinoMusculo[i].getId() == id) {
                return DivisaoTreinoMusculoView.divisoesTreinoMusculo[i];
            }
        }
        return null;
    }
}
