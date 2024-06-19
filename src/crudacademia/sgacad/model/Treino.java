package sgacad.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Treino {
    private int id;
    private String objetivo;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    // Construtor
    public Treino(int id, String objetivo, LocalDate dataInicio, LocalDate dataTermino,
            LocalDate dataCriacao,
            LocalDate dataModificacao) {
        this.id = id;
        this.objetivo = objetivo;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
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

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String exibirDetalhes() {
        String detalhes = "ID: " + id + "\n";
        detalhes += "Objetivo: " + objetivo + "\n";
        detalhes += "Data de Inicio: " + formatarData(dataInicio) + "\n";
        detalhes += "Data de Termino: " + formatarData(dataTermino) + "\n";
        detalhes += "Data de Criacao: " + formatarData(dataCriacao) + "\n";
        detalhes += "Data de Modificacao: " + formatarData(dataModificacao) + "\n";

        return detalhes;
    }

 private String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }
}
