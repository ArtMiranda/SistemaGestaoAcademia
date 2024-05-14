package sgacad.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Treino {
    private int id;
    private String objetivo;
    private Date dataInicio;
    private Date dataTermino;
    private Date dataCriacao;
    private Date dataModificacao;

    // Construtor
    public Treino(int id, String objetivo, Date dataInicio, Date dataTermino,
            Date dataCriacao,
            Date dataModificacao) {
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
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
        detalhes += "Data de Criacão: " + formatarData(dataCriacao) + "\n";
        detalhes += "Data de Modificacão: " + formatarData(dataModificacao) + "\n";

        return detalhes;
    }

    private String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }
}
