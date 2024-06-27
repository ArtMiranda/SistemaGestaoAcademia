package sgacad.controller;

import sgacad.model.TreinoAplicacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TreinoAplicacaoController {
    public static TreinoAplicacao geraTreinoAplicacao(int id, int idExercicio) {
        LocalDate currentDate = LocalDate.now();
        String treino = TreinoController.getTreinoById(id).getObjetivo();
        String exercicioAplicacao = ExercicioController.getExercicioById(idExercicio).getNome();
        String divisaoTreino = DivisaoTreinoController.getDivisaoTreinoById(idExercicio).getNome();
        String divisaoTreinoMusculo = DivisaoTreinoMusculoController.getDivisaoTreinoMusculoById(idExercicio).getDivisaoTreino();
        String exercicio = ExercicioController.getExercicioById(idExercicio).getNome();
        LocalDate dataInicio = TreinoController.getTreinoById(id).getDataInicio();
        LocalDate dataTermino = TreinoController.getTreinoById(id).getDataTermino();
        TreinoAplicacao treinoAplicacao = new TreinoAplicacao(0, treino, exercicio, exercicioAplicacao, divisaoTreino, divisaoTreinoMusculo, dataInicio, dataTermino, currentDate, currentDate);
        salvarTreinoAplicacao(treinoAplicacao);
        return treinoAplicacao;
    }

    public static void salvarTreinoAplicacao(TreinoAplicacao treinoAplicacao) {
        String sql = "INSERT INTO treino_aplicacao (treino, exercicio, exercicio_aplicacao, divisao_treino, divisao_treino_musculo, data_inicio, data_termino, data_criacao, data_modificacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, treinoAplicacao.getTreino());
            pstmt.setString(2, treinoAplicacao.getNomeExercicio());
            pstmt.setString(3, treinoAplicacao.getExercicioAplicacao());
            pstmt.setString(4, treinoAplicacao.getDivisaoTreino());
            pstmt.setString(5, treinoAplicacao.getDivisaoTreinoAplicacao());
            pstmt.setDate(6, java.sql.Date.valueOf(treinoAplicacao.getDataInicio()));
            pstmt.setDate(7, java.sql.Date.valueOf(treinoAplicacao.getDataTermino()));
            pstmt.setDate(8, java.sql.Date.valueOf(treinoAplicacao.getDataCriacao()));
            pstmt.setDate(9, java.sql.Date.valueOf(treinoAplicacao.getDataModificacao()));
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    treinoAplicacao.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static TreinoAplicacao getTreinoAplicacaoById(int id) {
        String sql = "SELECT * FROM treino_aplicacao WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new TreinoAplicacao(
                            rs.getInt("id"),
                            rs.getString("treino"),
                            rs.getString("exercicio"),
                            rs.getString("exercicio_aplicacao"),
                            rs.getString("divisao_treino"),
                            rs.getString("divisao_treino_musculo"),
                            rs.getDate("data_inicio").toLocalDate(),
                            rs.getDate("data_termino").toLocalDate(),
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

    public static List<TreinoAplicacao> getAllTreinosAplicacao() {
        String sql = "SELECT * FROM treino_aplicacao";
        List<TreinoAplicacao> treinosAplicacao = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                treinosAplicacao.add(new TreinoAplicacao(
                        rs.getInt("id"),
                        rs.getString("treino"),
                        rs.getString("exercicio"),
                        rs.getString("exercicio_aplicacao"),
                        rs.getString("divisao_treino"),
                        rs.getString("divisao_treino_musculo"),
                        rs.getDate("data_inicio").toLocalDate(),
                        rs.getDate("data_termino").toLocalDate(),
                        rs.getDate("data_criacao").toLocalDate(),
                        rs.getDate("data_modificacao").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return treinosAplicacao;
    }

    public static void atualizarTreinoAplicacao(TreinoAplicacao treinoAplicacao) {
        String sql = "UPDATE treino_aplicacao SET treino = ?, exercicio = ?, exercicio_aplicacao = ?, divisao_treino = ?, divisao_treino_musculo = ?, data_inicio = ?, data_termino = ?, data_modificacao = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, treinoAplicacao.getTreino());
            pstmt.setString(2, treinoAplicacao.getNomeExercicio());
            pstmt.setString(3, treinoAplicacao.getExercicioAplicacao());
            pstmt.setString(4, treinoAplicacao.getDivisaoTreino());
            pstmt.setString(5, treinoAplicacao.getDivisaoTreinoAplicacao());
            pstmt.setDate(6, java.sql.Date.valueOf(treinoAplicacao.getDataInicio()));
            pstmt.setDate(7, java.sql.Date.valueOf(treinoAplicacao.getDataTermino()));
            pstmt.setDate(8, java.sql.Date.valueOf(LocalDate.now()));
            pstmt.setInt(9, treinoAplicacao.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removerTreinoAplicacao(int id) {
        String sql = "DELETE FROM treino_aplicacao WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
