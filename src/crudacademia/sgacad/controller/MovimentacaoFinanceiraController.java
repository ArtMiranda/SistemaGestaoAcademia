package sgacad.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sgacad.model.MovimentacaoFinanceira;

public class MovimentacaoFinanceiraController {

    public static void cadastrar(double valor, int tipoId, String descricao) {
        LocalDate currentDate = LocalDate.now();
        String tipo = "";

        switch (tipoId) {
            case 1:
                tipo = "Saida";
                break;
            case 2:
                tipo = "Entrada";
                break;
            default:
                tipo = "Outro";
                break;
        }

        String sql = "INSERT INTO movimentacao_financeira (valor, tipo, descricao, data_criacao, data_modificacao) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, valor);
            pstmt.setString(2, tipo);
            pstmt.setString(3, descricao);
            pstmt.setDate(4, java.sql.Date.valueOf(currentDate));
            pstmt.setDate(5, java.sql.Date.valueOf(currentDate));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<MovimentacaoFinanceira> listarTodas() {
        List<MovimentacaoFinanceira> movimentacoes = new ArrayList<>();
        String sql = "SELECT * FROM movimentacao_financeira";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                double valor = rs.getDouble("valor");
                String tipo = rs.getString("tipo");
                String descricao = rs.getString("descricao");
                LocalDate dataCriacao = rs.getDate("data_criacao").toLocalDate();
                LocalDate dataModificacao = rs.getDate("data_modificacao").toLocalDate();
                MovimentacaoFinanceira movimentacao = new MovimentacaoFinanceira(id, valor, tipo, descricao, dataCriacao, dataModificacao);
                movimentacoes.add(movimentacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimentacoes;
    }

    public static void removerMovimentacaoFinanceiraPorId(int id) {
        String sql = "DELETE FROM movimentacao_financeira WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
