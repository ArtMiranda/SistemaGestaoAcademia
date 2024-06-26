package sgacad.controller;

import sgacad.model.AlunoPagamentoMensalidade;
import sgacad.model.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class AlunoPagamentoMensalidadeController {

    public static void gerarAlunoPagamentoMensalidade(double valorPago, int idAluno, String modalidade) {
        LocalDate dataPagamento = LocalDate.now();
        double MensalidadeVigente = MensalidadeVigenteController.getMensalidadeVigente().getValor();
        int mesesPagos = (int) (valorPago / MensalidadeVigente);
        LocalDate dataVencimento = dataPagamento.plus(Period.ofMonths(mesesPagos));

        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO aluno_pagamento_mensalidade (mensalidade_vigente, data_vencimento, data_pagamento, valor_pago, id_aluno, modalidade, data_criacao, data_modificacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setDouble(1, MensalidadeVigente);
                stmt.setDate(2, java.sql.Date.valueOf(dataVencimento));
                stmt.setDate(3, java.sql.Date.valueOf(dataPagamento));
                stmt.setDouble(4, valorPago);
                stmt.setInt(5, idAluno);
                stmt.setString(6, modalidade);
                stmt.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
                stmt.setDate(8, java.sql.Date.valueOf(LocalDate.now()));
                stmt.executeUpdate();
            }
            MovimentacaoFinanceiraController.cadastrar(valorPago, 2, "Pagamento de mensalidade por aluno");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static AlunoPagamentoMensalidade getPorId(int id) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM aluno_pagamento_mensalidade WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new AlunoPagamentoMensalidade(
                            rs.getInt("id"),
                            rs.getDouble("mensalidade_vigente"),
                            rs.getDate("data_vencimento").toLocalDate(),
                            rs.getDate("data_pagamento").toLocalDate(),
                            rs.getDouble("valor_pago"),
                            rs.getInt("id_aluno"),
                            rs.getString("modalidade"),
                            rs.getDate("data_criacao").toLocalDate(),
                            rs.getDate("data_modificacao").toLocalDate()
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean getPorIdAluno(int idAluno) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM aluno_pagamento_mensalidade WHERE id_aluno = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idAluno);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        LocalDate dataPagamento = rs.getDate("data_pagamento").toLocalDate();
                        LocalDate dataLimite = dataPagamento.plus(Period.ofDays(32));
                        LocalDate dataVencimento = rs.getDate("data_vencimento").toLocalDate();
                        if (dataLimite.isBefore(dataVencimento)) {
                            return true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void removePagamento(int id) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "DELETE FROM aluno_pagamento_mensalidade WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Pessoa> getAlunosAdimplentes() {
        List<Pessoa> alunosAdimplentes = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        String sql = "SELECT a.id, a.nome, a.sexo, a.nascimento, a.login, a.senha, a.data_modificacao " +
                     "FROM Alunos a " +
                     "JOIN aluno_pagamento_mensalidade apm ON a.id = apm.id_aluno " +
                     "WHERE apm.data_vencimento >= ? AND apm.data_pagamento <= apm.data_vencimento";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, java.sql.Date.valueOf(currentDate));
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    char sexo = rs.getString("sexo").charAt(0);
                    LocalDate nascimento = rs.getDate("nascimento").toLocalDate();
                    String login = rs.getString("login");
                    String senha = rs.getString("senha");
                    LocalDate dataModificacao = rs.getDate("data_modificacao").toLocalDate();
                    Pessoa aluno = new Pessoa(id, nome, sexo, nascimento, login, senha, "Aluno", dataModificacao, dataModificacao);
                    alunosAdimplentes.add(aluno);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunosAdimplentes;
    }
}