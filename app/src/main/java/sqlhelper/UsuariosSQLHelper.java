package sqlhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UsuariosSQLHelper extends SQLiteOpenHelper {
    private String crearTablaUsuario = "create table usuario(dni text primary key, nombre text, correo text, tipoProfesor text, ciclo text, titulacion text, contrasena text)";
    private String borrarTablaUsuario = "drop table if exists usuario";

    public UsuariosSQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crearTablaUsuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(borrarTablaUsuario);
        db.execSQL(crearTablaUsuario);
    }
}
