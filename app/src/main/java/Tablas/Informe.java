package Tablas;

public class Informe {
    private int id;
    private String dniProfesor;
    private int guardia;
    private String descripcion;

    public Informe(int id, String dniProfesor, int guardia, String descripcion) {
        this.id = id;
        this.dniProfesor = dniProfesor;
        this.guardia = guardia;
        this.descripcion = descripcion;
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

    public int getGuardia() {
        return guardia;
    }

    public void setGuardia(int guardia) {
        this.guardia = guardia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
