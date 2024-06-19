package sgacad.model;
import java.time.LocalDate;

public class PagamentoRecorrente {
    private int id;
    private int idPessoa;
    private LocalDate data;
    private String cartaoCredito;
    private double valor;
    private LocalDate dataInicio;
    private int numeroMesesAutorizados;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public PagamentoRecorrente(int id, int idPessoa, LocalDate data, String cartaoCredito, double valor, LocalDate dataInicio, int numeroMesesAutorizados, LocalDate dataCriacao, LocalDate dataModificacao) {
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getNumeroMesesAutorizados() {
        return numeroMesesAutorizados;
    }

    public void setNumeroMesesAutorizados(int numeroMesesAutorizados) {
        this.numeroMesesAutorizados = numeroMesesAutorizados;
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
