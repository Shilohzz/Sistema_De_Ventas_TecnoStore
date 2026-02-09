/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author baren
 */
public class Celular {
    private int id;
    private String marcaNombre; 
    private String sistemaOperativo; 
    private String gama; 
    private double precio;
    private int stock;

    // Constructor para consultar celulares existentes
    public Celular(int id, String marcaNombre, String sistemaOperativo, String gama, double precio, int stock) {
        this.id = id;
        this.marcaNombre = marcaNombre;
        this.sistemaOperativo = sistemaOperativo;
        this.gama = gama;
        this.precio = precio;
        this.stock = stock;
    }

    // Constructor para CREAR un nuevo celular
    public Celular(String marcaNombre, String sistemaOperativo, String gama, double precio, int stock) {
        this.marcaNombre = marcaNombre;
        this.sistemaOperativo = sistemaOperativo;
        this.gama = gama;
        this.precio = precio;
        this.stock = stock;
    }

    // GETTERS & SETTERS
    public String getMarcaNombre() {
        return marcaNombre;
    }

    public void setMarcaNombre(String marcaNombre) {
        this.marcaNombre = marcaNombre;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getGama() {
        return gama;
    }

    public void setGama(String gama) {
        this.gama = gama;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Cuando se consulte el celular, se presenta de esta manera más bonita
    @Override
    public String toString() {
        return String.format("ID: %d | %s | Gama: %s | Precio: $%.2f | Stock: %d |", id, marcaNombre, gama, precio, stock);
    }
}
