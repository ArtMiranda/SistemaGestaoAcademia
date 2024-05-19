package sgacad.dao;
import sgacad.model.*;

public class PessoaDAO {
    private Pessoa[] pessoas;
    private int tamanho;

    // Construtor padrao
    public PessoaDAO() {
        pessoas = new Pessoa[0]; // Inicializa o array com tamanho zero
        tamanho = 0;
    }

    // Metodo para adicionar uma pessoa
    public void adicionarPessoa(Pessoa pessoa) {
        // Cria um novo array com tamanho aumentado para acomodar a nova pessoa
        Pessoa[] novoArray = new Pessoa[tamanho + 1];
        // Copia os elementos do array original para o novo array
        System.arraycopy(pessoas, 0, novoArray, 0, tamanho);
        // Adiciona a nova pessoa ao final do novo array
        novoArray[tamanho] = pessoa;
        // Atualiza a referencia para o novo array
        pessoas = novoArray;
        // Incrementa o tamanho
        tamanho++;
    }

    // Metodo para buscar uma pessoa pelo ID
    public Pessoa buscarPessoaPorId(int id) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getId() == id) {
                return pessoa;
            }
        }
        return null; // Se nao encontrar a pessoa com o ID especificado
    }

    // Metodo para atualizar os detalhes de uma pessoa
    public void atualizarPessoa(Pessoa pessoa) {
        for (int i = 0; i < tamanho; i++) {
            if (pessoas[i].getId() == pessoa.getId()) {
                pessoas[i] = pessoa;
                return;
            }
        }
    }

    // Metodo para excluir uma pessoa
    public void excluirPessoa(int id) {
        // Cria um novo array com tamanho reduzido para armazenar as pessoas sem a pessoa a ser excluida
        Pessoa[] novoArray = new Pessoa[tamanho - 1];
        int indiceNovoArray = 0;
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getId() != id) {
                novoArray[indiceNovoArray++] = pessoa;
            }
        }
        // Atualiza a referencia para o novo array
        pessoas = novoArray;
        // Atualiza o tamanho
        tamanho--;
    }

    // Metodo para listar todas as pessoas
    public Pessoa[] listarPessoas() {
        return pessoas;
    }
}
