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
            // Drop the database if it already exists
            statement.executeUpdate("DROP DATABASE IF EXISTS " + DATABASE_NAME);
            // Create the database
            statement.executeUpdate("CREATE DATABASE " + DATABASE_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTables() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            // Use the database
            statement.executeUpdate("USE " + DATABASE_NAME);
            // Create the tables
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS academia (" +
                    "id INT DEFAULT 0 PRIMARY KEY," +
                    "nome VARCHAR(255) NOT NULL," +
                    "endereco VARCHAR(255) NOT NULL," +
                    "data_criacao DATE NOT NULL," +
                    "data_modificacao DATE NOT NULL)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS usuarios (" +
                    "id INTEGER PRIMARY KEY," +
                    "nome TEXT NOT NULL," +
                    "sexo TEXT NOT NULL," +
                    "nascimento TEXT NOT NULL," +
                    "login TEXT NOT NULL," +
                    "senha TEXT NOT NULL," +
                    "tipo_usuario TEXT NOT NULL," +
                    "data_criacao TEXT NOT NULL," +
                    "data_modificacao TEXT NOT NULL)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS treinos (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "objetivo VARCHAR(255) NOT NULL," +
                    "dataInicio DATE NOT NULL," +
                    "dataTermino DATE NOT NULL," +
                    "dataCriacao DATE NOT NULL," +
                    "dataModificacao DATE NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Cria o banco de dados
        createDatabase();
        // Cria as tabelas
        createTables();
        System.out.println("Banco de dados e tabelas criados com sucesso.");

        MenuAcademia.main(args);
    }
}
