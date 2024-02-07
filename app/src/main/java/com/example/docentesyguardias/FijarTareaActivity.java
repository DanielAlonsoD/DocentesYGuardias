package com.example.docentesyguardias;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * @author Daniel Alonso
 */
public class FijarTareaActivity extends AppCompatActivity implements View.OnClickListener{
    private Bundle usuario;
    private TextView textoFechaEntrega;
    private EditText textoDescripcion;
    String ciclo, profesor, tarea;
    private String[] profesores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fijar_tarea);

        usuario = getIntent().getExtras();

        MaterialToolbar encabezado = findViewById(R.id.encabezadoFijarTarea);
        Spinner spinnerCiclo = findViewById(R.id.spinnerCicloFijarTarea);
        Spinner spinnerProfesor = findViewById(R.id.spinnerProfesorFijarTarea);
        Spinner spinnerTipoTarea = findViewById(R.id.spinnerTipoTareaFijarTarea);
        textoFechaEntrega = findViewById(R.id.textoFechaEntregaFijarTarea);
        ImageButton botonFechaEntrega = findViewById(R.id.botonFechaEntregaFijarTarea);
        textoDescripcion = findViewById(R.id.textoInsertarDescripcionFijarTarea);
        FloatingActionButton botonRealizado = findViewById(R.id.botonRealizadoFijarTarea);

        String[] ciclos = {"Selecciona un ciclo", "Todos", "DAM"};
        spinnerCiclo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ciclos));
        spinnerCiclo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ciclo = parent.getItemAtPosition(position).toString();
                if (ciclo.equals("Selecciona un ciclo")) {
                    spinnerProfesor.setVisibility(View.GONE);
                    profesores = new String[0];
                    spinnerProfesor.setAdapter(new ArrayAdapter<String>(FijarTareaActivity.this, android.R.layout.simple_spinner_item, profesores));
                } else if (ciclo.equals("Todos")){
                    spinnerProfesor.setVisibility(View.VISIBLE);
                    profesores = new String[]{"Selecciona un Profesor", "Todos", "Pepe", "Pepa", "Pedro"};
                    spinnerProfesor.setAdapter(new ArrayAdapter<String>(FijarTareaActivity.this, android.R.layout.simple_spinner_item, profesores));
                } else {
                    spinnerProfesor.setVisibility(View.VISIBLE);
                    profesores = new String[]{"Selecciona un Profesor", "Todos", "Pepe", "Pepa"};
                    spinnerProfesor.setAdapter(new ArrayAdapter<String>(FijarTareaActivity.this, android.R.layout.simple_spinner_item, profesores));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerProfesor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                profesor = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        String[] tareas = {"Selecciona una opción", "Entrega", "Firma"};
        spinnerTipoTarea.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tareas));
        spinnerTipoTarea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tarea = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        encabezado.setNavigationOnClickListener(this);
        botonFechaEntrega.setOnClickListener(this);
        botonRealizado.setOnClickListener(this);
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.botonFechaEntregaFijarTarea) {
            Calendar calendario = Calendar.getInstance();
            int anyo = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    DateTimeFormatter formateadorDeFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate fecha = LocalDate.of(year, month+1, dayOfMonth);
                    textoFechaEntrega.setText(fecha.format(formateadorDeFecha));
                }
            }, anyo, mes, dia);
            datePickerDialog.show();
        } else if (v.getId() == R.id.botonRealizadoFijarTarea) {
            String textoFecha = textoFechaEntrega.getText().toString();
            String descripcion = textoDescripcion.getText().toString().trim();

            if (ciclo.equals("Selecciona un ciclo") || profesor.equals("Selecciona un Profesor") || tarea.equals("Selecciona una opción") || textoFecha.equals("00/00/0000")) {
                RelativeLayout layout = findViewById(R.id.layoutFijarTarea);
                Snackbar.make(layout, R.string.errorTextosVacíos, Snackbar.LENGTH_SHORT).show();
            } else {
                Intent actividadMenuPrincipal = new Intent(this, MenuPrincipalActivity.class);
                actividadMenuPrincipal.putExtras(usuario);
                startActivity(actividadMenuPrincipal);
            }
        } else {
            Intent actividadMenuPrincipal = new Intent(this, MenuPrincipalActivity.class);
            actividadMenuPrincipal.putExtras(usuario);
            startActivity(actividadMenuPrincipal);
        }
    }
}