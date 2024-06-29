package sgacad.controller;

import sgacad.model.Academia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AcademiaController {

    public Academia criarAcademia(String nomeAcademia, String enderecoAcademia) {
        LocalDate currentDate = LocalDate.now();

        Academia academiaExistente = buscarAcademiaUnica();
        if (academiaExistente != null) {
            removerAcademia();
        }

        Academia academia = new Academia(0, nomeAcademia, enderecoAcademia, currentDate, currentDate);
        String sql = "INSERT INTO academia (id, nome, endereco, data_criacao, data_modificacao) VALUES (0, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, academia.getNome());
            stmt.setString(2, academia.getEndereco());
            stmt.setDate(3, java.sql.Date.valueOf(academia.getDataCriacao()));
            stmt.setDate(4, java.sql.Date.valueOf(academia.getDataModificacao()));

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return academia;
    }

    public static Academia buscarAcademiaUnica() {
        Academia academia = null;
        String sql = "SELECT * FROM academia LIMIT 1";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                academia = new Academia(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getDate("data_criacao").toLocalDate(),
                        rs.getDate("data_modificacao").toLocalDate()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return academia;
    }

    public void removerAcademia() {
        String sql = "DELETE FROM academia WHERE id = 0";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static Boolean checarExistenciaAcademia(){
        String sql = "SELECT * FROM academia";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
