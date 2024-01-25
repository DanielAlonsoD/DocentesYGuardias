package tablas;

import java.time.LocalDateTime;

public class Ausencia {
    private int id;
    private String dniProfesor;
    private String razon;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    public Ausencia(int id, String dniProfesor, String razon, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        this.id = id;
        this.dniProfesor = dniProfesor;
        this.razon = razon;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
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

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }
}
