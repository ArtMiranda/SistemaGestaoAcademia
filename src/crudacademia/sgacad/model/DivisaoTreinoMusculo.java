package sgacad.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DivisaoTreinoMusculo {
    private int id;
    private String nomeTreino;
    private String descricao;
    private String divisaoTreinoMusculo;
    private Date dataCriacao;
    private Date dataModificacao;

    // Construtor
    public DivisaoTreinoMusculo(int id, String nomeTreino, String descricao, String divisaoTreinoMusculo,
            Date dataCriacao,
            Date dataModificacao) {
        this.id = id;
        this.nomeTreino = nomeTreino;
        this.descricao = descricao;
        this.divisaoTreinoMusculo = divisaoTreinoMusculo;
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

    public String getNomeTreino() {
        return nomeTreino;
    }

    public void setNomeTreino(String nomeTreino) {
        this.nomeTreino = nomeTreino;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDivisaoTreino() {
        return divisaoTreinoMusculo;
    }

    public void setDivisaoTreino(String divisaoTreinoMusculo) {
        this.divisaoTreinoMusculo = divisaoTreinoMusculo;
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

    public String exibirDetalhes() {
        String detalhes = "ID: " + id + "\n";
        detalhes += "Descricao: " + descricao + "\n";
        detalhes += "Divisao de Treino: " + divisaoTreinoMusculo + "\n";
        detalhes += "Data de Criacao: " + formatarData(dataCriacao) + "\n";
        detalhes += "Data de Modificacao: " + formatarData(dataModificacao) + "\n";
        return detalhes;
    }

    // Metodo para formatar datas
    public String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }
}
