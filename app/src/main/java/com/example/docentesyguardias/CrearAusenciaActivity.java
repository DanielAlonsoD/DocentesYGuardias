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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import tablas.Ausencia;

/**
 * @author Daniel Alonso
 */
public class CrearAusenciaActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference baseDeDatos;
    private Bundle datos;
    private Calendar calendario;
    private TextView textoFechaHoraInicio, textoFechaHoraFin;
    private String ausencia, fechaHora;
    private LocalDate fecha;
    private LocalTime horaYMinuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_ausencia);

        baseDeDatos = FirebaseDatabase.getInstance().getReference().child("ausencias");
        datos = getIntent().getExtras();
        calendario = Calendar.getInstance();

        MaterialToolbar encabezado = findViewById(R.id.encabezadoCrearAusencia);
        Spinner spinnerTipoAusencia = findViewById(R.id.spinnerTipoAusencia);
        textoFechaHoraInicio = findViewById(R.id.textoFechaHoraInicioCrearAusencia);
        ImageButton botonFechaHoraInicio = findViewById(R.id.botonFechaHoraInicioCrearAusencia);
        textoFechaHoraFin = findViewById(R.id.textoFechaHoraFinCrearAusencia);
        ImageButton botonFechaHoraFin = findViewById(R.id.botonFechaHoraFinCrearAusencia);
        FloatingActionButton botonRealizado = findViewById(R.id.botonRealizadoCrearAusencia);

        String[] opciones = {"Selecciona una opción", "Baja", "Enfermedad", "Consulta Médica"};
        spinnerTipoAusencia.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones));
        spinnerTipoAusencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ausencia = parent.getItemAtPosition(position).toString();
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
        if (v.getId() == R.id.botonFechaHoraInicioCrearAusencia || v.getId() == R.id.botonFechaHoraFinCrearAusencia) {
            int anyo = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);
            int hora = calendario.get(Calendar.HOUR_OF_DAY);
            int minuto = calendario.get(Calendar.MINUTE);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fecha = LocalDate.of(year, month+1, dayOfMonth);

                    TimePickerDialog timePickerDialog = new TimePickerDialog(CrearAusenciaActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            horaYMinuto = LocalTime.of(hourOfDay, minute);
                            DateTimeFormatter formateadorDeFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            DateTimeFormatter formateadorDeHora = DateTimeFormatter.ofPattern("HH:mm");
                            fechaHora = fecha.format(formateadorDeFecha)+" "+horaYMinuto.format(formateadorDeHora);
                            if (v.getId() == R.id.botonFechaHoraInicioCrearAusencia) {
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
        } else if (v.getId() == R.id.botonRealizadoCrearAusencia) {
            String fechaHoraInicio = textoFechaHoraInicio.getText().toString();
            String fechaHoraFin = textoFechaHoraFin.getText().toString();

            if (ausencia.equals("Selecciona una opción") || fechaHoraInicio.equals("00/00/0000 00:00") || fechaHoraFin.equals("00/00/0000 00:00")) {
                RelativeLayout layout = findViewById(R.id.layoutCrearAusencia);
                Snackbar.make(layout, R.string.errorTextosVacíos, Snackbar.LENGTH_SHORT).show();
            } else {
                baseDeDatos.push().setValue(new Ausencia(datos.getString("dni"), ausencia, fechaHoraInicio, fechaHoraFin));

                Intent actividadMenuPrincipal = new Intent(this, MenuPrincipalActivity.class);
                actividadMenuPrincipal.putExtras(datos);
                startActivity(actividadMenuPrincipal);
            }
        } else {
            Intent actividadMenuPrincipal = new Intent(this, MenuPrincipalActivity.class);
            actividadMenuPrincipal.putExtras(datos);
            startActivity(actividadMenuPrincipal);
        }
    }
}