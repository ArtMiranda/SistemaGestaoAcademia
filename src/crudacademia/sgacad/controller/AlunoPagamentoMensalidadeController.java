package sgacad.controller;

import sgacad.model.AlunoPagamentoMensalidade;
import sgacad.view.AlunoPagamentoMensalidadeView;

import java.time.LocalDate;
import java.time.Period;

public class AlunoPagamentoMensalidadeController {

    public static void gerarAlunoPagamentoMensalidade(double valorPago, int idAluno, String modalidade) {
        LocalDate dataPagamento = LocalDate.now();
        double MensalidadeVigente = MensalidadeVigenteController.getMensalidadeVigente().getValor();

        int mesesPagos = (int) (valorPago / MensalidadeVigente);

        LocalDate dataVencimento = dataPagamento.plus(Period.ofMonths(mesesPagos));  // Add months to payment date

        AlunoPagamentoMensalidade alunoPagamentoMensalidade = new AlunoPagamentoMensalidade(AlunoPagamentoMensalidadeView.numPagamentos, MensalidadeVigente, dataVencimento, dataPagamento, valorPago, idAluno, modalidade, LocalDate.now(), LocalDate.now());
        AlunoPagamentoMensalidadeView.alunosPagamentosMensalidades[AlunoPagamentoMensalidadeView.numPagamentos] = alunoPagamentoMensalidade;
        AlunoPagamentoMensalidadeView.numPagamentos++;
        MovimentacaoFinanceiraController.cadastrar(valorPago, 2, "Pagamento de mensalidade por aluno");
    }

    public static AlunoPagamentoMensalidade getPorId(int id) {
        for (int i = 0; i < AlunoPagamentoMensalidadeView.numPagamentos; i++) {
            if (AlunoPagamentoMensalidadeView.alunosPagamentosMensalidades[i].getId() == id) {
                return AlunoPagamentoMensalidadeView.alunosPagamentosMensalidades[i];
            }
        }
        return null;
    }

    public static boolean getPorIdAluno(int idAluno) {
        for (int i = 0; i < AlunoPagamentoMensalidadeView.numPagamentos; i++) {
            LocalDate dataLimite = AlunoPagamentoMensalidadeView.alunosPagamentosMensalidades[i].getDataPagamento().plus(Period.ofDays(32)); // Add 32 days for a full month + buffer
            if (AlunoPagamentoMensalidadeView.alunosPagamentosMensalidades[i].getIdAluno() == idAluno &&
                    AlunoPagamentoMensalidadeView.alunosPagamentosMensalidades[i].getDataVencimento().isBefore(dataLimite)) {
                return true;
            }
        }
        return false;
    }

    public static void removePagamento(int id) {
        for (int i = 0; i < AlunoPagamentoMensalidadeView.numPagamentos; i++) {
            if (AlunoPagamentoMensalidadeView.alunosPagamentosMensalidades[i].getId() == id) {
                AlunoPagamentoMensalidadeView.alunosPagamentosMensalidades[i] = null;
                AlunoPagamentoMensalidadeView.numPagamentos--;
                break;
            }
        }
    }

}
