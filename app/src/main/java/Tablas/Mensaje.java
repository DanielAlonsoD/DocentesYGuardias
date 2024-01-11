package Tablas;

import java.time.LocalDate;

public class Mensaje {
    private int id;
    private String dniProfesorEmisor;
    private String dniProfesorReceptor;
    private String titulo;
    private LocalDate fecha;
    private String mensaje;

    public Mensaje(int id, String dniProfesorEmisor, String dniProfesorReceptor, String titulo, LocalDate fecha, String mensaje) {
        this.id = id;
        this.dniProfesorEmisor = dniProfesorEmisor;
        this.dniProfesorReceptor = dniProfesorReceptor;
        this.titulo = titulo;
        this.fecha = fecha;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
