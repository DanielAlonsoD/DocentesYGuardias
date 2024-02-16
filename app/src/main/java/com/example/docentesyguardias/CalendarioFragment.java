package com.example.docentesyguardias;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;

import tablas.Usuario;

/**
 * @author Daniel Alonso
 */
public class CalendarioFragment extends Fragment {
    private Bundle datos = new Bundle();

    public CalendarioFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendario, container, false);

        TabLayout tabs = view.findViewById(R.id.tabsCalendario);

        tabs.addTab(tabs.newTab().setText(R.string.textoHorario));
        tabs.addTab(tabs.newTab().setText(R.string.textoReuniones));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Fragment fragmentoHorario = new HorarioFragment();
                        fragmentoHorario.setArguments(datos);
                        cargarFragmento(fragmentoHorario);
                        break;
                    case 1:
                        Fragment fragmentoReuniones = new ReunionesFragment();
                        fragmentoReuniones.setArguments(datos);
                        cargarFragmento(fragmentoReuniones);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        Fragment fragmentoHorario = new HorarioFragment();
        fragmentoHorario.setArguments(datos);
        cargarFragmento(fragmentoHorario);

        return view;
    }

    public void cargarFragmento(Fragment fragmento) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedorFragmentosCalendario, fragmento)
                .commit();
    }
}