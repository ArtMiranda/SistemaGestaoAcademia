import java.util.Date;

public class EntradaAluno {
    private int id;
    private Date dataHora;
    private Pessoa aluno;
    private String modalidade;
    private Date dataCriacao;
    private Date dataModificacao;

    // Construtor
    public EntradaAluno(int id, Date dataHora, Pessoa aluno, String modalidade, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.dataHora = dataHora;
        this.aluno = aluno;
        this.modalidade = modalidade;
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

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Pessoa getAluno() {
        return aluno;
    }

    public void setAluno(Pessoa aluno) {
        this.aluno = aluno;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
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
