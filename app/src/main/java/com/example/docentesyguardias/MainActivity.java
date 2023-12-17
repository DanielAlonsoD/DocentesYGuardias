package com.example.docentesyguardias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonIniciarSesion = findViewById(R.id.botonIniciarSesionMain);
        Button botonRegistrarse = findViewById(R.id.botonRegistrarseMain);

        botonIniciarSesion.setOnClickListener(this);
        botonRegistrarse.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.botonIniciarSesionMain) {
            Intent actividadIniciarSesion = new Intent(this, InicioSesionActivity.class);
            startActivity(actividadIniciarSesion);
        } else if (view.getId() == R.id.botonRegistrarseMain) {
            Intent actividadRegistrarse = new Intent(this, RegistroActivity.class);
            startActivity(actividadRegistrarse);
        }
    }
}