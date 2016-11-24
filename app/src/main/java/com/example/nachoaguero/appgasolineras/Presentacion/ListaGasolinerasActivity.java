package com.example.nachoaguero.appgasolineras.Presentacion;

import android.app.Activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
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
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.nachoaguero.appgasolineras.Datos.Gasolinera;
import com.example.nachoaguero.appgasolineras.Negocio.GestionGasolinera;
import com.example.nachoaguero.appgasolineras.Negocio.IGestionGasolinera;
import com.example.nachoaguero.appgasolineras.R;

import java.util.List;

public class ListaGasolinerasActivity extends AppCompatActivity {

    //atributos de la activity principal
    ListView list;
    TextView tipoText;
    IGestionGasolinera gestionGasolinera = new GestionGasolinera(this);
    private List<Gasolinera> gasolinerasTotal;

    double latitudActual;
    double longitudActual;
    double distanciaFinal;

    //bloque ubicacion
    private LocationManager locationManager;
    private LocationListener listener;

    //spinners
    private Spinner listaMarcas;
    private Spinner listaCarburantes;
    private int check=0;
    //popup
    private PopupWindow popup;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);

        //Boton Ordenar por distancia de la toolbar
        MenuItem b1 =  menu.findItem(R.id.ordenarKm);
        b1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ListaGasolinerasActivity.this.gestionGasolinera.ordenaGasolinerasPorDistancia();
                gasolinerasTotal = gestionGasolinera.getListaGasolineras();
                ArrayAdapter<Gasolinera> adapter = new gasolineraArrayAdapter(ListaGasolinerasActivity.this, 0, gasolinerasTotal);
                list.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                list.invalidateViews();
                return true;
            }

        });

        //Boton Ordenar por precio de la toolbar
        MenuItem b2 =  menu.findItem(R.id.ordenarPrecio);
        b2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                TextView text = (TextView) findViewById(R.id.textTipoGasolina);
                String tipo = String.valueOf(text.getText());
                ListaGasolinerasActivity.this.gestionGasolinera.ordenaGasolinerasPorPrecio(tipo);
                gasolinerasTotal = gestionGasolinera.getListaGasolineras();
                ArrayAdapter<Gasolinera> adapter = new gasolineraArrayAdapter(ListaGasolinerasActivity.this, 0, gasolinerasTotal);
                list.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                list.invalidateViews();
                return true;
            }

        });

        return super.onCreateOptionsMenu(menu);

    }

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
                if (conectadoDatos()) {
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
                            , Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_obtenidos), Toast.LENGTH_LONG).show();
                }

            } else {
                if (conectadoDatos() || conectadoWifi()) {
                    TextView actualizado = (TextView) findViewById(R.id.textFechaActualizacion);
                    actualizado.setText("No Actualizado. Sin acceso a los datos");
                    TextView gasolina = (TextView) findViewById(R.id.textTipoGasolina);
                    gasolina.setText(" ");
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_no_obtenidos), Toast.LENGTH_LONG).show();

                } else {
                    TextView actualizado = (TextView) findViewById(R.id.textFechaActualizacion);
                    actualizado.setText("No Actualizado. Sin acceso a internet");
                    TextView gasolina = (TextView) findViewById(R.id.textTipoGasolina);
                    gasolina.setText(" ");
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_conexion), Toast.LENGTH_LONG).show();


                }
            }

            //Comprobacion de que no se ha detectado correctamente la ubicacion
            if(latitudActual==0 && longitudActual==0){
                Toast.makeText(getApplicationContext(), "Ubicacion no detectada \nSe mostrara la distancia respecto al ayto de Santander", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Pruebe a reiniciar la aplicacion para actualizar su ubicacion", Toast.LENGTH_LONG).show();
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
            //gestionGasolinera.ordenaGasolinerasPorPrecio();
            //List<Gasolinera> gas = gestionGasolinera.getListaGasolineras();
            gasolinerasTotal = gestionGasolinera.getListaGasolineras();
            calculaDistancias(gestionGasolinera.getListaGasolineras(),context);
            gasolineraArrayAdapter adapter= new gasolineraArrayAdapter(context, 0, gasolinerasTotal);
            gestionGasolinera.ordenaGasolinerasPorPrecio(gestionGasolinera.getTipoCarburanteActivo());
            List<Gasolinera> gas = gestionGasolinera.getListaGasolineras();
            adapter = new gasolineraArrayAdapter(context, 0, gas);
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
            gasolinerasTotal = objects;
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

            muestraTipoGasolina(gestionGasolinera.getTipoCarburanteActivo(),gasolina,gasolinera);



            int imageID = context.getResources().getIdentifier("drawable/" + gasolinera.getRotulo().toLowerCase().trim(), null, context.getPackageName());

            //El nombre (referencia de la marca de la gasolinera) sólo se muestra si la marca es desconocida.
            if (imageID == 0) {
                imagen.setImageResource(Integer.valueOf(R.drawable.por_defecto));
                nombre.setText(gasolinera.getRotulo());

            } else {
                imagen.setImageResource(imageID);
                nombre.setText(gasolinera.getRotulo());
            }

            distancia.setText(String.format("%.2f", gasolinera.getDistancia()) + "Km");

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
                latitudActual = location.getLatitude();
                longitudActual = location.getLongitude();
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
        tipoText=(TextView)findViewById(R.id.textTipoGasolina);
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
        switch (requestCode) {
            case 10:
                configuraPermisos();
                break;
            default:
                break;
        }
    }


    private void configuraPermisos() {
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }
        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.


        //noinspection MissingPermission
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, listener);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Boolean res=false;
        switch (item.getItemId()){
            case R.id.filtro_setting:
                displayPopupWindow();
                res=true;
            break;
            case R.id.recarga:
                visualizaListaInicial();
                res=true;
            break;
            default:
        }

        return res;
    }

    public void cargaSpinnerCarburantes(View view){



        listaCarburantes=(Spinner)view.findViewById(R.id.spinnerCarburante);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.tiposCarburante,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        listaCarburantes.setAdapter(adapter);
        listaCarburantes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                check=check+1;
                if(check>2) {
                    Toast.makeText(ListaGasolinerasActivity.this, listaCarburantes.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                    List<Gasolinera> gas = gestionGasolinera.filtraPorCarburante(listaCarburantes.getSelectedItem().toString());
                    gestionGasolinera.setListaGasolineras(gas);
                    gestionGasolinera.ordenaGasolinerasPorPrecio(gestionGasolinera.getTipoCarburanteActivo());
                    TextView text=(TextView)tipoText.findViewById(R.id.textTipoGasolina);
                    text.setText(gestionGasolinera.getTipoCarburanteActivo());
                    ArrayAdapter<Gasolinera> adapter = new gasolineraArrayAdapter(ListaGasolinerasActivity.this, 0, gestionGasolinera.getListaGasolineras());
                    list.setAdapter(adapter);
                    popup.dismiss();
                    check=0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    public void cargaSpinnerMarcas(View view){

        listaMarcas=(Spinner)view.findViewById(R.id.spinnerMarca);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.marcas,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        listaMarcas.setAdapter(adapter);
        listaMarcas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                check=check+1;
                if(check>2){
                    Toast.makeText(ListaGasolinerasActivity.this,listaMarcas.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
                    List<Gasolinera> gas = gestionGasolinera.filtraPorMarca(listaMarcas.getSelectedItem().toString());
                    ArrayAdapter<Gasolinera> adapter = new gasolineraArrayAdapter(ListaGasolinerasActivity.this, 0, gas);
                    list.setAdapter(adapter);
                    popup.dismiss();
                    check=0;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    public void calculaDistancias(List<Gasolinera> lista,Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.interfaz_lista, null);

        for (Gasolinera gasolinera : lista) {
            if ((Math.abs(latitudActual - 0.0) < 0.000000001) && (Math.abs(longitudActual - 0.0) < 0.000000001)) {
                //Calculo de la distancia respecto al centro de Santander
                //Posicion ayuntamiento santander: 43.462175, -3.809989
                distanciaFinal = gestionGasolinera.DistanciaKm(43.462175, -3.809989,
                        gasolinera.getLatitud(), gasolinera.getLongitud());
                gasolinera.setDistancia(distanciaFinal);
            } else {
                //Calculo de la distancia respecto a la posición actual
                distanciaFinal = gestionGasolinera.DistanciaKm(latitudActual, longitudActual,
                        gasolinera.getLatitud(), gasolinera.getLongitud());
                gasolinera.setDistancia(distanciaFinal);
            }
        }

    }

    public void displayPopupWindow() {
        popup = new PopupWindow(this);
        View layout = getLayoutInflater().inflate(R.layout.popup, null);
        popup.setContentView(layout);
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);
        cargaSpinnerCarburantes(popup.getContentView());
        cargaSpinnerMarcas(popup.getContentView());

    }

    public void visualizaListaInicial(){
        List<Gasolinera> gas = gestionGasolinera.getListaResguardo();
        gestionGasolinera.setTipoCarburanteActivo("Gasolina 95");
        TextView text=(TextView)tipoText.findViewById(R.id.textTipoGasolina);
        text.setText(gestionGasolinera.getTipoCarburanteActivo());
        gestionGasolinera.setListaGasolineras(gas);
        ArrayAdapter<Gasolinera> adapter = new gasolineraArrayAdapter(ListaGasolinerasActivity.this, 0, gas);
        list.setAdapter(adapter);
    }


    public void muestraTipoGasolina(String tipo,TextView gasolina,Gasolinera gasolinera){
        String t=gestionGasolinera.quitaEspacioAcentos(tipo);
        switch (t) {
            case "gasolina95":
                if(gasolinera.getGasolina_95()!=Double.MAX_VALUE){
                    gasolina.setText(String.valueOf(gasolinera.getGasolina_95()) + "€/L");
                }else{
                    gasolina.setText("No disponible");
                }
                break;
            case "gasolina98":
                if(gasolinera.getGasolina_98()!=Double.MAX_VALUE){
                    gasolina.setText(String.valueOf(gasolinera.getGasolina_98()) + "€/L");
                }else{
                    gasolina.setText("No disponible");
                }
                break;
            case "diesel":
                if(gasolinera.getGasoleo_a()!=Double.MAX_VALUE){
                    gasolina.setText(String.valueOf(gasolinera.getGasoleo_a()) + "€/L");
                }else{
                    gasolina.setText("No disponible");
                }
                break;
            case "dieselsuper":
                if(gasolinera.getGasoleoSuper()!=Double.MAX_VALUE){
                    gasolina.setText(String.valueOf(gasolinera.getGasoleoSuper()) + "€/L");
                }else{
                    gasolina.setText("No disponible");
                }
                break;
            default:

        }

    }
}
