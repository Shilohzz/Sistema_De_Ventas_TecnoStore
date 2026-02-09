/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author baren
 */
public class Empleado extends Persona {

    private String cargo;
    private double salario;
    private int codigoEmp;

    public Empleado(String cargo, double salario, int codigoEmp, String nombre, String tipo_identificacion, int identificacion, String email, int telefono, String sexo, String direccion) {
        super(nombre, tipo_identificacion, identificacion, email, telefono, sexo, direccion);
        this.cargo = cargo;
        this.salario = salario;
        this.codigoEmp = codigoEmp;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getCodigoEmp() {
        return codigoEmp;
    }

    public void setCodigoEmp(int codigoEmp) {
        this.codigoEmp = codigoEmp;
    }
}
