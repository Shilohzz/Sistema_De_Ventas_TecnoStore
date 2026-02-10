/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author baren
 */
public class Celular extends Producto {
    public Celular() {
        super();
    }

    public Celular(int id, double precio, int stock, Marca marca) {
        // Enviamos los datos al constructor del padre (Producto)
        super(id, precio, stock, marca);
    }
}
