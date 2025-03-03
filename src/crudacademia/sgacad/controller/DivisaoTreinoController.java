package sgacad.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sgacad.model.DivisaoTreino;

public class DivisaoTreinoController {
    public static DivisaoTreino geraDivisaoTreino(int treinoId, String nome, String descricao) {
        LocalDate currentDate = LocalDate.now();
        DivisaoTreino divisaoTreino = getDivisaoTreinoById(treinoId);

        if (divisaoTreino == null) {
            String sql = "INSERT INTO divisao_treino (id, nome, descricao, data_criacao, data_modificacao) VALUES (?, ?, ?, ?, ?)";
            
            try (Connection connection = DatabaseUtil.getConnection();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, treinoId);
                pstmt.setString(2, nome);
                pstmt.setString(3, descricao);
                pstmt.setDate(4, java.sql.Date.valueOf(currentDate));
                pstmt.setDate(5, java.sql.Date.valueOf(currentDate));
                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    divisaoTreino = new DivisaoTreino(treinoId, nome, descricao, currentDate, currentDate);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            divisaoTreino.setNome(nome);
            divisaoTreino.setDescricao(descricao);
            divisaoTreino.setDataModificacao(currentDate);
            updateDivisaoTreino(divisaoTreino);
        }
        return divisaoTreino;
    }

    public static DivisaoTreino getDivisaoTreinoById(int id) {
        DivisaoTreino divisaoTreino = null;
        String sql = "SELECT * FROM divisao_treino WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String descricao = rs.getString("descricao");
                    LocalDate dataCriacao = rs.getDate("data_criacao").toLocalDate();
                    LocalDate dataModificacao = rs.getDate("data_modificacao").toLocalDate();
                    divisaoTreino = new DivisaoTreino(id, nome, descricao, dataCriacao, dataModificacao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return divisaoTreino;
    }

    public List<DivisaoTreino> getAllDivisoesTreino() {
        List<DivisaoTreino> divisoesTreino = new ArrayList<>();
        String sql = "SELECT * FROM divisao_treino";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                LocalDate dataCriacao = rs.getDate("data_criacao").toLocalDate();
                LocalDate dataModificacao = rs.getDate("data_modificacao").toLocalDate();
                DivisaoTreino divisaoTreino = new DivisaoTreino(id, nome, descricao, dataCriacao, dataModificacao);
                divisoesTreino.add(divisaoTreino);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return divisoesTreino;
    }

    public static void updateDivisaoTreino(DivisaoTreino divisaoTreino) {
        String sql = "UPDATE divisao_treino SET nome = ?, descricao = ?, data_modificacao = ? WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, divisaoTreino.getNome());
            pstmt.setString(2, divisaoTreino.getDescricao());
            pstmt.setDate(3, java.sql.Date.valueOf(divisaoTreino.getDataModificacao()));
            pstmt.setInt(4, divisaoTreino.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDivisaoTreino(int id) {
        String sql = "DELETE FROM divisao_treino WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
