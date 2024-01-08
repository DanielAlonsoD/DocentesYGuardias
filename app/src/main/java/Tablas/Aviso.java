package Tablas;

import java.time.LocalDateTime;

public class Aviso {
    private int id;
    private String dniProfesorEmisor;
    private String dniProfesorReceptor;
    private LocalDateTime fechaHora;
    private String mensaje;

    public Aviso(int id, String dniProfesorEmisor, String dniProfesorReceptor, LocalDateTime fechaHora, String mensaje) {
        this.id = id;
        this.dniProfesorEmisor = dniProfesorEmisor;
        this.dniProfesorReceptor = dniProfesorReceptor;
        this.fechaHora = fechaHora;
        this.mensaje = mensaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDniProfesorEmisor() {
        return dniProfesorEmisor;
    }

    public void setDniProfesorEmisor(String dniProfesorEmisor) {
        this.dniProfesorEmisor = dniProfesorEmisor;
    }

    public String getDniProfesorReceptor() {
        return dniProfesorReceptor;
    }

    public void setDniProfesorReceptor(String dniProfesorReceptor) {
        this.dniProfesorReceptor = dniProfesorReceptor;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
