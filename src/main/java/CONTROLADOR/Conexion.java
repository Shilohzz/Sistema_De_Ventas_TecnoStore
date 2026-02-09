/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author baren
 */
public class Conexion {
    private static Connection c = null;

   
    private static void conectar() {
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tecno_store", "root", "leavemealone09");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

    public static Connection getConexion() {
        try {
            if (c == null || c.isClosed()) {
                conectar(); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
