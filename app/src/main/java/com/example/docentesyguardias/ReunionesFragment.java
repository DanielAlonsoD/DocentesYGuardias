package com.example.docentesyguardias;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDateTime;
import java.util.ArrayList;

import adaptadores.AdaptadorReuniones;
import tablas.Profesor;
import tablas.Reunion;

public class ReunionesFragment extends Fragment {
    Bundle usuario = new Bundle();
    Profesor profesor = new Profesor();
    ArrayList<Reunion> reuniones = new ArrayList<>();

    public ReunionesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_reuniones, container, false);

        ListView lista = view.findViewById(R.id.listaReuniones);
        FloatingActionButton botonCrear = view.findViewById(R.id.botonCrearReunion);

        if (usuario.isEmpty()){
        } else if (profesor.getTipoProfesor().equals("Docente")) {
            botonCrear.setVisibility(View.GONE);
        }

        reuniones.add(new Reunion(1, "71186545D", "Asistiré", LocalDateTime.of(1, 1,1,1,1)));
        reuniones.add(new Reunion(2, "71186545D", "No Sé Si Asistiré", LocalDateTime.of(1, 1,1,1,1)));
        reuniones.add(new Reunion(3, "71186545D", "No Asistiré", LocalDateTime.of(1, 1,1,1,1)));

        AdaptadorReuniones adaptador = new AdaptadorReuniones(getContext(), reuniones);
        lista.setAdapter(adaptador);

        return view;
    }
}