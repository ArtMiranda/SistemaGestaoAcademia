package sgacad.controller;

import java.time.LocalDate;
import sgacad.model.EntradaAluno;
import sgacad.view.EntradaAlunoView;


public class EntradaAlunoController {
    

    public static void gerarEntradaAluno(int idAluno) {
        LocalDate currentDate = LocalDate.now();
        
        EntradaAluno entradaAluno = new EntradaAluno(EntradaAlunoView.numEntradas, idAluno, currentDate, currentDate, currentDate);
        EntradaAlunoView.entradasAlunos[EntradaAlunoView.numEntradas] = entradaAluno;
        EntradaAlunoView.numEntradas++;
    }
}
