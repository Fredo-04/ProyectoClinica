package com.mycompany.clinicaapp;

import java.sql.*;

public class EstudianteOdontoDAO {

    public void registrarEstudiante(Connection connection, String nombre, String apellidoPaterno, String apellidoMaterno, String email, int codigo) {
        String sql = "INSERT INTO eodonto (code, name, apaterno, amaterno, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, codigo);
            pstmt.setString(2, nombre);
            pstmt.setString(3, apellidoPaterno);
            pstmt.setString(4, apellidoMaterno);
            pstmt.setString(5, email);
            pstmt.executeUpdate();
            System.out.println("Estudiante registrado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar estudiante: " + e.getMessage());
        }
    }
}
