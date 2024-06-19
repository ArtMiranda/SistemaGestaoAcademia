package sgacad.controller;

import java.time.LocalDate;
import sgacad.model.ExercicioAplicacao;

public class ExercicioAplicacaoController {

    public ExercicioAplicacao geraExercicioAplicacao(int id, String nome, String nomedetalhado) {
        LocalDate currentDate = LocalDate.now();
        ExercicioAplicacao exercicioAplicacao = new ExercicioAplicacao(id, nome, nomedetalhado, currentDate,
                currentDate);
        return exercicioAplicacao;
    }

}
