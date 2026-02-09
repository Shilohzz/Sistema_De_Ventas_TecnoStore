/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author baren
 */
public class Cliente extends Persona {
    private int idCliente;

    public Cliente(int idCliente, int id, String nombre, String tipo_identificacion, int identificacion, String email, int telefono, String sexo, String direccion) {
        super(id, nombre, tipo_identificacion, identificacion, email, telefono, sexo, direccion);
        this.idCliente = idCliente;
    }

    

    public Cliente(String nombre, String tipo_identificacion, int identificacion, String email, int telefono, String sexo, String direccion) {
        super(nombre, tipo_identificacion, identificacion, email, telefono, sexo, direccion);
    }
    
    
    public int getIdCliente() {
        return idCliente;
    }  
}
