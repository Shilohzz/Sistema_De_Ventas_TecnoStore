/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author baren
 */
public class Venta {
    private int id;
    private int idCliente;
    private int idEmpleado; 
    private Date fecha;
    private double vTotal; 
    private List<ItemVenta> items;

    
    // Constructor para iniciar una venta
    public Venta(int idCliente, int idEmpleado) {
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.fecha = new Date();
        this.items = new ArrayList<>();
        this.vTotal = 0;
    }

    // Método para agregar productos y actualizar el valor total
    public void agregarItem(ItemVenta item) {
        this.items.add(item);
        calcularVTotal();
    }

    private void calcularVTotal() {
        // Sumo los subtotales de cada item usando Streams
        this.vTotal = items.stream()
                          .mapToDouble(ItemVenta::getSubtotal) // Le pido que tome solo el subTotal que haya en cada item dentro de ItemVenta
                          .sum();
    }

    // Getters necesarios para el DAO
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdCliente() { return idCliente; }
    public int getIdEmpleado() { return idEmpleado; }
    public Date getFecha() { return fecha; }
    public double getVTotal() { return vTotal; }
    public List<ItemVenta> getItems() { return items; }
}
