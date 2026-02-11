/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author baren
 */
public class PersonaFactory {
    // Aquí van los métodos para crear personas 

    public static Cliente crearCliente(String nombre, String tipo_identificacion, int identificacion, String email, int telefono, String sexo, String direccion) {
        return new Cliente(nombre, tipo_identificacion, identificacion, email, telefono, sexo, direccion);
    }

    public static Empleado crearEmpleado(String nombre, String tipoIdentificacion, int identificacion, String email, int telefono, String sexo, String direccion, String cargo, double salario, int codigoEmp) {
        return new Empleado(cargo, salario, codigoEmp, nombre, tipoIdentificacion, identificacion, email, telefono, sexo, direccion);
    }
}
