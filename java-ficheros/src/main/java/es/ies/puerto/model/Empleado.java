package es.ies.puerto.model;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Empleado {
    String identificador;
    String nombre;
    String puesto;
    double salario;
    String fechaNacimiento;

    /**
     * constructor vacio
     * @return
     */
    public Empleado() {
    }

    /**
     * construtor generico
     * @param identificador
     * @param nombre
     * @param puesto
     * @param salario
     * @param fechaNacimiento
     */
    public Empleado(String identificador, String nombre, String puesto, double salario, String fechaNacimiento) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * constructor solo identificador
     * @param identificador
     */
    public Empleado(String identificador) {
        this.identificador = identificador;
        
    }

    /**
     * getters y setters
     * @return
     */
    public String getIdentificador() {
        return this.identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return this.puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario() {
        return this.salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    
    /**
     * equal, hashcode y toString
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Empleado)) {
            return false;
        }
        Empleado empleado = (Empleado) o;
        return Objects.equals(identificador, empleado.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }

    @Override
    public String toString() {
        return 
            getIdentificador() + ", " +
            getNombre() + ", " +
            getPuesto() + ", " +
            getSalario() + ", " +
            getFechaNacimiento();
    }

    /**
     * metodo que devuelve la edad del empleado
     * @param empleado
     * @return
     */
    public int getEdad(Empleado empleado){
        if (empleado == null || empleado.getFechaNacimiento() == null || empleado.getFechaNacimiento().isEmpty()) {
            return -1;
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd\\MM\\yyyy");
        LocalDate localDate = LocalDate.parse(empleado.getFechaNacimiento(), formato);
        if (localDate.isBefore(LocalDate.now())) {
            return -1;
        }
        Period periodo = Period.between(localDate, LocalDate.now());
        return periodo.getYears();
    }
}
