package com.example.docentesyguardias;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * @author Daniel Alonso
 */
public class AsignarGuardiaActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle usuario;
    private Calendar calendario;
    private TextView textoFechaHoraInicio, textoFechaHoraFin;
    String profesorAusente, profesorGuardia, fechaHora;
    private LocalDate fecha;
    private LocalTime horaYMinuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_guardia);

        usuario = getIntent().getExtras();
        calendario = Calendar.getInstance();

        MaterialToolbar encabezado = findViewById(R.id.encabezadoAsignarGuardia);
        Spinner spinnerProfesorAusente = findViewById(R.id.spinnerProfesorAusenteAsignarGuardia);
        Spinner spinnerProfesorGuardia = findViewById(R.id.spinnerProfesorGuardiaAsignarGuardia);
        textoFechaHoraInicio = findViewById(R.id.textoFechaHoraInicioAsignarGuardia);
        ImageButton botonFechaHoraInicio = findViewById(R.id.botonFechaHoraInicioAsignarGuardia);
        textoFechaHoraFin = findViewById(R.id.textoFechaHoraFinAsignarGuardia);
        ImageButton botonFechaHoraFin = findViewById(R.id.botonFechaHoraFinAsignarGuardia);
        FloatingActionButton botonRealizado = findViewById(R.id.botonRealizadoAsignarGuardia);

        String[] profesoresAusentes = {"Selecciona una Ausencia", "Pepe", "Pepa", "Pedro"};
        spinnerProfesorAusente.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, profesoresAusentes));
        spinnerProfesorAusente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                profesorAusente = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        String[] profesoresGuardias = {"Selecciona al que realice la Guardia", "Pepe", "Pepa", "Pedro"};
        spinnerProfesorGuardia.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, profesoresGuardias));
        spinnerProfesorGuardia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                profesorGuardia = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        encabezado.setNavigationOnClickListener(this);
        botonFechaHoraInicio.setOnClickListener(this);
        botonFechaHoraFin.setOnClickListener(this);
        botonRealizado.setOnClickListener(this);
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.botonFechaHoraInicioAsignarGuardia || v.getId() == R.id.botonFechaHoraFinAsignarGuardia) {
            int anyo = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);
            int hora = calendario.get(Calendar.HOUR_OF_DAY);
            int minuto = calendario.get(Calendar.MINUTE);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fecha = LocalDate.of(year, month+1, dayOfMonth);

                    TimePickerDialog timePickerDialog = new TimePickerDialog(AsignarGuardiaActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            horaYMinuto = LocalTime.of(hourOfDay, minute);
                            DateTimeFormatter formateadorDeFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            DateTimeFormatter formateadorDeHora = DateTimeFormatter.ofPattern("HH:mm");
                            fechaHora = fecha.format(formateadorDeFecha)+" "+horaYMinuto.format(formateadorDeHora);
                            if (v.getId() == R.id.botonFechaHoraInicioAsignarGuardia) {
                                textoFechaHoraInicio.setText(fechaHora);
                            } else {
                                textoFechaHoraFin.setText(fechaHora);
                            }
                        }
                    }, hora, minuto, true);
                    timePickerDialog.show();
                }
            }, anyo, mes, dia);
            datePickerDialog.show();
        } else if (v.getId() == R.id.botonRealizadoAsignarGuardia) {
            String fechaHoraInicio = textoFechaHoraInicio.getText().toString();
            String fechaHoraFin = textoFechaHoraFin.getText().toString();

            if (profesorAusente.equals("Selecciona una Ausencia") || profesorGuardia.equals("Selecciona al que realice la Guardia") || fechaHoraInicio.equals("00/00/0000 00:00") || fechaHoraFin.equals("00/00/0000 00:00")) {
                RelativeLayout layout = findViewById(R.id.layoutAsignarGuardia);
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