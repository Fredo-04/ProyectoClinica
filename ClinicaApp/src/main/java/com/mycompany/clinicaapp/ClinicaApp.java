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
                 System.out.println("12. Salir");
                 
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
                         mostrarPacientesYCantidadCitas();
                         break;
                     case 5:
                         contarCitasOdontologo();
                         break;
                     case 6:
                         mostrarCitasRegistradas();
                         break;
                     case 7:
                         mostrarCitasOdontologo();
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
        System.out.print("Ingrese nombre del paciente: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Ingrese apellido paterno: ");
        String apellidoPaterno = scanner.nextLine();
        
        System.out.print("Ingrese apellido materno: ");
        String apellidoMaterno = scanner.nextLine();
        
        System.out.print("Ingrese identificación (docid): ");
        int docid = scanner.nextInt();
        
        System.out.print("Ingrese email del paciente: ");
        String email = scanner.nextLine();
        
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
        } catch (Exception e) {
            System.out.println("Error en los datos ingresados: " + e.getMessage());
            scanner.nextLine();
        }
    }

     private static void mostrarPacientesYCantidadCitas() {
        String sql = "SELECT fn_contar_citas_odontologo(?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, 20201101);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Número de citas del odontólogo: " + rs.getInt(1));
                }
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
 