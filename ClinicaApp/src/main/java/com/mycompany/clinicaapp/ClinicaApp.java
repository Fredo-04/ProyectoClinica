<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> c9bc4d92b1684b30ba3406c67ed041a15f68c2dd
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
<<<<<<< HEAD
=======
=======
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
                 System.out.println("2. Registrar estudiante Odonto");
                 System.out.println("3. Registrar cita");
                 System.out.println("4. Mostrar el nombre de cada paciente y la cantidad de citas que ha tenido");
                 System.out.println("5. Contar las citas de un odontólogo específico (código 20201101)");
                 System.out.println("6. Mostrar todas las citas registradas");
                 System.out.println("7. Ver citas de un odontólogo específico");
                 System.out.println("8. Mostrar citas del turno 1 del 5 de octubre de 2023");
                 System.out.println("9. Ver información de un empleado de portería");
                 System.out.println("10. Mostrar citas después del 19 de octubre de 2023");
                 System.out.println("11. Ver nombre de paciente y odontólogo");
                 System.out.println("12. Eliminar estudiante de odontologia");
                 System.out.println("14. Salir");
                 
                 System.out.print("Elige una opción: ");
                 int option = scanner.nextInt();
 
                 switch (option) {
                     case 1:
                         registrarPaciente(scanner);
                         break;
                     case 2:
                         registrarEstudiante(scanner);
                         break;
                     case 3:
                         registrarCita(scanner);
                         break;
                     case 4:
                         mostrarPacientesYCantidadCitas(scanner);
                         break;
                     case 5:
                         contarCitasOdontologo(scanner);
                         break;
                     case 6:
                         mostrarCitasRegistradas();
                         break;
                     case 7:
                         mostrarCitasOdontologo(scanner);
                         break;
                     case 8:
                         mostrarCitasTurnoEspecifico();
                         break;
                     case 9:
                         mostrarEmpleadoPorteria();
                         break;
                     case 10:
                         mostrarCitasPosteriores();
                         break;
                     case 11:
                         mostrarPacienteYEstudiante();
                         break;
                     case 12:
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
>>>>>>> 89c4c7efaebecbd8e1f54c8ece89118a6566da02
>>>>>>> c9bc4d92b1684b30ba3406c67ed041a15f68c2dd
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
        
<<<<<<< HEAD
        pacienteDAO.registrarPaciente(connection, nombre, apellidoPaterno, apellidoMaterno, docid, email);
    }

    private static void registrarEstudiante(Scanner scanner, EstudianteOdontoDAO estudianteDAO, Connection connection) {
        // Similar a registrarPaciente, con los parámetros del estudiante.
=======
<<<<<<< HEAD
        pacienteDAO.registrarPaciente(connection, nombre, apellidoPaterno, apellidoMaterno, docid, email);
    }

    private static void registrarEstudiante(Scanner scanner, EstudianteOdontoDAO estudianteDAO, Connection connection) {
        // Similar a registrarPaciente, con los parámetros del estudiante.
=======
        String sql = "{CALL sp_registrar_paciente(?, ?, ?, ?, ?)}";
        try (CallableStatement cstmt = connection.prepareCall(sql)) {
            cstmt.setInt(1, docid);
            cstmt.setString(2, nombre);
            cstmt.setString(3, apellidoPaterno);
            cstmt.setString(4, apellidoMaterno);
            cstmt.setString(5, email);
            cstmt.execute();
            System.out.println("Paciente registrado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar paciente: " + e.getMessage());
        }
    }

    private static void registrarEstudiante(Scanner scanner) {
        System.out.print("Ingrese nombre del estudiante: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese apellido paterno: ");
        String apellidoPaterno = scanner.nextLine();
        System.out.print("Ingrese apellido materno: ");
        String apellidoMaterno = scanner.nextLine();
        System.out.print("Ingrese email del estudiante: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese código del estudiante (code): ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
    
        String sql = "CALL registrar_estudiante(?, ?, ?, ?, ?)";
        
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
    
    private static void registrarCita(Scanner scanner) {
        try {
            System.out.print("Ingrese el ID del paciente (docid): ");
            int docid = scanner.nextInt();
            scanner.nextLine(); 
    
            System.out.print("Ingrese el código del odontólogo (code): ");
            int code = scanner.nextInt();
            scanner.nextLine();
    
            System.out.print("Ingrese el número de la cita (ncita): ");
            int ncita = scanner.nextInt();
            scanner.nextLine();
    
            System.out.print("Ingrese el día de la cita: ");
            int day = scanner.nextInt();
            System.out.print("Ingrese el mes de la cita: ");
            int month = scanner.nextInt();
            System.out.print("Ingrese el año de la cita: ");
            int year = scanner.nextInt();
            scanner.nextLine();
    
            System.out.print("Ingrese el turno (1 = mañana, 2 = tarde): ");
            int turno = scanner.nextInt();
            scanner.nextLine();
    
            System.out.print("Ingrese el motivo de la cita: ");
            String motivo = scanner.nextLine();
    
            String sql = "CALL registrar_cita(?, ?, ?, ?, ?, ?, ?, ?)";
            
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
        } catch (Exception e) {
            System.out.println("Error en los datos ingresados: " + e.getMessage());
            scanner.nextLine();
        }
>>>>>>> 89c4c7efaebecbd8e1f54c8ece89118a6566da02
>>>>>>> c9bc4d92b1684b30ba3406c67ed041a15f68c2dd
    }
    

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> c9bc4d92b1684b30ba3406c67ed041a15f68c2dd
    private static void registrarCita(Scanner scanner, CitaDAO citaDAO, Connection connection) {
        // Similar a registrarPaciente, pero para citas.
    }
}
<<<<<<< HEAD
=======
=======
    private static void mostrarPacientesYCantidadCitas(Scanner scanner) {
        System.out.print("Ingrese el DNI del paciente: ");
        int docid = scanner.nextInt();
        scanner.nextLine();
    
        String sql = "SELECT * FROM sp_mostrar_citas_paciente(?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, docid);
    
            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Citas del paciente con DNI " + docid + ":");
                while (rs.next()) {
                    int ncita = rs.getInt("ncita");
                    int dia = rs.getInt("dia");
                    int mes = rs.getInt("mes");
                    int anio = rs.getInt("anio");
                    String motivo = rs.getString("motivo");
                    System.out.println("Cita #" + ncita + ": " + dia + "/" + mes + "/" + anio + " - Motivo: " + motivo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar citas del paciente: " + e.getMessage());
        }
    }
    
 
    private static void contarCitasOdontologo(Scanner scanner) {
        System.out.print("Ingrese el código del odontólogo: ");
        int code = scanner.nextInt();
        scanner.nextLine();
    
        String query = "SELECT fn_contar_citas_odontologo(?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, code);
    
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int nrocitas = rs.getInt(1);
                    System.out.println("Número de citas del odontólogo con código " + code + ": " + nrocitas);
                } else {
                    System.out.println("No se encontraron citas para el odontólogo con código " + code);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al contar citas del odontólogo: " + e.getMessage());
        }
    }
    
 
    private static void mostrarCitasRegistradas() {
        String sql = "CALL contar_citas_registradas()";
        
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 
    private static void mostrarCitasOdontologo(Scanner scanner) {
        System.out.print("Ingrese el código del odontólogo (code): ");
        int code = scanner.nextInt();
        
        String sql = "CALL mostrar_citas_odontologo(?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, code);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 
     private static void mostrarCitasTurnoEspecifico() {
        String sql = "CALL sp_mostrar_citas_por_turno(?, ?, ?, ?)";
        try (CallableStatement cstmt = connection.prepareCall(sql)) {
            cstmt.setInt(1, 1); 
            cstmt.setInt(2, 5);
            cstmt.setInt(3, 10);
            cstmt.setInt(4, 2023);
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Cita: " + rs.getInt("ncita") + 
                        ", Día: " + rs.getInt("day") +
                        ", Mes: " + rs.getInt("month") +
                        ", Año: " + rs.getInt("year") +
                        ", Motivo: " + rs.getString("motivo"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

     }
 
     private static void mostrarEmpleadoPorteria() {
        String sql = "CALL sp_mostrar_empleado_porteria(?)";
        try (CallableStatement cstmt = connection.prepareCall(sql)) {
            cstmt.setInt(1, 4);
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("ID Lista: " + rs.getInt("id_lista") +
                        ", Nombre: " + rs.getString("nombre") +
                        ", Apellido: " + rs.getString("apaterno"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }
 
     private static void mostrarCitasPosteriores() {
        String sql = "CALL sp_mostrar_citas_posteriores(?, ?, ?)";
        try (CallableStatement cstmt = connection.prepareCall(sql)) {
            cstmt.setInt(1, 19);
            cstmt.setInt(2, 10);
            cstmt.setInt(3, 2023);
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Cita: " + rs.getInt("ncita") + 
                        ", Día: " + rs.getInt("day") +
                        ", Mes: " + rs.getInt("month") +
                        ", Año: " + rs.getInt("year") +
                        ", Motivo: " + rs.getString("motivo"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }
 
     private static void mostrarPacienteYEstudiante() {
        String sql = "CALL sp_mostrar_pacientes_y_odontologos()";
        try (CallableStatement cstmt = connection.prepareCall(sql)) {
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Paciente: " + rs.getString("paciente") + 
                        ", Odontólogo: " + rs.getString("odontologo"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }
 }
 
>>>>>>> 89c4c7efaebecbd8e1f54c8ece89118a6566da02
>>>>>>> c9bc4d92b1684b30ba3406c67ed041a15f68c2dd
