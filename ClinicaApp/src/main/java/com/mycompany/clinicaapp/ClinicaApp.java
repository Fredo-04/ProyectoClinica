/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clinicaapp;
import java.sql.*;
import java.util.Scanner;

public class ClinicaApp {

    private static Connection connection;

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinicadb", "postgres", "root");
            System.out.println("Conexión exitosa a la base de datos.");
            
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("\nMenú:");
                System.out.println("1. Registrar paciente");
                System.out.println("2. Mostrar el nombre de cada paciente y la cantidad de citas que ha tenido");
                System.out.println("3. Contar las citas de un odontólogo específico (código 20201101)");
                System.out.println("4. Mostrar todas las citas registradas");
                System.out.println("5. Ver citas de un odontólogo específico");
                System.out.println("6. Mostrar citas del turno 1 del 5 de octubre de 2023");
                System.out.println("7. Ver información de un empleado de portería");
                System.out.println("8. Mostrar citas después del 19 de octubre de 2023");
                System.out.println("9. Ver nombre de paciente y odontólogo");
                System.out.println("10. Salir");
                
                System.out.print("Elige una opción: ");
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        registrarPaciente(scanner);
                        break;
                    case 2:
                        mostrarPacientesYCantidadCitas();
                        break;
                    case 3:
                        contarCitasOdontologo();
                        break;
                    case 4:
                        mostrarCitasRegistradas();
                        break;
                    case 5:
                        mostrarCitasOdontologo();
                        break;
                    case 6:
                        mostrarCitasTurnoEspecifico();
                        break;
                    case 7:
                        mostrarEmpleadoPorteria();
                        break;
                    case 8:
                        mostrarCitasPosteriores();
                        break;
                    case 9:
                        mostrarPacienteYEstudiante();
                        break;
                    case 10:
                        exit = true;
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                        break;
                }
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    private static void registrarPaciente(Scanner scanner) {
        System.out.print("Ingrese nombre del paciente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese apellido paterno: ");
        String apellidoPaterno = scanner.nextLine();
        System.out.print("Ingrese apellido materno: ");
        String apellidoMaterno = scanner.nextLine();
        System.out.print("Ingrese identificación (docid): ");
        int docid = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese email del paciente: ");
        String email = scanner.nextLine();

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

    private static void mostrarPacientesYCantidadCitas() {
        String query = "SELECT paciente.name, COUNT(ncita) AS nrocitas " +
                       "FROM paciente " +
                       "INNER JOIN cita ON paciente.docid = cita.docid " +
                       "GROUP BY paciente.name;";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("Paciente: " + rs.getString("name") + ", Citas: " + rs.getInt("nrocitas"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void contarCitasOdontologo() {
        String query = "SELECT COUNT(ncita) AS nrocitas " +
                       "FROM eodonto " +
                       "INNER JOIN cita ON eodonto.code = cita.code " +
                       "WHERE eodonto.code = 20201101;";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                System.out.println("Número de citas del odontólogo 20201101: " + rs.getInt("nrocitas"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarCitasRegistradas() {
        String query = "SELECT COUNT(ncita) AS nrocitasMes " +
                       "FROM eodonto " +
                       "INNER JOIN cita ON eodonto.code = cita.code;";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                System.out.println("Número total de citas registradas: " + rs.getInt("nrocitasMes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarCitasOdontologo() {
        String query = "SELECT cita.ncita, cita.day, cita.month, cita.year, eodonto.name " +
                       "FROM eodonto " +
                       "INNER JOIN cita ON eodonto.code = cita.code " +
                       "WHERE eodonto.code = 20201101;";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("Cita: " + rs.getInt("ncita") + ", Día: " + rs.getInt("day") + 
                                   ", Mes: " + rs.getInt("month") + ", Año: " + rs.getInt("year") + 
                                   ", Odontólogo: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarCitasTurnoEspecifico() {
        String query = "SELECT * FROM cita " +
                       "WHERE turno = '1' AND day = 5 AND month = 10 AND year = 2023;";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("Cita: " + rs.getInt("ncita") + ", Día: " + rs.getInt("day") + 
                                   ", Mes: " + rs.getInt("month") + ", Año: " + rs.getInt("year") + 
                                   ", Motivo: " + rs.getString("motivo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarEmpleadoPorteria() {
        String query = "SELECT listc.id, eport.nombre, eport.apaterno " +
                       "FROM eport " +
                       "INNER JOIN listc ON eport.idlista = listc.id " +
                       "WHERE eport.idport = 4;";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID Lista: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre") + 
                                   ", Apellido: " + rs.getString("apaterno"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarCitasPosteriores() {
        String query = "SELECT * FROM cita " +
                       "WHERE day > 19 AND month = 10;";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("Cita: " + rs.getInt("ncita") + ", Día: " + rs.getInt("day") + 
                                   ", Mes: " + rs.getInt("month") + ", Año: " + rs.getInt("year"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarPacienteYEstudiante() {
        String query = "SELECT paciente.name AS paciente, eodonto.name AS estudiante " +
                       "FROM cita " +
                       "INNER JOIN eodonto ON eodonto.code = cita.code " +
                       "INNER JOIN paciente ON paciente.docid = cita.docid;";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("Paciente: " + rs.getString("paciente") + ", Odontólogo: " + rs.getString("estudiante"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
