package Modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Contacto implements Serializable {
    private static final long serialVersionUID = 1L; // Para serialización
    private String nombre;
    private long telefono;
    private String correoElectronico;
    private LocalDate fechaNacimiento;

    public Contacto(String nombre, long telefono, String correoElectronico, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public long getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
