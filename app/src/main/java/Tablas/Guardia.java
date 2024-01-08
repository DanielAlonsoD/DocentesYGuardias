package Tablas;

import java.time.LocalDateTime;

public class Guardia {
    private int id;
    private String dniProfesor;
    private int ausencia;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    public Guardia(int id, String dniProfesor, int ausencia, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        this.id = id;
        this.dniProfesor = dniProfesor;
        this.ausencia = ausencia;
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

    public int getAusencia() {
        return ausencia;
    }

    public void setAusencia(int ausencia) {
        this.ausencia = ausencia;
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
