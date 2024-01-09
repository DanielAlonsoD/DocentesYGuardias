package com.example.docentesyguardias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

import Tablas.Profesor;

public class InicioSesionActivity extends AppCompatActivity implements View.OnClickListener {
    private Profesor profesorPrueba1 = new Profesor("78945367G", "Pepe", "pepe.sangor@sanviatorvalladolid.com", "Docente", "Técnico Informático", "123456");
    private Profesor profesorPrueba2 = new Profesor("78269292Q", "Pepa", "pepa.margar@sanviatorvalladolid.com", "Coordinador", "Ciencias Sociales", "123456");
    private Profesor profesorPrueba3 = new Profesor("79967541T", "Pedro", "pedro.sanmar@sanviatorvalladolid.com", "Jefe de Estudios", "Administración Pública", "123456");
    private EditText textoCorreo;
    private EditText textoContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        MaterialToolbar encabezado = findViewById(R.id.encabezadoInicioSesión);
        textoCorreo = findViewById(R.id.textoInsertarCorreoInicioSesion);
        textoContrasena = findViewById(R.id.textoInsertarContrasenaInicioSesion);
        Button botonInicioSesion = findViewById(R.id.botonIniciarSesionInicioSesion);

        encabezado.setNavigationOnClickListener(this);
        botonInicioSesion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.botonIniciarSesionInicioSesion) {
            String correo = textoCorreo.getText().toString();
            String contrasena = textoContrasena.getText().toString();

            if (correo.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, R.string.errorTextosVacíos, Toast.LENGTH_SHORT).show();
            } else {
                Profesor[] profesoresInicio = {profesorPrueba1, profesorPrueba2, profesorPrueba3};
                boolean realizado = false;

                for (Profesor profesor:profesoresInicio) {
                    if (profesor.getCorreo().equals(correo) && profesor.getContrasena().equals(contrasena)) {
                        Bundle usuario = new Bundle();
                        usuario.putParcelable("profesor", profesor);
                        Intent actividadMenuPrincipal = new Intent(this, MenuPrincipalActivity.class);
                        actividadMenuPrincipal.putExtras(usuario);
                        startActivity(actividadMenuPrincipal);
                        realizado = true;
                    }
                }

                if (!realizado) {
                    Toast.makeText(this, R.string.textoFalloInsertarDatosInicioSesion, Toast.LENGTH_SHORT).show();
                }
            }

        } else {
            Intent actividadMain = new Intent(this, MainActivity.class);
            startActivity(actividadMain);
        }
    }
}