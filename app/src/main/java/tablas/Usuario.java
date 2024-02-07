package tablas;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * @author Daniel Alonso
 */
public class Usuario {
    private String dNI;
    private String nombre;
    private String correo;
    private String tipoProfesor;
    private String titulacion;
    private String contrasena;

    public Usuario() {
    }

    public Usuario(String dNI, String nombre, String correo, String tipoProfesor, String titulacion, String contrasena) {
        this.dNI = dNI;
        this.nombre = nombre;
        this.correo = correo;
        this.tipoProfesor = tipoProfesor;
        this.titulacion = titulacion;
        this.contrasena = contrasena;
    }

    public String getdNI() {
        return dNI;
    }

    public void setdNI(String dNI) {
        this.dNI = dNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipoProfesor() {
        return tipoProfesor;
    }

    public void setTipoProfesor(String tipoProfesor) {
        this.tipoProfesor = tipoProfesor;
    }

    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
