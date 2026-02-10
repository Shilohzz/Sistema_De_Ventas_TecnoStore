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

   private int id;  
    private Celular celular; // Atributo de tipo clase para poder acceder a los datos completos del celular 
    private int cantidad;
    private double subtotal;

    // Constructor para nuevos items
    public ItemVenta(Celular celular, int cantidad) {
        this.celular = celular;
        this.cantidad = cantidad;
        this.subtotal = celular.getPrecio() * cantidad;
    }

     
    public Celular getCelular() { return celular; }
    public int getCantidad() { return cantidad; }
    public double getSubtotal() { return subtotal; }

}
