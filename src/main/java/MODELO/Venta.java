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
    private Date Fecha;
    private List<ItemVenta> items;
    private double totalNeto;
    private double totalIva;
    private double totalFinal;

    // Constructor para una nueva venta
    public Venta(int idCliente) {
        this.idCliente = idCliente;
        this.Fecha = new Date();
        this.items = new ArrayList<>();
        this.totalNeto = 0;
    }

    // Este método lo uso para agregar productos y calcular los totales
    public void agregarItem(ItemVenta item) {
        this.items.add(item);
        calcularTotales();
    }

    // Este método lo uso para agregar productos y recalcular totales
    private void calcularTotales() {
        this.totalNeto = 0;
        for (ItemVenta item : items) {
            this.totalNeto += item.getSubtotal();
        }
        this.totalIva = this.totalNeto * 0.19;
        this.totalFinal = this.totalNeto + this.totalIva;
    }

    public double getTotalFinal() {
        return totalFinal;
    }

    public List<ItemVenta> getItems() {
        return items;
    }
}
