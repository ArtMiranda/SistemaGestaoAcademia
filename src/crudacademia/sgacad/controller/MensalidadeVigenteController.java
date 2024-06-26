package sgacad.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sgacad.model.MensalidadeVigente;

public class MensalidadeVigenteController {

    public static MensalidadeVigente cadastrarOuAtualizar(int id, double valor, LocalDate inicio, LocalDate termino) {
            LocalDate currentDate = LocalDate.now();
            String sql = "INSERT INTO mensalidade_vigente (valor, inicio, termino, data_criacao, data_modificacao) VALUES (?, ?, ?, ?, ?)";
            MensalidadeVigente mensalidade = null;

            try (Connection connection = DatabaseUtil.getConnection();
                 PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstmt.setDouble(1, valor);
                pstmt.setDate(2, java.sql.Date.valueOf(inicio));
                pstmt.setDate(3, java.sql.Date.valueOf(termino));
                pstmt.setDate(4, java.sql.Date.valueOf(currentDate));
                pstmt.setDate(5, java.sql.Date.valueOf(currentDate));
                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int newId = generatedKeys.getInt(1);
                            mensalidade = new MensalidadeVigente(newId, valor, inicio, termino, currentDate, currentDate);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

     
        return mensalidade;
    }

    public static MensalidadeVigente getMensalidadeVigente() {
        MensalidadeVigente mensalidadeVigente = null;
        LocalDate currentDate = LocalDate.now();
        String sql = "SELECT * FROM mensalidade_vigente WHERE inicio <= ? AND termino >= ?";
    
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, java.sql.Date.valueOf(currentDate));
            pstmt.setDate(2, java.sql.Date.valueOf(currentDate));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    double valor = rs.getDouble("valor");
                    LocalDate inicio = rs.getDate("inicio").toLocalDate();
                    LocalDate termino = rs.getDate("termino").toLocalDate();
                    LocalDate dataCriacao = rs.getDate("data_criacao").toLocalDate();
                    LocalDate dataModificacao = rs.getDate("data_modificacao").toLocalDate();
                    mensalidadeVigente = new MensalidadeVigente(id, valor, inicio, termino, dataCriacao, dataModificacao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensalidadeVigente;
    }
    

    public List<MensalidadeVigente> getAllMensalidades() {
        List<MensalidadeVigente> mensalidades = new ArrayList<>();
        String sql = "SELECT * FROM mensalidade_vigente";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                double valor = rs.getDouble("valor");
                LocalDate inicio = rs.getDate("inicio").toLocalDate();
                LocalDate termino = rs.getDate("termino").toLocalDate();
                LocalDate dataCriacao = rs.getDate("data_criacao").toLocalDate();
                LocalDate dataModificacao = rs.getDate("data_modificacao").toLocalDate();
                MensalidadeVigente mensalidade = new MensalidadeVigente(id, valor, inicio, termino, dataCriacao, dataModificacao);
                mensalidades.add(mensalidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensalidades;
    }

    public static void updateMensalidadeVigente(MensalidadeVigente mensalidadeVigente) {
        String sql = "UPDATE mensalidade_vigente SET valor = ?, inicio = ?, termino = ?, data_modificacao = ? WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, mensalidadeVigente.getValor());
            pstmt.setDate(2, java.sql.Date.valueOf(mensalidadeVigente.getInicio()));
            pstmt.setDate(3, java.sql.Date.valueOf(mensalidadeVigente.getTermino()));
            pstmt.setDate(4, java.sql.Date.valueOf(mensalidadeVigente.getDataModificacao()));
            pstmt.setInt(5, mensalidadeVigente.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMensalidadeVigente(int id) {
        String sql = "DELETE FROM mensalidade_vigente WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
