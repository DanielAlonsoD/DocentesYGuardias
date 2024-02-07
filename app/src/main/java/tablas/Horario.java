package tablas;

import java.time.LocalTime;

/**
 * @author Daniel Alonso
 */
public class Horario {
    private int id;
    private String dniProfesor;
    private LocalTime hora;
    private String diaSemana;
    private String asignatura;
    private boolean libre;

    public Horario(int id, String dniProfesor, LocalTime hora, String diaSemana, String asignatura, boolean libre) {
        this.id = id;
        this.dniProfesor = dniProfesor;
        this.hora = hora;
        this.diaSemana = diaSemana;
        this.asignatura = asignatura;
        this.libre = libre;
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

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public boolean isLibre() {
        return libre;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
    }
}
