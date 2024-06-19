package sgacad.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DivisaoTreinoMusculo {
    private int id;
    private String nomeTreino;
    private String descricao;
    private String divisaoTreinoMusculo;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    // Construtor
    public DivisaoTreinoMusculo(int id, String nomeTreino, String descricao, String divisaoTreinoMusculo,
            LocalDate dataCriacao,
            LocalDate dataModificacao) {
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

    public String exibirDetalhes() {
        String detalhes = "ID: " + id + "\n";
        detalhes += "Descricao: " + descricao + "\n";
        detalhes += "Divisao de Treino: " + divisaoTreinoMusculo + "\n";
        detalhes += "Data de Criacao: " + formatarData(dataCriacao) + "\n";
        detalhes += "Data de Modificacao: " + formatarData(dataModificacao) + "\n";
        return detalhes;
    }

    private String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }
}
