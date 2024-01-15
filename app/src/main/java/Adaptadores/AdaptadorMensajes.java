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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Tablas.Mensaje;

public class AdaptadorMensajes extends ArrayAdapter<Mensaje> {
    private ArrayList<Mensaje> mensajes;


    public AdaptadorMensajes(@NonNull Context contexto, ArrayList<Mensaje> mensajes) {
        super(contexto, R.layout.elemento_mensaje_lista);
        this.mensajes = mensajes;
    }

    @SuppressLint("NewApi")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View view = mostrado.inflate(R.layout.elemento_mensaje_lista, parent, false);

        TextView textoEmisor = view.findViewById(R.id.textoEmisorElementoMensaje);
        TextView textoTitulo = view.findViewById(R.id.textoMensajeElementoMensaje);
        TextView textoFecha = view.findViewById(R.id.textoFechaElementoMensaje);

        textoEmisor.setText(mensajes.get(position).getDniProfesorEmisor());
        textoTitulo.setText(mensajes.get(position).getMensaje());
        LocalDate fecha = mensajes.get(position).getFecha();
        DateTimeFormatter formateadorDeFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        textoFecha.setText(fecha.format(formateadorDeFecha));

        return view;
    }
}
