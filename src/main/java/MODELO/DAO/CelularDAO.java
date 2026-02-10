/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO.DAO;

import MODELO.Celular;
import MODELO.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author baren
 */
public class CelularDAO {
    private Connection connection;

    public CelularDAO(Connection connection) {
        this.connection = connection;
    }

    
    public boolean registrar(Celular cel) {
        // Recordamos tus campos: id - marca (FK) - stock - precio
        String sql = "INSERT INTO celulares (marca, stock, precio) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cel.getMarca().getId()); // Guardamos el ID de la marca
            ps.setInt(2, cel.getStock());
            ps.setDouble(3, cel.getPrecio());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar celular: " + e.getMessage());
            return false;
        }
    }

    // Hago un INNER JOIN para obtener datos de dos tablas
    public List<Celular> obtenerTodos() {
        List<Celular> lista = new ArrayList<>();
         
        String sql = "SELECT c.id, c.stock, c.precio, m.id AS id_m, m.sistema_op, m.gama, m.nombre_marca " +
                     "FROM celulares c " +
                     "INNER JOIN marca m ON c.marca = m.id";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                // Creo un objeto por cada vez que itere para poder mostrar la info individual de cada marca/celular
                Marca marca = new Marca(
                    rs.getInt("id_m"),
                    rs.getString("sistema_op"),
                    rs.getString("gama"),
                    rs.getString("nombre_marca")
                );

                 
                Celular cel = new Celular(
                    rs.getInt("id"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    marca
                );
                lista.add(cel);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar celulares: " + e.getMessage());
        }
        return lista;
    }
}
