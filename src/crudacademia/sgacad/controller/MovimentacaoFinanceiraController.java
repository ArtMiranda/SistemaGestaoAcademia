package sgacad.controller;

import java.time.LocalDate;
import sgacad.model.MovimentacaoFinanceira;
import sgacad.view.MovimentacaoFinanceiraView;

public class MovimentacaoFinanceiraController {
    

    public static void cadastrar(double valor, int tipoId, String descricao) {
    LocalDate currentDate = LocalDate.now();
    
    String tipo = "";

        switch(tipoId) {
            case 1:
                tipo = "Saida";
                break;
            case 2:
                tipo = "Entrada";
                break;
            default:
                tipo = "Outro";
                break;
        }

    MovimentacaoFinanceira movimentacaoFinanceira = new MovimentacaoFinanceira(MovimentacaoFinanceiraView.movimentacoesFinanceiras ,valor, tipo, descricao, currentDate, currentDate);
    MovimentacaoFinanceiraView.movimentacaoFinanceiras[MovimentacaoFinanceiraView.movimentacoesFinanceiras] = movimentacaoFinanceira;
    MovimentacaoFinanceiraView.movimentacoesFinanceiras++;
    }
}
