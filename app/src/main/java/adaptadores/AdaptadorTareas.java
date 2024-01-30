package adaptadores;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.docentesyguardias.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import tablas.Tarea;

public class AdaptadorTareas extends ArrayAdapter<Tarea> {
    private ArrayList<Tarea> tareas;


    public AdaptadorTareas(@NonNull Context context, @NonNull ArrayList<Tarea> tareas) {
        super(context, R.layout.elemento_tarea_lista, tareas);
        this.tareas = tareas;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.elemento_tarea_lista, parent, false);

        ImageView imagenTareaRealizada = view.findViewById(R.id.imagenElementoTarea);
        TextView textoTipoTarea = view.findViewById(R.id.textoTipoTareaElementoTarea);
        TextView textoRealizada = view.findViewById(R.id.textoRealizadaElementoTarea);
        TextView textoFechaEntrega = view.findViewById(R.id.textoFechaEntregaElementoTarea);

        boolean realizado = tareas.get(position).isRealizado();
        if (realizado) {
            imagenTareaRealizada.setImageResource(R.drawable.check);
            imagenTareaRealizada.setColorFilter(getContext().getResources().getColor(R.color.green_mantis));
            textoRealizada.setText(R.string.textoTareaRealizada);
        } else {
            imagenTareaRealizada.setImageResource(R.drawable.close);
            imagenTareaRealizada.setColorFilter(getContext().getResources().getColor(R.color.red));
            textoRealizada.setText(R.string.textoTareaNoRealizada);
        }

        textoTipoTarea.setText(tareas.get(position).getTipoTarea());
        textoFechaEntrega.setText(tareas.get(position).getFechaFin());

        return view;
    }
}
