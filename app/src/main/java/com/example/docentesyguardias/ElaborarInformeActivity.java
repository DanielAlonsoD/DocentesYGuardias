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

/**
 * @author Daniel Alonso
 */
public class ElaborarInformeActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle usuario;
    private EditText textoDescripcion;
    private String guardia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elaborar_informe);

        usuario = getIntent().getExtras();

        MaterialToolbar encabezado = findViewById(R.id.encabezadoElaborarInforme);
        Spinner spinnerGuardia = findViewById(R.id.spinnerGuardiaElaborarInforme);
        textoDescripcion = findViewById(R.id.textoInsertarDescripcionElaborarInforme);
        FloatingActionButton botonRealizado = findViewById(R.id.botonRealizadoElaborarInforme);

        String[] profesoresGuardias = {"Selecciona una Guardia", "Pepe 19/12/2023", "Pepa 20/12/2023", "Pedro 12/01/2024"};
        spinnerGuardia.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, profesoresGuardias));
        spinnerGuardia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                guardia = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        encabezado.setNavigationOnClickListener(this);
        botonRealizado.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.botonRealizadoElaborarInforme) {
            String descripcion = textoDescripcion.getText().toString().trim();

            if (guardia.equals("Selecciona una Guardia") || descripcion.isEmpty()) {
                RelativeLayout layout = findViewById(R.id.layoutElaborarInforme);
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