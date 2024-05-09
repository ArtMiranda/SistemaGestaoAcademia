package sgacad.controller;

import java.util.Calendar;
import java.util.Date;
import sgacad.model.Exercicio;
import sgacad.view.ExercicioView;

public class ExercicioController {

    public Exercicio geraExercicio(int id, String nome, String descricao) {
        Date currentDate = Calendar.getInstance().getTime();
        Exercicio exercicio = new Exercicio(id, nome, descricao, currentDate, currentDate);
        return exercicio;
    }

    public static Exercicio getExercicioById(int id){
        for (int i = 0; i < ExercicioView.numExercicios; i++) {
            if (ExercicioView.exercicios[i].getId() == id) {
                return ExercicioView.exercicios[i];
            }
        }
        return null;
    }

}
