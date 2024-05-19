package sgacad.model;
import java.util.Date;

public class AlunoPagamentoMensalidade {
    private int id;
    private double mensalidadeVigente;
    private Date dataVencimento;
    private Date dataPagamento;
    private double valorPago;
    private int idAluno;
    private String modalidade;
    private Date dataCriacao;
    private Date dataModificacao;

    public AlunoPagamentoMensalidade(int id, double mensalidadeVigente, Date dataVencimento, Date dataPagamento, double valorPago, int idAluno, String modalidade, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.mensalidadeVigente = mensalidadeVigente;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valorPago = valorPago;
        this.idAluno = idAluno;
        this.modalidade = modalidade;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMensalidadeVigente() {
        return mensalidadeVigente;
    }

    public void setMensalidadeVigente(double mensalidadeVigente) {
        this.mensalidadeVigente = mensalidadeVigente;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }


}
