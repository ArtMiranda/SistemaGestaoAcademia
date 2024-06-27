package sgacad.controller;

import sgacad.model.PagamentoRecorrente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class PagamentoRecorrenteController {

    public static void cadastrarPagamentoRecorrente(int idPessoa, String cartaoCredito, int numeroMesesAutorizados) {
        LocalDate currentDate = LocalDate.now();
        double valor = MensalidadeVigenteController.getMensalidadeVigente().getValor() * numeroMesesAutorizados;
        String sql = "INSERT INTO pagamento_recorrente (id_pessoa, data, cartao_credito, valor, data_inicio, numero_meses_autorizados, data_criacao, data_modificacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idPessoa);
            pstmt.setDate(2, java.sql.Date.valueOf(currentDate));
            pstmt.setString(3, cartaoCredito);
            pstmt.setDouble(4, valor);
            pstmt.setDate(5, java.sql.Date.valueOf(currentDate));
            pstmt.setInt(6, numeroMesesAutorizados);
            pstmt.setDate(7, java.sql.Date.valueOf(currentDate));
            pstmt.setDate(8, java.sql.Date.valueOf(currentDate));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static PagamentoRecorrente getPagamentoRecorrenteById(int id) {
        String sql = "SELECT * FROM pagamento_recorrente WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new PagamentoRecorrente(
                            rs.getInt("id"),
                            rs.getInt("id_pessoa"),
                            rs.getDate("data").toLocalDate(),
                            rs.getString("cartao_credito"),
                            rs.getDouble("valor"),
                            rs.getDate("data_inicio").toLocalDate(),
                            rs.getInt("numero_meses_autorizados"),
                            rs.getDate("data_criacao").toLocalDate(),
                            rs.getDate("data_modificacao").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<PagamentoRecorrente> getAllPagamentosRecorrentes() {
        String sql = "SELECT * FROM pagamento_recorrente";
        List<PagamentoRecorrente> pagamentos = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                pagamentos.add(new PagamentoRecorrente(
                        rs.getInt("id"),
                        rs.getInt("id_pessoa"),
                        rs.getDate("data").toLocalDate(),
                        rs.getString("cartao_credito"),
                        rs.getDouble("valor"),
                        rs.getDate("data_inicio").toLocalDate(),
                        rs.getInt("numero_meses_autorizados"),
                        rs.getDate("data_criacao").toLocalDate(),
                        rs.getDate("data_modificacao").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pagamentos;
    }

    public static void checarPagamentos(LocalDate dataAChecar) {
        List<PagamentoRecorrente> pagamentos = getAllPagamentosRecorrentes();
        for (PagamentoRecorrente pagamento : pagamentos) {
            LocalDate pagamentoRecorrenteDate = pagamento.getDataCriacao();
            if (pagamentoRecorrenteDate.isBefore(dataAChecar) || pagamentoRecorrenteDate.isEqual(dataAChecar)) {
                int mesesPagos = getMesesPagos(pagamentoRecorrenteDate, dataAChecar);

                AlunoPagamentoMensalidadeController.gerarAlunoPagamentoMensalidade(mesesPagos * MensalidadeVigenteController.getMensalidadeVigente().getValor(),
                        pagamento.getIdPessoa(), "Pagamento recorrente");

                pagamento.setNumeroMesesAutorizados(pagamento.getNumeroMesesAutorizados() - mesesPagos);
                MovimentacaoFinanceiraController.cadastrar(mesesPagos * MensalidadeVigenteController.getMensalidadeVigente().getValor(), 2,
                        "Pagamento de mensalidade realizada de forma recorrente.");

                atualizarPagamentoRecorrente(pagamento);
            }
        }
    }

    private static int getMesesPagos(LocalDate dataInicio, LocalDate dataFinal) {
        return Period.between(dataInicio, dataFinal).getMonths();
    }

    public static void atualizarPagamentoRecorrente(PagamentoRecorrente pagamento) {
        String sql = "UPDATE pagamento_recorrente SET numero_meses_autorizados = ?, data_modificacao = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, pagamento.getNumeroMesesAutorizados());
            pstmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            pstmt.setInt(3, pagamento.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removerPagamentoRecorrente(int id) {
        String sql = "DELETE FROM pagamento_recorrente WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
