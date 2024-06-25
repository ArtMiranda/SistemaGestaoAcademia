package sgacad.controller;

import sgacad.view.MenuAcademia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    private static final String DATABASE_NAME = "sgacad";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);
    }

    public static void createDatabase() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP DATABASE IF EXISTS " + DATABASE_NAME);
            statement.executeUpdate("CREATE DATABASE " + DATABASE_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTables() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("USE " + DATABASE_NAME);
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS academia (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome VARCHAR(255) NOT NULL," +
                    "endereco VARCHAR(255) NOT NULL," +
                    "data_criacao DATE NOT NULL," +
                    "data_modificacao DATE NOT NULL)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS usuarios (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome VARCHAR(255) NOT NULL," +
                    "sexo VARCHAR(255) NOT NULL," +
                    "nascimento DATE NOT NULL," +
                    "login VARCHAR(255) NOT NULL," +
                    "senha VARCHAR(255) NOT NULL," +
                    "tipo_usuario VARCHAR(255) NOT NULL," +
                    "data_criacao DATE NOT NULL," +
                    "data_modificacao DATE NOT NULL)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS treinos (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "objetivo VARCHAR(255) NOT NULL," +
                    "dataInicio DATE NOT NULL," +
                    "dataTermino DATE NOT NULL," +
                    "dataCriacao DATE NOT NULL," +
                    "dataModificacao DATE NOT NULL)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS exercicios (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome VARCHAR(255) NOT NULL," +
                    "descricao TEXT NOT NULL," +
                    "dataCriacao DATE NOT NULL," +
                    "dataModificacao DATE NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createDatabase();
        createTables();
        System.out.println("Banco de dados e tabelas criados com sucesso.");

        MenuAcademia.main(args);
    }
}
