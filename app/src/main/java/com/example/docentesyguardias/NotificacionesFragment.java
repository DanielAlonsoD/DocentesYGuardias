package com.example.docentesyguardias;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.time.LocalDateTime;
import java.util.ArrayList;

import Adaptadores.AdaptadorAusencias;
import Adaptadores.AdaptadorGuardias;
import Tablas.Ausencia;
import Tablas.Guardia;
import Tablas.Profesor;

public class NotificacionesFragment extends Fragment {
    private Bundle usuario = new Bundle();
    private Profesor profesor;
    private ArrayList<Ausencia> ausencias;
    private ArrayList<Guardia> guardias;

    public NotificacionesFragment() {
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notificaciones, container, false);

        ListView lista = view.findViewById(R.id.listaNotifications);

        if (usuario.isEmpty()) {
        } else if (profesor.getTipoProfesor().equals("Jefe de Estudios")) {
            ausencias = new ArrayList<>();
            ausencias.add(new Ausencia(1, "23asda", "baja", LocalDateTime.of(0, 0,0,0,0), LocalDateTime.of(0,0,0,0,0)));
            AdaptadorAusencias adaptador = new AdaptadorAusencias(view.getContext(), ausencias);
            lista.setAdapter(adaptador);
        } else {
            guardias = new ArrayList<>();
            guardias.add(new Guardia(1, "23asda",1, LocalDateTime.of(0, 0,0,0,0), LocalDateTime.of(0,0,0,0,0)));
            AdaptadorGuardias adaptador = new AdaptadorGuardias(view.getContext(), guardias);
            lista.setAdapter(adaptador);
        }

        return view;
    }
}