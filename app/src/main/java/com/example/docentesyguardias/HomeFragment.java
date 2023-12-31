package com.example.docentesyguardias;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import Tablas.Profesor;

public class HomeFragment extends Fragment {
    private Bundle usuario = new Bundle();
    private Profesor profesor;

    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button botonGestionarPermisos = view.findViewById(R.id.botonGestionarPermisos);
        Button botonTareasAdministrativas = view.findViewById(R.id.botonTareasAdministrativas);
        Button botonNotificarAusencias = view.findViewById(R.id.botonNotificarAusencias);
        Button botonFijarTareas = view.findViewById(R.id.botonFijarTareas);
        Button botonAsignarGuardias = view.findViewById(R.id.botonAsignarGuardias);
        Button botonElaboracionInformes = view.findViewById(R.id.botonElaboracionInformes);

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

        return view;
    }
}