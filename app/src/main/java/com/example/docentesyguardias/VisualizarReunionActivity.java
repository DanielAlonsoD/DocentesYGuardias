package com.example.docentesyguardias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import tablas.Reunion;

/**
 * @author Daniel Alonso
 */
public class VisualizarReunionActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_reunion);

        usuario = getIntent().getExtras();
        Reunion reunion = (Reunion) usuario.get("reunionSeleccionado");
        usuario.putInt("navegacionMenu", 2);

        MaterialToolbar encabezado = findViewById(R.id.encabezadoVisualizarReunion);
        TextView textoProfesorAnfitrion = findViewById(R.id.textoProfesorAnfitrionVisualizarReunion);
        Spinner spinnerAsistencia = findViewById(R.id.spinnerAsistenciaVisualizarReunion);
        TextView textoFecha = findViewById(R.id.textoFechaVisualiarReunion);
        FloatingActionButton botonRealizado = findViewById(R.id.botonRealizadoVisualizarReunion);

        textoProfesorAnfitrion.setText(reunion.getDniProfesor()+"");
        textoFecha.setText(reunion.getFechaHora());

        String[] opciones = {"Asistiré", "No Sé Si Asistiré", "No Asistiré"};
        spinnerAsistencia.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones));
        spinnerAsistencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        switch(reunion.getAsistencia()) {
            case "Asistiré":
                spinnerAsistencia.setSelection(0);
                break;
            case "No Sé Si Asistiré":
                spinnerAsistencia.setSelection(1);
                break;
            case "No Asistiré":
                spinnerAsistencia.setSelection(2);
                break;
        }

        encabezado.setNavigationOnClickListener(this);
        botonRealizado.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent actividadCalendario = new Intent(this, MenuPrincipalActivity.class);
        usuario.putInt("navegacionMenu", 2);
        actividadCalendario.putExtras(usuario);
        startActivity(actividadCalendario);
    }
}