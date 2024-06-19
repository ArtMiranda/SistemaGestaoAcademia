package sgacad.controller;

import java.time.LocalDate;
import java.time.Period;

import sgacad.view.PagamentoRecorrenteView;
import sgacad.model.PagamentoRecorrente;

public class PagamentoRecorrenteController {

    public static void cadastrarPagamentoRecorrente(int idPessoa, String cartaoCredito, int numeroMesesAutorizados) {
        LocalDate currentDate = LocalDate.now();
        double valor = MensalidadeVigenteController.getMensalidadeVigente().getValor() * numeroMesesAutorizados;
        PagamentoRecorrente pagamentoRecorrente = new PagamentoRecorrente(PagamentoRecorrenteView.numPagamentosRecorrentes, idPessoa, currentDate, cartaoCredito, valor, currentDate, numeroMesesAutorizados, currentDate, currentDate);
        PagamentoRecorrenteView.pagamentoRecorrente[PagamentoRecorrenteView.numPagamentosRecorrentes] = pagamentoRecorrente;
        PagamentoRecorrenteView.numPagamentosRecorrentes++;
    }

    public static PagamentoRecorrente getPagamentoRecorrenteById(int id) {
        for (int i = 0; i < PagamentoRecorrenteView.numPagamentosRecorrentes; i++) {
            if (PagamentoRecorrenteView.pagamentoRecorrente[i].getId() == id) {
                return PagamentoRecorrenteView.pagamentoRecorrente[i];
            }
        }
        return null;
    }

    public static void checarPagamentos(LocalDate dataAChecar) {
        for (int i = 0; i < PagamentoRecorrenteView.numPagamentosRecorrentes; i++) {
            LocalDate pagamentoRecorrenteDate = PagamentoRecorrenteView.pagamentoRecorrente[i].getData();
            if (pagamentoRecorrenteDate.isBefore(dataAChecar) || pagamentoRecorrenteDate.isEqual(dataAChecar)) {
                int mesesPagos = getMesesPagos(pagamentoRecorrenteDate, dataAChecar);

                AlunoPagamentoMensalidadeController.gerarAlunoPagamentoMensalidade(mesesPagos * MensalidadeVigenteController.getMensalidadeVigente().getValor(),
                        PagamentoRecorrenteView.pagamentoRecorrente[i].getIdPessoa(), "Pagamento recorrente");

                PagamentoRecorrenteView.pagamentoRecorrente[i].setNumeroMesesAutorizados(PagamentoRecorrenteView.pagamentoRecorrente[i].getNumeroMesesAutorizados() - mesesPagos);

                MovimentacaoFinanceiraController.cadastrar(mesesPagos * MensalidadeVigenteController.getMensalidadeVigente().getValor(), 2,
                        "Pagamento de mensalidade realizada de forma recorrente.");
            }
        }
    }

    private static int getMesesPagos(LocalDate dataInicio, LocalDate dataFinal) {
        Period period = Period.between(dataInicio, dataFinal);
        return period.getMonths();
    }
}
