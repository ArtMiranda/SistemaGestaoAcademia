package sgacad.model;
import java.util.Date;

public class MovimentacaoFinanceira {
    private int id;
    private double valor;
    private String tipo;
    private String descricao;
    private Date dataCriacao;
    private Date dataModificacao;

    // Construtor
    public MovimentacaoFinanceira(int id, double valor, String tipo, String descricao, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
