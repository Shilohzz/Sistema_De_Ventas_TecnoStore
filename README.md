# TECNO_STORE - Sistema de Gestión de Ventas

Sistema de gestión para una tienda de tecnología desarrollado bajo el patrón de diseño **MVC (Modelo-Vista-Controlador)** y arquitectura de persistencia **DAO**. El sistema permite gestionar el inventario de dispositivos, clientes, empleados y el procesamiento de ventas mediante transacciones SQL.

## Descripción General
La aplicación centraliza las operaciones de venta validando la existencia de productos en el inventario y vinculando la transacción a un cliente y un empleado registrados. La integridad de los datos se garantiza mediante el manejo de transacciones a nivel de base de datos.

## Arquitectura y Patrones de Diseño
* **MVC:** Separación de responsabilidades entre la interfaz de consola, el controlador de lógica de negocio y las entidades de datos.
* **DAO (Data Access Object):** Desacoplamiento de la lógica de acceso a datos, facilitando el mantenimiento y la escalabilidad.
* **Factory Pattern:** Implementación de una fábrica para la creación de objetos de tipo Persona (Clientes y Empleados).
* **Herencia en Base de Datos:** Uso de una tabla base (Persona) relacionada mediante llaves foráneas con tablas especializadas (Cliente, Empleado).

## Modelo de Base de Datos (SQL)
El sistema utiliza el siguiente esquema relacional:

```sql
CREATE TABLE PERSONA (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    tipo_identificacion ENUM('CC', 'TI') NOT NULL,
    identificacion INT NOT NULL UNIQUE,
    sexo ENUM('Femenino', 'Masculino', 'Otro') NOT NULL,
    email VARCHAR(50) UNIQUE,
    telefono INT NOT NULL UNIQUE,
    direccion VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE EMPLEADO (
    id_empleado INT NOT NULL,
    codigo INT NOT NULL UNIQUE,
    salario DOUBLE NOT NULL,
    cargo VARCHAR(20) NOT NULL,
    estado ENUM("ACTIVO","INACTIVO"),
    FOREIGN KEY(id_empleado) REFERENCES PERSONA(id)
);

CREATE TABLE Cliente(
    id_cliente INT NOT NULL,
    FOREIGN KEY(id_cliente) REFERENCES PERSONA(id)
);

CREATE TABLE marca (
   id int NOT NULL AUTO_INCREMENT,
   sistema_op enum('IOS','Android') DEFAULT NULL,
   gama enum('Alta','Media','Baja') DEFAULT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE celulares (
   id int NOT NULL AUTO_INCREMENT,
   marca int NOT NULL,
   stock int DEFAULT NULL,
   precio double NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (marca) REFERENCES marca(id)
);

CREATE TABLE venta (
   id int NOT NULL AUTO_INCREMENT,
   id_cliente int NOT NULL,
   id_empleado int NOT NULL,
   fecha date NOT NULL,
   V_Total double NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente),
   FOREIGN KEY (id_empleado) REFERENCES empleado (id_empleado)
);