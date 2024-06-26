package sgacad.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sgacad.model.DivisaoTreinoMusculo;

public class DivisaoTreinoMusculoController {

    public static DivisaoTreinoMusculo geraOuAtualizaDivisaoTreinoMusculo(int divisaoTreinoId, String nomeTreino, String descricao, String divisaoTreinoMusculoStr) {
        LocalDate currentDate = LocalDate.now();
        DivisaoTreinoMusculo divisaoTreinoMusculo = getDivisaoTreinoMusculoById(divisaoTreinoId);

        if (divisaoTreinoMusculo == null) {
            String sql = "INSERT INTO divisao_treino_musculo (id, nome_treino, descricao, divisao_treino_musculo, data_criacao, data_modificacao) VALUES (?, ?, ?, ?, ?, ?)";
            
            try (Connection connection = DatabaseUtil.getConnection();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, divisaoTreinoId);
                pstmt.setString(2, nomeTreino);
                pstmt.setString(3, descricao);
                pstmt.setString(4, divisaoTreinoMusculoStr);
                pstmt.setDate(5, java.sql.Date.valueOf(currentDate));
                pstmt.setDate(6, java.sql.Date.valueOf(currentDate));
                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    divisaoTreinoMusculo = new DivisaoTreinoMusculo(divisaoTreinoId, nomeTreino, descricao, divisaoTreinoMusculoStr, currentDate, currentDate);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            divisaoTreinoMusculo.setNomeTreino(nomeTreino);
            divisaoTreinoMusculo.setDescricao(descricao);
            divisaoTreinoMusculo.setDivisaoTreino(divisaoTreinoMusculoStr);
            divisaoTreinoMusculo.setDataModificacao(currentDate);
            updateDivisaoTreinoMusculo(divisaoTreinoMusculo);
        }
        return divisaoTreinoMusculo;
    }

    public static DivisaoTreinoMusculo getDivisaoTreinoMusculoById(int id) {
        DivisaoTreinoMusculo divisaoTreinoMusculo = null;
        String sql = "SELECT * FROM divisao_treino_musculo WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String nomeTreino = rs.getString("nome_treino");
                    String descricao = rs.getString("descricao");
                    String divisaoTreinoMusculoStr = rs.getString("divisao_treino_musculo");
                    LocalDate dataCriacao = rs.getDate("data_criacao").toLocalDate();
                    LocalDate dataModificacao = rs.getDate("data_modificacao").toLocalDate();
                    divisaoTreinoMusculo = new DivisaoTreinoMusculo(id, nomeTreino, descricao, divisaoTreinoMusculoStr, dataCriacao, dataModificacao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return divisaoTreinoMusculo;
    }

    public List<DivisaoTreinoMusculo> getAllDivisoesTreinoMusculo() {
        List<DivisaoTreinoMusculo> divisoesTreinoMusculo = new ArrayList<>();
        String sql = "SELECT * FROM divisao_treino_musculo";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nomeTreino = rs.getString("nome_treino");
                String descricao = rs.getString("descricao");
                String divisaoTreinoMusculoStr = rs.getString("divisao_treino_musculo");
                LocalDate dataCriacao = rs.getDate("data_criacao").toLocalDate();
                LocalDate dataModificacao = rs.getDate("data_modificacao").toLocalDate();
                DivisaoTreinoMusculo divisaoTreinoMusculo = new DivisaoTreinoMusculo(id, nomeTreino, descricao, divisaoTreinoMusculoStr, dataCriacao, dataModificacao);
                divisoesTreinoMusculo.add(divisaoTreinoMusculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return divisoesTreinoMusculo;
    }

    public static void updateDivisaoTreinoMusculo(DivisaoTreinoMusculo divisaoTreinoMusculo) {
        String sql = "UPDATE divisao_treino_musculo SET nome_treino = ?, descricao = ?, divisao_treino_musculo = ?, data_modificacao = ? WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, divisaoTreinoMusculo.getNomeTreino());
            pstmt.setString(2, divisaoTreinoMusculo.getDescricao());
            pstmt.setString(3, divisaoTreinoMusculo.getDivisaoTreino());
            pstmt.setDate(4, java.sql.Date.valueOf(divisaoTreinoMusculo.getDataModificacao()));
            pstmt.setInt(5, divisaoTreinoMusculo.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDivisaoTreinoMusculo(int id) {
        String sql = "DELETE FROM divisao_treino_musculo WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
