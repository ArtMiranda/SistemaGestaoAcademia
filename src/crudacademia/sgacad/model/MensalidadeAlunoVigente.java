package sgacad.model;
import java.util.Date;

public class MensalidadeAlunoVigente {
    private int id;
    private Pessoa aluno; // Modificado para usar a classe Pessoa
    private Date vencimento;
    private MensalidadeVigente mensalidade;
    private Date dataCriacao;
    private Date dataModificacao;

    // Construtor
    public MensalidadeAlunoVigente(int id, Pessoa aluno, Date vencimento, MensalidadeVigente mensalidade, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.aluno = aluno;
        this.vencimento = vencimento;
        this.mensalidade = mensalidade;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    // Getters
    public int getId() {
        return id;
    }

    public Pessoa getAluno() {
        return aluno;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public MensalidadeVigente getMensalidade() {
        return mensalidade;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Date getDataModificacao() {
        return dataModificacao;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setAluno(Pessoa aluno) {
        this.aluno = aluno;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public void setMensalidade(MensalidadeVigente mensalidade) {
        this.mensalidade = mensalidade;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
}
