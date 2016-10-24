package com.example.nachoaguero.appgasolineras.Presentacion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nachoaguero.appgasolineras.R;

/**
 * Created by deivid on 20/10/16.
 */

public class VistaDetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent);
        Context context=this;

        ImageView imagen = (ImageView) findViewById(R.id.imageView);
        TextView precioDiesel = (TextView) findViewById(R.id.precioDiesel_valor);
        TextView precioGasolina95 = (TextView) findViewById(R.id.precioGasolinera_valor);
        TextView precioGasolina98 = (TextView) findViewById(R.id.precioGasolina98_valor);
        TextView precioDieselSuper = (TextView) findViewById(R.id.precioDieselSuper_valor);
        TextView horario = (TextView) findViewById(R.id.horario_valor);
        TextView direccion = (TextView) findViewById(R.id.direccionGasolinera_valor);
        TextView coordenadas = (TextView) findViewById(R.id.coordenadas_valor);
        TextView nombre=(TextView) findViewById(R.id.rotuloGasolinera);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String direccionvalor = (String) extras.get("direccion");
            direccion.setText(direccionvalor);
            String horariovalor = (String) extras.get("horario");
            horario.setText(horariovalor);
            double longitud=(double) extras.get("longitud");
            double latitud=(double) extras.get("latitud");
            coordenadas.setText("Latitud:"+String.valueOf(latitud)+"\n"+"Longitud:"+String.valueOf(longitud));
            double precioDieselValor=(double) extras.get("precioDiesel");
            if(precioDieselValor!=0.0) {
                precioDiesel.setText(String.valueOf(precioDieselValor) + "€");
            }
            double precioGasolina95Valor=(double) extras.get("precioGasolina");
            if(precioGasolina95Valor!=10000.0) {
                precioGasolina95.setText(String.valueOf(precioGasolina95Valor) + "€");
            }

            double precioGasolina98Valor=(double) extras.get("precioGasolina98");
            if(precioGasolina98Valor!=0.0) {
                precioGasolina98.setText(String.valueOf(precioGasolina98Valor) + "€");
            }
            double precioDieselSuperValor=(double) extras.get("precioGasoleoSuper");
            if (precioDieselSuperValor!=0.0) {
                precioDieselSuper.setText(String.valueOf(precioDieselSuperValor) + "€");
            }
            String rotuloValor=(String) extras.get("rotulo");
            int imageID = context.getResources().getIdentifier(rotuloValor.toLowerCase().trim(), "drawable", context.getPackageName());
            if (imageID == 0) {
                imagen.setImageResource(Integer.valueOf(R.drawable.por_defecto));

            } else {
                imagen.setImageResource(imageID);
            }
            nombre.setText(rotuloValor);




        }
    }
}
