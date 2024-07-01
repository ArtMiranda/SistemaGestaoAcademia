package sgacad.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TreinoAplicacao {
    private int id;
    private String treino;
    private String exercicio;
    private String exercicioAplicacao;
    private String divisaoTreino;
    private String divisaoTreinoMusculo;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public TreinoAplicacao(int id, String treino, String exercicio, String exercicioAplicacao, String divisaoTreino, String divisaoTreinoMusculo, LocalDate dataInicio, LocalDate dataTermino, LocalDate dataCriacao, LocalDate dataModificacao) {
        this.id = id;
        this.treino = treino;
        this.exercicio = exercicio;
        this.exercicioAplicacao = exercicioAplicacao;
        this.divisaoTreino = divisaoTreino;
        this.divisaoTreinoMusculo = divisaoTreinoMusculo;
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

    public String getTreino() {
        return treino;
    }

    public void setTreino(String treino) {
        this.treino = treino;
    }

    public String getNomeExercicio() {
        return exercicio;
    }

    public void setExercicio(String exercicio) {
        this.exercicio = exercicio;
    }

    public String getExercicioAplicacao() {
        return exercicioAplicacao;
    }

    public void setExercicioAplicacao(String exercicioAplicacao) {
        this.exercicioAplicacao = exercicioAplicacao;
    }

    public String getDivisaoTreino() {
        return divisaoTreino;
    }

    public void setDivisaoTreino(String divisaoTreino) {
        this.divisaoTreino = divisaoTreino;
    }

    public String getDivisaoTreinoAplicacao() {
        return divisaoTreinoMusculo;
    }

    public void setDivisaoTreinoMusculo(String divisaoTreinoMusculo) {
        this.divisaoTreinoMusculo = divisaoTreinoMusculo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public String exibirDetalhes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "ID: " + id +
                "\nTreino: " + treino +
                "\nExercicio: " + exercicio +
                "\nExercicio Aplicacão: " + exercicioAplicacao +
                "\nDivisão Treino: " + divisaoTreino +
                "\nDivisão Treino Musculo: " + divisaoTreinoMusculo +
                "\nData de Inicio: " + formatter.format(dataInicio) +
                "\nData de Termino: " + formatter.format(dataTermino) +
                "\nData de Criacão: " + formatter.format(dataCriacao) +
                "\nData de Modificacão: " + formatter.format(dataModificacao);
    }
}
