package sgacad.controller;

import java.util.Date;

import sgacad.model.AlunoPagamentoMensalidade;
import sgacad.view.AlunoPagamentoMensalidadeView;

import java.util.Calendar;

public class AlunoPagamentoMensalidadeController {
    
    public static void gerarAlunoPagamentoMensalidade(double valorPago, int idAluno, String modalidade) {
        Date dataPagamento = Calendar.getInstance().getTime();
        Date currentDate = Calendar.getInstance().getTime();
        double MensalidadeVigente = MensalidadeVigenteController.getMensalidadeVigente().getValor();
        
        int mesesPagos = (int) (valorPago / MensalidadeVigente);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataPagamento);
        calendar.add(Calendar.DAY_OF_MONTH, mesesPagos * 30);
        Date dataVencimento = calendar.getTime();

        AlunoPagamentoMensalidade alunoPagamentoMensalidade = new AlunoPagamentoMensalidade(AlunoPagamentoMensalidadeView.numPagamentos, MensalidadeVigente, dataVencimento, dataPagamento, valorPago, idAluno, modalidade, currentDate, currentDate);
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
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(AlunoPagamentoMensalidadeView.alunosPagamentosMensalidades[i].getDataPagamento());
            calendar.add(Calendar.DAY_OF_MONTH, 32); // Adiciona 32 dias para considerar o mês inteiro e dois dias de folga
            if (AlunoPagamentoMensalidadeView.alunosPagamentosMensalidades[i].getIdAluno() == idAluno && calendar.getTime().before(Calendar.getInstance().getTime()) || calendar.getTime().equals(Calendar.getInstance().getTime())) {
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
