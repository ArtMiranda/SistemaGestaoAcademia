package sgacad.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExercicioAplicacao {
    private int id;
    private String nome;
    private String nomeDetalhado;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    // Construtor
    public ExercicioAplicacao(int id, String nome, String nomeDetalhado, LocalDate dataCriacao, LocalDate dataModificacao) {
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
        detalhes += "Nome: " + nome + "\n";
        detalhes += "Descricao detalhada: " + nomeDetalhado + "\n";
        detalhes += "Data de Criacao: " + formatarData(dataCriacao) + "\n";
        detalhes += "Data de Modificacao: " + formatarData(dataModificacao) + "\n";

        return detalhes;
    }

 private String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }
}
