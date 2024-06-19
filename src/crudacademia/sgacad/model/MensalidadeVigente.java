package sgacad.model;

import java.time.LocalDate;

public class MensalidadeVigente {
    private int id;
    private double valor;
    private LocalDate inicio;
    private LocalDate termino;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public MensalidadeVigente(int id, double valor, LocalDate inicio, LocalDate termino, LocalDate dataCriacao, LocalDate dataModificacao) {
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

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getTermino() {
        return termino;
    }

    public void setTermino(LocalDate termino) {
        this.termino = termino;
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
