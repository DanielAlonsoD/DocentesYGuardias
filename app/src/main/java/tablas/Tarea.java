package tablas;

import java.time.LocalDate;

public class Tarea {
    private int id;
    private String dniProfesor;
    private String tipoTarea;
    private String tarea;
    private LocalDate fechaFin;
    private boolean realizado;

    public Tarea(int id, String dniProfesor, String tipoTarea, String tarea, LocalDate fechaFin, boolean realizado) {
        this.id = id;
        this.dniProfesor = dniProfesor;
        this.tipoTarea = tipoTarea;
        this.tarea = tarea;
        this.fechaFin = fechaFin;
        this.realizado = realizado;
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

    public String getTipoTarea() {
        return tipoTarea;
    }

    public void setTipoTarea(String tipoTarea) {
        this.tipoTarea = tipoTarea;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }
}
