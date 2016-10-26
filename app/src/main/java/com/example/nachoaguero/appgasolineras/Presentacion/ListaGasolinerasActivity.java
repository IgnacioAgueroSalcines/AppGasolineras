package com.example.nachoaguero.appgasolineras.Presentacion;

import android.app.Activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nachoaguero.appgasolineras.Datos.Gasolinera;
import com.example.nachoaguero.appgasolineras.Negocio.GestionGasolinera;
import com.example.nachoaguero.appgasolineras.Negocio.IGestionGasolinera;
import com.example.nachoaguero.appgasolineras.R;


import java.util.List;

public class ListaGasolinerasActivity extends AppCompatActivity {
    ListView list;
    IGestionGasolinera gestionGasolinera =new GestionGasolinera(this);



    private class Hilo   extends AsyncTask<Void, Void, Boolean> {
        Context context;
        ProgressDialog progress;


        public Hilo(Context context) {
            this.context = context;
            progress=new ProgressDialog(context);
            progress.setMessage("Cargando datos de las gasolineras");

        }

        protected  Boolean conectadoWifi(){

            ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (info != null) {
                    if (info.isConnected()) {

                        return true;

                    }
                }
            }

            return false;

        }

        protected  Boolean conectadoDatos(){
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
              if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }
        @Override
        protected void onPreExecute(){
            progress.show();

        }


    @Override
        protected Boolean doInBackground(Void... params) {

            boolean res=false;
            if(conectadoWifi()) {

                res = gestionGasolinera.obtenGasolineras();
            } else {
                if(conectadoDatos()){
                    res = gestionGasolinera.obtenGasolineras();
                } else{

                    res=gestionGasolinera.obtenGasolinerasSinconexion();
                }

            }
            return res;
        }




        @Override
        protected void onPostExecute(Boolean b) {
            progress.dismiss();
            if (b) {
                HiloLectura hilolectura=new HiloLectura(context);
                hilolectura.execute();
                if(conectadoDatos()==false && conectadoWifi()==false){
                    TextView actualizado = (TextView) findViewById(R.id.textFechaActualizacion);
                    actualizado.setText("No Actualizado. Datos previos");

                }

            } else {
                if (conectadoDatos() || conectadoWifi()) {
                    TextView actualizado = (TextView) findViewById(R.id.textFechaActualizacion);
                    actualizado.setText("No Actualizado. Sin acceso a los datos");
                    TextView gasolina=(TextView) findViewById(R.id.textTipoGasolina);
                    gasolina.setText(" ");
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_no_obtenidos), Toast.LENGTH_SHORT).show();

                } else {
                    TextView actualizado = (TextView) findViewById(R.id.textFechaActualizacion);
                    actualizado.setText("No Actualizado. Sin acceso a internet");
                    TextView gasolina=(TextView) findViewById(R.id.textTipoGasolina);
                    gasolina.setText(" ");
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_conexion), Toast.LENGTH_SHORT).show();


                }
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
            gestionGasolinera.ordenaGasolinerasPorPrecio();
            List<Gasolinera> gas = gestionGasolinera.getListaGasolineras();
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
                 TextView localidad = (TextView) view.findViewById(R.id.direccion);

                localidad.setText(gasolinera.getProvincia()+", "+gasolinera.getLocalidad());


                  if(((Double) gasolinera.getGasolina_95()).equals(10000.0)){
                     gasolina.setText("No disponible");
                 } else {
                     gasolina.setText(String.valueOf(gasolinera.getGasolina_95()) + "€/L");
                 }
                 int imageID = context.getResources().getIdentifier("drawable/"+gasolinera.getRotulo().toLowerCase().trim(), null, context.getPackageName());

                //El nombre (referencia de la marca de la gasolinera) sólo se muestra si la marca es desconocida.
                 if (imageID == 0) {
                 imagen.setImageResource(Integer.valueOf(R.drawable.por_defecto));
                 nombre.setText(gasolinera.getRotulo());

                 } else {
                 imagen.setImageResource(imageID);
                 nombre.setText(gasolinera.getRotulo());
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

                list.setClickable(true);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object listItem = list.getItemAtPosition(position);
                    }
                });
            }
        }

