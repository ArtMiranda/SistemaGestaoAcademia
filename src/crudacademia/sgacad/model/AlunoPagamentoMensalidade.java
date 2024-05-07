package sgacad.model;
import java.util.Date;

public class AlunoPagamentoMensalidade {
    private int id;
    private MensalidadeAlunoVigente mensalidade;
    private Date data;
    private Pessoa modalidade;
    private Date dataCriacao;
    private Date dataModificacao;

    // Construtor
    public AlunoPagamentoMensalidade(int id, MensalidadeAlunoVigente mensalidade, Date data, Pessoa modalidade, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.mensalidade = mensalidade;
        this.data = data;
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

    public MensalidadeAlunoVigente getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(MensalidadeAlunoVigente mensalidade) {
        this.mensalidade = mensalidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Pessoa getModalidade() {
        return modalidade;
    }

    public void setModalidade(Pessoa modalidade) {
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
