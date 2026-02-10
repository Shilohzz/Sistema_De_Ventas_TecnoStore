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
import MODELO.DAO.MarcaDAO;
import MODELO.DAO.VentaDAO;
import MODELO.Marca;
import MODELO.Venta;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author baren
 */
public class TiendaController {
  private ClienteDAO clienteDAO;
    private EmpleadoDAO empleadoDAO;
    private CelularDAO celularDAO;
    private VentaDAO ventaDAO;
    private MarcaDAO marcaDAO;

    public TiendaController(Connection connection) {
        
        this.clienteDAO = new ClienteDAO(connection);
        this.empleadoDAO = new EmpleadoDAO(connection);
        this.celularDAO = new CelularDAO(connection);
        this.ventaDAO = new VentaDAO(connection);
        this.marcaDAO = new MarcaDAO(connection);
    }



    public String registrarVenta(Venta v) {
       
        if (ventaDAO.realizarVentaCompleta(v)) {
            return "Venta procesada con éxito.";
        } else {
            return "Error: No se pudo completar la venta. Verifique stock o IDs.";
        }
    }

    public List<Celular> listarCatalogo() {
        return celularDAO.obtenerTodos();
    }

    public boolean agregarNuevoCliente(Cliente c) {
        return clienteDAO.registrarCliente(c);
    }
    
    public List<Marca> listarMarcas() {
        return marcaDAO.obtenerTodas();
    }
}
