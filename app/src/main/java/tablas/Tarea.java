package tablas;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.time.LocalDate;

/**
 * @author Daniel Alonso
 */
public class Tarea implements Parcelable {
    private int id;
    private String dniProfesor;
    private String tipoTarea;
    private String tarea;
    private String fechaFin;
    private boolean realizado;

    public Tarea(int id, String dniProfesor, String tipoTarea, String tarea, String fechaFin, boolean realizado) {
        this.id = id;
        this.dniProfesor = dniProfesor;
        this.tipoTarea = tipoTarea;
        this.tarea = tarea;
        this.fechaFin = fechaFin;
        this.realizado = realizado;
    }

    protected Tarea(Parcel in) {
        id = in.readInt();
        dniProfesor = in.readString();
        tipoTarea = in.readString();
        tarea = in.readString();
        fechaFin = in.readString();
        realizado = in.readByte() != 0;
    }

    public static final Creator<Tarea> CREATOR = new Creator<Tarea>() {
        @Override
        public Tarea createFromParcel(Parcel in) {
            return new Tarea(in);
        }

        @Override
        public Tarea[] newArray(int size) {
            return new Tarea[size];
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

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(dniProfesor);
        dest.writeString(tipoTarea);
        dest.writeString(tarea);
        dest.writeString(fechaFin);
        dest.writeByte((byte) (realizado ? 1 : 0));
    }
}
