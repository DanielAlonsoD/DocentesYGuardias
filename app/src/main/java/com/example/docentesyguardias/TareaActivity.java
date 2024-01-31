package com.example.docentesyguardias;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import tablas.Tarea;

public class TareaActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle usuario;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea);

        usuario = getIntent().getExtras();
        Tarea tarea = (Tarea) usuario.get("tareaSeleccionada");

        MaterialToolbar encabezado = findViewById(R.id.encabezadoTarea);
        TextView textoNombre = findViewById(R.id.textoNombreTipoTarea);
        TextView textoDescripcion = findViewById(R.id.textoDescripcionTarea);
        TextView textoFechaFinTarea = findViewById(R.id.textoFechaFinTarea);
        Spinner spinnerTareaRealizada = findViewById(R.id.spinnerTareaRealizada);
        FloatingActionButton botonRealizado = findViewById(R.id.botonRealizadoCambiarTarea);

        textoNombre.setText(tarea.getTipoTarea());
        textoDescripcion.setText(tarea.getTarea());
        textoFechaFinTarea.setText(tarea.getFechaFin());

        String[] opciones = {"No Realizada", "Realizada"};
        if (tarea.isRealizado()) {
            opciones = new String[]{"Realizada", "No Realizada"};
        }
        spinnerTareaRealizada.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones));
        spinnerTareaRealizada.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        encabezado.setNavigationOnClickListener(this);
        botonRealizado.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.botonRealizadoCambiarTarea) {
            Intent actividadAdministrarTareas = new Intent(this, AdministrarTareasActivity.class);
            actividadAdministrarTareas.putExtras(usuario);
            startActivity(actividadAdministrarTareas);
        } else {
            Intent actividadAdministrarTareas = new Intent(this, AdministrarTareasActivity.class);
            actividadAdministrarTareas.putExtras(usuario);
            startActivity(actividadAdministrarTareas);
        }
    }
}