package tablas;

/**
 * @author Daniel Alonso
 */
public class Ciclo {
    private int id;
    private String dniProfesor;
    private String nombreCiclo;

    public Ciclo(int id, String dniProfesor, String nombreCiclo) {
        this.id = id;
        this.dniProfesor = dniProfesor;
        this.nombreCiclo = nombreCiclo;
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

    public String getNombreCiclo() {
        return nombreCiclo;
    }

    public void setNombreCiclo(String nombreCiclo) {
        this.nombreCiclo = nombreCiclo;
    }
}
