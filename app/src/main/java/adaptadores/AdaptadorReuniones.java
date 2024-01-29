package adaptadores;

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
import com.google.android.material.button.MaterialButton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import tablas.Reunion;


public class AdaptadorReuniones extends ArrayAdapter<Reunion> implements View.OnClickListener{
    private ArrayList<Reunion> reuniones;
    private ImageView imagenAsistencia;
    private TextView textoAsistencia;
    private int itemSelected;


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
        MaterialButton botonCambiarAsistencia = view.findViewById(R.id.botonCambiarAsistenciaReunion);

        String asistencia = reuniones.get(position).getAsistencia();

        textoCreadorReunion.setText(reuniones.get(position).getDniProfesor().toString());

        establecerAsistencia(asistencia);

        LocalDateTime fechaHora = reuniones.get(position).getFechaHora();
        DateTimeFormatter formateadorDeFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        textoFechaHora.setText(fechaHora.format(formateadorDeFechaHora));

        botonCambiarAsistencia.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        String[] opciones = {"Asistiré", "No Asistiré", "No Sé Si Asistiré"};
        itemSelected = -1;

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle(R.string.textoTituloCambiarAsistencia);
        builder.setSingleChoiceItems(opciones, itemSelected, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                itemSelected = which;
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                establecerAsistencia(opciones[itemSelected]);
            }
        });
        builder.show();
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
