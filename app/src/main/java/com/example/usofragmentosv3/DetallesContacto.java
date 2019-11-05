package com.example.usofragmentosv3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetallesContacto extends AppCompatActivity {

    TextView lblUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_contacto);

        lblUsuario = findViewById(R.id.lblUsuario);
        Bundle parametros = this.getIntent().getExtras();
        if(parametros != null){
            String recibidos = getIntent().getExtras().getString("nombre");
            lblUsuario.setText(recibidos);
        }


    }
}
