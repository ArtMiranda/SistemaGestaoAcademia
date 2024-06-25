package sgacad.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sgacad.model.EntradaAluno;

public class EntradaAlunoController {

    public static void gerarEntradaAluno(int idAluno) {
        LocalDate currentDate = LocalDate.now();
        
        // Obtenha o próximo valor do ID
        int nextId = getNextIdFromDatabase();

        // Crie uma nova entrada de aluno
        EntradaAluno entradaAluno = new EntradaAluno(nextId, idAluno, currentDate, currentDate, currentDate);
        
        // Salve a entrada do aluno no banco de dados
        salvarEntradaAlunoNoBancoDeDados(entradaAluno);
    }

    private static void salvarEntradaAlunoNoBancoDeDados(EntradaAluno entradaAluno) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO entradas_aluno (id_aluno, data_hora, data_criacao, data_modificacao) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setInt(1, entradaAluno.getIdAluno());
            preparedStatement.setDate(2, java.sql.Date.valueOf(entradaAluno.getDataHora()));
            preparedStatement.setDate(3, java.sql.Date.valueOf(entradaAluno.getDataCriacao()));
            preparedStatement.setDate(4, java.sql.Date.valueOf(entradaAluno.getDataModificacao()));
            
            // Execute a consulta
            preparedStatement.executeUpdate();
            
            System.out.println("Entrada do aluno salva no banco de dados.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar entrada do aluno no banco de dados.");
        }
    }

    public static int getNextIdFromDatabase(){
        int nextId = 0;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(id) FROM entradas_aluno")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                nextId = resultSet.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar o próximo ID de entrada do aluno no banco de dados.");
        }
        return nextId;
    }

    public static boolean removerEntrada(int idEntrada) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM entradas_aluno WHERE id = ?")) {
            preparedStatement.setInt(1, idEntrada);
            
            preparedStatement.executeUpdate();
            
            System.out.println("Entrada do aluno removida do banco de dados.");
            return true; 
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao remover entrada do aluno do banco de dados.");
        }
        return false;
    }

    public static EntradaAluno buscarEntradaAlunoPorId(int idEntrada) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM entradas_aluno WHERE id = ?")) {
            preparedStatement.setInt(1, idEntrada);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                int id = resultSet.getInt("id"); // Corrigir para idEntrada (variável local
                int idAluno = resultSet.getInt("id_aluno");
                LocalDate dataHora = resultSet.getDate("data_hora").toLocalDate();
                LocalDate dataCriacao = resultSet.getDate("data_criacao").toLocalDate();
                LocalDate dataModificacao = resultSet.getDate("data_modificacao").toLocalDate();
                
                return new EntradaAluno(id, idAluno, dataHora, dataCriacao, dataModificacao);
            } else {
                System.out.println("Entrada do aluno não encontrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar entrada do aluno no banco de dados.");
        }
        return null;
    }

    public static List<EntradaAluno> buscarEntradasPorAluno(int idAluno) {
        List<EntradaAluno> entradas = new ArrayList<>();
        
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM entradas_aluno WHERE id_aluno = ?")) {
            preparedStatement.setInt(1, idAluno);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int idEntrada = resultSet.getInt("id");
                LocalDate dataHora = resultSet.getDate("data_hora").toLocalDate();
                LocalDate dataCriacao = resultSet.getDate("data_criacao").toLocalDate();
                LocalDate dataModificacao = resultSet.getDate("data_modificacao").toLocalDate();
                
                EntradaAluno entradaAluno = new EntradaAluno(idEntrada, idAluno, dataHora, dataCriacao, dataModificacao);
                entradas.add(entradaAluno);
            }
            
            if (entradas.isEmpty()) {
                System.out.println("Nenhuma entrada encontrada para o aluno.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar entradas do aluno no banco de dados.");
        }
        
        return entradas;
    }

    public static List<EntradaAluno> buscarTodasEntradas() {
        List<EntradaAluno> entradas = new ArrayList<>();
        
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM entradas_aluno")) {
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int idEntrada = resultSet.getInt("id");
                int idAluno = resultSet.getInt("id_aluno");
                LocalDate dataHora = resultSet.getDate("data_hora").toLocalDate();
                LocalDate dataCriacao = resultSet.getDate("data_criacao").toLocalDate();
                LocalDate dataModificacao = resultSet.getDate("data_modificacao").toLocalDate();
                
                EntradaAluno entradaAluno = new EntradaAluno(idEntrada, idAluno, dataHora, dataCriacao, dataModificacao);
                entradas.add(entradaAluno);
            }
            
            if (entradas.isEmpty()) {
                System.out.println("Nenhuma entrada encontrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar entradas no banco de dados.");
        }
        
        return entradas;
    }

}
