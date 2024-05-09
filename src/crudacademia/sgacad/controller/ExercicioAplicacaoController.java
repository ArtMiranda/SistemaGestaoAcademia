package sgacad.controller;

import java.util.Calendar;
import java.util.Date;
import sgacad.model.ExercicioAplicacao;

public class ExercicioAplicacaoController {

    public ExercicioAplicacao geraExercicioAplicacao(int id, String nome, String nomedetalhado) {
        Date currentDate = Calendar.getInstance().getTime();
        ExercicioAplicacao exercicioAplicacao = new ExercicioAplicacao(id, nome, nomedetalhado, currentDate,
                currentDate);
        return exercicioAplicacao;
    }

}
