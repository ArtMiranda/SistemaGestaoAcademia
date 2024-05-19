package sgacad.model;
import java.util.Date;

public class PagamentoRecorrente {
    private int id;
    private int idPessoa;
    private Date data;
    private String cartaoCredito;
    private double valor;
    private Date dataInicio;
    private int numeroMesesAutorizados;
    private Date dataCriacao;
    private Date dataModificacao;

    public PagamentoRecorrente(int id, int idPessoa, Date data, String cartaoCredito, double valor, Date dataInicio, int numeroMesesAutorizados, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.idPessoa = idPessoa;
        this.data = data;
        this.cartaoCredito = cartaoCredito;
        this.valor = valor;
        this.dataInicio = dataInicio;
        this.numeroMesesAutorizados = numeroMesesAutorizados;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(String cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getNumeroMesesAutorizados() {
        return numeroMesesAutorizados;
    }

    public void setNumeroMesesAutorizados(int numeroMesesAutorizados) {
        this.numeroMesesAutorizados = numeroMesesAutorizados;
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
