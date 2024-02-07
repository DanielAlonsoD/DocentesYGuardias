package com.example.docentesyguardias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    private Usuario usuarioPrueba1 = new Usuario("78945367G", "Pepe", "pepe.sangor@sanviatorvalladolid.com", "Docente", "Técnico Informático", "123456");
    private Usuario usuarioPrueba2 = new Usuario("78269292Q", "Pepa", "pepa.margar@sanviatorvalladolid.com", "Coordinador", "Ciencias Sociales", "123456");
    private Usuario usuarioPrueba3 = new Usuario("79967541T", "Pedro", "pedro.sanmar@sanviatorvalladolid.com", "Jefe de Estudios", "Administración Pública", "123456");
    private EditText textoCorreo;
    private EditText textoContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        baseDeDatos = FirebaseDatabase.getInstance().getReference().child("usuario");

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

                baseDeDatos.orderByChild("correo").equalTo(correo).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                String contrasenaBBDD = dataSnapshot.child("contrasena").getValue(String.class);

                                if (contrasenaBBDD.equals(contrasena)) {
                                    Snackbar.make(layout, "Funciono", Snackbar.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
                /*Usuario[] profesoresInicio = {usuarioPrueba1, usuarioPrueba2, usuarioPrueba3};
                boolean realizado = false;

                for (Usuario profesor:profesoresInicio) {
                    if (profesor.getCorreo().equals(correo) && profesor.getContrasena().equals(contrasena)) {
                        Bundle usuario = new Bundle();
                        usuario.putParcelable("profesor", profesor);
                        usuario.putInt("navegacionMenu", 1);
                        Intent actividadMenuPrincipal = new Intent(this, MenuPrincipalActivity.class);
                        actividadMenuPrincipal.putExtras(usuario);
                        startActivity(actividadMenuPrincipal);
                        realizado = true;
                    }
                }

                if (!realizado) {
                    Snackbar mensaje = Snackbar.make(layout, R.string.errorInsertarDatosInicioSesion, Snackbar.LENGTH_INDEFINITE);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                    mensaje.show();
                }*/
            }

        } else {
            Intent actividadMain = new Intent(this, MainActivity.class);
            startActivity(actividadMain);
        }
    }
}