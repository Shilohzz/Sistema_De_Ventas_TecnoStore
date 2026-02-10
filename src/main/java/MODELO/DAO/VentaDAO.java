/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO.DAO;

import MODELO.ItemVenta;
import MODELO.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 *
 * @author baren
 */
public class VentaDAO {
    private Connection connection;

    public VentaDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean realizarVentaCompleta(Venta venta) {
        String sqlVenta = "INSERT INTO venta (id_cliente, id_empleado, fecha, V_Total) VALUES (?, ?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalle_venta (id_venta, id_celular, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        
        try {
            // False para que haga ambos commits al final y no solo uno en caso de que el segundo commit falle o no se cumpla
            connection.setAutoCommit(false);

            // Insert en la tabla Venta
            try (PreparedStatement psVenta = connection.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
                psVenta.setInt(1, venta.getIdCliente());
                psVenta.setInt(2, venta.getIdEmpleado());
                psVenta.setTimestamp(3, new Timestamp(venta.getFecha().getTime()));
                psVenta.setDouble(4, venta.getVTotal());
                
                psVenta.executeUpdate();

                // Aquí obtengo el id que se generó en la venta
                ResultSet rs = psVenta.getGeneratedKeys();
                int idVentaGenerado = 0;
                if (rs.next()) {
                    idVentaGenerado = rs.getInt(1);
                }

                // Insert en la tabla detalle_venta
                try (PreparedStatement psDetalle = connection.prepareStatement(sqlDetalle)) {
                    for (ItemVenta item : venta.getItems()) {
                        psDetalle.setInt(1, idVentaGenerado); 
                        psDetalle.setInt(2, item.getCelular().getId());
                        psDetalle.setInt(3, item.getCantidad());
                        psDetalle.setDouble(4, item.getSubtotal());
                        psDetalle.addBatch(); 
                    }
                    psDetalle.executeBatch(); 
                }
            }

            // Hago el commit para confirmar los cambios en caso de que nada falle
            connection.commit();
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback(); // Hago un rollback en el catch por si algo llega a fallar, se borre todo lo que se intentó hacer.
                System.err.println("Venta cancelada, Error: " + e.getMessage());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
