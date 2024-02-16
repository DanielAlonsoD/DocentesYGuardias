package com.example.docentesyguardias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * @author Daniel Alonso
 */
public class MenuPrincipalActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, NavigationBarView.OnItemSelectedListener {
    private NavController navController;

    private Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        navController = Navigation.findNavController(this, R.id.contenedorFragmentos);
        datos = getIntent().getExtras();

        MaterialToolbar encabezado = findViewById(R.id.encabezadoMenuPrincipal);
        encabezado.setOnMenuItemClickListener(this::onMenuItemClick);

        BottomNavigationView barraInferior = findViewById(R.id.barraInferiorMenuProfesor);
        barraInferior.setOnItemSelectedListener(this::onNavigationItemSelected);

        switch (datos.getInt("navegacionMenu")) {
            case 1:
                navController.navigate(R.id.inicioFragment, datos);
                break;
            case 2:
                navController.navigate(R.id.calendarioFragment, datos);
                datos.putInt("navegacionMenu", 1);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        boolean realizado = false;
        if (item.getItemId() == R.id.itemMensajes) {
            realizado = true;
            Intent actividadMensajes = new Intent(MenuPrincipalActivity.this, MensajesActivity.class);
            actividadMensajes.putExtras(datos);
            startActivity(actividadMensajes);
        } else if (item.getItemId() == R.id.itemPerfil) {
            realizado = true;
            Intent actividadPerfil = new Intent(MenuPrincipalActivity.this, PerfilActivity.class);
            actividadPerfil.putExtras(datos);
            startActivity(actividadPerfil);
        } else if (item.getItemId() == R.id.itemCerrarSesion) {
            realizado = true;
            Intent actividadMain = new Intent(MenuPrincipalActivity.this, MainActivity.class);
            startActivity(actividadMain);
        }
        return realizado;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        boolean realizado = false;
        if (item.getItemId() == R.id.itemInicio) {
            realizado = true;
            navController.navigate(R.id.inicioFragment, datos);
        } else if (item.getItemId() == R.id.itemCalendario) {
            realizado = true;
            navController.navigate(R.id.calendarioFragment, datos);
        } else if (item.getItemId() == R.id.itemNotificaciones) {
            realizado = true;
            navController.navigate(R.id.notificacionesFragment, datos);
        }
        return realizado;
    }
}