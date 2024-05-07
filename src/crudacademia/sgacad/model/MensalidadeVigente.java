package sgacad.model;
import java.util.Date;

public class MensalidadeVigente {
    private int id;
    private double valor;
    private Date inicio;
    private Date termino;
    private Date dataCriacao;
    private Date dataModificacao;

    // Construtor
    public MensalidadeVigente(int id, double valor, Date inicio, Date termino, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.valor = valor;
        this.inicio = inicio;
        this.termino = termino;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getTermino() {
        return termino;
    }

    public void setTermino(Date termino) {
        this.termino = termino;
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
