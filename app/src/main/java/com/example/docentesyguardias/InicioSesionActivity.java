package com.example.docentesyguardias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

public class InicioSesionActivity extends AppCompatActivity implements View.OnClickListener {
    Bundle usuario1;
    Bundle usuario2;
    Bundle usuario3;
    EditText textoCorreo;
    EditText textoContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        usuario1 = new Bundle();
        usuario1.putString("DNI", "78945367G");
        usuario1.putString("Nombre", "Pepe");
        usuario1.putString("Correo", "pepe.sangor@sanviatorvalladolid.com");
        usuario1.putString("TipoProfesor", "Docente");
        usuario1.putString("Titulacion", "Graduado en Tecnologías de la Información");
        usuario1.putString("Contrasena", "123456");

        usuario2 = new Bundle();
        usuario2.putString("DNI", "78269292Q");
        usuario2.putString("Nombre", "Pepa");
        usuario2.putString("Correo", "pepa.margar@sanviatorvalladolid.com");
        usuario2.putString("TipoProfesor", "Coordinador");
        usuario2.putString("Titulacion", "Graduado en Ciencias Sociales");
        usuario2.putString("Contrasena", "123456");

        usuario3 = new Bundle();
        usuario3.putString("DNI", "79967541T");
        usuario3.putString("Nombre", "Pedro");
        usuario3.putString("Correo", "pedro.sanmar@sanviatorvalladolid.com");
        usuario3.putString("TipoProfesor", "Jefe De Estudios");
        usuario3.putString("Titulacion", "Graduado en Administración Pública");
        usuario3.putString("Contrasena", "123456");

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
            Bundle[] usuarios = {usuario1, usuario2, usuario3};
            String correo = textoCorreo.getText().toString();
            String contrasena = textoContrasena.getText().toString();
            boolean realizado = false;

            for (Bundle usuario:usuarios) {
                if (usuario.getString("Correo").equals(correo) && usuario.getString("Contrasena").equals(contrasena)) {
                    Intent actividadMenuPrincipal = new Intent(this, MenuPrincipalActivity.class);
                    startActivity(actividadMenuPrincipal, usuario);
                    realizado = true;
                }
            }

            if (!realizado) {
                LinearLayout linearPadre = (LinearLayout) findViewById(R.id.layoutInicioSesion);
                Snackbar mensajeFallo = Snackbar.make(linearPadre, R.string.textoFalloInsertarDatosInicioSesion, Snackbar.LENGTH_SHORT);
                mensajeFallo.show();
            }
        } else {
            Intent actividadMain = new Intent(this, MainActivity.class);
            startActivity(actividadMain);
        }
    }
}