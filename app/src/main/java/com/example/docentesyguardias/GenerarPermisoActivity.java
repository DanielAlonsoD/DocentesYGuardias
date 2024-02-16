package com.example.docentesyguardias;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import tablas.Ausencia;

/**
 * @author Daniel Alonso
 */
public class GenerarPermisoActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference baseDeDatos;
    private Bundle datos;
    private Calendar calendario;
    private TextView textoFechaHoraInicio, textoFechaHoraFin;
    private String permiso, fechaHora;
    private int id;
    private LocalDate fecha;
    private LocalTime horaYMinuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_permiso);

        baseDeDatos = FirebaseDatabase.getInstance().getReference().child("ausencias");
        datos = getIntent().getExtras();
        calendario = Calendar.getInstance();

        MaterialToolbar encabezado = findViewById(R.id.encabezadoGenerarPermiso);
        Spinner spinnerTipoPermiso = findViewById(R.id.spinnerTipoPermiso);
        textoFechaHoraInicio = findViewById(R.id.textoFechaHoraInicioGenerarPermiso);
        ImageButton botonFechaHoraInicio = findViewById(R.id.botonFechaHoraInicioGenerarPermiso);
        textoFechaHoraFin = findViewById(R.id.textoFechaHoraFinGenerarPermiso);
        ImageButton botonFechaHoraFin = findViewById(R.id.botonFechaHoraFinGenerarPermiso);
        FloatingActionButton botonRealizado = findViewById(R.id.botonRealizadoGenerarPermiso);

        String[] opciones = {"Selecciona una opción", "Solicitud", "Consulta de Estado", "Permisos Pendientes"};
        spinnerTipoPermiso.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones));
        spinnerTipoPermiso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                permiso = parent.getItemAtPosition(position).toString();
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
        if (v.getId() == R.id.botonFechaHoraInicioGenerarPermiso || v.getId() == R.id.botonFechaHoraFinGenerarPermiso) {
            int anyo = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);
            int hora = calendario.get(Calendar.HOUR_OF_DAY);
            int minuto = calendario.get(Calendar.MINUTE);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fecha = LocalDate.of(year, month+1, dayOfMonth);

                    TimePickerDialog timePickerDialog = new TimePickerDialog(GenerarPermisoActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            horaYMinuto = LocalTime.of(hourOfDay, minute);
                            DateTimeFormatter formateadorDeFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            DateTimeFormatter formateadorDeHora = DateTimeFormatter.ofPattern("HH:mm");
                            fechaHora = fecha.format(formateadorDeFecha)+" "+horaYMinuto.format(formateadorDeHora);
                            if (v.getId() == R.id.botonFechaHoraInicioGenerarPermiso) {
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
        } else if (v.getId() == R.id.botonRealizadoGenerarPermiso) {
            String fechaHoraInicio = textoFechaHoraInicio.getText().toString();
            String fechaHoraFin = textoFechaHoraFin.getText().toString();

            if (permiso.equals("Selecciona una opción") || fechaHoraInicio.equals("00/00/0000 00:00") || fechaHoraFin.equals("00/00/0000 00:00")) {
                RelativeLayout layout = findViewById(R.id.layoutGenerarPermiso);
                Snackbar.make(layout, R.string.errorTextosVacíos, Snackbar.LENGTH_SHORT).show();
            } else {
                baseDeDatos.push().setValue(new Ausencia(datos.getString("dni"), permiso, fechaHoraInicio, fechaHoraFin));

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