package sgacad.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sgacad.model.ExercicioAplicacao;

public class ExercicioAplicacaoController {

    public static ExercicioAplicacao geraExercicioAplicacao(int id, String nome, String nomeDetalhado) {
        
        LocalDate currentDate = LocalDate.now();
        ExercicioAplicacao exercicioAplicacao = new ExercicioAplicacao(id, nome, nomeDetalhado, currentDate, currentDate);
        salvarExercicioAplicacao(exercicioAplicacao);
        return exercicioAplicacao;
    }

    public static void salvarExercicioAplicacao(ExercicioAplicacao exercicioAplicacao) {
        String sql = "INSERT INTO ExercicioAplicacao (nome, nomeDetalhado, dataCriacao, dataModificacao) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, exercicioAplicacao.getNome());
            stmt.setString(2, exercicioAplicacao.getNomeDetalhado());
            stmt.setDate(3, java.sql.Date.valueOf(exercicioAplicacao.getDataCriacao()));
            stmt.setDate(4, java.sql.Date.valueOf(exercicioAplicacao.getDataModificacao()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ExercicioAplicacao> listarTodosExerciciosAplicacao() {
        String sql = "SELECT * FROM ExercicioAplicacao";
        List<ExercicioAplicacao> exercicios = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ExercicioAplicacao exercicio = new ExercicioAplicacao(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("nomeDetalhado"),
                        rs.getDate("dataCriacao").toLocalDate(),
                        rs.getDate("dataModificacao").toLocalDate()
                );
                exercicios.add(exercicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercicios;
    }

    public static ExercicioAplicacao buscarExercicioAplicacaoPorId(int id) {
        String sql = "SELECT * FROM ExercicioAplicacao WHERE id = ?";
        ExercicioAplicacao exercicio = null;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    exercicio = new ExercicioAplicacao(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("nomeDetalhado"),
                            rs.getDate("dataCriacao").toLocalDate(),
                            rs.getDate("dataModificacao").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercicio;
    }

    public void atualizarExercicioAplicacao(ExercicioAplicacao exercicioAplicacao) {
        String sql = "UPDATE ExercicioAplicacao SET nome = ?, nomeDetalhado = ?, dataModificacao = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, exercicioAplicacao.getNome());
            stmt.setString(2, exercicioAplicacao.getNomeDetalhado());
            stmt.setDate(3, java.sql.Date.valueOf(exercicioAplicacao.getDataModificacao()));
            stmt.setInt(4, exercicioAplicacao.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarExercicioAplicacao(int id) {
        String sql = "DELETE FROM ExercicioAplicacao WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
