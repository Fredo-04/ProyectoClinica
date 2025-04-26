package com.mycompany.clinicaapp;

import java.sql.*;

public class PacienteDAO {

    public void registrarPaciente(Connection connection, String nombre, String apellidoPaterno, String apellidoMaterno, int docid, String email) {
        String sql = "INSERT INTO paciente (docid, name, apaterno, amaterno, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, docid);
            pstmt.setString(2, nombre);
            pstmt.setString(3, apellidoPaterno);
            pstmt.setString(4, apellidoMaterno);
            pstmt.setString(5, email);
            pstmt.executeUpdate();
            System.out.println("Paciente registrado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar paciente: " + e.getMessage());
        }
    }
    
    // Puedes agregar otros métodos para obtener pacientes, actualizar, etc.
}
