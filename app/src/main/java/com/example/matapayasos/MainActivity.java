package com.example.matapayasos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

     Button btnLogin;
     Button btnRegistro;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         btnLogin = findViewById(R.id.btnLogin);
         btnRegistro = findViewById(R.id.btnRegistro);

         btnLogin.setOnClickListener( (event) -> {

         });

         btnRegistro.setOnClickListener( (event) -> {
             Intent intent = new Intent(this, Registro.class);
             startActivity(intent);
         });



     }

}