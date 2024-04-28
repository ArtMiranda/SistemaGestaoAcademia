import java.util.Arrays;
import java.util.Date;

public class AcademiaDAO {
    private Academia[] academias;
    private int tamanho;

    // Construtor padrão
    public AcademiaDAO() {
        this.academias = new Academia[10]; // Inicializa com capacidade inicial de 10 elementos
        this.tamanho = 0;
    }
    
        // Método para criar uma nova academia e adicioná-la ao DAO
    public void criaAcademia(int id, String nome, String endereco) {
        // Cria uma instância de Date para representar a data de criação
        Date dataCriacao = new Date();
        
        // Cria uma instância de Date para representar a data de modificação
        Date dataModificacao = new Date();
        
        // Cria uma nova instância de Academia com os valores fornecidos
        Academia novaAcademia = new Academia(id, nome, endereco, dataCriacao, dataModificacao);
        
        // Adiciona a nova academia ao array de academias
        adicionarAcademia(novaAcademia);
    }


    // Método para adicionar uma academia
    public void adicionarAcademia(Academia academia) {
        if (tamanho == academias.length) {
            aumentarCapacidade(); // Aumenta a capacidade do array se necessário
        }
        academias[tamanho] = academia;
        tamanho++;
    }

    // Método para buscar uma academia pelo ID
    public Academia buscarAcademiaPorId(int id) {
        for (int i = 0; i < tamanho; i++) {
            if (academias[i].getId() == id) {
                return academias[i];
            }
        }
        return null; // Se não encontrar a academia com o ID especificado
    }

    // Método para atualizar os detalhes de uma academia
    public void atualizarAcademia(Academia academia) {
        for (int i = 0; i < tamanho; i++) {
            if (academias[i].getId() == academia.getId()) {
                academias[i] = academia;
                return;
            }
        }
    }

    // Método para excluir uma academia
    public void excluirAcademia(int id) {
        for (int i = 0; i < tamanho; i++) {
            if (academias[i].getId() == id) {
                for (int j = i; j < tamanho - 1; j++) {
                    academias[j] = academias[j + 1];
                }
                academias[tamanho - 1] = null; // Remove a referência ao último elemento
                tamanho--;
                return;
            }
        }
    }

    // Método para listar todas as academias
    public Academia[] listarAcademias() {
        return Arrays.copyOf(academias, tamanho);
    }

    // Método auxiliar para aumentar a capacidade do array
    private void aumentarCapacidade() {
        int novaCapacidade = academias.length * 2;
        academias = Arrays.copyOf(academias, novaCapacidade);
    }
}
