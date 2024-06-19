package sgacad.controller;

import java.time.LocalDate;

import sgacad.model.AvaliacaoFisica;
import sgacad.view.AvaliacaoFisicaView;

public class AvaliacaoFisicaController {

    public static String calculaAvaliacaoFisica(int id, int ultimoTreino, double peso, double altura) {
    
    String avFisicaResultado = String.valueOf(peso / (altura * altura));
    
    return avFisicaResultado;
    }

    public static AvaliacaoFisica geraAvaliacaoFisica(int id, int ultimoTreinoId, double peso, double altura, int indiceSatisfacaoResultado) {
        
        String nomePessoa = PessoaController.getAlunoById(id).getNome();
        String ultimoTreino = TreinoController.getTreinoById(ultimoTreinoId).getObjetivo();

        LocalDate currentDate = LocalDate.now();

        
        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica(id, nomePessoa, ultimoTreino, peso, altura, indiceSatisfacaoResultado, currentDate, currentDate);
        AvaliacaoFisicaView.avaliacoesFisicas[AvaliacaoFisicaView.numAvaliacoes] = avaliacaoFisica;
        AvaliacaoFisicaView.numAvaliacoes++;
        return avaliacaoFisica;
    }

    public static AvaliacaoFisica getAvaliacaoFisicaById(int id) {
        for (int i = 0; i < AvaliacaoFisicaView.avaliacoesFisicas.length; i++) {
            if (AvaliacaoFisicaView.avaliacoesFisicas[i].getId() == id) {
                return AvaliacaoFisicaView.avaliacoesFisicas[i];
            }
        }
        return null;
    }

    public static void removeAvaliacaoFisica(int id) {
        for (int i = 0; i < AvaliacaoFisicaView.numAvaliacoes; i++) {
            if (AvaliacaoFisicaView.avaliacoesFisicas[i].getId() == id) {
                AvaliacaoFisicaView.avaliacoesFisicas[i] = null;
                for (int j = i; j < AvaliacaoFisicaView.numAvaliacoes - 1; j++) {
                    AvaliacaoFisicaView.avaliacoesFisicas[j] = AvaliacaoFisicaView.avaliacoesFisicas[j + 1];
                }
                AvaliacaoFisicaView.numAvaliacoes--;
                break;
            }
        }
    }
}
