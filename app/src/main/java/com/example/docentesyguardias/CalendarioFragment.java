package com.example.docentesyguardias;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import tablas.Profesor;

public class CalendarioFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    Bundle usuario = new Bundle();
    Profesor profesor;
    private TabLayout tabs;

    public CalendarioFragment() {
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
        View view = inflater.inflate(R.layout.fragment_calendario, container, false);

        tabs = view.findViewById(R.id.tabsCalendario);

        tabs.addTab(tabs.newTab().setText(R.string.textoHorario));
        tabs.addTab(tabs.newTab().setText(R.string.textoReuniones));


            tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    switch (tab.getPosition()) {
                        case 0:
                            Fragment fragmentoHorario = new HorarioFragment();
                            fragmentoHorario.setArguments(usuario);
                            cargarFragmento(fragmentoHorario);
                            break;
                        case 1:
                            Fragment fragmentoReuniones = new ReunionesFragment();
                            fragmentoReuniones.setArguments(usuario);
                            cargarFragmento(fragmentoReuniones);
                            break;
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

            switch (usuario.getInt("navegacionCaledario")) {
                case 1:
                    Fragment fragmentoHorario = new HorarioFragment();
                    fragmentoHorario.setArguments(usuario);
                    cargarFragmento(fragmentoHorario);
                case 2:
                    Fragment fragmentoReuniones = new ReunionesFragment();
                    usuario.putInt("navegacionCaledario", 1);
                    fragmentoReuniones.setArguments(usuario);
                    cargarFragmento(fragmentoReuniones);
            }

        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                Fragment fragmentoHorario = new HorarioFragment();
                fragmentoHorario.setArguments(usuario);
                cargarFragmento(fragmentoHorario);
                break;
            case 1:
                Fragment fragmentoReuniones = new ReunionesFragment();
                fragmentoReuniones.setArguments(usuario);
                cargarFragmento(fragmentoReuniones);
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void cargarFragmento(Fragment fragmento) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedorFragmentosCalendario, fragmento)
                .commit();
    }
}