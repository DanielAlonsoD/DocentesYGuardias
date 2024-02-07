package tablas;

import java.time.LocalDate;

/**
 * @author Daniel Alonso
 */
public class Permiso {
    private int id;
    private String dniProfesor;
    private String tipo;
    private String permiso;
    private String estado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Permiso(int id, String dniProfesor, String tipo, String permiso, String estado, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.dniProfesor = dniProfesor;
        this.tipo = tipo;
        this.permiso = permiso;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDniProfesor() {
        return dniProfesor;
    }

    public void setDniProfesor(String dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
