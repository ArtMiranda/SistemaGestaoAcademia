package sgacad.model;
import java.util.Date;

public class Treino {
    private int id;
    private Date dataInicio;
    private Date dataTermino;
    private Exercicio exercicio;
    private DivisaoTreino divisaoTreino;
    private Date dataCriacao;
    private Date dataModificacao;

    // Construtor
    public Treino(int id, Date dataInicio, Date dataTermino, Exercicio exercicio, DivisaoTreino divisaoTreino, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.exercicio = exercicio;
        this.divisaoTreino = divisaoTreino;
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

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public DivisaoTreino getDivisaoTreino() {
        return divisaoTreino;
    }

    public void setDivisaoTreino(DivisaoTreino divisaoTreino) {
        this.divisaoTreino = divisaoTreino;
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
}
