package sgacad.controller;

import java.util.Calendar;
import java.util.Date;
import sgacad.model.MovimentacaoFinanceira;
import sgacad.view.MovimentacaoFinanceiraView;

public class MovimentacaoFinanceiraController {
    

    public static void cadastrar(double valor, int tipoId, String descricao) {
    Date currentDate = Calendar.getInstance().getTime();
    
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
