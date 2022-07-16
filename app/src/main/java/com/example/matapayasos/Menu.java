package com.example.matapayasos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity {


     FirebaseAuth auth;
     FirebaseUser user;
     Button btnJugar;
     Button btnPuntuacion;
     Button btnAcercaDe;
     Button btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnJugar = findViewById(R.id.btnJugar);
        btnPuntuacion = findViewById(R.id.btnPuntaciones);
        btnAcercaDe = findViewById(R.id.btnHacerca);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        btnJugar.setOnClickListener( (event)-> {});
        btnPuntuacion.setOnClickListener( (event)-> {});
        btnAcercaDe.setOnClickListener( (event)-> {});

        btnCerrarSesion.setOnClickListener( (event)-> cerrarSesion());

    }

    @Override
    protected void onStart() {
        usuarioLogeado();
        super.onStart();
    }

    private void usuarioLogeado() {

        if (user != null) {
            Toast.makeText(this, "en linea", Toast.LENGTH_LONG).show();
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void cerrarSesion(){
        auth.signOut();
        startActivity(new Intent(Menu.this, MainActivity.class));
        Toast.makeText(this, "Sesión cerrada exitosamente", Toast.LENGTH_SHORT).show();

    }

}