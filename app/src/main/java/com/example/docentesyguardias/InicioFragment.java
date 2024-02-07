package com.example.docentesyguardias;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

import tablas.Usuario;

/**
 * @author Daniel Alonso
 */
public class InicioFragment extends Fragment implements View.OnClickListener {
    private Bundle usuario = new Bundle();
    private Usuario usuarioDatos;

    public InicioFragment() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        MaterialButton botonGestionarPermisos = view.findViewById(R.id.botonGenerarPermiso);
        MaterialButton botonTareasAdministrativas = view.findViewById(R.id.botonAdministrarTareas);
        MaterialButton botonNotificarAusencias = view.findViewById(R.id.botonCrearAusencia);
        MaterialButton botonFijarTareas = view.findViewById(R.id.botonFijarTarea);
        MaterialButton botonAsignarGuardias = view.findViewById(R.id.botonAsignarGuardia);
        MaterialButton botonElaboracionInformes = view.findViewById(R.id.botonElaboracionInformes);

        if (usuario.isEmpty()) {
        } else if (usuarioDatos.getTipoProfesor().equals("Jefe de Estudios")) {
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
        if (v.getId() == R.id.botonGenerarPermiso) {
            Intent actividadGenerarPermiso = new Intent(getActivity(), GenerarPermisoActivity.class);
            actividadGenerarPermiso.putExtras(usuario);
            startActivity(actividadGenerarPermiso);
        } else if (v.getId() == R.id.botonAdministrarTareas) {
            Intent actividadAdministrarTareas = new Intent(getActivity(), AdministrarTareasActivity.class);
            actividadAdministrarTareas.putExtras(usuario);
            startActivity(actividadAdministrarTareas);
        } else if (v.getId() == R.id.botonCrearAusencia) {
            Intent actividadCrearAusencia = new Intent(getActivity(), CrearAusenciaActivity.class);
            actividadCrearAusencia.putExtras(usuario);
            startActivity(actividadCrearAusencia);
        } else if (v.getId() == R.id.botonFijarTarea) {
            Intent actividadFijarTarea = new Intent(getActivity(), FijarTareaActivity.class);
            actividadFijarTarea.putExtras(usuario);
            startActivity(actividadFijarTarea);
        } else if (v.getId() == R.id.botonAsignarGuardia) {
            Intent actividadAsignarGuardia = new Intent(getActivity(), AsignarGuardiaActivity.class);
            actividadAsignarGuardia.putExtras(usuario);
            startActivity(actividadAsignarGuardia);
        } else if (v.getId() == R.id.botonElaboracionInformes) {
            Intent actividadElaborarInforme = new Intent(getActivity(), ElaborarInformeActivity.class);
            actividadElaborarInforme.putExtras(usuario);
            startActivity(actividadElaborarInforme);
        }
    }
}