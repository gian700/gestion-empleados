package es.ies.puerto.model;

import java.util.Set;

public interface OperacionesInterfaces {
    
    public boolean create(Empleado empleado);
    public boolean update(Empleado empleado);
    public boolean delete(String identificador);
    public Empleado read(Empleado empleado);
    public Empleado read(String identificador);
    public Set<Empleado> empleadosPorPuesto(String puesto);
    public Set<Empleado> empleadosPorEdad(String fechaInicio, String fechaFin);
}
