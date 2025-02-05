package es.ies.puerto;

import java.io.IOException;

import es.ies.puerto.model.Empleado;
import es.ies.puerto.model.fichero.FileOperations;

/**
 * 
 * @author Giancarlo
 * @version 1.0.0
 */
public class Main {
    public static void main(String[] args) throws IOException {
        FileOperations operaciones = new FileOperations();
        Empleado empleado = new Empleado("1", "Juan Pérez", "Desarrollador", 3000.50, "15\\10\\1995");
        Empleado empleado2 = new Empleado("2", "Ana Gómez", "Diseñadora", 2800.75, "10\\01\\1990");
        Empleado empleado3 = new Empleado("3", "Luis López", "Diseñadora", 4000.00, "30\\07\\2000");
        Empleado empleado4 = new Empleado("1", "Pato lucas", "Diseñadora", 2800.75, "10\\09\\1990");
        operaciones.create(empleado);
        operaciones.create(empleado2);
        operaciones.create(empleado3);
        operaciones.create(empleado4);
        operaciones.update(empleado4);
        System.out.println(operaciones.empleadosPorPuesto("Diseñadora"));
        System.out.println(operaciones.empleadosPorEdad("09\\09\\1990", "30\\08\\2000"));
    }
}