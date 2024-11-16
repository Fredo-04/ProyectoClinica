/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clinicaapp;
import java.sql.*;
import java.util.Scanner;

public class ClinicaApp {

    public static void main(String[] args) throws SQLException {
                    String url = "jdbc:postgresql://localhost/clinicadb";
            Connection conection = DriverManager.getConnection(url,"postgres","root");
        Scanner sc = new Scanner(System.in);
                int opcion;

                do {
                    System.out.println("\n--- Menú Odontología ---");
                    System.out.println("1. Buscar paciente");
                    System.out.println("2. Crear Estudiante de Odontología");
                    System.out.println("3. Crear Cita");
                    System.out.println("4. Reporte de Citas");
                    System.out.println("5. Actualizar Cita");
                    System.out.println("6. Salir");
                    System.out.print("Seleccione una opción: ");
                    opcion = sc.nextInt();
                    sc.nextLine(); 
                    switch (opcion) {
                    case 1:
                        System.out.println("Ingrese su consulta SQL:");
                        String query = sc.nextLine();

                        try (Statement stmt = conection.createStatement()) {
                            if (query.trim().toUpperCase().startsWith("SELECT")) {
                                try (ResultSet rs = stmt.executeQuery(query)) {
                                    int columnCount = rs.getMetaData().getColumnCount();
                                    while (rs.next()) {
                                        for (int i = 1; i <= columnCount; i++) {
                                            System.out.print(rs.getString(i) + " ");
                                        }
                                        System.out.println();
                                    }
                                }
                            }
                        } catch (SQLException e) {
                            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
                        }
                        break;
                        case 2:
                            //crearEstudiante(scanner);
                            break;
                        case 3:
                            //crearCita(scanner);
                            break;
                        case 4:
                            //reporteCitas();
                            break;
                        case 5:
                            //actualizarCita(scanner);
                            break;
                        case 6:
                            System.out.println("Saliendo del sistema...");
                            break;
                        default:
                            System.out.println("Opción no válida, intente de nuevo.");
                    }
                } while (opcion != 6);

                //scanner.close();


            }

       
    }

