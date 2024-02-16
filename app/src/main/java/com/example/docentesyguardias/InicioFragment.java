package com.example.docentesyguardias;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tablas.Usuario;

/**
 * @author Daniel Alonso
 */
public class InicioFragment extends Fragment implements View.OnClickListener {
    private DatabaseReference baseDeDatos;
    private Bundle datos = new Bundle();

    public InicioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            datos = getArguments();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        baseDeDatos = FirebaseDatabase.getInstance().getReference().child("usuarios");

        MaterialButton botonGestionarPermisos = view.findViewById(R.id.botonGenerarPermiso);
        MaterialButton botonTareasAdministrativas = view.findViewById(R.id.botonAdministrarTareas);
        MaterialButton botonNotificarAusencias = view.findViewById(R.id.botonCrearAusencia);
        MaterialButton botonFijarTareas = view.findViewById(R.id.botonFijarTarea);
        MaterialButton botonAsignarGuardias = view.findViewById(R.id.botonAsignarGuardia);
        MaterialButton botonElaboracionInformes = view.findViewById(R.id.botonElaboracionInformes);

        if (!datos.isEmpty()){
            baseDeDatos.orderByChild("dNI").equalTo(datos.getString("dni")).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String tipoProfesor = ds.child("tipoProfesor").getValue(String.class);

                        if (tipoProfesor.equals("Jefe de Estudios")) {
                            botonFijarTareas.setVisibility(View.VISIBLE);
                            botonAsignarGuardias.setVisibility(View.VISIBLE);
                            botonElaboracionInformes.setVisibility(View.VISIBLE);
                        } else {
                            botonGestionarPermisos.setVisibility(View.VISIBLE);
                            botonTareasAdministrativas.setVisibility(View.VISIBLE);
                            botonNotificarAusencias.setVisibility(View.VISIBLE);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });
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
            actividadGenerarPermiso.putExtras(datos);
            startActivity(actividadGenerarPermiso);
        } else if (v.getId() == R.id.botonAdministrarTareas) {
            Intent actividadAdministrarTareas = new Intent(getActivity(), AdministrarTareasActivity.class);
            actividadAdministrarTareas.putExtras(datos);
            startActivity(actividadAdministrarTareas);
        } else if (v.getId() == R.id.botonCrearAusencia) {
            Intent actividadCrearAusencia = new Intent(getActivity(), CrearAusenciaActivity.class);
            actividadCrearAusencia.putExtras(datos);
            startActivity(actividadCrearAusencia);
        } else if (v.getId() == R.id.botonFijarTarea) {
            Intent actividadFijarTarea = new Intent(getActivity(), FijarTareaActivity.class);
            actividadFijarTarea.putExtras(datos);
            startActivity(actividadFijarTarea);
        } else if (v.getId() == R.id.botonAsignarGuardia) {
            Intent actividadAsignarGuardia = new Intent(getActivity(), AsignarGuardiaActivity.class);
            actividadAsignarGuardia.putExtras(datos);
            startActivity(actividadAsignarGuardia);
        } else if (v.getId() == R.id.botonElaboracionInformes) {
            Intent actividadElaborarInforme = new Intent(getActivity(), ElaborarInformeActivity.class);
            actividadElaborarInforme.putExtras(datos);
            startActivity(actividadElaborarInforme);
        }
    }
}