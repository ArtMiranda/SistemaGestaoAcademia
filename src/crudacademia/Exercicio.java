import java.util.Date;

public class Exercicio {
    private int id;
    private String nome;
    private String descricaoFoto;
    private Date dataCriacao;
    private Date dataModificacao;

    // Construtor
    public Exercicio(int id, String nome, String descricaoFoto, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.nome = nome;
        this.descricaoFoto = descricaoFoto;
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

    public String getDescricaoFoto() {
        return descricaoFoto;
    }

    public void setDescricaoFoto(String descricaoFoto) {
        this.descricaoFoto = descricaoFoto;
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
