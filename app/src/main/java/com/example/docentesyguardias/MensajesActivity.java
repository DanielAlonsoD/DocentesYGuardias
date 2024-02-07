package com.example.docentesyguardias;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;

import adaptadores.AdaptadorMensajes;
import tablas.Mensaje;
import tablas.Profesor;

/**
 * @author Daniel Alonso
 */
public class MensajesActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle usuario;
    private ArrayList<Mensaje> mensajes;

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
        encabezado.setNavigationOnClickListener(this);
        botonCrear.setOnClickListener(this);
        mensajes = new ArrayList<Mensaje>();
        Mensaje m1 = new Mensaje(1, "712565F", "1", "Saludo", "Este es un mensaje para saludar al usuario","12/12/2023");
        Mensaje m2 = new Mensaje(2, "714334D", "1", "Entrega de Firmas", "asdasdasdasvgdfag","10/01/2024");
        Mensaje m3 = new Mensaje(3, "716545T", "1", "Visita Mañana", "asdasdasdasvgdfag","20/11/2023");
        mensajes.add(m1);
        mensajes.add(m2);
        mensajes.add(m3);
        AdaptadorMensajes adaptador = new AdaptadorMensajes(this, mensajes);
        listaMensajes.setAdapter(adaptador);
        listaMensajes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent actividadVisualizar = new Intent(MensajesActivity.this, VisualizarMensajeActivity.class);
                usuario.putParcelable("mensajeSeleccionado", mensajes.get(position));
                actividadVisualizar.putExtras(usuario);
                startActivity(actividadVisualizar);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.botonCrearMensaje) {
            Intent actividadCrearMensaje = new Intent(MensajesActivity.this, CrearMensajeActivity.class);
            actividadCrearMensaje.putExtras(usuario);
            startActivity(actividadCrearMensaje);
        } else {
            Intent actividadMenuPrincipal = new Intent(MensajesActivity.this, MenuPrincipalActivity.class);
            actividadMenuPrincipal.putExtras(usuario);
            startActivity(actividadMenuPrincipal);
        }
    }
}