package com.example.docentesyguardias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

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
public class RegistroActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private DatabaseReference baseDeDatos;
    private Usuario usuario = new Usuario();
    private EditText textoDNI, textoNombre, textoCorreo, textoCiclo, textoTitulacion, textoContrasena, textoConfirmarContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        baseDeDatos = FirebaseDatabase.getInstance().getReference().child("usuarios");

        MaterialToolbar encabezado = findViewById(R.id.encabezadoRegistro);
        textoDNI = findViewById(R.id.textoInsertarDNIRegistro);
        textoNombre = findViewById(R.id.textoInsertarNombreRegistro);
        textoCorreo = findViewById(R.id.textoInsertarCorreoRegistro);
        Spinner spinnerTipoProfesor = findViewById(R.id.spinnerTipoProfesorRegistro);
        textoCiclo = findViewById(R.id.textoInsertarCicloRegistro);
        textoTitulacion = findViewById(R.id.textoInsertarTitulacionRegistro);
        textoContrasena = findViewById(R.id.textoInsertarContrasenaRegistro);
        textoConfirmarContrasena = findViewById(R.id.textoInsertarConfirmarContrasenaRegistro);
        MaterialButton botonRegistrarse = findViewById(R.id.botonRegistrarse);

        String[] tipoProfesores = {"Selecciona uno", "Docente", "Coordinador", "Jefe de Estudios"};
        spinnerTipoProfesor.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipoProfesores));

        encabezado.setNavigationOnClickListener(this);
        spinnerTipoProfesor.setOnItemSelectedListener(this);
        botonRegistrarse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        LinearLayout layout = findViewById(R.id.layoutRegistro);
        if (v.getId() == R.id.botonRegistrarse) {
            String dni = textoDNI.getText().toString().trim();
            String nombre = textoNombre.getText().toString().trim();
            String correo = textoCorreo.getText().toString().trim();
            String ciclo = textoCiclo.getText().toString().trim();
            String titulacion = textoTitulacion.getText().toString().trim();
            String contrasena = textoContrasena.getText().toString();
            String confirmarContrasena = textoConfirmarContrasena.getText().toString();

            if (dni.isEmpty() || nombre.isEmpty() || correo.isEmpty() || titulacion.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty()) {
                Snackbar.make(layout, R.string.errorTextosVacíos, Snackbar.LENGTH_SHORT).show();
            } else if (usuario.getTipoProfesor().equals("Selecciona uno")) {
                Snackbar.make(layout, R.string.errorTipoProfesorNoInsertado, Snackbar.LENGTH_SHORT).show();
            } else if (contrasena.equals(confirmarContrasena)) {
                baseDeDatos.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean repetido = false;
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String dniBBDD = ds.child("dNI").getValue(String.class);
                            String correoBBDD = ds.child("correo").getValue(String.class);
                            Log.i("Entrado", "Ha entrado aquí");
                            if(dniBBDD.equals(dni) || correoBBDD.equals(correo)){
                                Log.i("Repetido", "Está repetido");
                                repetido = true;
                            }
                        }

                        if (repetido == true) {
                            Snackbar.make(layout, R.string.errorUsuarioExistente, Snackbar.LENGTH_SHORT).show();
                        } else {
                            usuario.setdNI(dni);
                            usuario.setNombre(nombre);
                            usuario.setCorreo(correo);
                            usuario.setCiclo(ciclo);
                            usuario.setTitulacion(titulacion);
                            usuario.setContrasena(contrasena);
                            baseDeDatos.child(dni).setValue(usuario);

                            Bundle datos = new Bundle();
                            datos.putInt("navegacionMenu", 1);
                            datos.putString("dni", dni);
                            Intent actividadMenuPrincipal = new Intent(RegistroActivity.this, MenuPrincipalActivity.class);
                            actividadMenuPrincipal.putExtras(datos);
                            startActivity(actividadMenuPrincipal);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
            } else {
                Snackbar.make(layout, R.string.errorContrasenaYConfirmacion, Snackbar.LENGTH_SHORT).show();
            }
        } else {
            Intent actividadMain = new Intent(RegistroActivity.this, MainActivity.class);
            startActivity(actividadMain);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        usuario.setTipoProfesor(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}