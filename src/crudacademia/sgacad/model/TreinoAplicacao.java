package sgacad.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TreinoAplicacao {
    private int id;
    private String treino;
    private String exercicio;
    private String exercicioAplicacao;
    private String divisaoTreino;
    private String divisaoTreinoMusculo;
    private Date dataInicio;
    private Date dataTermino;
    private Date dataCriacao;
    private Date dataModificacao;

    public TreinoAplicacao(int id, String treino, String exercicio, String exercicioAplicacao, String divisaoTreino, String divisaoTreinoMusculo, Date dataInicio, Date dataTermino, Date dataCriacao, Date dataModificacao) {
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Date getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public String exibirDetalhes() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return "ID: " + id +
                "\nTreino: " + treino +
                "\nExerc: " + exercicio +
                "\nExercicio Aplicacao: " + exercicioAplicacao +
                "\nDivisao Treino: " + divisaoTreino +
                "\nDivisao Treino Musculo: " + divisaoTreinoMusculo +
                "\nData de Inicio: " + sdf.format(dataInicio) +
                "\nData de Termino: " + sdf.format(dataTermino) +
                "\nData de Criacao: " + sdf.format(dataCriacao) +
                "\nData de Modificacao: " + sdf.format(dataModificacao);
    }
}
