package sgacad.controller;

import java.time.LocalDate;
import sgacad.model.Exercicio;
import sgacad.view.ExercicioView;

public class ExercicioController {

    public Exercicio geraExercicio(int id, String nome, String descricao) {
        LocalDate currentDate = LocalDate.now();
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
