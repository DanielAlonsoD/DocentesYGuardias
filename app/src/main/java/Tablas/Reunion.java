package Tablas;

import java.time.LocalDateTime;

public class Reunion {
    private int id;
    private String dniProfesor;
    private LocalDateTime fechaHora;

    public Reunion(int id, String dniProfesor, LocalDateTime fechaHora) {
        this.id = id;
        this.dniProfesor = dniProfesor;
        this.fechaHora = fechaHora;
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

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
