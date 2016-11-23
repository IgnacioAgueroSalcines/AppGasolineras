package com.example.nachoaguero.appgasolineras.Presentacion;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.Double.*;
import java.util.List;
import java.util.ArrayList;

import com.example.nachoaguero.appgasolineras.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import android.location.Location;
import android.location.LocationManager;
import android.location.Criteria;

import static com.example.nachoaguero.appgasolineras.R.id.coordenadas;

/**
 * Created by deivid on 20/10/16.
 */

public class VistaDetalleActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private TextView t;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent);
        Context context = this;

        ImageView imagen = (ImageView) findViewById(R.id.imageView);
        TextView precioDiesel = (TextView) findViewById(R.id.precioDiesel_valor);
        TextView precioGasolina95 = (TextView) findViewById(R.id.precioGasolinera_valor);
        TextView precioGasolina98 = (TextView) findViewById(R.id.precioGasolina98_valor);
        TextView precioDieselSuper = (TextView) findViewById(R.id.precioDieselSuper_valor);
        TextView horario = (TextView) findViewById(R.id.horario_valor);
        TextView direccion = (TextView) findViewById(R.id.direccionGasolinera_valor);
        TextView coordenadas = (TextView) findViewById(R.id.coordenadas_valor);
        t = (TextView) findViewById(R.id.coordenadas_valor);
        TextView nombre = (TextView) findViewById(R.id.rotuloGasolinera);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String direccionvalor = (String) extras.get("direccion");
            direccion.setText(direccionvalor);
            String horariovalor = (String) extras.get("horario");
            horario.setText(horariovalor);


            double precioDieselValor = (double) extras.get("precioDiesel");
            String unidadesPrecio = "€/L";
            if (Double.compare(precioDieselValor, Double.MAX_VALUE) != 0) {
                precioDiesel.setText(String.valueOf(precioDieselValor) + unidadesPrecio);
            }
            double precioGasolina95Valor = (double) extras.get("precioGasolina");
            if (Double.compare(precioGasolina95Valor, 0.0) != 0) {
                if (Double.compare(precioGasolina95Valor, Double.MAX_VALUE) == 0) {
                    precioGasolina95.setText("No disponible");
                } else {
                    precioGasolina95.setText(String.valueOf(precioGasolina95Valor) + unidadesPrecio);
                }
            }
            double precioGasolina98Valor = (double) extras.get("precioGasolina98");
            if (Double.compare(precioGasolina98Valor, Double.MAX_VALUE) == 0) {

                precioGasolina98.setText("No disponible");

            } else {

                precioGasolina98.setText(String.valueOf(precioGasolina98Valor) + unidadesPrecio);

            }
            double precioDieselSuperValor = (double) extras.get("precioGasoleoSuper");
            if (Double.compare(precioDieselSuperValor, 0.0) != 0) {
                if (Double.compare(precioDieselSuperValor, Double.MAX_VALUE) == 0) {
                    precioDieselSuper.setText("No disponible");
                } else {
                    precioDieselSuper.setText(String.valueOf(precioDieselSuperValor) + unidadesPrecio);
                }
            }
            String rotuloValor = (String) extras.get("rotulo");
            int imageID = context.getResources().getIdentifier("drawable/" + rotuloValor.toLowerCase().trim(), null, context.getPackageName());
            if (imageID == 0) {
                imagen.setImageResource(Integer.valueOf(R.drawable.por_defecto));

            } else {
                imagen.setImageResource(imageID);
            }
            nombre.setText(rotuloValor);

            double latitud = (double) extras.get("latitud");
            double longitud = (double) extras.get("longitud");
            coordenadas.setText(String.valueOf(latitud) + "\n" + String.valueOf(longitud));

        }
    }



}

