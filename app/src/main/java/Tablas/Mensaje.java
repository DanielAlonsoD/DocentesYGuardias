package Tablas;

import java.time.LocalDate;

public class Mensaje {
    private int id;
    private String dniProfesorEmisor;
    private String dniProfesorReceptor;
    private String mensaje;
    private LocalDate fecha;

    public Mensaje(int id, String dniProfesorEmisor, String dniProfesorReceptor, String mensaje, LocalDate fecha) {
        this.id = id;
        this.dniProfesorEmisor = dniProfesorEmisor;
        this.dniProfesorReceptor = dniProfesorReceptor;
        this.mensaje = mensaje;
        this.fecha = fecha;
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
