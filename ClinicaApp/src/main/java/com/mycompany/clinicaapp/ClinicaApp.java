package com.mycompany.clinicaapp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ClinicaApp {

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Conexión exitosa a la base de datos.");
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;
            
            PacienteDAO pacienteDAO = new PacienteDAO();
            EstudianteOdontoDAO estudianteDAO = new EstudianteOdontoDAO();
            CitaDAO citaDAO = new CitaDAO();
            
            while (!exit) {
                System.out.println("\nMenú:");
                System.out.println("1. Registrar paciente");
                System.out.println("2. Registrar estudiante Odonto");
                System.out.println("3. Registrar cita");
                System.out.println("4. Salir");

                System.out.print("Elige una opción: ");
                int option = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer de entrada
                
                switch (option) {
                    case 1:
                        registrarPaciente(scanner, pacienteDAO, connection);
                        break;
                    case 2:
                        registrarEstudiante(scanner, estudianteDAO, connection);
                        break;
                    case 3:
                        registrarCita(scanner, citaDAO, connection);
                        break;
                    case 4:
                        exit = true;
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void registrarPaciente(Scanner scanner, PacienteDAO pacienteDAO, Connection connection) {
        System.out.print("Ingrese nombre del paciente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese apellido paterno: ");
        String apellidoPaterno = scanner.nextLine();
        System.out.print("Ingrese apellido materno: ");
        String apellidoMaterno = scanner.nextLine();
        System.out.print("Ingrese identificación (docid): ");
        int docid = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese email del paciente: ");
        String email = scanner.nextLine();
        
        pacienteDAO.registrarPaciente(connection, nombre, apellidoPaterno, apellidoMaterno, docid, email);
    }

    private static void registrarEstudiante(Scanner scanner, EstudianteOdontoDAO estudianteDAO, Connection connection) {
        // Similar a registrarPaciente, con los parámetros del estudiante.
    }

    private static void registrarCita(Scanner scanner, CitaDAO citaDAO, Connection connection) {
        // Similar a registrarPaciente, pero para citas.
    }
}
