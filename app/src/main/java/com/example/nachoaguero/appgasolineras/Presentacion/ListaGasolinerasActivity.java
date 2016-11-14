package com.example.nachoaguero.appgasolineras.Presentacion;

import android.app.Activity;
import android.util.Log;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
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

    //atributos de la activity principal
    ListView list;
    IGestionGasolinera gestionGasolinera = new GestionGasolinera(this);
    double latitudActual;
    double longitudActual;

    //bloque ubicacion
    private LocationManager locationManager;
    private LocationListener listener;




    private TextView t;

    private class Hilo extends AsyncTask<Void, Void, Boolean> {
        Context context;
        ProgressDialog progress;


        public Hilo(Context context) {
            this.context = context;
            progress = new ProgressDialog(context);
            progress.setMessage("Cargando datos de las gasolineras");

        }

        protected Boolean conectadoWifi() {

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

        protected Boolean conectadoDatos() {
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
        protected void onPreExecute() {
            progress.show();

        }


        @Override
        protected Boolean doInBackground(Void... params) {

            boolean res = false;
            if (conectadoWifi()) {

                res = gestionGasolinera.obtenGasolineras();
            } else {
                if(conectadoDatos()){

                    res = gestionGasolinera.obtenGasolineras();
                } else {

                    res = gestionGasolinera.obtenGasolinerasSinconexion();
                }

            }
            return res;
        }


        @Override
        protected void onPostExecute(Boolean b) {
            progress.dismiss();
            if (b) {
                HiloLectura hilolectura = new HiloLectura(context);
                hilolectura.execute();
                if (conectadoDatos() == false && conectadoWifi() == false) {
                    TextView actualizado = (TextView) findViewById(R.id.textFechaActualizacion);
                    actualizado.setText("No Actualizado. Datos previos");
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_conexion)
                            +getResources().getString(R.string.ubicacion_default), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_obtenidos), Toast.LENGTH_SHORT).show();
                }

            } else {
                if (conectadoDatos() || conectadoWifi()) {
                    TextView actualizado = (TextView) findViewById(R.id.textFechaActualizacion);
                    actualizado.setText("No Actualizado. Sin acceso a los datos");
                    TextView gasolina = (TextView) findViewById(R.id.textTipoGasolina);
                    gasolina.setText(" ");
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_no_obtenidos), Toast.LENGTH_SHORT).show();

                } else {
                    TextView actualizado = (TextView) findViewById(R.id.textFechaActualizacion);
                    actualizado.setText("No Actualizado. Sin acceso a internet");
                    TextView gasolina = (TextView) findViewById(R.id.textTipoGasolina);
                    gasolina.setText(" ");
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_conexion), Toast.LENGTH_SHORT).show();


                }
            }

        }
    }

    private class HiloLectura extends AsyncTask<Void, Void, ArrayAdapter> {
        Context context;


        public HiloLectura(Context context) {
            this.context = context;

        }

        @Override
        protected ArrayAdapter<Gasolinera> doInBackground(Void... voids) {
            gestionGasolinera.ordenaGasolinerasPorPrecio();
            List<Gasolinera> gas = gestionGasolinera.getListaGasolineras();
            ArrayAdapter<Gasolinera> adapter = new gasolineraArrayAdapter(context, 0, gas);
            return adapter;


        }

        @Override
        protected void onPostExecute(ArrayAdapter adapter) {
            if (adapter.isEmpty()) {
                TextView actualizado = (TextView) findViewById(R.id.textFechaActualizacion);
                actualizado.setText("Actualizado. No existen datos para esta búsqueda");
                TextView gasolina = (TextView) findViewById(R.id.textTipoGasolina);
                gasolina.setText(" ");

            }
            list.setAdapter(adapter);


        }
    }



    public class gasolineraArrayAdapter extends ArrayAdapter<Gasolinera> {


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
            TextView distancia = (TextView) view.findViewById(R.id.distancia);

            localidad.setText(gasolinera.getProvincia() + ", " + gasolinera.getLocalidad());


            if (((Double) gasolinera.getGasolina_95()).equals(10000.0)) {
                gasolina.setText("No disponible");
            } else {
                gasolina.setText(String.valueOf(gasolinera.getGasolina_95()) + "€/L");
            }
            int imageID = context.getResources().getIdentifier("drawable/" + gasolinera.getRotulo().toLowerCase().trim(), null, context.getPackageName());

            //El nombre (referencia de la marca de la gasolinera) sólo se muestra si la marca es desconocida.
            if (imageID == 0) {
                imagen.setImageResource(Integer.valueOf(R.drawable.por_defecto));
                nombre.setText(gasolinera.getRotulo());

            } else {
                imagen.setImageResource(imageID);
                nombre.setText(gasolinera.getRotulo());
            }


            if ((Math.abs(latitudActual-0.0)<0.000000001)&& (Math.abs(longitudActual-0.0)<0.000000001)) {
                //Calculo de la distancia respecto al centro de Santander
                //Posicion ayuntamiento santander: 43.462175, -3.809989
                distancia.setText(String.format("%.2f", gestionGasolinera.DistanciaKm(43.462175, -3.809989,
                        gasolinera.getLatitud(), gasolinera.getLongitud())) + "Km");
            } else {
                //Calculo de la distancia respecto a la posición actual
                distancia.setText(String.format("%.2f", gestionGasolinera.DistanciaKm(latitudActual, longitudActual,
                        gasolinera.getLatitud(), gasolinera.getLongitud())) + "Km");
            }

            return view;
        }


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //servicio de localizacion
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //listener que detecta la ubicacion
        listener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                latitudActual=location.getLatitude();
                longitudActual=location.getLongitude();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);

            }
        };
        configuraPermisos();

        //se apunta a la lista
        list = (ListView) findViewById(R.id.customListView);
        //se crea y ejecuta hilo para pedir las gasolineras
        Hilo a = new Hilo(this);
        a.execute();






        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                intent = new Intent(getApplicationContext(), VistaDetalleActivity.class);
                Gasolinera listItem = (Gasolinera) list.getItemAtPosition(position);
                String direccion = listItem.getDireccion();
                double precioDiesel = listItem.getGasoleo_a();
                double precioGasolina = listItem.getGasolina_95();
                String horario = listItem.getHorario();
                double precioGasolina98 = listItem.getGasolina_98();
                double precioGasoleoSuper = listItem.getGasoleoSuper();
                double longitud = listItem.getLongitud();
                double latitud = listItem.getLatitud();
                String rotulo = listItem.getRotulo();
                intent.putExtra("direccion", direccion);
                intent.putExtra("horario", horario);
                intent.putExtra("longitud", longitud);
                intent.putExtra("latitud", latitud);
                intent.putExtra("precioDiesel", precioDiesel);
                intent.putExtra("precioGasolina", precioGasolina);
                intent.putExtra("precioGasolina98", precioGasolina98);
                intent.putExtra("precioGasoleoSuper", precioGasoleoSuper);
                intent.putExtra("rotulo", rotulo);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                configuraPermisos();
                break;
            default:
                break;
        }
    }


    private void configuraPermisos(){
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }
        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.



        //noinspection MissingPermission
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, listener);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Boolean res=false;
        if(item.getItemId()==R.id.filtro_setting){
            displayPopupWindow();
            res=true;
        }
        return res;
    }

    public void displayPopupWindow() {

        //LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        //View popupView = layoutInflater.inflate(R.layout.popup, null);
        PopupWindow popup = new PopupWindow();
        View layout = getLayoutInflater().inflate(R.layout.popup, null);
        popup.setContentView(layout);
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);
        visualizaTiposCarburante(popup);
        visualizaMarcas(popup);

    }

    public void visualizaTiposCarburante(PopupWindow popup){

    }

    public void visualizaMarcas(PopupWindow popup){

    }

}

