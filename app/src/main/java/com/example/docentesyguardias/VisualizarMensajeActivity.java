package com.example.docentesyguardias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

import tablas.Mensaje;

/**
 * @author Daniel Alonso
 */
public class VisualizarMensajeActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_mensaje);

        usuario = getIntent().getExtras();
        Mensaje mensaje = usuario.getParcelable("mensajeSeleccionado");

        MaterialToolbar encabezado = findViewById(R.id.encabezadoVisualizarMensaje);
        TextView textoProfesorEmisor = findViewById(R.id.textoProfesorEmisorVisualizar);
        TextView textoFecha = findViewById(R.id.textoFechaVisualizarMensaje);
        TextView textoTitulo = findViewById(R.id.textoTituloVisualizarMensaje);
        TextView textoMensaje = findViewById(R.id.textoMensajeVisualizarMensaje);

        textoProfesorEmisor.setText(mensaje.getDniProfesorEmisor());
        textoFecha.setText(mensaje.getFecha());
        textoTitulo.setText(mensaje.getTitulo());
        textoMensaje.setText(mensaje.getMensaje());

        encabezado.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent actividadMensajes = new Intent(this, MensajesActivity.class);
        actividadMensajes.putExtras(usuario);
        startActivity(actividadMensajes);
    }
}