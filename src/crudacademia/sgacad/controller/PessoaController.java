package sgacad.controller;

import sgacad.model.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoaController {

    public static Pessoa criarPessoa(String nome, char sexo, LocalDate nascimento, String login, String senha, String tipoUsuario) {
        LocalDate currentDate = LocalDate.now();
        Pessoa pessoa = new Pessoa(0, nome, sexo, nascimento, login, senha, tipoUsuario, currentDate, currentDate);
        salvarPessoaNoBanco(pessoa, tipoUsuario);
        return pessoa;
    }

    public static Pessoa autenticarPessoa(String login, String senha, String tipoUsuario) {
        String sql = null;
        Pessoa pessoa = null;

        switch (tipoUsuario) {
            case "Administrador":
                sql = "SELECT * FROM Administradores WHERE login = ? AND senha = ?";
                break;
            case "Aluno":
                sql = "SELECT * FROM Alunos WHERE login = ? AND senha = ?";
                break;
            case "Professor/Instrutor":
                sql = "SELECT * FROM ProfessoresInstrutores WHERE login = ? AND senha = ?";
                break;
            default:
                // Tipo de usuario nao reconhecido
                return null;
        }

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, login);
            statement.setString(2, senha);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                char sexo = resultSet.getString("sexo").charAt(0);
                LocalDate nascimento = resultSet.getDate("nascimento").toLocalDate();
                LocalDate dataModificacao = resultSet.getDate("data_modificacao").toLocalDate();

                pessoa = new Pessoa(id, nome, sexo, nascimento, login, senha, tipoUsuario, dataModificacao, dataModificacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoa;
    }
    

    private static void salvarPessoaNoBanco(Pessoa pessoa, String tipoUsuario) {
        String sql = null;
    
        switch (tipoUsuario) {
            case "Administrador":
                sql = "INSERT INTO Administradores (nome, sexo, nascimento, login, senha, data_modificacao) VALUES (?, ?, ?, ?, ?, ?)";
                break;
            case "Aluno":
                sql = "INSERT INTO Alunos (nome, sexo, nascimento, login, senha, data_modificacao) VALUES (?, ?, ?, ?, ?, ?)";
                break;
            case "Professor/Instrutor":
                sql = "INSERT INTO ProfessoresInstrutores (nome, sexo, nascimento, login, senha, data_modificacao) VALUES (?, ?, ?, ?, ?, ?)";
                break;
        }
    
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
    
            statement.setString(1, pessoa.getNome());
            statement.setString(2, String.valueOf(pessoa.getSexo()));
            statement.setDate(3, java.sql.Date.valueOf(pessoa.getNascimento()));
            statement.setString(4, pessoa.getLogin());
            statement.setString(5, pessoa.getSenha());
            statement.setDate(6, java.sql.Date.valueOf(pessoa.getDataModificacao() == null ? LocalDate.now() : pessoa.getDataModificacao()));
    
            statement.executeUpdate();
    
            // Obter o ID gerado para a pessoa e definir no objeto Pessoa
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                pessoa.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static Pessoa getPessoaById(int id, String tipoUsuario) {
        String sql = null;
        Pessoa pessoa = null;

        switch (tipoUsuario) {
            case "Administrador":
                sql = "SELECT * FROM Administradores WHERE id = ?";
                break;
            case "Aluno":
                sql = "SELECT * FROM Alunos WHERE id = ?";
                break;
            case "Professor/Instrutor":
                sql = "SELECT * FROM ProfessoresInstrutores WHERE id = ?";
                break;
        }

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                char sexo = resultSet.getString("sexo").charAt(0);
                LocalDate nascimento = resultSet.getDate("nascimento").toLocalDate();
                String login = resultSet.getString("login");
                String senha = resultSet.getString("senha");
                LocalDate dataModificacao = resultSet.getDate("data_modificacao").toLocalDate();

                pessoa = new Pessoa(id, nome, sexo, nascimento, login, senha, tipoUsuario, dataModificacao, dataModificacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoa;
    }
    
    public void atualizarPessoa(Pessoa pessoa) {
        String sql = null;

        switch (pessoa.getTipoUsuario()) {
            case "Administrador":
                sql = "UPDATE Administradores SET nome = ?, sexo = ?, nascimento = ?, login = ?, senha = ?, data_modificacao = ? WHERE id = ?";
                break;
            case "Aluno":
                sql = "UPDATE Alunos SET nome = ?, sexo = ?, nascimento = ?, login = ?, senha = ?, data_modificacao = ? WHERE id = ?";
                break;
            case "Professor/Instrutor":
                sql = "UPDATE ProfessoresInstrutores SET nome = ?, sexo = ?, nascimento = ?, login = ?, senha = ?, data_modificacao = ? WHERE id = ?";
                break;
        }

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, pessoa.getNome());
            statement.setString(2, String.valueOf(pessoa.getSexo()));
            statement.setDate(3, java.sql.Date.valueOf(pessoa.getNascimento()));
            statement.setString(4, pessoa.getLogin());
            statement.setString(5, pessoa.getSenha());
            statement.setDate(6, java.sql.Date.valueOf(LocalDate.now()));
            statement.setInt(7, pessoa.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean removerPessoaPorId(int id, String tipoUsuario) {
        String sql = null;
    
        switch (tipoUsuario) {
            case "Administrador":
                sql = "DELETE FROM Administradores WHERE id = ?";
                break;
            case "Aluno":
                sql = "DELETE FROM Alunos WHERE id = ?";
                break;
            case "Professor/Instrutor":
                sql = "DELETE FROM ProfessoresInstrutores WHERE id = ?";
                break;
        }
    
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Add a return statement in case of an exception
        }
        return true; // Change the return value to true if the deletion is successful
    }

public Pessoa[] buscarTodos(String tipoUsuario) {
    String sql = null;
    List<Pessoa> pessoasList = new ArrayList<>(); // Usando ArrayList em vez de array fixo

    switch (tipoUsuario) {
        case "Administrador":
            sql = "SELECT * FROM Administradores";
            break;
        case "Aluno":
            sql = "SELECT * FROM Alunos";
            break;
        case "Professor/Instrutor":
            sql = "SELECT * FROM ProfessoresInstrutores";
            break;
    }

    try (Connection connection = DatabaseUtil.getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql)) {

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            char sexo = resultSet.getString("sexo").charAt(0);
            LocalDate nascimento = resultSet.getDate("nascimento").toLocalDate();
            String login = resultSet.getString("login");
            String senha = resultSet.getString("senha");
            LocalDate dataModificacao = resultSet.getDate("data_modificacao").toLocalDate();

            pessoasList.add(new Pessoa(id, nome, sexo, nascimento, login, senha, tipoUsuario, dataModificacao, dataModificacao));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    Pessoa[] pessoas = pessoasList.toArray(new Pessoa[0]);
    return pessoas;
}


    public Pessoa buscarPessoaPorId(int id, String tipoUsuario) {
        String sql = null;
        Pessoa pessoa = null;

        switch (tipoUsuario) {
            case "Administrador":
                sql = "SELECT * FROM Administradores WHERE id = ?";
                break;
            case "Aluno":
                sql = "SELECT * FROM Alunos WHERE id = ?";
                break;
            case "Professor/Instrutor":
                sql = "SELECT * FROM ProfessoresInstrutores WHERE id = ?";
                break;
        }

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                char sexo = resultSet.getString("sexo").charAt(0);
                LocalDate nascimento = resultSet.getDate("nascimento").toLocalDate();
                String login = resultSet.getString("login");
                String senha = resultSet.getString("senha");
                LocalDate dataModificacao = resultSet.getDate("data_modificacao").toLocalDate();

                pessoa = new Pessoa(id, nome, sexo, nascimento, login, senha, tipoUsuario, dataModificacao, dataModificacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoa;
    }

}
