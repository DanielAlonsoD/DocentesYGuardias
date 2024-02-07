package com.example.docentesyguardias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import tablas.Usuario;

/**
 * @author Daniel Alonso
 */
public class CrearMensajeActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle usuario;
    private Usuario usuarioDatos;
    private MultiAutoCompleteTextView textoDestinatarios;
    private EditText textoTitulo, textoMensaje;
    private String ciclo;
    private String[] posiblesDestinatarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_mensaje);

        usuario = getIntent().getExtras();
        usuarioDatos = usuario.getParcelable("profesor");

        MaterialToolbar encabezado = findViewById(R.id.encabezadoCrearMensaje);
        Spinner spinnerCiclo = findViewById(R.id.spinnerCicloCrearMensaje);
        TextInputLayout textInputDestinatarios = findViewById(R.id.textInputDestinatariosCrearMensaje);
        textoDestinatarios = findViewById(R.id.textoInsertarDestinatariosCrearMensaje);
        textoTitulo = findViewById(R.id.textoInsertarTituloCrearMensaje);
        textoMensaje = findViewById(R.id.textoInsertarMensajeCrearMensaje);
        FloatingActionButton botonRealizado = findViewById(R.id.botonRealizadoCrearMensaje);

        if (usuarioDatos.getTipoProfesor().equals("Jefe de Estudios")) {
            String[] ciclos = {"Selecciona un ciclo", "Todos", "DAM"};
            spinnerCiclo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ciclos));
            spinnerCiclo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ciclo = parent.getItemAtPosition(position).toString();
                    if (ciclo.equals("Selecciona un ciclo")) {
                        textInputDestinatarios.setVisibility(View.GONE);
                        posiblesDestinatarios = new String[0];
                        textoDestinatarios.setAdapter(new ArrayAdapter<String>(CrearMensajeActivity.this, android.R.layout.simple_dropdown_item_1line, posiblesDestinatarios));
                    } else if (ciclo.equals("Todos")){
                        textInputDestinatarios.setVisibility(View.VISIBLE);
                        posiblesDestinatarios = new String[]{"Pepe", "Pepa", "Pedro"};
                        textoDestinatarios.setAdapter(new ArrayAdapter<String>(CrearMensajeActivity.this, android.R.layout.simple_dropdown_item_1line, posiblesDestinatarios));
                    } else {
                        textInputDestinatarios.setVisibility(View.VISIBLE);
                        posiblesDestinatarios = new String[]{"Pepe", "Pepa"};
                        textoDestinatarios.setAdapter(new ArrayAdapter<String>(CrearMensajeActivity.this, android.R.layout.simple_dropdown_item_1line, posiblesDestinatarios));
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });
        } else {
            spinnerCiclo.setVisibility(View.GONE);
            posiblesDestinatarios = new String[]{"Pepe", "Pepa"};
            textoDestinatarios.setAdapter(new ArrayAdapter<String>(CrearMensajeActivity.this, android.R.layout.simple_dropdown_item_1line, posiblesDestinatarios));
        }

        textoDestinatarios.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        encabezado.setNavigationOnClickListener(this);
        botonRealizado.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.botonRealizadoCrearMensaje) {
            String destinatarios = textoDestinatarios.getText().toString().trim();
            String titulo = textoTitulo.getText().toString().trim();
            String mensaje = textoMensaje.getText().toString().trim();

            if ((usuarioDatos.getTipoProfesor().equals("Jefe de Estudios") && ciclo.equals("Selecciona un ciclo")) || destinatarios.isEmpty() || titulo.isEmpty() || mensaje.isEmpty()) {
                RelativeLayout layout = findViewById(R.id.layoutCrearMensaje);
                Snackbar.make(layout, R.string.errorTextosVacíos, Snackbar.LENGTH_SHORT).show();
            } else {
                Intent actividadMensajes = new Intent(this, MensajesActivity.class);
                actividadMensajes.putExtras(usuario);
                startActivity(actividadMensajes);
            }
        } else {
            Intent actividadMensajes = new Intent(this, MensajesActivity.class);
            actividadMensajes.putExtras(usuario);
            startActivity(actividadMensajes);
        }
    }
}