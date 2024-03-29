package com.example.docentesyguardias;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import adaptadores.AdaptadorReuniones;
import tablas.Usuario;
import tablas.Reunion;

/**
 * @author Daniel Alonso
 */
public class ReunionesFragment extends Fragment implements View.OnClickListener {
    Bundle usuario = new Bundle();
    Usuario usuarioDatos = new Usuario();
    ArrayList<Reunion> reuniones = new ArrayList<>();

    public ReunionesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            usuario = getArguments();
            usuarioDatos = usuario.getParcelable("profesor");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reuniones, container, false);

        ListView lista = view.findViewById(R.id.listaReuniones);
        FloatingActionButton botonCrear = view.findViewById(R.id.botonCrearReunion);

        if (usuario.isEmpty()){
        } else if (usuarioDatos.getTipoProfesor().equals("Docente")) {
            botonCrear.setVisibility(View.GONE);
        }

        reuniones.add(new Reunion(1, "71186545D", "Asistiré", "08/02/2024 08:30"));
        reuniones.add(new Reunion(2, "71186545D", "No Sé Si Asistiré", "12/03/2024 09:30"));
        reuniones.add(new Reunion(3, "71186545D", "No Asistiré", "15/03/2024 11:00"));

        AdaptadorReuniones adaptador = new AdaptadorReuniones(getContext(), reuniones);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent actividadVisualizar = new Intent(getActivity(), VisualizarReunionActivity.class);
                usuario.putParcelable("reunionSeleccionado", reuniones.get(position));
                actividadVisualizar.putExtras(usuario);
                startActivity(actividadVisualizar);
            }
        });

        botonCrear.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent actividadCrearReunion = new Intent(getActivity(), CrearReunionActivity.class);
        actividadCrearReunion.putExtras(usuario);
        startActivity(actividadCrearReunion);
    }
}