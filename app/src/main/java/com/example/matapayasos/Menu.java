package com.example.matapayasos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
    Button btnJugar, btnPuntuacion, btnAcercaDe, btnCerrarSesion;

    TextView txtTituloMenu, txtUidJugadorMenu, txtZombieMenu, txtSubTituloMenu,
            txtCorreoJugadorMenu, txtNombreJugadorMenu;

    FirebaseDatabase database;
    DatabaseReference jugadores;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        txtTituloMenu = findViewById(R.id.txtTituloMenu);
        txtUidJugadorMenu = findViewById(R.id.txtUidMenu);
        txtSubTituloMenu = findViewById(R.id.txtSubtituloBotones);
        txtCorreoJugadorMenu = findViewById(R.id.txtCorreoMenu);
        txtNombreJugadorMenu = findViewById(R.id.txtNombreMenu);
        txtZombieMenu = findViewById(R.id.txtZombies);

        btnJugar = findViewById(R.id.btnJugar);
        btnPuntuacion = findViewById(R.id.btnPuntaciones);
        btnAcercaDe = findViewById(R.id.btnHacerca);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        imagen = findViewById(R.id.imageGif);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        jugadores = database.getReference("MI DATA BASE JUGADORES");

        btnJugar.setOnClickListener((event) -> {


            Intent intent = new Intent(Menu.this, EscenarioJuego.class);

            String nombre = txtNombreJugadorMenu.getText() +"";
            String uId = txtUidJugadorMenu.getText() +"";
            String email = txtCorreoJugadorMenu.getText() +"";
            String zombies = txtZombieMenu.getText() +"";

            intent.putExtra("uId", uId);
            intent.putExtra("nombres", nombre);
            intent.putExtra("email", email);
            intent.putExtra("zombie", zombies);
            startActivity(intent);


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

        String url = "https://i.imgur.com/lMHQTjI.gif";
        Uri urlParse = Uri.parse(url);
        Glide.with(getApplicationContext()).load(urlParse).into(imagen);

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
        Toast.makeText(this, "Sesi??n cerrada exitosamente", Toast.LENGTH_SHORT).show();

    }

    private void consulta() {
        Query query = jugadores.orderByChild("Email").equalTo(user.getEmail());

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {

                    String email = ds.child("Email").getValue() + "";
                    String uId = ds.child("Uid").getValue() + "";
                    String nombres = ds.child("Nombres").getValue() + "";
                    String zombies = ds.child("Zombies").getValue() + "";
                    txtCorreoJugadorMenu.setText(email);
                    txtNombreJugadorMenu.setText(nombres);
                    txtZombieMenu.setText(zombies);
                    txtUidJugadorMenu.setText(uId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}