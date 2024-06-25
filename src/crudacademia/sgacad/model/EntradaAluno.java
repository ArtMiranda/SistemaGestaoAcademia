package sgacad.model;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EntradaAluno {
    private int id;
    private int idAluno;
    private LocalDateTime dataHora;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public EntradaAluno(int id, int idAluno, LocalDateTime dataHora, LocalDate dataCriacao, LocalDate dataModificacao) {
        this.id = id;
        this.idAluno = idAluno;
        this.dataHora = dataHora;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
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
}
