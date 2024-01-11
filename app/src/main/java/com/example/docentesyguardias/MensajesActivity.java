package com.example.docentesyguardias;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;

import Adaptadores.AdaptadorMensajes;
import Tablas.Mensaje;
import Tablas.Profesor;

public class MensajesActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle usuario;
    ArrayList<Mensaje> mensajes;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes);

        usuario = getIntent().getExtras();
        Profesor profesor = usuario.getParcelable("profesor");

        MaterialToolbar encabezado = findViewById(R.id.encabezadoMensajes);
        ListView listaMensajes = findViewById(R.id.listaMensajes);
        FloatingActionButton botonCrear = findViewById(R.id.botonCrearMensaje);

        if (profesor.getTipoProfesor().equals("Docente")) {
            botonCrear.setVisibility(View.GONE);
        }

        mensajes = new ArrayList<>();
        mensajes.add(new Mensaje(1, "712", "1", "Hola", LocalDate.now(), "sadgasdgkjasdga"));
        AdaptadorMensajes adaptador = new AdaptadorMensajes(this, mensajes);
        listaMensajes.setAdapter(adaptador);

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