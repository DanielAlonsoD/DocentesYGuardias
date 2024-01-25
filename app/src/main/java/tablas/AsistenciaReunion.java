package tablas;

public class AsistenciaReunion {
    private int id;
    private int reunion;
    private String dniProfesor;
    private boolean asistencia;

    public AsistenciaReunion(int id, int reunion, String dniProfesor, boolean asistencia) {
        this.id = id;
        this.reunion = reunion;
        this.dniProfesor = dniProfesor;
        this.asistencia = asistencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReunion() {
        return reunion;
    }

    public void setReunion(int reunion) {
        this.reunion = reunion;
    }

    public String getDniProfesor() {
        return dniProfesor;
    }

    public void setDniProfesor(String dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    public boolean isAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }
}
