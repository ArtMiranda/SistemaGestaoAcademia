import java.util.Date;

public class PagamentoRecorrente {
    private int id;
    private Pessoa pessoa;
    private Date data;
    private String cartaoCredito;
    private double valor;
    private Date dataInicio;
    private int numeroMesesAutorizados;
    private Date dataCriacao;
    private Date dataModificacao;

    // Construtor
    public PagamentoRecorrente(int id, Pessoa pessoa, Date data, String cartaoCredito, double valor, Date dataInicio, int numeroMesesAutorizados, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.pessoa = pessoa;
        this.data = data;
        this.cartaoCredito = cartaoCredito;
        this.valor = valor;
        this.dataInicio = dataInicio;
        this.numeroMesesAutorizados = numeroMesesAutorizados;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    // Getters
    public int getId() {
        return id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Date getData() {
        return data;
    }

    public String getCartaoCredito() {
        return cartaoCredito;
    }

    public double getValor() {
        return valor;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public int getNumeroMesesAutorizados() {
        return numeroMesesAutorizados;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Date getDataModificacao() {
        return dataModificacao;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setCartaoCredito(String cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setNumeroMesesAutorizados(int numeroMesesAutorizados) {
        this.numeroMesesAutorizados = numeroMesesAutorizados;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
}
