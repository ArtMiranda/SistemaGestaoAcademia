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

    public void exibirDetalhes() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Endereço: " + endereco);
        System.out.println("Data de Criação: " + dataCriacao);
        System.out.println("Data de Modificação: " + dataModificacao);
        if (numAlunosMatriculados > 0) {
            System.out.println("Alunos Matriculados:");
            for (int i = 0; i < numAlunosMatriculados; i++) {
                System.out.println("Nome: " + alunosMatriculados[i].getNome());
            }
        } else {
            System.out.println("Nenhum aluno matriculado.");
        }
    }

    // Método para matricular um aluno
    public void matricularAluno(Pessoa aluno) {
        alunosMatriculados[numAlunosMatriculados] = aluno;
        numAlunosMatriculados++;
        System.out.println("Aluno matriculado com sucesso!");
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
