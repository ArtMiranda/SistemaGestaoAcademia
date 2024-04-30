import java.text.SimpleDateFormat;
import java.util.Date;

public class Pessoa {
    private int id;
    private String nome;
    private char sexo;
    private Date nascimento;
    private String login;
    private String senha;
    private String tipoUsuario;
    private Date dataCriacao;
    private Date dataModificacao;

    // Construtor
    public Pessoa(int id, String nome, char sexo, Date nascimento, String login, String senha, String tipoUsuario,
            Date dataCriacao, Date dataModificacao) {
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

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
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

    public String exibirDetalhes() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String detalhes = "ID: " + id + "\n";
        detalhes += "Nome: " + nome + "\n";
        detalhes += "Sexo: " + sexo + "\n";
        detalhes += "Data de Nascimento: " + sdf.format(nascimento) + "\n";
        detalhes += "Login: " + login + "\n";
        detalhes += "Senha: " + senha + "\n";
        detalhes += "Tipo de Usuário: " + tipoUsuario + "\n";
        detalhes += "Data de Criação: " + sdf.format(dataCriacao) + "\n";
        detalhes += "Data de Modificação: " + sdf.format(dataModificacao) + "\n";

        return detalhes;
    }

}
