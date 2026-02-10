/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO.DAO;

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
public class MarcaDAO {
    private Connection connection;

    public MarcaDAO(Connection connection) {
        this.connection = connection;
    }

    // INserto una marca en la db
    public boolean registrar(Marca marca) {
        String sql = "INSERT INTO marca (sistema_op, gama, nombre_marca) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, marca.getSistema_op());
            ps.setString(2, marca.getGama());
            ps.setString(3, marca.getNombre_marca());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[Error MarcaDAO.registrar]: " + e.getMessage());
            return false;
        }
    }

    // Método para poder OBTENER todas las marcas que tengo en mi db
    public List<Marca> obtenerTodas() {
        List<Marca> lista = new ArrayList<>();
        String sql = "SELECT id, sistema_op, gama, nombre_marca FROM marca";
        
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            // Mientras que se itera en la db, se crean objetos distintos para cada marca que va encontrando.
            while (rs.next()) {
                Marca m = new Marca(
                    rs.getInt("id"),
                    rs.getString("sistema_op"),
                    rs.getString("gama"),
                    rs.getString("nombre_marca")
                );
                lista.add(m);
            }
        } catch (SQLException e) {
            System.err.println("[Error MarcaDAO.obtenerTodas]: " + e.getMessage());
        }
        return lista;
    }

    // Para buscar una marca por id
    public Marca buscarPorId(int id) {
        String sql = "SELECT * FROM marca WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Marca(
                        rs.getInt("id"),
                        rs.getString("sistema_op"),
                        rs.getString("gama"),
                        rs.getString("nombre_marca")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("[Error MarcaDAO.buscarPorId]: " + e.getMessage());
        }
        return null;
    }
}
