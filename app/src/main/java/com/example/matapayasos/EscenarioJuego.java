package com.example.matapayasos;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class EscenarioJuego extends AppCompatActivity {


    String nombre,email, uId, zombie;
    TextView txtContador;
    TextView txtNombre;
    TextView txtTiempo;
     ImageView imgZombie;

    private Random aleatorio;
    private int anchoPantalla;
    private int altoPantalla;
    int contador =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escenario_juego);

        txtContador = findViewById(R.id.txtContadorEsc);
        txtNombre = findViewById(R.id.txtNombreEs);
        txtTiempo = findViewById(R.id.txtTiempoEsc);
        imgZombie = findViewById(R.id.imgJuego);
        Bundle intent = getIntent().getExtras();


        uId = intent.getString("uId");
        nombre = intent.getString("nombres");
        email = intent.getString("email");
        zombie= intent.getString("zombie");


         txtNombre.setText(nombre);
         txtContador.setText(zombie);

         pantalla();
         imgZombie.setOnClickListener((event)->{
             contador++;
             txtContador.setText(String.valueOf(contador));
             imgZombie.setImageResource(R.drawable.tumba);

             new Handler().postDelayed((  ()-> {
                 movimiento();
                 imgZombie.setImageResource(R.drawable.icono_app);

         }),500);

         } );

        Typeface typeface = Typeface.createFromAsset(EscenarioJuego.this.getAssets(), "fuentes/zombie.TTF");

        txtNombre.setTypeface(typeface);
        txtContador.setTypeface(typeface);
        txtTiempo.setTypeface(typeface);

        cuentaAtras();
    }
    private void  cuentaAtras(){

        new CountDownTimer(10000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                long segundosRestantes =   millisUntilFinished/1000;
                txtTiempo.setText(segundosRestantes +" s");
            }

            @Override
            public void onFinish() {
                txtTiempo.setText("0s");

            }
        }.start();
    }

    private void pantalla(){

        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        altoPantalla = point.y;
        anchoPantalla = point.x;
        aleatorio = new Random();
    }
    private void movimiento(){

        int min = 0;

        int maxX = anchoPantalla - imgZombie.getWidth();
        int maxY = anchoPantalla - imgZombie.getHeight();
        int randomX = aleatorio.nextInt(    ((maxX - min) + 1) + min );
        int randomY = aleatorio.nextInt(    ((maxY - min) + 1) + min );
        imgZombie.setX(randomX);
        imgZombie.setY(randomY);
    }
}