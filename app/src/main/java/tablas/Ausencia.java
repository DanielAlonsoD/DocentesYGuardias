package tablas;

import java.time.LocalDateTime;

/**
 * @author Daniel Alonso
 */
public class Ausencia {
    private String dniProfesor;
    private String razon;
    private String fechaHoraInicio;
    private String fechaHoraFin;

    public Ausencia(String dniProfesor, String razon, String fechaHoraInicio, String fechaHoraFin) {
        this.dniProfesor = dniProfesor;
        this.razon = razon;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getDniProfesor() {
        return dniProfesor;
    }

    public void setDniProfesor(String dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(String fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public String getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(String fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }
}
