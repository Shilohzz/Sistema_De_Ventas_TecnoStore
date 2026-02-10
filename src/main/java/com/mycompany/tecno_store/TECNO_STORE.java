package com.mycompany.tecno_store;

import CONTROLADOR.VentaController;
import VISTA.MenuPrincipal;
import java.sql.Connection;


public class TECNO_STORE {

    public static void main(String[] args) {
        // 1. Conexión
        Connection conn = new CONTROLADOR.Conexion().getConexion();
        
        if (conn != null) {
            
            VentaController controller = new VentaController(conn);
            
            
            MenuPrincipal vista = new MenuPrincipal(controller);
            
            
            vista.mostrarMenu();
        }
    }
    }
    

