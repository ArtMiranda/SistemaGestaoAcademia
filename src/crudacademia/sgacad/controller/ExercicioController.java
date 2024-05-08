package sgacad.controller;

import java.util.Calendar;
import java.util.Date;
import sgacad.model.Exercicio;

public class ExercicioController {

    public Exercicio geraExercicio(int id, String nome, String descricao) {
        Date currentDate = Calendar.getInstance().getTime();
        Exercicio exercicio = new Exercicio(id, nome, descricao, currentDate, currentDate);
        return exercicio;
    }

}
