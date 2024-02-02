package tablas;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class Reunion implements Parcelable {
    private int id;
    private String dniProfesor;
    private String asistencia;
    private String fechaHora;

    public Reunion() {
    }

    public Reunion(int id, String dniProfesor, String asistencia, String fechaHora) {
        this.id = id;
        this.dniProfesor = dniProfesor;
        this.asistencia = asistencia;
        this.fechaHora = fechaHora;
    }

    protected Reunion(Parcel in) {
        id = in.readInt();
        dniProfesor = in.readString();
        asistencia = in.readString();
        fechaHora = in.readString();
    }

    public static final Creator<Reunion> CREATOR = new Creator<Reunion>() {
        @Override
        public Reunion createFromParcel(Parcel in) {
            return new Reunion(in);
        }

        @Override
        public Reunion[] newArray(int size) {
            return new Reunion[size];
        }
    };

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

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(dniProfesor);
        dest.writeString(asistencia);
        dest.writeString(fechaHora);
    }
}
