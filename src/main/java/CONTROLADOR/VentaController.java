/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.Celular;
import MODELO.Cliente;
import MODELO.DAO.CelularDAO;
import MODELO.DAO.ClienteDAO;
import MODELO.DAO.EmpleadoDAO;
import MODELO.DAO.VentaDAO;
import MODELO.Empleado;
import MODELO.Venta;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author baren
 */
public class VentaController {

    private CelularDAO celularDAO;
    private VentaDAO ventaDAO;
    private ClienteDAO clienteDAO;
    private EmpleadoDAO empleadoDAO;

    public VentaController(Connection conn) {
        this.celularDAO = new CelularDAO(conn);
        this.ventaDAO = new VentaDAO(conn);
        this.clienteDAO = new ClienteDAO(conn);
        this.empleadoDAO = new EmpleadoDAO(conn);
    }

    public boolean registrarNuevoCliente(Cliente cliente) {
        return clienteDAO.registrarCliente(cliente);
    }

    public List<Celular> getCatalogo() {
        return celularDAO.obtenerTodos();
    }

    public List<Cliente> getClientes() {
        return clienteDAO.obtenerTodos();
    }

    public List<Empleado> getEmpleados() {
        return empleadoDAO.obtenerTodos();
    }

    public Celular buscarCelularPorId(int id) {
        return celularDAO.buscarPorId(id);
    }

    public boolean realizarVenta(Venta venta) {
        if (venta.getItems().isEmpty()) {
            return false;
        }
        return ventaDAO.realizarVentaCompleta(venta);
    }
}
