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
        String sqlVenta = "INSERT INTO venta (id_cliente, id_empleado, fecha, V_Total) VALUES (?, ?, ?, ?)"; // Insertar Venta
        String sqlDetalle = "INSERT INTO detalle_venta (id_venta, id_celular, cantidad, subtotal) VALUES (?, ?, ?, ?)"; // Insertar detalle de venta
        String sqlStock = "UPDATE celulares SET stock = stock - ? WHERE id = ?"; // Actualizar stock

        try {
            connection.setAutoCommit(false);

            // Insert en la tabla Venta
            int idVentaGenerado = 0;
            try (PreparedStatement psVenta = connection.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
                psVenta.setInt(1, venta.getIdCliente());
                psVenta.setInt(2, venta.getIdEmpleado());
                psVenta.setTimestamp(3, new Timestamp(venta.getFecha().getTime()));
                psVenta.setDouble(4, venta.getVTotal());
                psVenta.executeUpdate();

                ResultSet rs = psVenta.getGeneratedKeys();
                if (rs.next()) {
                    idVentaGenerado = rs.getInt(1);
                }
            }

            try (PreparedStatement psDetalle = connection.prepareStatement(sqlDetalle); PreparedStatement psStock = connection.prepareStatement(sqlStock)) {

                for (ItemVenta item : venta.getItems()) {

                    psDetalle.setInt(1, idVentaGenerado);
                    psDetalle.setInt(2, item.getCelular().getId());
                    psDetalle.setInt(3, item.getCantidad());
                    psDetalle.setDouble(4, item.getSubtotal());
                    psDetalle.addBatch();

                    psStock.setInt(1, item.getCantidad());
                    psStock.setInt(2, item.getCelular().getId());
                    psStock.addBatch();
                }

                psDetalle.executeBatch();
                psStock.executeBatch();
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback();
                System.err.println("Venta cancelada, Error: " + e.getMessage());
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
}
