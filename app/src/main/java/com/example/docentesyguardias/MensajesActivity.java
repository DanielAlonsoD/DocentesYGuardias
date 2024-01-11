package com.example.docentesyguardias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import Tablas.Profesor;

public class MensajesActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes);

        usuario = getIntent().getExtras();
        Profesor profesor = usuario.getParcelable("profesor");

        MaterialToolbar encabezado = findViewById(R.id.encabezadoMensajes);
        FloatingActionButton botonCrear = findViewById(R.id.botonCrearMensaje);

        if (profesor.getTipoProfesor().equals("Docente")) {
            botonCrear.setVisibility(View.GONE);
        }

        encabezado.setNavigationOnClickListener(this);
        botonCrear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.botonCrearMensaje) {

        } else {
            Intent actividadMenuPrincipal = new Intent(MensajesActivity.this, MenuPrincipalActivity.class);
            actividadMenuPrincipal.putExtras(usuario);
            startActivity(actividadMenuPrincipal);
        }
    }
}