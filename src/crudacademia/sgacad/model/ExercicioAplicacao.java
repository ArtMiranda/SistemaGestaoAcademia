package sgacad.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExercicioAplicacao {
    private int id;
    private String nome;
    private String nomeDetalhado;
    private Date dataCriacao;
    private Date dataModificacao;

    // Construtor
    public ExercicioAplicacao(int id, String nome, String nomeDetalhado, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.nome = nome;
        this.nomeDetalhado = nomeDetalhado;
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

    public String getNomeDetalhado() {
        return nomeDetalhado;
    }

    public void setNomeDetalhado(String nomeDetalhado) {
        this.nomeDetalhado = nomeDetalhado;
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
        detalhes += "Nome: " + nome + "\n";
        detalhes += "Descricão detalhada: " + nomeDetalhado + "\n";
        detalhes += "Data de Criacão: " + formatarData(dataCriacao) + "\n";
        detalhes += "Data de Modificacão: " + formatarData(dataModificacao) + "\n";

        return detalhes;
    }

    private String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }

}
