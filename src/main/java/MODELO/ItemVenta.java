/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author baren
 */
public class ItemVenta {

    private int idCelular;
    private String modeloCelular;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public ItemVenta(int idCelular, String modeloCelular, int cantidad, double precioUnitario) {
        this.idCelular = idCelular;
        this.modeloCelular = modeloCelular;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = cantidad * precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getidCelular() {
        return idCelular;
    }

    public double getSubtotal() {
        return subtotal;
    }
}
