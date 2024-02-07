package tablas;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalDate;

/**
 * @author Daniel Alonso
 */
public class Mensaje implements Parcelable {
    private int id;
    private String dniProfesorEmisor;
    private String dniProfesorReceptor;
    private String titulo;
    private String mensaje;
    private String fecha;

    public Mensaje(int id, String dniProfesorEmisor, String dniProfesorReceptor, String titulo, String mensaje, String fecha) {
        this.id = id;
        this.dniProfesorEmisor = dniProfesorEmisor;
        this.dniProfesorReceptor = dniProfesorReceptor;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    protected Mensaje(Parcel in) {
        id = in.readInt();
        dniProfesorEmisor = in.readString();
        dniProfesorReceptor = in.readString();
        titulo = in.readString();
        mensaje = in.readString();
        fecha = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(dniProfesorEmisor);
        dest.writeString(dniProfesorReceptor);
        dest.writeString(titulo);
        dest.writeString(mensaje);
        dest.writeString(fecha);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Mensaje> CREATOR = new Creator<Mensaje>() {
        @Override
        public Mensaje createFromParcel(Parcel in) {
            return new Mensaje(in);
        }

        @Override
        public Mensaje[] newArray(int size) {
            return new Mensaje[size];
        }
    };

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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
