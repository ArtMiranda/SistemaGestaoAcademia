package sgacad.controller;

import sgacad.model.Exercicio;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExercicioController {

    // Metodo para criar um novo exercicio no banco de dados
    public static void adicionarExercicio(String nome, String descricao) {
        String sql = "INSERT INTO exercicios (nome, descricao, dataCriacao, dataModificacao) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, nome);
            stmt.setString(2, descricao);
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            stmt.setDate(4, Date.valueOf(LocalDate.now()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para buscar um exercicio pelo ID
    public static Exercicio getExercicioById(int id) {
        String sql = "SELECT * FROM exercicios WHERE id = ?";
        Exercicio exercicio = null;

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                LocalDate dataCriacao = rs.getDate("dataCriacao").toLocalDate();
                LocalDate dataModificacao = rs.getDate("dataModificacao").toLocalDate();

                exercicio = new Exercicio(id, nome, descricao, dataCriacao, dataModificacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exercicio;
    }

    // Metodo para atualizar um exercicio no banco de dados
    public void atualizarExercicio(int id, String novoNome, String novaDescricao) {
        String sql = "UPDATE exercicios SET nome = ?, descricao = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setString(2, novaDescricao);
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para listar todos os exercicios do banco de dados
    public static Exercicio[] listarExercicios() {
        String sql = "SELECT * FROM exercicios";
        List<Exercicio> exercicioList = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                LocalDate dataCriacao = rs.getDate("dataCriacao").toLocalDate();
                LocalDate dataModificacao = rs.getDate("dataModificacao").toLocalDate();

                Exercicio exercicio = new Exercicio(id, nome, descricao, dataCriacao, dataModificacao);
                exercicioList.add(exercicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exercicioList.toArray(new Exercicio[0]);
    }

    // Metodo para excluir um exercicio do banco de dados
    public void removerExercicio(int id) {
        String sql = "DELETE FROM exercicios WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
