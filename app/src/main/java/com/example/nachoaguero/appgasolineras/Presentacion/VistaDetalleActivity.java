package com.example.nachoaguero.appgasolineras.Presentacion;

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

        ImageView imagen=(ImageView) findViewById(R.id.imageView);
        TextView precioDiesel=(TextView) findViewById(R.id.precioDiesel_valor);
        TextView precioGasolina95=(TextView) findViewById(R.id.precioGasolinera_valor);
        TextView precioGasolina98=(TextView) findViewById(R.id.precioGasolina98_valor);
        TextView precioDieselSuper=(TextView) findViewById(R.id.precioDieselSuper_valor);
        TextView horario=(TextView) findViewById(R.id.horario_valor);
        TextView direccion=(TextView) findViewById(R.id.direccionGasolinera_valor);
        TextView coordenadas=(TextView) findViewById(R.id.coordenadas_valor);
    }
}
