package com.example.docentesyguardias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import tablas.Usuario;

/**
 * @author Daniel Alonso
 */
public class CambiarContrasenaActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle usuario;
    private Usuario usuarioDatos;
    private EditText textoViejaContrasena;
    private EditText textoNuevaContrasena;
    private EditText textoConfirmarContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contrasena);

        usuario = getIntent().getExtras();
        usuarioDatos = usuario.getParcelable("profesor");

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
            } else if (!usuarioDatos.getContrasena().equals(viejaContrasena)) {
                Snackbar.make(layout, R.string.errorContrasenaIncorrecta, Snackbar.LENGTH_SHORT).show();
            } else if (nuevaContrasena.equals(confirmarContrasena)) {
                usuarioDatos.setContrasena(nuevaContrasena);
                //usuario.put("profesor", usuarioDatos);
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