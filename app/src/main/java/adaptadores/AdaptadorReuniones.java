package adaptadores;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
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
import com.google.android.material.button.MaterialButton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import tablas.Reunion;


public class AdaptadorReuniones extends ArrayAdapter<Reunion> implements View.OnClickListener{
    private ArrayList<Reunion> reuniones;


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

        ImageView imagenAsistencia = view.findViewById(R.id.imagenElementoReunion);
        TextView textoCreadorReunion = view.findViewById(R.id.textoCreadorElementoReunion);
        TextView textoAsistencia = view.findViewById(R.id.textoAusenciaElementoReunion);
        TextView textoFechaHora = view.findViewById(R.id.textoFechaHoraElementoReunion);
        MaterialButton botonCambiarAsistencia = view.findViewById(R.id.botonCambiarAsistenciaReunion);

        String asistencia = reuniones.get(position).getAsistencia();

        textoCreadorReunion.setText(reuniones.get(position).getDniProfesor().toString());

        if (asistencia.equals("Asistiré")) {
            imagenAsistencia.setImageResource(R.drawable.check_red);
           //imagenAsistencia.setImageTintList(ColorStateList.valueOf(R.color.green_mantis));
            textoAsistencia.setText(R.string.textoSiAsistir);
        } else if (asistencia.equals("No Asistiré")) {
            imagenAsistencia.setImageResource(R.drawable.close);
            imagenAsistencia.setColorFilter(R.color.red);
            textoAsistencia.setText(R.string.textoNoAsistir);
        }

        LocalDateTime fechaHora = reuniones.get(position).getFechaHora();
        DateTimeFormatter formateadorDeFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        textoFechaHora.setText(fechaHora.format(formateadorDeFechaHora));

        botonCambiarAsistencia.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        String[] opciones = {"Asistiré", "No Asistiré", "No Sé Si Asistiré"};

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle(R.string.textoTituloCambiarAsistencia);
        builder.setMessage(R.string.textoCambiarAsistenciaDescripción);
        builder.setSingleChoiceItems(opciones, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }
}
