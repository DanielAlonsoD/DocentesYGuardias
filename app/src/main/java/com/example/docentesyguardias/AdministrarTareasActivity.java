package com.example.docentesyguardias;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.appbar.MaterialToolbar;

import java.time.LocalDate;
import java.util.ArrayList;

import adaptadores.AdaptadorTareas;
import tablas.Tarea;

public class AdministrarTareasActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle usuario;
    private ArrayList<Tarea> tareas;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_tareas);

        usuario = getIntent().getExtras();

        MaterialToolbar encabezado = findViewById(R.id.encabezadoAdministrarTareas);
        ListView listaTareas = findViewById(R.id.listaTareas);

        tareas = new ArrayList<Tarea>();
        tareas.add(new Tarea(1, "123456789", "Entrega", "Realizar una entrega de libros.", "12/1/2024", false));
        tareas.add(new Tarea(2, "98765431", "Firma", "Realizar una firma de documentos.", "10/12/2023", true));
        AdaptadorTareas adaptador = new AdaptadorTareas(this, tareas);
        listaTareas.setAdapter(adaptador);
        listaTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent actividadTarea = new Intent(AdministrarTareasActivity.this, TareaActivity.class);
                usuario.putParcelable("tareaSeleccionada", tareas.get(position));
                actividadTarea.putExtras(usuario);
                startActivity(actividadTarea);
            }
        });

        encabezado.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent actividadMenuPrincipal = new Intent(this, MenuPrincipalActivity.class);
        actividadMenuPrincipal.putExtras(usuario);
        startActivity(actividadMenuPrincipal);
    }
}