package com.example.docentesyguardias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import sqlhelper.UsuariosSQLHelper;
import tablas.Usuario;

/**
 * @author Daniel Alonso
 */
public class PerfilActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference baseDeDatos;
    private SQLiteDatabase baseSQLite;
    private Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        baseDeDatos = FirebaseDatabase.getInstance().getReference().child("usuarios");
        datos = getIntent().getExtras();

        UsuariosSQLHelper baseUsuarios = new UsuariosSQLHelper(this, "baseDeUsuario", null, 1);
        baseSQLite = baseUsuarios.getWritableDatabase();

        MaterialToolbar encabezado = findViewById(R.id.encabezadoPerfil);
        TextView textoTipoProfesor = findViewById(R.id.textoTipoProfesorPerfil);
        TextView textoDNI = findViewById(R.id.textoDNIPerfil);
        TextView textoNombre = findViewById(R.id.textoNombrePerfil);
        TextView textoCorreo = findViewById(R.id.textoCorreoPerfil);
        TextView textoCiclo = findViewById(R.id.textoCicloPerfil);
        TextView textoTitulacion = findViewById(R.id.textoTitulacionPerfil);
        FloatingActionButton botonEditarPerfil = findViewById(R.id.botonEditarPerfil);
        ExtendedFloatingActionButton botonCambiarContrasena = findViewById(R.id.botonCambiarContrasena);

        Cursor cursor = baseSQLite.rawQuery("select * from usuario", null);
        if (cursor.moveToFirst()) {
            textoDNI.setText(cursor.getString(0));
            textoNombre.setText(cursor.getString(1));
            textoCorreo.setText(cursor.getString(2));
            textoCiclo.setText(cursor.getString(3));
            textoTitulacion.setText(cursor.getString(4));
        }

        encabezado.setNavigationOnClickListener(this);
        botonEditarPerfil.setOnClickListener(this);
        botonCambiarContrasena.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.botonEditarPerfil) {
            Intent actividadEditarPerfil = new Intent(PerfilActivity.this, EditarPerfilActivity.class);
            actividadEditarPerfil.putExtras(datos);
            startActivity(actividadEditarPerfil);
        } else if (v.getId() == R.id.botonCambiarContrasena) {
            Intent actividadCambiarContrasena = new Intent(PerfilActivity.this, CambiarContrasenaActivity.class);
            actividadCambiarContrasena.putExtras(datos);
            startActivity(actividadCambiarContrasena);
        } else {
            Intent actividadMenuPrincipal = new Intent(PerfilActivity.this, MenuPrincipalActivity.class);
            actividadMenuPrincipal.putExtras(datos);
            startActivity(actividadMenuPrincipal);
        }
    }
}