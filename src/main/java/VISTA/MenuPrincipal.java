/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VISTA;

import CONTROLADOR.VentaController;
import MODELO.Celular;
import MODELO.Cliente;
import MODELO.Empleado;
import MODELO.ItemVenta;
import MODELO.Venta;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author baren
 */
public class MenuPrincipal {
    private Scanner sc = new Scanner(System.in);
    private VentaController controller;

    public MenuPrincipal(VentaController controller) {
        this.controller = controller;
    }

    public void mostrarMenu() {
    int opcion = 0;
    do {
        System.out.println("\n--- TECNO_STORE: GESTIÓN INTEGRAL ---");
        System.out.println("1. Ver Catalogo de Celulares");
        System.out.println("2. Registrar Nueva Venta");
        System.out.println("3. Registrar Nuevo Cliente");
        System.out.println("4. Ver Lista de Clientes");    
        System.out.println("5. Ver Lista de Empleados");   
        System.out.println("6. Salir");
        System.out.print("Seleccione: ");
        
        opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1 -> mostrarCatalogo();
            case 2 -> procesoVenta();
            case 3 -> menuRegistroCliente();
            case 4 -> verClientesRegistrados();
            case 5 -> verEmpleadosRegistrados();
            case 6 -> System.out.println("Saliendo...");
            default -> System.out.println("Opcion no reconocida.");
        }
    } while (opcion != 6);
}
    
    private void verClientesRegistrados() {
    System.out.println("\n--- LISTADO DE CLIENTES ---");
    System.out.println("1. ID \t 2. NOMBRE \t 3. IDENTIFICACION \t 4. EMAIL");
    
    List<Cliente> clientes = controller.getClientes();
    
    if (clientes.isEmpty()) {
        System.out.println("No hay clientes registrados.");
    } else {
        
        clientes.stream().forEach(c -> {
            System.out.println("----------------------------------------------------------------\n"+c.getId() + " \t " + 
                               c.getNombre() + " \t " + 
                               c.getIdentificacion() + " \t " + 
                               c.getEmail() + "\n"+ "----------------------------------------------------------------");
        });
    }
}

private void verEmpleadosRegistrados() {
    System.out.println("\n--- LISTADO DE EMPLEADOS ---");
    System.out.println("ID \t NOMBRE \t CARGO \t SALARIO");
    
    List<Empleado> empleados = controller.getEmpleados();
    
    if (empleados.isEmpty()) {
        System.out.println("No hay empleados registrados.");
    } else {
        empleados.stream().forEach(e -> {
            System.out.println("----------------------------------------------------------------\n"+e.getId() + " \t " + 
                               e.getNombre() + " \t " + 
                               e.getCargo() + " \t $" + 
                               e.getSalario()+ "\n"+ "----------------------------------------------------------------");
        });
    }
}

    private void mostrarCatalogo() {
    System.out.println("\nID \t MARCA \t PRECIO \t STOCK");
    controller.getCatalogo().stream().forEach(c -> {
        System.out.println(c.getId() + " \t " + 
                           c.getMarca().getNombre_marca() + " \t $" + 
                           c.getPrecio() + " \t " + 
                           c.getStock());
    });
}

    private void procesoVenta() {
        System.out.println("\n--- SELECCION DE CLIENTE ---");
        List<Cliente> clientes = controller.getClientes();
        for (Cliente cl : clientes) {
            System.out.println("ID: " + cl.getId() + " - " + cl.getNombre());
        }
        System.out.print("Ingrese el ID del Cliente: ");
        int idC = sc.nextInt();

        System.out.println("\n--- SELECCION DE EMPLEADO ---");
        List<Empleado> empleados = controller.getEmpleados();
        for (Empleado emp : empleados) {
            System.out.println("ID: " + emp.getId() + " - " + emp.getNombre() + " (" + emp.getCargo() + ")");
        }
        System.out.print("Ingrese el ID del Empleado: ");
        int idE = sc.nextInt();
        sc.nextLine(); // Limpio el bufer

        Venta nuevaVenta = new Venta(idC, idE);
        String continuar;

        do {
            mostrarCatalogo();
            System.out.print("\nIngrese ID del Celular a vender: ");
            int idCel = sc.nextInt();
            Celular cel = controller.buscarCelularPorId(idCel);
            
            if (cel != null) {
                System.out.print("Cantidad (Max " + cel.getStock() + "): ");
                int cant = sc.nextInt();
                if (cant > 0 && cant <= cel.getStock()) {
                    nuevaVenta.agregarItem(new ItemVenta(cel, cant));
                    System.out.println("Producto agregado.");
                } else {
                    System.out.println("Stock insuficiente o cantidad inválida.");
                }
            } else {
                System.out.println("Celular no encontrado.");
            }
            
            System.out.print("¿Desea agregar otro producto? (s/n): ");
            continuar = sc.next();
        } while (continuar.equalsIgnoreCase("s"));

        System.out.println("\n--- RESUMEN ---");
        System.out.println("Total a pagar: $" + nuevaVenta.getVTotal());
        System.out.print("¿Confirmar venta en Base de Datos? (s/n): ");
        if (sc.next().equalsIgnoreCase("s")) {
            if (controller.realizarVenta(nuevaVenta)) {
                System.out.println("VENTA GUARDADA");
            } else {
                System.out.println("Error crítico al guardar la venta.");
            }
        } else {
            System.out.println("Venta cancelada.");
        }
    }
    private void menuRegistroCliente() {
    System.out.println("\n--- REGISTRO DE NUEVO CLIENTE ---");
    System.out.print("Nombre completo: ");
    String nombre = sc.nextLine();
    
    System.out.print("Tipo Identificacion (CC/TI): ");
    String tipoId = sc.next();
    
    System.out.print("Número Identificacion: ");
    int iden = sc.nextInt();
    sc.nextLine();

    String email;
    while (true) {
        System.out.print("Correo electronico (@gmail.com o @mail.com): ");
        email = sc.nextLine();
        
        // Valido el @mail
        if (email.contains("@gmail.com") || email.contains("@mail.com")) {
            break; 
        } else {
            System.out.println("Error. Correo electronico no reconocido.");
        }
    }

    System.out.print("Telefono: ");
    int tel = sc.nextInt();
    sc.nextLine(); // Limpio el bufer porque me daba error búfer
    
    System.out.print("Sexo (Femenino/Masculino/Otro): ");
    String sexo = sc.nextLine();
    
    System.out.print("Dirección: ");
    String dir = sc.nextLine();

    // Usamos el Factory para crear el objeto
    Cliente nuevo = MODELO.PersonaFactory.crearCliente(nombre, tipoId, iden, email, tel, sexo, dir);

    if (controller.registrarNuevoCliente(nuevo)) {
        System.out.println("Cliente registrado con correctamente.");
    } else {
        System.out.println("Error al registrar el cliente.");
    }
}
}


