package sgacad.model;

import java.util.Date;

public class AvaliacaoFisica {
    private int id;
    private Pessoa pessoa;
    private Treino ultimoTreino;
    private double peso;
    private double altura;
    private double imc;
    private int indiceSatisfacaoResultado;
    private Date dataCriacao;
    private Date dataModificacao;

    // Construtor
    public AvaliacaoFisica(int id, Pessoa pessoa, Treino ultimoTreino, double peso, double altura, int indiceSatisfacaoResultado, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.pessoa = pessoa;
        this.ultimoTreino = ultimoTreino;
        this.peso = peso;
        this.altura = altura;
        this.imc = calcularIMC(peso, altura);
        this.indiceSatisfacaoResultado = indiceSatisfacaoResultado;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    // Metodo para calcular o Índice de Massa Corporal (IMC)
    private double calcularIMC(double peso, double altura) {
        return peso / (altura * altura);
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Treino getUltimoTreino() {
        return ultimoTreino;
    }

    public void setUltimoTreino(Treino ultimoTreino) {
        this.ultimoTreino = ultimoTreino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getImc() {
        return imc;
    }

    public int getIndiceSatisfacaoResultado() {
        return indiceSatisfacaoResultado;
    }

    public void setIndiceSatisfacaoResultado(int indiceSatisfacaoResultado) {
        this.indiceSatisfacaoResultado = indiceSatisfacaoResultado;
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
