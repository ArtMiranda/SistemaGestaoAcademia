package sgacad.model;

import java.time.LocalDate;

public class MensalidadeAlunoVigente {
    private int id;
    private Pessoa aluno; // Modificado para usar a classe Pessoa
    private LocalDate vencimento;
    private MensalidadeVigente mensalidade;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    // Construtor
    public MensalidadeAlunoVigente(int id, Pessoa aluno, LocalDate vencimento, MensalidadeVigente mensalidade, LocalDate dataCriacao, LocalDate dataModificacao) {
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

    public LocalDate getVencimento() {
        return vencimento;
    }

    public MensalidadeVigente getMensalidade() {
        return mensalidade;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setAluno(Pessoa aluno) {
        this.aluno = aluno;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public void setMensalidade(MensalidadeVigente mensalidade) {
        this.mensalidade = mensalidade;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
}
