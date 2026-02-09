/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO.DAO;

import MODELO.Empleado;
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
public class EmpleadoDAO {
    private Connection connection;
    
    public EmpleadoDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean registrarEmpleado(Empleado emp) {
        if (this.connection == null) return false;

        String sqlPersona = "INSERT INTO persona (nombre, tipo_identificacion, identificacion, email, telefono, sexo, direccion) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlEmpleado = "INSERT INTO empleado (id_empleado, cargo, salario, codigo) VALUES (?, ?, ?, ?)";

        try {
            connection.setAutoCommit(false);

             
            PreparedStatement psPersona = connection.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS);
            psPersona.setString(1, emp.getNombre());
            psPersona.setString(2, emp.getTipo_identificacion());
            psPersona.setInt(3, emp.getIdentificacion());
            psPersona.setString(4, emp.getEmail());
            psPersona.setInt(5, emp.getTelefono());
            psPersona.setString(6, emp.getSexo());
            psPersona.setString(7, emp.getDireccion());
            psPersona.executeUpdate();

             
            ResultSet rs = psPersona.getGeneratedKeys();
            int idGenerado = 0;
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }

            
            PreparedStatement psEmpleado = connection.prepareStatement(sqlEmpleado);
            psEmpleado.setInt(1, idGenerado);
            psEmpleado.setString(2, emp.getCargo());
            psEmpleado.setDouble(3, emp.getSalario());
            psEmpleado.setInt(4, emp.getCodigoEmp());
            psEmpleado.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException e) {
            try { connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            System.err.println("Error al registrar empleado: " + e.getMessage());
            return false;
        }
    }
    
    
    public List<Empleado> obtenerTodos() {
    List<Empleado> lista = new ArrayList<>();
    String sql = "SELECT p.*, e.cargo, e.salario, e.codigo " +
                 "FROM persona p " +
                 "INNER JOIN empleado e ON p.id = e.id_empleado";

    try (Statement st = connection.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            Empleado emp = PersonaFactory.crearEmpleado(
                rs.getString("nombre"),
                rs.getString("tipo_identificacion"),
                rs.getInt("identificacion"),
                rs.getString("email"),
                rs.getInt("telefono"),
                rs.getString("sexo"),
                rs.getString("direccion"),
                rs.getString("cargo"),
                rs.getDouble("salario"),
                rs.getInt("codigo")
            );
            lista.add(emp);
        }
    } catch (SQLException e) {
        System.err.println("Error al listar empleados: " + e.getMessage());
    }
    return lista;
}
}
