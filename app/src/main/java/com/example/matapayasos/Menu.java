package com.example.matapayasos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Menu extends AppCompatActivity {


    FirebaseAuth auth;
    FirebaseUser user;
    Button btnJugar;
    Button btnPuntuacion;
    Button btnAcercaDe;
    Button btnCerrarSesion;

    TextView txtTituloMenu;
    TextView txtZombieMenu;
    TextView txtSubTituloMenu;
    TextView txtCorreoJugadorMenu;
    TextView txtNombreJugadorMenu;

    FirebaseDatabase database;
    DatabaseReference jugadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        txtTituloMenu = findViewById(R.id.txtTituloMenu);
        txtSubTituloMenu = findViewById(R.id.txtSubtituloBotones);
        txtCorreoJugadorMenu = findViewById(R.id.txtCorreoMenu);
        txtNombreJugadorMenu = findViewById(R.id.txtNombreMenu);
        txtZombieMenu = findViewById(R.id.txtZombies);

        btnJugar = findViewById(R.id.btnJugar);
        btnPuntuacion = findViewById(R.id.btnPuntaciones);
        btnAcercaDe = findViewById(R.id.btnHacerca);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        jugadores = database.getReference("MI DATA BASE JUGADORES");

        btnJugar.setOnClickListener((event) -> {
        });
        btnPuntuacion.setOnClickListener((event) -> {
        });
        btnAcercaDe.setOnClickListener((event) -> {
        });


        Typeface typeface = Typeface.createFromAsset(Menu.this.getAssets(), "fuentes/zombie.TTF");

        txtTituloMenu.setTypeface(typeface);
        txtZombieMenu.setTypeface(typeface);
        txtSubTituloMenu.setTypeface(typeface);
        txtCorreoJugadorMenu.setTypeface(typeface);
        txtNombreJugadorMenu.setTypeface(typeface);


        btnJugar.setTypeface(typeface);
        btnPuntuacion.setTypeface(typeface);
        btnAcercaDe.setTypeface(typeface);
        btnCerrarSesion.setTypeface(typeface);


        btnCerrarSesion.setOnClickListener((event) -> cerrarSesion());

    }

    @Override
    protected void onStart() {
        usuarioLogeado();
        super.onStart();
    }

    private void usuarioLogeado() {

        if (user != null) {
            consulta();
            Toast.makeText(this, "en linea", Toast.LENGTH_LONG).show();
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void cerrarSesion() {
        auth.signOut();
        startActivity(new Intent(Menu.this, MainActivity.class));
        Toast.makeText(this, "Sesión cerrada exitosamente", Toast.LENGTH_SHORT).show();

    }

    private void consulta() {
        Query query = jugadores.orderByChild("Email").equalTo(user.getEmail());

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {

                    String email = ds.child("Email").getValue() + "";
                    String nombres = ds.child("Nombres").getValue() + "";
                    String zombies = ds.child("Zombies").getValue() + "";
                    txtCorreoJugadorMenu.setText(email);
                    txtNombreJugadorMenu.setText(nombres);
                    txtZombieMenu.setText(zombies);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}