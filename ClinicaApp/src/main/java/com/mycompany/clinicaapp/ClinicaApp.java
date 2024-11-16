/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clinicaapp;
import java.sql.*;
public class ClinicaApp {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost/clinicadb";
        Connection conection = DriverManager.getConnection(url,"postgres","root");
        

//inyection SQL
        String query = "select code,email from eodonto;";
        try(Statement stmt = conection.createStatement();
                ResultSet rs = stmt.executeQuery(query))
        {
            while(rs.next()){
                String code = rs.getString("code");
                String email = rs.getString("email");
                System.out.println(code+email);
            }
        }
    }
}
