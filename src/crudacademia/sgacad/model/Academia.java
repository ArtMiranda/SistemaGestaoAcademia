package sgacad.model;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Academia {
    private int id;
    private String nome;
    private String endereco;
    private Date dataCriacao;
    private Date dataModificacao;
    private Pessoa[] alunosMatriculados;
    private int numAlunosMatriculados;

    // Construtor
    public Academia(int id, String nome, String endereco, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
        this.alunosMatriculados = new Pessoa[10]; // Inicializa o array com tamanho 10
        this.numAlunosMatriculados = 0;
    }

    public static Academia criarAcademia(int id, String nome, String endereco) {
        Date dataCriacao = new Date();
        Date dataModificacao = new Date();
        return new Academia(id, nome, endereco, dataCriacao, dataModificacao);
    }

    public String exibirDetalhes() {
        String detalhes = "ID: " + id + "\n";
        detalhes += "Nome: " + nome + "\n";
        detalhes += "Endereco: " + endereco + "\n";
        detalhes += "Data de Criacao: " + formatarData(dataCriacao) + "\n";
        detalhes += "Data de Modificacao: " + formatarData(dataModificacao) + "\n";
        if (numAlunosMatriculados > 0) {
            detalhes += "Alunos Matriculados:\n";
            for (int i = 0; i < numAlunosMatriculados; i++) {
                detalhes += "Nome: " + alunosMatriculados[i].getNome() + "\n";
            }
        } else {
            detalhes += "Nenhum aluno matriculado.\n";
        }
        return detalhes;
    }

    private String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
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
