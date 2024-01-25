package adaptadores;

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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import tablas.Ausencia;

public class AdaptadorAusencias extends ArrayAdapter<Ausencia> {
    private ArrayList<Ausencia> ausencias;


    public AdaptadorAusencias(@NonNull Context contexto, ArrayList<Ausencia> ausencias) {
        super(contexto, R.layout.elemento_ausencia_lista, ausencias);
        this.ausencias = ausencias;
    }

    @SuppressLint("NewApi")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View view = mostrado.inflate(R.layout.elemento_ausencia_lista, parent, false);

        TextView textoAusente = view.findViewById(R.id.textoAusenteElementoAusencia);
        TextView textoRazon = view.findViewById(R.id.textoRazonElementoAusencia);
        TextView textoFechaHora = view.findViewById(R.id.textoFechaHoraElementoAusencia);

        textoAusente.setText(ausencias.get(position).getDniProfesor());
        textoRazon.setText(ausencias.get(position).getRazon());
        LocalDateTime fechaHoraInicio = ausencias.get(position).getFechaHoraInicio();
        LocalDateTime fechaHoraFin = ausencias.get(position).getFechaHoraFin();
        DateTimeFormatter formateadorDeFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        textoFechaHora.setText(fechaHoraInicio.format(formateadorDeFechaHora)+" - "+fechaHoraFin.format(formateadorDeFechaHora));

        return view;
    }
}
