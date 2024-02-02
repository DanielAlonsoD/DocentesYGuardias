package adaptadores;

import static androidx.core.app.ActivityCompat.recreate;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Build;
import android.util.Log;
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
import com.example.docentesyguardias.ReunionesFragment;
import com.google.android.material.button.MaterialButton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import tablas.Reunion;


public class AdaptadorReuniones extends ArrayAdapter<Reunion> {
    private ArrayList<Reunion> reuniones;
    private ImageView imagenAsistencia;
    private TextView textoAsistencia;
    private int posicion;


    public AdaptadorReuniones(@NonNull Context contexto, @NonNull ArrayList<Reunion> reuniones) {
        super(contexto, R.layout.elemento_reunion_lista, reuniones);
        this.reuniones = reuniones;
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.elemento_reunion_lista, parent, false);

        imagenAsistencia = view.findViewById(R.id.imagenElementoReunion);
        TextView textoCreadorReunion = view.findViewById(R.id.textoCreadorElementoReunion);
        textoAsistencia = view.findViewById(R.id.textoAusenciaElementoReunion);
        TextView textoFechaHora = view.findViewById(R.id.textoFechaHoraElementoReunion);

        String asistencia = reuniones.get(position).getAsistencia();

        textoCreadorReunion.setText(reuniones.get(position).getDniProfesor().toString());
        textoFechaHora.setText(reuniones.get(position).getFechaHora());

        establecerAsistencia(asistencia);

        return view;
    }

    public void establecerAsistencia(String asistencia) {
        if (asistencia.equals("Asistiré")) {
            imagenAsistencia.setImageResource(R.drawable.check);
            imagenAsistencia.setColorFilter(getContext().getResources().getColor(R.color.green_mantis));
            textoAsistencia.setText(R.string.textoSiAsistir);
        } else if (asistencia.equals("No Asistiré")) {
            imagenAsistencia.setImageResource(R.drawable.close);
            imagenAsistencia.setColorFilter(getContext().getResources().getColor(R.color.red));
            textoAsistencia.setText(R.string.textoNoAsistir);
        } else {
            imagenAsistencia.setImageResource(R.drawable.question_mark);
            imagenAsistencia.setColorFilter(getContext().getResources().getColor(R.color.orange_tree_poppy));
            textoAsistencia.setText(R.string.textoNoSeSiAsistir);
        }
    }
}
