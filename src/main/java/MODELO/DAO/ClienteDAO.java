/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO.DAO;

import MODELO.Cliente;
import MODELO.PersonaFactory;
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
public class ClienteDAO {

    private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean registrarCliente(Cliente cliente) {
        if (this.connection == null) {
            return false;
        }

        String sqlPersona = "INSERT INTO persona (nombre, tipo_identificacion, identificacion, email, telefono, sexo, direccion) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlCliente = "INSERT INTO cliente (id_cliente) VALUES (?)";

        try {
            connection.setAutoCommit(false);

            int idGenerado = 0;
            try (PreparedStatement psPersona = connection.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
                psPersona.setString(1, cliente.getNombre());
                psPersona.setString(2, cliente.getTipo_identificacion());
                psPersona.setInt(3, cliente.getIdentificacion());
                psPersona.setString(4, cliente.getEmail());
                psPersona.setInt(5, cliente.getTelefono());
                psPersona.setString(6, cliente.getSexo());
                psPersona.setString(7, cliente.getDireccion());
                psPersona.executeUpdate();

                try (ResultSet rs = psPersona.getGeneratedKeys()) {
                    if (rs.next()) {
                        idGenerado = rs.getInt(1);
                    }
                }
            }

            try (PreparedStatement psCliente = connection.prepareStatement(sqlCliente)) {
                psCliente.setInt(1, idGenerado);
                psCliente.executeUpdate();
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Cliente> obtenerTodos() {
        List<Cliente> lista = new ArrayList<>();

        String sql = "SELECT p.* FROM persona p INNER JOIN cliente c ON p.id = c.id_cliente";

        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cliente c = PersonaFactory.crearCliente(
                        rs.getString("nombre"),
                        rs.getString("tipo_identificacion"),
                        rs.getInt("identificacion"),
                        rs.getString("email"),
                        rs.getInt("telefono"),
                        rs.getString("sexo"),
                        rs.getString("direccion")
                );

                c.setId(rs.getInt("id"));

                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }
}
