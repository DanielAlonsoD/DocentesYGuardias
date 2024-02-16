package com.example.docentesyguardias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tablas.Usuario;

/**
 * @author Daniel Alonso
 */
public class InicioSesionActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference baseDeDatos;
    private EditText textoCorreo;
    private EditText textoContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        baseDeDatos = FirebaseDatabase.getInstance().getReference().child("usuarios");

        MaterialToolbar encabezado = findViewById(R.id.encabezadoInicioSesión);
        textoCorreo = findViewById(R.id.textoInsertarCorreoInicioSesion);
        textoContrasena = findViewById(R.id.textoInsertarContrasenaInicioSesion);
        MaterialButton botonInicioSesion = findViewById(R.id.botonIniciarSesionInicioSesion);

        encabezado.setNavigationOnClickListener(this);
        botonInicioSesion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.botonIniciarSesionInicioSesion) {
            LinearLayout layout = findViewById(R.id.layoutInicioSesion);

            String correo = textoCorreo.getText().toString().trim();
            String contrasena = textoContrasena.getText().toString().trim();

            if (correo.isEmpty() || contrasena.isEmpty()) {
                Snackbar.make(layout, R.string.errorTextosVacíos, Snackbar.LENGTH_SHORT).show();
            } else {

                baseDeDatos.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                String dni = ds.child("dNI").getValue(String.class);
                                String correoBBDD = ds.child("correo").getValue(String.class);
                                String contrasenaBBDD = ds.child("contrasena").getValue(String.class);

                                if (correoBBDD.equals(correo) && contrasenaBBDD.equals(contrasena)) {
                                    Bundle datos = new Bundle();
                                    datos.putInt("navegacionMenu", 1);
                                    datos.putString("dni", dni);
                                    Intent actividadMenuPrincipal = new Intent(InicioSesionActivity.this, MenuPrincipalActivity.class);
                                    actividadMenuPrincipal.putExtras(datos);
                                    startActivity(actividadMenuPrincipal);
                                } else {
                                    Snackbar.make(layout, R.string.errorUsuarioNoExistente, Snackbar.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Snackbar.make(layout, R.string.errorUsuarioNoExistente, Snackbar.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
            }

        } else {
            Intent actividadMain = new Intent(this, MainActivity.class);
            startActivity(actividadMain);
        }
    }
}