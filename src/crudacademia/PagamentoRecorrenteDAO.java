import java.util.Arrays;

public class PagamentoRecorrenteDAO {
    private PagamentoRecorrente[] pagamentosRecorrentes;
    private int tamanho;

    // Construtor padrão
    public PagamentoRecorrenteDAO() {
        this.pagamentosRecorrentes = new PagamentoRecorrente[10]; // Inicializa com capacidade inicial de 10 elementos
        this.tamanho = 0;
    }

    // Método para adicionar um pagamento recorrente
    public void adicionarPagamentoRecorrente(PagamentoRecorrente pagamentoRecorrente) {
        if (tamanho == pagamentosRecorrentes.length) {
            aumentarCapacidade(); // Aumenta a capacidade do array se necessário
        }
        pagamentosRecorrentes[tamanho] = pagamentoRecorrente;
        tamanho++;
    }

    // Método para buscar um pagamento recorrente pelo ID
    public PagamentoRecorrente buscarPagamentoRecorrentePorId(int id) {
        for (int i = 0; i < tamanho; i++) {
            if (pagamentosRecorrentes[i].getId() == id) {
                return pagamentosRecorrentes[i];
            }
        }
        return null; // Se não encontrar o pagamento recorrente com o ID especificado
    }

    // Método para atualizar os detalhes de um pagamento recorrente
    public void atualizarPagamentoRecorrente(PagamentoRecorrente pagamentoRecorrente) {
        for (int i = 0; i < tamanho; i++) {
            if (pagamentosRecorrentes[i].getId() == pagamentoRecorrente.getId()) {
                pagamentosRecorrentes[i] = pagamentoRecorrente;
                return;
            }
        }
    }

    // Método para excluir um pagamento recorrente
    public void excluirPagamentoRecorrente(int id) {
        for (int i = 0; i < tamanho; i++) {
            if (pagamentosRecorrentes[i].getId() == id) {
                for (int j = i; j < tamanho - 1; j++) {
                    pagamentosRecorrentes[j] = pagamentosRecorrentes[j + 1];
                }
                pagamentosRecorrentes[tamanho - 1] = null; // Remove a referência ao último elemento
                tamanho--;
                return;
            }
        }
    }

    // Método para listar todos os pagamentos recorrentes
    public PagamentoRecorrente[] listarPagamentosRecorrentes() {
        return Arrays.copyOf(pagamentosRecorrentes, tamanho);
    }

    // Método auxiliar para aumentar a capacidade do array
    private void aumentarCapacidade() {
        int novaCapacidade = pagamentosRecorrentes.length * 2;
        pagamentosRecorrentes = Arrays.copyOf(pagamentosRecorrentes, novaCapacidade);
    }
}
