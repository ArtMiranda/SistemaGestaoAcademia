package sgacad.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sgacad.model.AvaliacaoFisica;

public class AvaliacaoFisicaController {

    public static String calculaAvaliacaoFisica(int id, int ultimoTreino, double peso, double altura) {
        return String.valueOf(peso / (altura * altura));
    }

    public static AvaliacaoFisica geraAvaliacaoFisica(int id, int ultimoTreinoId, double peso, double altura, int indiceSatisfacaoResultado) {
        String nomePessoa = PessoaController.getPessoaById(id, "Aluno").getNome();
        String ultimoTreino = TreinoController.getTreinoById(ultimoTreinoId).getObjetivo();
        LocalDate currentDate = LocalDate.now();

        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica(id, nomePessoa, ultimoTreino, peso, altura, indiceSatisfacaoResultado, currentDate, currentDate);
        salvarAvaliacaoFisica(avaliacaoFisica);
        return avaliacaoFisica;
    }

    public static void salvarAvaliacaoFisica(AvaliacaoFisica avaliacaoFisica) {
        String sql = "INSERT INTO AvaliacaoFisica (nomePessoa, ultimoTreino, peso, altura, indiceSatisfacaoResultado, dataCriacao, dataModificacao) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, avaliacaoFisica.getPessoa());
            stmt.setString(2, avaliacaoFisica.getUltimoTreino());
            stmt.setDouble(3, avaliacaoFisica.getPeso());
            stmt.setDouble(4, avaliacaoFisica.getAltura());
            stmt.setInt(5, avaliacaoFisica.getIndiceSatisfacaoResultado());
            stmt.setDate(6, java.sql.Date.valueOf(avaliacaoFisica.getDataCriacao()));
            stmt.setDate(7, java.sql.Date.valueOf(avaliacaoFisica.getDataModificacao()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<AvaliacaoFisica> listarTodasAvaliacoesFisicas() {
        String sql = "SELECT * FROM AvaliacaoFisica";
        List<AvaliacaoFisica> avaliacoes = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                AvaliacaoFisica avaliacao = new AvaliacaoFisica(
                        rs.getInt("id"),
                        rs.getString("nomePessoa"),
                        rs.getString("ultimoTreino"),
                        rs.getDouble("peso"),
                        rs.getDouble("altura"),
                        rs.getInt("indiceSatisfacaoResultado"),
                        rs.getDate("dataCriacao").toLocalDate(),
                        rs.getDate("dataModificacao").toLocalDate()
                );
                avaliacoes.add(avaliacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avaliacoes;
    }

    public static AvaliacaoFisica buscarAvaliacaoFisicaPorId(int id) {
        String sql = "SELECT * FROM AvaliacaoFisica WHERE id = ?";
        AvaliacaoFisica avaliacao = null;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    avaliacao = new AvaliacaoFisica(
                            rs.getInt("id"),
                            rs.getString("nomePessoa"),
                            rs.getString("ultimoTreino"),
                            rs.getDouble("peso"),
                            rs.getDouble("altura"),
                            rs.getInt("indiceSatisfacaoResultado"),
                            rs.getDate("dataCriacao").toLocalDate(),
                            rs.getDate("dataModificacao").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avaliacao;
    }

    public static void atualizarAvaliacaoFisica(AvaliacaoFisica avaliacaoFisica) {
        String sql = "UPDATE AvaliacaoFisica SET nomePessoa = ?, ultimoTreino = ?, peso = ?, altura = ?, indiceSatisfacaoResultado = ?, dataModificacao = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, avaliacaoFisica.getPessoa());
            stmt.setString(2, avaliacaoFisica.getUltimoTreino());
            stmt.setDouble(3, avaliacaoFisica.getPeso());
            stmt.setDouble(4, avaliacaoFisica.getAltura());
            stmt.setInt(5, avaliacaoFisica.getIndiceSatisfacaoResultado());
            stmt.setDate(6, java.sql.Date.valueOf(avaliacaoFisica.getDataModificacao()));
            stmt.setInt(7, avaliacaoFisica.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletarAvaliacaoFisica(int id) {
        String sql = "DELETE FROM AvaliacaoFisica WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
