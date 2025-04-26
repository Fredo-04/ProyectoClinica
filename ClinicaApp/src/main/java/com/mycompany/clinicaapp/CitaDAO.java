package com.mycompany.clinicaapp;

import java.sql.*;

public class CitaDAO {

    public void registrarCita(Connection connection, int docid, int code, int ncita, int day, int month, int year, int turno, String motivo) {
        String sql = "INSERT INTO cita (ncita, docid, code, day, month, year, turno, motivo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, ncita);
            pstmt.setInt(2, docid);
            pstmt.setInt(3, code);
            pstmt.setInt(4, day);
            pstmt.setInt(5, month);
            pstmt.setInt(6, year);
            pstmt.setInt(7, turno);
            pstmt.setString(8, motivo);
            pstmt.executeUpdate();
            System.out.println("Cita registrada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar cita: " + e.getMessage());
        }
    }
}
