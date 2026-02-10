/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

 
public abstract class Producto {
    protected int id;
    protected double precio;
    protected int stock;
    protected Marca marca;  

    public Producto() {}

    public Producto(int id, double precio, int stock, Marca marca) {
        this.id = id;
        this.precio = precio;
        this.stock = stock;
        this.marca = marca;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public Marca getMarca() { return marca; }
    public void setMarca(Marca marca) { this.marca = marca; }
}
