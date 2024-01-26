package com.example.docentesyguardias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

public class AsignarGuardiaActivity extends AppCompatActivity implements View.OnClickListener {
    private Bundle usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_guardia);

        MaterialToolbar encabezado = findViewById(R.id.encabezadoAsignarGuardia);

        encabezado.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent actividadMenuPrincipal = new Intent(this, MenuPrincipalActivity.class);
        actividadMenuPrincipal.putExtras(usuario);
        startActivity(actividadMenuPrincipal);
    }
}