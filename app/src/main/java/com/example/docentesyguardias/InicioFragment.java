package com.example.docentesyguardias;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

import tablas.Profesor;

public class InicioFragment extends Fragment implements View.OnClickListener {
    private Bundle usuario = new Bundle();
    private Profesor profesor;

    public InicioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            usuario = getArguments();
            profesor = usuario.getParcelable("profesor");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        MaterialButton botonGestionarPermisos = view.findViewById(R.id.botonGestionarPermisos);
        MaterialButton botonTareasAdministrativas = view.findViewById(R.id.botonTareasAdministrativas);
        MaterialButton botonNotificarAusencias = view.findViewById(R.id.botonNotificarAusencias);
        MaterialButton botonFijarTareas = view.findViewById(R.id.botonFijarTarea);
        MaterialButton botonAsignarGuardias = view.findViewById(R.id.botonAsignarGuardia);
        MaterialButton botonElaboracionInformes = view.findViewById(R.id.botonElaboracionInformes);

        if (usuario.isEmpty()) {
        } else if (profesor.getTipoProfesor().equals("Jefe de Estudios")) {
            botonGestionarPermisos.setVisibility(View.GONE);
            botonTareasAdministrativas.setVisibility(View.GONE);
            botonNotificarAusencias.setVisibility(View.GONE);
        } else {
            botonFijarTareas.setVisibility(View.GONE);
            botonAsignarGuardias.setVisibility(View.GONE);
            botonElaboracionInformes.setVisibility(View.GONE);
        }

        botonGestionarPermisos.setOnClickListener(this);
        botonTareasAdministrativas.setOnClickListener(this);
        botonNotificarAusencias.setOnClickListener(this);
        botonFijarTareas.setOnClickListener(this);
        botonAsignarGuardias.setOnClickListener(this);
        botonElaboracionInformes.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.botonGestionarPermisos) {

        } else if (v.getId() == R.id.botonTareasAdministrativas) {

        } else if (v.getId() == R.id.botonNotificarAusencias) {

        } else if (v.getId() == R.id.botonFijarTarea) {
            Intent actividadFijarTarea = new Intent(getActivity(), FijarTareaActivity.class);
            actividadFijarTarea.putExtras(usuario);
            startActivity(actividadFijarTarea);
        } else if (v.getId() == R.id.botonAsignarGuardia) {
            Intent actividadAsignarGuardia = new Intent(getActivity(), AsignarGuardiaActivity.class);
            actividadAsignarGuardia.putExtras(usuario);
            startActivity(actividadAsignarGuardia);
        } else if (v.getId() == R.id.botonElaboracionInformes) {

        }
    }
}