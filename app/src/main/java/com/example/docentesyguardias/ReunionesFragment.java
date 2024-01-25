package com.example.docentesyguardias;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import tablas.Profesor;

public class ReunionesFragment extends Fragment {
    Bundle usuario = new Bundle();
    Profesor profesor = new Profesor();

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reuniones, container, false);

        ListView lista = view.findViewById(R.id.listaReuniones);
        FloatingActionButton botonCrear = view.findViewById(R.id.botonCrearReunion);

        if (usuario.isEmpty()){
        } else if (profesor.getTipoProfesor().equals("Docente")) {
            botonCrear.setVisibility(View.GONE);
        }


        return view;
    }
}