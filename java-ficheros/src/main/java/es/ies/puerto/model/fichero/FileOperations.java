package es.ies.puerto.model.fichero;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import es.ies.puerto.model.Empleado;
import es.ies.puerto.model.OperacionesInterfaces;



public class FileOperations implements OperacionesInterfaces{
    
    File fichero;
    String path = "C:\\Users\\gian7\\Desktop\\/java-ficheros/src/main/resources";

    /**
     * constructor vacio
     * @return
     */
    public FileOperations() throws IOException{
        fichero = new File(path, "Empleados.txt");
        if (!fichero.exists() || !fichero.isFile()) {
            fichero.createNewFile();
        }
    }

    /**
     * Metodo que permite crear un empleado dentro del fichero
     * @param empleado
     * @return
     */
    @Override
    public boolean create(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null) {
            return false;
        }
        Set<Empleado> empleados = read(fichero);
        if (empleados.contains(empleado)) {  
            return false;
        }
        return create(empleado.toString(), fichero);
    }

    private boolean create(String data, File fichero){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichero, true))) {
            writer.write(data);
            writer.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    
    /**
     * Metodo que permite eliminar un empleado dentro del fichero
     * @param empleado
     * @return
     */
    @Override
    public boolean delete(String identificador) {
        if (identificador == null || identificador.isEmpty()) {
            return false;
        }
        Empleado empleado = new Empleado(identificador);
        return delete(empleado);
    }

    private boolean delete(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null) {
            return false;
        }
        Set<Empleado> Empleados = read(fichero);
        if (!Empleados.contains(empleado)) {
            System.out.println("false");
            return false;
        }
        for (Empleado empleadoBuscada : Empleados) {
            if (empleadoBuscada.equals(empleado)) {
                Empleados.remove(empleadoBuscada);  
            }
        }
        return updateFile(Empleados, fichero);
    }

    private Set<Empleado> read(File file) {
        Set<Empleado> empleados = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arrayLine = line.split(", ");
                double doubleValue = Double.parseDouble(arrayLine[3]);
                Empleado empleado = new Empleado(arrayLine[0], arrayLine[1], arrayLine[2], doubleValue, arrayLine[4]);
                empleados.add(empleado);    
            }
        } catch (IOException e) {
            return new HashSet<>();
        }
        return empleados;
    }

    
    /**
     * Metodo que permite buscar un empleado dentro del fichero
     * @param empleado
     * @return
     */
    @Override
    public Empleado read(String identificador) {
        if (identificador == null || identificador.isEmpty()) {
            return null;
        }
        Empleado empleado = new Empleado(identificador);
        return read(empleado);
    }

    /**
     * Metodo que permite buscar un empleado dentro del fichero
     * @param empleado
     * @return
     */
    @Override
    public Empleado read(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null) {
            return null;            
        }
        Set<Empleado> empleados = read(fichero);
        for (Empleado empleadoBuscada : empleados) {
            if (empleadoBuscada.equals(empleado)) {
                return empleadoBuscada;
            }
        }
        return empleado;
    }

    
    /**
     * Metodo que permite modificar una empleado dentro del fichero
     * @param empleado
     * @return
     */
    @Override
    public boolean update(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null) {
            return false;
        }
        Set<Empleado> empleados = read(fichero);
        if (!empleados.contains(empleado)) {
            return false;
        }
        empleados.remove(empleado);
        empleados.add(empleado);
        return updateFile(empleados, fichero);
    }

    private boolean updateFile(Set<Empleado> empleados, File file){
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            return false;
        }
        for (Empleado Empleado : empleados) {
            create(Empleado);
        }
        return true;
    }

    /**
     * Metodo que permite buscar a todos los empleados que tenga un puesto especifico dentro del fichero
     * @param empleado
     * @return
     */
    @Override
    public Set<Empleado> empleadosPorPuesto(String puesto) {
        Set<Empleado> empleados = new HashSet<>();
        if (puesto == null || puesto.isEmpty()) {
            return empleados;
        }
        Set<Empleado> listaEmpleados = read(fichero);
        for (Empleado i : listaEmpleados) {
            if (i.getPuesto().equals(puesto)) {
                empleados.add(i);
            }
        }
        return empleados;
    }

    /**
     * Metodo que permite buscar a todos los empleados que hallan nacido entre dos fechas dentro del fichero
     * @param empleado
     * @return
     */
    @Override
    public Set<Empleado> empleadosPorEdad(String fechaInicio, String fechaFin) {
        Set<Empleado> empleados = new HashSet<>();
        if (fechaInicio == null || fechaInicio.isEmpty() || fechaFin == null || fechaFin.isEmpty()) {
            return empleados;
        }
        Set<Empleado> listaEmpleados = read(fichero);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd\\MM\\yyyy");

        LocalDate primeraFecha = LocalDate.parse(fechaInicio, formato);
        LocalDate segundaFecha = LocalDate.parse(fechaFin, formato);
        LocalDate fechaEmpleado = LocalDate.now();
        for (Empleado i : listaEmpleados) {
            fechaEmpleado = LocalDate.parse(i.getFechaNacimiento(), formato);
            if (primeraFecha.isBefore(fechaEmpleado) && segundaFecha.isAfter(fechaEmpleado)) {
                empleados.add(i);
            }
        }
        return empleados;
    }  
}
