package sgacad.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {
    private int id;
    private String nome;
    private char sexo;
    private LocalDate nascimento;
    private String login;
    private String senha;
    private String tipoUsuario;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    // Construtor
    public Pessoa(int id, String nome, char sexo, LocalDate nascimento, String login, String senha, String tipoUsuario,
    LocalDate dataCriacao, LocalDate dataModificacao) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
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

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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
        detalhes += "Sexo: " + sexo + "\n";
        detalhes += "Data de Nascimento: " + formatarData(nascimento) + "\n";
        detalhes += "Login: " + login + "\n";
        detalhes += "Senha: " + senha + "\n";
        detalhes += "Tipo de Usuario: " + tipoUsuario + "\n";
        detalhes += "Data de Criacao: " + formatarData(dataCriacao) + "\n";
        detalhes += "Data de Modificacao: " + formatarData(dataModificacao) + "\n";

        return detalhes;
    }

    private String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

}
