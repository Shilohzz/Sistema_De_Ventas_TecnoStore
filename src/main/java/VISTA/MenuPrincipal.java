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
            System.out.println("\n--- TECNO_STORE: SISTEMA DE VENTAS ---");
            System.out.println("1. Ver Catálogo de Celulares");
            System.out.println("2. Registrar Nueva Venta");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine(); // Limpiar búfer
                switch (opcion) {
                    case 1 -> mostrarCatalogo();
                    case 2 -> procesoVenta();
                    case 3 -> System.out.println("Cerrando sistema...");
                    default -> System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("Por favor, ingrese un número.");
                sc.next();
            }
        } while (opcion != 3);
    }

    private void mostrarCatalogo() {
        System.out.println("\nID | MARCA | PRECIO | STOCK");
        List<Celular> lista = controller.getCatalogo();
        for (Celular c : lista) {
            System.out.printf("%d | %s | $%.2f | %d\n", 
                c.getId(), c.getMarca().getNombre_marca(), c.getPrecio(), c.getStock());
        }
    }

    private void procesoVenta() {
        System.out.println("\n--- SELECCIÓN DE CLIENTE ---");
        List<Cliente> clientes = controller.getClientes();
        for (Cliente cl : clientes) {
            System.out.println("ID: " + cl.getId() + " - " + cl.getNombre());
        }
        System.out.print("Ingrese el ID del Cliente: ");
        int idC = sc.nextInt();

        System.out.println("\n--- SELECCIÓN DE EMPLEADO ---");
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
}
