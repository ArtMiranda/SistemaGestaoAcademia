package sgacad.model;
import java.time.LocalDate;

public class AlunoPagamentoMensalidade {
    private int id;
    private double mensalidadeVigente;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private double valorPago;
    private int idAluno;
    private String modalidade;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;


    public AlunoPagamentoMensalidade(int id, double mensalidadeVigente, LocalDate dataVencimento, LocalDate dataPagamento, double valorPago, int idAluno, String modalidade, LocalDate dataCriacao, LocalDate dataModificacao) {
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

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
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

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }


}
