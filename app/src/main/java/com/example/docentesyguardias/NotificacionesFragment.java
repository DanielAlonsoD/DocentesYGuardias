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

import adaptadores.AdaptadorAusencias;
import adaptadores.AdaptadorGuardias;
import tablas.Ausencia;
import tablas.Guardia;
import tablas.Profesor;

/**
 * @author Daniel Alonso
 */
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

        ListView lista = view.findViewById(R.id.listaNotificaciones);

        if (usuario.isEmpty()) {
        } else if (profesor.getTipoProfesor().equals("Jefe de Estudios")) {
            ausencias = new ArrayList<>();
            ausencias.add(new Ausencia(1, "711869T", "baja", LocalDateTime.of(2024, 2,12,8,15), LocalDateTime.of(2024,3,1,14,15)));
            AdaptadorAusencias adaptador = new AdaptadorAusencias(getContext(), ausencias);
            lista.setAdapter(adaptador);
        } else {
            guardias = new ArrayList<>();
            guardias.add(new Guardia(1, "711869T",1, LocalDateTime.of(2024, 2,12,8,15), LocalDateTime.of(2024,3,1,14,15)));
            guardias.add(new Guardia(2, "711869T",2, LocalDateTime.of(2024, 3,7,8,15), LocalDateTime.of(2024,3,8,10,5)));
            guardias.add(new Guardia(3, "711869T",3, LocalDateTime.of(2024, 3,10,8,15), LocalDateTime.of(2024,3,13,11,30)));
            AdaptadorGuardias adaptador = new AdaptadorGuardias(getContext(), guardias);
            lista.setAdapter(adaptador);
        }

        return view;
    }
}