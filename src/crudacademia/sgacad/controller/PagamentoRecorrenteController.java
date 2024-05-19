package sgacad.controller;
import java.util.Calendar;
import java.util.Date;

import sgacad.view.PagamentoRecorrenteView;
import sgacad.model.PagamentoRecorrente;

public class PagamentoRecorrenteController {
    

    public static void cadastrarPagamentoRecorente(int idPessoa, String cartaoCredito, int numeroMesesAutorizados) {
        Date currentDate = Calendar.getInstance().getTime();
        double valor = MensalidadeVigenteController.getMensalidadeVigente().getValor() * numeroMesesAutorizados;
        PagamentoRecorrente pagamentoRecorrente = new PagamentoRecorrente(PagamentoRecorrenteView.numPagamentosRecorrentes, idPessoa, currentDate, cartaoCredito, valor, currentDate, numeroMesesAutorizados, currentDate, currentDate);
        PagamentoRecorrenteView.pagamentoRecorrente[PagamentoRecorrenteView.numPagamentosRecorrentes] = pagamentoRecorrente;
        PagamentoRecorrenteView.numPagamentosRecorrentes++;
    }

    public static PagamentoRecorrente getPagamentoRecorrenteById(int id){
        for (int i = 0; i < PagamentoRecorrenteView.numPagamentosRecorrentes; i++) {
            if (PagamentoRecorrenteView.pagamentoRecorrente[i].getId() == id) {
                return PagamentoRecorrenteView.pagamentoRecorrente[i];
            }
        }
        return null;
    }

    public static void checarPagamentos(Date dataAChecar){
        for (int i = 0; i < PagamentoRecorrenteView.numPagamentosRecorrentes; i++) {
            if (PagamentoRecorrenteView.pagamentoRecorrente[i].getData().before(dataAChecar) || PagamentoRecorrenteView.pagamentoRecorrente[i].getData().equals(dataAChecar)){
                AlunoPagamentoMensalidadeController.gerarAlunoPagamentoMensalidade(MensalidadeVigenteController.getMensalidadeVigente().getValor(), PagamentoRecorrenteView.pagamentoRecorrente[i].getIdPessoa(), "Pagamento recorrente");
                PagamentoRecorrenteView.pagamentoRecorrente[i].setNumeroMesesAutorizados(PagamentoRecorrenteView.pagamentoRecorrente[i].getNumeroMesesAutorizados() - (dataAChecar.getMonth() - PagamentoRecorrenteView.pagamentoRecorrente[i].getData().getMonth()));
                MovimentacaoFinanceiraController.cadastrar(MensalidadeVigenteController.getMensalidadeVigente().getValor(), 2, "Pagamento de mensalidade realizada de forma recorrente.");
            }
        }
    }
}
