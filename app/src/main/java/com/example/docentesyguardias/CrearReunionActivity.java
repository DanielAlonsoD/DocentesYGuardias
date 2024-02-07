package com.example.docentesyguardias;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
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
public class CrearReunionActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle usuario;
    private MultiAutoCompleteTextView textoInvitados;
    private TextView textoFechaHoraInicio;
    private LocalDate fecha;
    private LocalTime horaYMinuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_reunion);

        usuario = getIntent().getExtras();

        MaterialToolbar encabezado = findViewById(R.id.encabezadoCrearReunion);
        textoInvitados = findViewById(R.id.textoInsertarInvitadosCrearReunion);
        textoFechaHoraInicio = findViewById(R.id.textoFechaHoraInicioCrearReunion);
        ImageButton botonFechaHoraInicio = findViewById(R.id.botonFechaHoraInicioCrearReunion);
        FloatingActionButton botonRealizado = findViewById(R.id.botonRealizadoCrearReunion);

        String[] posiblesInvitados = {"Pepe", "Pepa", "Pedro"};
        textoInvitados.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, posiblesInvitados));
        textoInvitados.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        encabezado.setNavigationOnClickListener(this);
        botonFechaHoraInicio.setOnClickListener(this);
        botonRealizado.setOnClickListener(this);
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.botonFechaHoraInicioCrearReunion) {
            Calendar calendario = Calendar.getInstance();
            int anyo = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);
            int hora = calendario.get(Calendar.HOUR_OF_DAY);
            int minuto = calendario.get(Calendar.MINUTE);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fecha = LocalDate.of(year, month+1, dayOfMonth);

                    TimePickerDialog timePickerDialog = new TimePickerDialog(CrearReunionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            horaYMinuto = LocalTime.of(hourOfDay, minute);
                            DateTimeFormatter formateadorDeFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            DateTimeFormatter formateadorDeHora = DateTimeFormatter.ofPattern("HH:mm");
                            textoFechaHoraInicio.setText(fecha.format(formateadorDeFecha)+" "+horaYMinuto.format(formateadorDeHora));
                        }
                    }, hora, minuto, true);
                    timePickerDialog.show();
                }
            }, anyo, mes, dia);
            datePickerDialog.show();
        } else if (v.getId() == R.id.botonRealizadoCrearReunion) {
            String invitados = textoInvitados.getText().toString().trim();
            String fechaHora = textoFechaHoraInicio.getText().toString();

            if (invitados.isEmpty() || fechaHora.equals("00/00/0000 00:00")) {
                RelativeLayout layout = findViewById(R.id.layoutCrearReunion);
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