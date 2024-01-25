package tablas;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Profesor implements Parcelable {
    private String dNI;
    private String nombre;
    private String correo;
    private String tipoProfesor;
    private String titulacion;
    private String contrasena;

    public Profesor() {
    }

    public Profesor(String dNI, String nombre, String correo, String tipoProfesor, String titulacion, String contrasena) {
        this.dNI = dNI;
        this.nombre = nombre;
        this.correo = correo;
        this.tipoProfesor = tipoProfesor;
        this.titulacion = titulacion;
        this.contrasena = contrasena;
    }

    protected Profesor(Parcel in) {
        dNI = in.readString();
        nombre = in.readString();
        correo = in.readString();
        tipoProfesor = in.readString();
        titulacion = in.readString();
        contrasena = in.readString();
    }

    public static final Creator<Profesor> CREATOR = new Creator<Profesor>() {
        @Override
        public Profesor createFromParcel(Parcel in) {
            return new Profesor(in);
        }

        @Override
        public Profesor[] newArray(int size) {
            return new Profesor[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(dNI);
        dest.writeString(nombre);
        dest.writeString(correo);
        dest.writeString(tipoProfesor);
        dest.writeString(titulacion);
        dest.writeString(contrasena);
    }

}
