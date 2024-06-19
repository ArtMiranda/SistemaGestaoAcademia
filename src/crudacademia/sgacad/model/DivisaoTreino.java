package sgacad.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DivisaoTreino {
    private int id;
    private String nome;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    // Construtor
    public DivisaoTreino(int id, String nome, String descricao, LocalDate dataCriacao, LocalDate dataModificacao) {
        this.id = id;
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String nomeDetalhado) {
        this.descricao = nomeDetalhado;
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
        detalhes += "Nome do Treino: " + nome + "\n";
        detalhes += "Descricao: " + descricao + "\n";
        detalhes += "Data de Criacao: " + formatarData(dataCriacao) + "\n";
        detalhes += "Data de Modificacao: " + formatarData(dataModificacao) + "\n";
        return detalhes;
    }

 private String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }
}
