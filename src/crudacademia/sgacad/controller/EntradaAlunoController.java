package sgacad.controller;

import java.util.Date;

import sgacad.model.EntradaAluno;
import sgacad.view.EntradaAlunoView;

import java.util.Calendar;

public class EntradaAlunoController {
    

    public static void gerarEntradaAluno(int idAluno) {
        Date currentDate = Calendar.getInstance().getTime();
        
        EntradaAluno entradaAluno = new EntradaAluno(EntradaAlunoView.numEntradas, idAluno, currentDate, currentDate, currentDate);
        EntradaAlunoView.entradasAlunos[EntradaAlunoView.numEntradas] = entradaAluno;
        EntradaAlunoView.numEntradas++;
    }
}
