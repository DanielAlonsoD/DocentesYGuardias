package Adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.docentesyguardias.R;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Tablas.Guardia;

public class AdaptadorGuardias extends ArrayAdapter<Guardia> {
    private ArrayList<Guardia> guardias;


    public AdaptadorGuardias(@NonNull Context contexto, ArrayList<Guardia> guardias) {
        super(contexto, R.layout.elemento_guardia_lista, guardias);
        this.guardias = guardias;
    }

    @SuppressLint("NewApi")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View view = mostrado.inflate(R.layout.elemento_guardia_lista, parent, false);

        TextView textoAusente = view.findViewById(R.id.textoAusenteElementoGuardia);
        TextView textoFechaHora = view.findViewById(R.id.textoFechaHoraElementoGuardia);

        textoAusente.setText(guardias.get(position).getAusencia()+"");
        LocalDateTime fechaHoraInicio = guardias.get(position).getFechaHoraInicio();
        LocalDateTime fechaHoraFin = guardias.get(position).getFechaHoraFin();
        DateTimeFormatter formateadorDeFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        textoFechaHora.setText(fechaHoraInicio.format(formateadorDeFechaHora)+" - "+fechaHoraFin.format(formateadorDeFechaHora));

        return view;
    }
}
