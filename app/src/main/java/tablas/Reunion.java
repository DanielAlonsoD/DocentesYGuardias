package tablas;

import java.time.LocalDateTime;

public class Reunion {
    private int id;
    private String dniProfesor;
    private String asistencia;
    private LocalDateTime fechaHora;

    public Reunion() {
    }

    public Reunion(int id, String dniProfesor, String asistencia, LocalDateTime fechaHora) {
        this.id = id;
        this.dniProfesor = dniProfesor;
        this.asistencia = asistencia;
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

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
