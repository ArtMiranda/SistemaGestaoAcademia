package sgacad.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import sgacad.view.MenuAcademia;

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
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Administradores (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "nome VARCHAR(100) NOT NULL," +
                    "sexo CHAR(1) NOT NULL," +
                    "nascimento DATE NOT NULL," +
                    "login VARCHAR(50) NOT NULL UNIQUE," +
                    "senha VARCHAR(50) NOT NULL," +
                    "data_modificacao DATE)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Alunos (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "nome VARCHAR(100) NOT NULL," +
                    "sexo CHAR(1) NOT NULL," +
                    "nascimento DATE NOT NULL," +
                    "login VARCHAR(50) NOT NULL UNIQUE," +
                    "senha VARCHAR(50) NOT NULL," +
                    "data_modificacao DATE)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS ProfessoresInstrutores (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "nome VARCHAR(100) NOT NULL," +
                    "sexo CHAR(1) NOT NULL," +
                    "nascimento DATE NOT NULL," +
                    "login VARCHAR(50) NOT NULL UNIQUE," +
                    "senha VARCHAR(50) NOT NULL," +
                    "data_modificacao DATE)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS aluno_pagamento_mensalidade (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "mensalidade_vigente DOUBLE NOT NULL," +
                    "data_vencimento DATE NOT NULL," +
                    "data_pagamento DATE NOT NULL," +
                    "valor_pago DOUBLE NOT NULL," +
                    "id_aluno INT NOT NULL," +
                    "modalidade VARCHAR(255) NOT NULL," +
                    "data_criacao DATE NOT NULL," +
                    "data_modificacao DATE NOT NULL," +
                    "FOREIGN KEY (id_aluno) REFERENCES Alunos(id))");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS entradas_aluno (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "id_aluno INT NOT NULL," +
                    "data_hora DATETIME NOT NULL," +
                    "data_criacao DATE NOT NULL," +
                    "data_modificacao DATE NOT NULL," +
                    "FOREIGN KEY (id_aluno) REFERENCES Alunos(id))");

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
