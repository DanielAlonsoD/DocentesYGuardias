package com.example.docentesyguardias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import tablas.Usuario;

/**
 * @author Daniel Alonso
 */
public class EditarPerfilActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Bundle usuario;
    private Usuario usuarioDatos;
    private String tipoProfesor;
    private EditText textoDNI;
    private EditText textoNombre;
    private EditText textoCorreo;
    private EditText textoTitulacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        usuario = getIntent().getExtras();
        usuarioDatos = usuario.getParcelable("profesor");

        MaterialToolbar encabezado = findViewById(R.id.encabezadoEditarPerfil);
        Spinner spinnerTipoProfesor = findViewById(R.id.spinnerTipoProfesorEditarPerfil);
        textoDNI = findViewById(R.id.textoInsertarDNIEditarPerfil);
        textoNombre = findViewById(R.id.textoInsertarNombreEditarPerfil);
        textoCorreo = findViewById(R.id.textoInsertarCorreoEditarPerfil);
        textoTitulacion = findViewById(R.id.textoInsertarTitulacionEditarPerfil);
        FloatingActionButton botonRealizado = findViewById(R.id.botonRealizadoEditarPerfil);

        String[] tipoProfesores = new String[2];
        switch (usuarioDatos.getTipoProfesor()) {
            case "Docente":
                tipoProfesores = new String[]{"Docente", "Coordinador", "Jefe de Estudios"};
                break;
            case "Coordinador":
                tipoProfesores = new String[]{"Coordinador", "Docente", "Jefe de Estudios"};
                break;
            case "Jefe de Estudios":
                tipoProfesores = new String[]{"Jefe de Estudios", "Docente", "Coordinador"};
                break;
        }
        spinnerTipoProfesor.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipoProfesores));

        textoDNI.setText(usuarioDatos.getdNI());
        textoNombre.setText(usuarioDatos.getNombre());
        textoCorreo.setText(usuarioDatos.getCorreo());
        textoTitulacion.setText(usuarioDatos.getTitulacion());

        encabezado.setNavigationOnClickListener(this);
        spinnerTipoProfesor.setOnItemSelectedListener(this);
        botonRealizado.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent actividadPerfil = new Intent(EditarPerfilActivity.this, PerfilActivity.class);
        if (v.getId() == R.id.botonRealizadoEditarPerfil) {
            String dni = textoDNI.getText().toString();
            String nombre = textoNombre.getText().toString();
            String correo = textoCorreo.getText().toString();
            String titulacion = textoTitulacion.getText().toString();
            if (dni.isEmpty() || nombre.isEmpty() || correo.isEmpty() || titulacion.isEmpty()) {
                RelativeLayout layout = findViewById(R.id.layoutEditarPerfil);
                Snackbar.make(layout, R.string.errorTextosVacíos, Snackbar.LENGTH_SHORT).show();
            } else {
                usuarioDatos.setdNI(dni);
                usuarioDatos.setNombre(nombre);
                usuarioDatos.setCorreo(correo);
                if (!tipoProfesor.isEmpty()) {
                    usuarioDatos.setTipoProfesor(tipoProfesor);
                }
                usuarioDatos.setTitulacion(titulacion);
                //usuario.putParcelable("profesor", usuarioDatos);
                actividadPerfil.putExtras(usuario);
                startActivity(actividadPerfil);
            }
        } else {
            actividadPerfil.putExtras(usuario);
            startActivity(actividadPerfil);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tipoProfesor = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}