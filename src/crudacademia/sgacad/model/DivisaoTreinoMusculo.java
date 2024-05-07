package sgacad.model;

import java.util.Date;

public class DivisaoTreinoMusculo {
    private int id;
    private String descricao;
    private String divisaoTreino;
    private Date dataCriacao;
    private Date dataModificacao;

    // Construtor
    public DivisaoTreinoMusculo(int id, String descricao, String divisaoTreino, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.descricao = descricao;
        this.divisaoTreino = divisaoTreino;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDivisaoTreino() {
        return divisaoTreino;
    }

    public void setDivisaoTreino(String divisaoTreino) {
        this.divisaoTreino = divisaoTreino;
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
