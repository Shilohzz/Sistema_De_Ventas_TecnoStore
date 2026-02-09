/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author baren
 */
public class Persona {
    protected int id;
    protected String nombre;
    protected String tipo_identificacion;
    protected int identificacion;
    protected String email;
    protected int telefono;
    protected String sexo;
    protected String direccion;

    public Persona(int id, String nombre, String tipo_identificacion, int identificacion, String email, int telefono, String sexo, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.tipo_identificacion = tipo_identificacion;
        this.identificacion = identificacion;
        this.email = email;
        this.telefono = telefono;
        this.sexo = sexo;
        this.direccion = direccion;
    }

    
    
    public Persona(String nombre, String tipo_identificacion, int identificacion, String email, int telefono, String sexo, String direccion) {
        this.nombre = nombre;
        this.tipo_identificacion = tipo_identificacion;
        this.identificacion = identificacion;
        this.email = email;
        this.telefono = telefono;
        this.sexo = sexo;
        this.direccion = direccion;
    }
    
    
    public String getNombre() { return nombre; }
    public String getTipo_identificacion() { return tipo_identificacion; }
    public int getIdentificacion() { return identificacion; }
    public String getEmail() { return email; }
    public int getTelefono() { return telefono; }
    public String getSexo() { return sexo; }
    public String getDireccion() { return direccion; }
}
