package Adaptadores;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import Tablas.Mensaje;

public class AdaptadorMensajes extends ArrayAdapter<Mensaje> {
    private ArrayList<Mensaje> lista;


    public AdaptadorMensajes(@NonNull Context contexto, int resource) {
        super(contexto, resource);
    }
}
