package sgacad.controller;

import sgacad.model.Treino;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TreinoController {

    // Método para criar um novo treino no banco de dados
    public static void adicionarTreino(String objetivo, LocalDate dataInicio, LocalDate dataTermino) {
        String sql = "INSERT INTO treinos (objetivo, dataInicio, dataTermino, dataCriacao, dataModificacao) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, objetivo);
            stmt.setDate(2, Date.valueOf(dataInicio));
            stmt.setDate(3, Date.valueOf(dataTermino));
            stmt.setDate(4, Date.valueOf(LocalDate.now()));
            stmt.setDate(5, Date.valueOf(LocalDate.now()));
            stmt.executeUpdate();

            // Obter o ID gerado para o treino e definir no objeto Treino
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar um treino pelo ID
    public static Treino getTreinoById(int id) {
        String sql = "SELECT * FROM treinos WHERE id = ?";
        Treino treino = null;

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String objetivo = rs.getString("objetivo");
                LocalDate dataInicio = rs.getDate("dataInicio").toLocalDate();
                LocalDate dataTermino = rs.getDate("dataTermino").toLocalDate();
                LocalDate dataCriacao = rs.getDate("dataCriacao").toLocalDate();
                LocalDate dataModificacao = rs.getDate("dataModificacao").toLocalDate();

                treino = new Treino(id, objetivo, dataInicio, dataTermino, dataCriacao, dataModificacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return treino;
    }

    // Método para atualizar um treino no banco de dados
    public void atualizarTreino(int id, String novoObjetivo, LocalDate novaDataInicio, LocalDate novaDataTermino) {
        String sql = "UPDATE treinos SET objetivo = ?, dataInicio = ?, dataTermino = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novoObjetivo);
            stmt.setDate(2, Date.valueOf(novaDataInicio));
            stmt.setDate(3, Date.valueOf(novaDataTermino));
            stmt.setDate(4, Date.valueOf(LocalDate.now()));
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os treinos do banco de dados
    public List<Treino> listarTreinos() {
        List<Treino> treinos = new ArrayList<>();
        String sql = "SELECT * FROM treinos";

        try (Connection connection = DatabaseUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String objetivo = rs.getString("objetivo");
                LocalDate dataInicio = rs.getDate("dataInicio").toLocalDate();
                LocalDate dataTermino = rs.getDate("dataTermino").toLocalDate();
                LocalDate dataCriacao = rs.getDate("dataCriacao").toLocalDate();
                LocalDate dataModificacao = rs.getDate("dataModificacao").toLocalDate();

                Treino treino = new Treino(id, objetivo, dataInicio, dataTermino, dataCriacao, dataModificacao);
                treinos.add(treino);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return treinos;
    }

    // Método para excluir um treino do banco de dados
    public void removerTreino(int id) {
        String sql = "DELETE FROM treinos WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
