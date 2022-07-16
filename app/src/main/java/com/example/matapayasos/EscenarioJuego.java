package com.example.matapayasos;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class EscenarioJuego extends AppCompatActivity {


    String nombre,email, uId, zombie;
    TextView txtContador;
    TextView txtNombre;
    TextView txtTiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escenario_juego);

        txtContador = findViewById(R.id.txtContadorEsc);
        txtNombre = findViewById(R.id.txtNombreEs);
        txtTiempo = findViewById(R.id.txtTiempoEsc);

        Bundle intent = getIntent().getExtras();


        uId = intent.getString("uId");
        nombre = intent.getString("nombres");
        email = intent.getString("email");
         zombie= intent.getString("zombie");

         txtNombre.setText(nombre);
         txtContador.setText(zombie);
        Typeface typeface = Typeface.createFromAsset(EscenarioJuego.this.getAssets(), "fuentes/zombie.TTF");

        txtNombre.setTypeface(typeface);
        txtContador.setTypeface(typeface);
        txtTiempo.setTypeface(typeface);

    }
}