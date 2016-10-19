package com.example.nachoaguero.appgasolineras.Presentacion;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nachoaguero.appgasolineras.Datos.Gasolinera;
import com.example.nachoaguero.appgasolineras.Negocio.DatosGasolineras;
import com.example.nachoaguero.appgasolineras.R;

import java.util.ArrayList;
import java.util.List;

public class ListaGasolinerasActivity extends AppCompatActivity {
    ListView list;
    DatosGasolineras datosGasolineras=new DatosGasolineras();
    private List<Gasolinera> gasolineras=new ArrayList<>();

    private class Hilo   extends AsyncTask<Void, Void, Boolean> {
        Context context;


        public Hilo(Context context) {
            this.context = context;

        }


        @Override
        protected Boolean doInBackground(Void... params) {
            Boolean res= datosGasolineras.obtenGasolineras();
            return res;

        }

        @Override
        protected void onPostExecute(Boolean b) {
            if (b) {
                HiloLectura hilolectura=new HiloLectura(context);
                hilolectura.execute();

            } else {
              //  Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_no_obtenidos), Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class HiloLectura extends AsyncTask<Void,Void,ArrayAdapter> {
        Context context;


        public HiloLectura(Context context) {
            this.context = context;

        }

        @Override
        protected ArrayAdapter<Gasolinera> doInBackground(Void...voids) {
            List<Gasolinera> gas = datosGasolineras.getListaGasolineras();
            ArrayAdapter<Gasolinera> adapter = new gasolineraArrayAdapter(context, 0, gas);
            return adapter;


        }

        @Override
        protected void onPostExecute(ArrayAdapter adapter){
            list.setAdapter(adapter);
        }


    }



        class gasolineraArrayAdapter extends ArrayAdapter<Gasolinera> {

            private Context context;
            private List<Gasolinera> gasolineras;

            public gasolineraArrayAdapter(Context context, int resource, List<Gasolinera> objects) {
                super(context, resource, objects);
                this.context = context;
                this.gasolineras = objects;
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                Gasolinera gasolinera = gasolineras.get(position);
                //get the inflater and inflate the XML layout for each item
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);


                View view = inflater.inflate(R.layout.interfaz_lista, null);


                 TextView nombre = (TextView) view.findViewById(R.id.nombre);
                 TextView gasolina = (TextView) view.findViewById(R.id.precio);
                 ImageView imagen = (ImageView) view.findViewById(R.id.image);


                 gasolina.setText("Gasolina: " + String.valueOf(gasolinera.getGasolina_95()) + "€");
                 int imageID = context.getResources().getIdentifier(gasolinera.getRotulo().toLowerCase().trim(), "drawable", context.getPackageName());
                //El nombre (referencia de la marca de la gasolinera) sólo se muestra si la marca es desconocida.
                 if (imageID == 0) {
                 imagen.setImageResource(Integer.valueOf(R.drawable.por_defecto));
                 nombre.setText(gasolinera.getRotulo());

                 } else {
                 imagen.setImageResource(imageID);
                 }

                return view;
            }
        }

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                list = (ListView) findViewById(R.id.customListView);
                Hilo a = new Hilo(this);
                a.execute();
            }
        }

