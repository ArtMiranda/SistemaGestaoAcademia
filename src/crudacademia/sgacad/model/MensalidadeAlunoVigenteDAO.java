package sgacad.model;
import java.util.Arrays;

public class MensalidadeAlunoVigenteDAO {
    private MensalidadeAlunoVigente[] mensalidadesAlunoVigentes;
    private int tamanho;

    // Construtor padrao
    public MensalidadeAlunoVigenteDAO() {
        this.mensalidadesAlunoVigentes = new MensalidadeAlunoVigente[10]; // Inicializa com capacidade inicial de 10 elementos
        this.tamanho = 0;
    }

    // Metodo para adicionar uma mensalidade de aluno vigente
    public void adicionarMensalidadeAlunoVigente(MensalidadeAlunoVigente mensalidadeAlunoVigente) {
        if (tamanho == mensalidadesAlunoVigentes.length) {
            aumentarCapacidade(); // Aumenta a capacidade do array se necessario
        }
        mensalidadesAlunoVigentes[tamanho] = mensalidadeAlunoVigente;
        tamanho++;
    }

    // Metodo para buscar uma mensalidade de aluno vigente pelo ID
    public MensalidadeAlunoVigente buscarMensalidadeAlunoVigentePorId(int id) {
        for (int i = 0; i < tamanho; i++) {
            if (mensalidadesAlunoVigentes[i].getId() == id) {
                return mensalidadesAlunoVigentes[i];
            }
        }
        return null; // Se nao encontrar a mensalidade de aluno vigente com o ID especificado
    }

    // Metodo para atualizar os detalhes de uma mensalidade de aluno vigente
    public void atualizarMensalidadeAlunoVigente(MensalidadeAlunoVigente mensalidadeAlunoVigente) {
        for (int i = 0; i < tamanho; i++) {
            if (mensalidadesAlunoVigentes[i].getId() == mensalidadeAlunoVigente.getId()) {
                mensalidadesAlunoVigentes[i] = mensalidadeAlunoVigente;
                return;
            }
        }
    }

    // Metodo para excluir uma mensalidade de aluno vigente
    public void excluirMensalidadeAlunoVigente(int id) {
        for (int i = 0; i < tamanho; i++) {
            if (mensalidadesAlunoVigentes[i].getId() == id) {
                for (int j = i; j < tamanho - 1; j++) {
                    mensalidadesAlunoVigentes[j] = mensalidadesAlunoVigentes[j + 1];
                }
                mensalidadesAlunoVigentes[tamanho - 1] = null; // Remove a referencia ao ultimo elemento
                tamanho--;
                return;
            }
        }
    }

    // Metodo para listar todas as mensalidades de aluno vigentes
    public MensalidadeAlunoVigente[] listarMensalidadesAlunoVigentes() {
        return Arrays.copyOf(mensalidadesAlunoVigentes, tamanho);
    }

    // Metodo auxiliar para aumentar a capacidade do array
    private void aumentarCapacidade() {
        int novaCapacidade = mensalidadesAlunoVigentes.length * 2;
        mensalidadesAlunoVigentes = Arrays.copyOf(mensalidadesAlunoVigentes, novaCapacidade);
    }
}
