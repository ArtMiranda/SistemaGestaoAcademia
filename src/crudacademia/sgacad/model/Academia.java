package sgacad.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Academia {
    private int id;
    private String nome;
    private String endereco;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private Pessoa[] alunosMatriculados;
    private int numAlunosMatriculados;

    // Construtor
    public Academia(int id, String nome, String endereco, LocalDate dataCriacao, LocalDate dataModificacao) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
        this.alunosMatriculados = new Pessoa[10]; // Inicializa o array com tamanho 10
        this.numAlunosMatriculados = 0;
    }

    public static Academia criarAcademia(int id, String nome, String endereco) {
        LocalDate dataCriacao = LocalDate.now();
        LocalDate dataModificacao = LocalDate.now();
        return new Academia(id, nome, endereco, dataCriacao, dataModificacao);
    }

    public String exibirDetalhes() {
        String detalhes = "ID: " + id + "\n";
        detalhes += "Nome: " + nome + "\n";
        detalhes += "Endereco: " + endereco + "\n";
        detalhes += "Data de Criacao: " + formatarData(dataCriacao) + "\n";
        detalhes += "Data de Modificacao: " + formatarData(dataModificacao) + "\n";
        return detalhes;
    }

    private String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

    // Metodo para matricular um aluno
    public int matricularAluno(Pessoa aluno) {
        alunosMatriculados[numAlunosMatriculados] = aluno;
        numAlunosMatriculados++;
        return 1;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

}
