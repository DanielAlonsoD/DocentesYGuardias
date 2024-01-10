package com.example.docentesyguardias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import Tablas.Profesor;

public class CambiarContrasenaActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle usuario;
    private Profesor profesor;
    private EditText textoViejaContrasena;
    private EditText textoNuevaContrasena;
    private EditText textoConfirmarContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contrasena);

        usuario = getIntent().getExtras();
        profesor = usuario.getParcelable("profesor");

        MaterialToolbar encabezado = findViewById(R.id.encabezadoCambiarContrasena);
        textoViejaContrasena = findViewById(R.id.textoInsertarContrasenaCambiarContrasena);
        textoNuevaContrasena = findViewById(R.id.textoInsertarNuevaContrasenaCambiarContrasena);
        textoConfirmarContrasena = findViewById(R.id.textoInsertarConfirmarContrasenaCambiarContrasena);
        FloatingActionButton botonRealizado = findViewById(R.id.botonRealizadoCambiarContrasena);

        encabezado.setNavigationOnClickListener(this);
        botonRealizado.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent actividadPerfil = new Intent(CambiarContrasenaActivity.this, PerfilActivity.class);
        RelativeLayout layout = findViewById(R.id.layoutCambiarContrasena);
        if (v.getId() == R.id.botonRealizadoCambiarContrasena) {
            String viejaContrasena = textoViejaContrasena.getText().toString();
            String nuevaContrasena = textoNuevaContrasena.getText().toString();
            String confirmarContrasena = textoConfirmarContrasena.getText().toString();
            if (viejaContrasena.isEmpty() || nuevaContrasena.isEmpty() || confirmarContrasena.isEmpty()) {
                Snackbar.make(layout, R.string.errorTextosVacíos, Snackbar.LENGTH_SHORT).show();
            } else if (!profesor.getContrasena().equals(viejaContrasena)) {
                Snackbar.make(layout, R.string.errorContrasenaIncorrecta, Snackbar.LENGTH_SHORT).show();
            } else if (nuevaContrasena.equals(confirmarContrasena)) {
                profesor.setContrasena(nuevaContrasena);
                usuario.putParcelable("profesor", profesor);
                actividadPerfil.putExtras(usuario);
                startActivity(actividadPerfil);
            } else {
                Snackbar.make(layout, R.string.errorNuevaContrasenaYConfirmacion, Snackbar.LENGTH_SHORT).show();
            }
        } else {
            actividadPerfil.putExtras(usuario);
            startActivity(actividadPerfil);
        }
    }
}