package com.example.docentesyguardias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MenuPrincipalActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, NavigationBarView.OnItemSelectedListener {
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        navController = Navigation.findNavController(this, R.id.contenedorFragmentos);

        MaterialToolbar encabezado = findViewById(R.id.encabezadoMenuPrincipal);
        encabezado.setOnMenuItemClickListener(this::onMenuItemClick);

        BottomNavigationView barraInferior = findViewById(R.id.barraInferiorMenuProfesor);
        barraInferior.setOnItemSelectedListener(this::onNavigationItemSelected);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        boolean realizado = false;
        return realizado;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        boolean realizado = false;
        if (item.getItemId() == R.id.itemHome) {
            navController.navigate(R.id.homeFragment);
        } else if (item.getItemId() == R.id.itemCalendar) {
            navController.navigate(R.id.calendarFragment);
        } else if (item.getItemId() == R.id.itemNotifications) {
            navController.navigate(R.id.notificationsFragment);
        }
        return realizado;
    }
}