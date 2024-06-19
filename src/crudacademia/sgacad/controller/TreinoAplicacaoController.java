package sgacad.controller;

import java.time.LocalDate;

import sgacad.model.TreinoAplicacao;
import sgacad.view.TreinoAplicacaoView;

public class TreinoAplicacaoController {
    public TreinoAplicacao geraTreinoAplicacao(int id, int idExercicio) {
        LocalDate currentDate = LocalDate.now();
        String treino = TreinoController.getTreinoById(id).getObjetivo();
        String exercicioAplicacao = ExercicioController.getExercicioById(idExercicio).getNome();
        String divisaoTreino = DivisaoTreinoController.getDivisaoTreinoById(idExercicio).getNome();
        String divisaoTreinoMusculo = DivisaoTreinoMusculoController.getDivisaoTreinoMusculoById(idExercicio).getDivisaoTreino();
        String exercicio = ExercicioController.getExercicioById(idExercicio).getNome();
        LocalDate dataInicio = TreinoController.getTreinoById(id).getDataInicio();
        LocalDate dataTermino = TreinoController.getTreinoById(id).getDataTermino();
        TreinoAplicacao treinoAplicacao = new TreinoAplicacao(id, treino, exercicio, exercicioAplicacao, divisaoTreino, divisaoTreinoMusculo, dataInicio, dataTermino, currentDate, currentDate);
        return treinoAplicacao;
    }

    public static TreinoAplicacao getTreinoAplicacaoById(int id) {
        for (int i = 0; i < TreinoAplicacaoView.numTreinosAplicacao; i++) {
            if (TreinoAplicacaoView.treinosAplicacao[i].getId() == id) {
                return TreinoAplicacaoView.treinosAplicacao[i];
            }
        }
        return null;
    }

    public static void atualizarTreinoAplicacao(int id, String novoTreino, int novoIdExercicio, String novoExercicioAplicacao, String novaDivisaoTreino, String novaDivisaoTreinoMusculo) {
        TreinoAplicacao treinoAplicacao = getTreinoAplicacaoById(id);
        if (treinoAplicacao != null) {
            if (novoTreino != null) treinoAplicacao.setTreino(novoTreino);
            if (novoIdExercicio != 0) treinoAplicacao.setExercicio(ExercicioController.getExercicioById(novoIdExercicio).getNome());
            if (novoExercicioAplicacao != null) treinoAplicacao.setExercicioAplicacao(novoExercicioAplicacao);
            if (novaDivisaoTreino != null) treinoAplicacao.setDivisaoTreino(novaDivisaoTreino);
            if (novaDivisaoTreinoMusculo != null) treinoAplicacao.setDivisaoTreinoMusculo(novaDivisaoTreinoMusculo);
            treinoAplicacao.setDataModificacao(LocalDate.now());
        }
    }
    

    public static void removerTreinoAplicacao(int id) {
        for (int i = 0; i < TreinoAplicacaoView.numTreinosAplicacao; i++) {
            if (TreinoAplicacaoView.treinosAplicacao[i].getId() == id) {
                for (int j = i; j < TreinoAplicacaoView.numTreinosAplicacao - 1; j++) {
                    TreinoAplicacaoView.treinosAplicacao[j] = TreinoAplicacaoView.treinosAplicacao[j + 1];
                }
                TreinoAplicacaoView.treinosAplicacao[TreinoAplicacaoView.numTreinosAplicacao - 1] = null;
                TreinoAplicacaoView.numTreinosAplicacao--;
                break;
            }
        }
    }
}
