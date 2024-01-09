package com.example.docentesyguardias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import Tablas.Profesor;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Profesor profesor = new Profesor();
    private EditText textoDNI;
    private EditText textoNombre;
    private EditText textoCorreo;
    private EditText textoTitulacion;
    private EditText textoContrasena;
    private EditText textoConfirmarContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        MaterialToolbar encabezado = findViewById(R.id.encabezadoRegistro);
        textoDNI = findViewById(R.id.textoInsertarDNIRegistro);
        textoNombre = findViewById(R.id.textoInsertarNombreRegistro);
        textoCorreo = findViewById(R.id.textoInsertarCorreoRegistro);
        Spinner spinnerTipoProfesor = findViewById(R.id.spinnerTipoProfesorRegistro);
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
        if (v.getId() == R.id.botonRegistrarse) {
            String dni = textoDNI.getText().toString();
            String nombre = textoNombre.getText().toString();
            String correo = textoCorreo.getText().toString();
            String titulacion = textoTitulacion.getText().toString();
            String contrasena = textoContrasena.getText().toString();
            String confirmarContrasena = textoConfirmarContrasena.getText().toString();
            if (dni.isEmpty() || nombre.isEmpty() || correo.isEmpty() || titulacion.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty()) {
                Toast.makeText(this, R.string.errorTextosVacíos, Toast.LENGTH_SHORT).show();
            } else if (profesor.getTipoProfesor().equals("Selecciona uno")) {
                Toast.makeText(this, R.string.errorTipoProfesorNoInsertado, Toast.LENGTH_SHORT).show();
            } else if (contrasena.equals(confirmarContrasena)) {
                profesor.setdNI(dni);
                profesor.setNombre(nombre);
                profesor.setCorreo(correo);
                profesor.setTitulacion(titulacion);
                profesor.setContrasena(contrasena);
                Bundle usuario = new Bundle();
                usuario.putParcelable("profesor", profesor);
                Intent actividadMenuPrincipal = new Intent(RegistroActivity.this, MenuPrincipalActivity.class);
                actividadMenuPrincipal.putExtras(usuario);
                startActivity(actividadMenuPrincipal);
            } else {
                Toast.makeText(this, R.string.errorContrasenaYConfirmación, Toast.LENGTH_SHORT).show();
            }
        } else {
            Intent actividadMain = new Intent(RegistroActivity.this, MainActivity.class);
            startActivity(actividadMain);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        profesor.setTipoProfesor(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}