package sgacad.model;
import java.util.Date;

public class EntradaAluno {
    private int id;
    private int idAluno;
    private Date dataHora;
    private Date dataCriacao;
    private Date dataModificacao;

    public EntradaAluno(int id, int idAluno, Date dataHora, Date dataCriacao, Date dataModificacao) {
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

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
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
