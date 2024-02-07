package com.example.docentesyguardias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import tablas.Profesor;

/**
 * @author Daniel Alonso
 */
public class PerfilActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        usuario = getIntent().getExtras();
        Profesor profesor = usuario.getParcelable("profesor");

        MaterialToolbar encabezado = findViewById(R.id.encabezadoPerfil);
        TextView textoTipoProfesor = findViewById(R.id.textoTipoProfesorPerfil);
        TextView textoDNI = findViewById(R.id.textoDNIPerfil);
        TextView textoNombre = findViewById(R.id.textoNombrePerfil);
        TextView textoCorreo = findViewById(R.id.textoCorreoPerfil);
        TextView textoTitulacion = findViewById(R.id.textoTitulacionPerfil);
        FloatingActionButton botonEditarPerfil = findViewById(R.id.botonEditarPerfil);
        ExtendedFloatingActionButton botonCambiarContrasena = findViewById(R.id.botonCambiarContrasena);

        textoTipoProfesor.setText(profesor.getTipoProfesor());
        textoDNI.setText(profesor.getdNI());
        textoNombre.setText(profesor.getNombre());
        textoCorreo.setText(profesor.getCorreo());
        textoTitulacion.setText(profesor.getTitulacion());

        encabezado.setNavigationOnClickListener(this);
        botonEditarPerfil.setOnClickListener(this);
        botonCambiarContrasena.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.botonEditarPerfil) {
            Intent actividadEditarPerfil = new Intent(PerfilActivity.this, EditarPerfilActivity.class);
            actividadEditarPerfil.putExtras(usuario);
            startActivity(actividadEditarPerfil);
        } else if (v.getId() == R.id.botonCambiarContrasena) {
            Intent actividadCambiarContrasena = new Intent(PerfilActivity.this, CambiarContrasenaActivity.class);
            actividadCambiarContrasena.putExtras(usuario);
            startActivity(actividadCambiarContrasena);
        } else {
            Intent actividadMenuPrincipal = new Intent(PerfilActivity.this, MenuPrincipalActivity.class);
            actividadMenuPrincipal.putExtras(usuario);
            startActivity(actividadMenuPrincipal);
        }
    }
}