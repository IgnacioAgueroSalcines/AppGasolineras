package com.example.nachoaguero.appgasolineras.Datos;

import android.content.Context;
import android.util.Log;

import com.example.nachoaguero.appgasolineras.Utilities.FilesOperations;
import com.example.nachoaguero.appgasolineras.Utilities.ParserJSON;
import com.example.nachoaguero.appgasolineras.Utilities.RemoteFetch;

import java.io.BufferedInputStream;
import java.util.Collections;
import java.util.List;

/**
 * Created by NachoAguero on 24/10/2016.
 */

public class GasolineraDAO implements IGasolineraDAO{
    private List<Gasolinera> listaGasolineras;
    private RemoteFetch remoteFetch;
    private Context context;
    private static final double radioTierraKm = 6378.0;

    /**
     * Calcula la distancia en linea recta entre dos posiciones geográficas
     * @param lat1 latitud origen
     * @param lon1 longitud origen
     * @param lat2 latitud destino
     * @param lon2 longitud destino
     * @return distancia en km entre las dos posiciones
     */
    public double DistanciaKm(double lat1, double lon1, double lat2, double lon2){//mas casos de prueaba
        double c=0;
        if(compruebaEntradas(lat1,lon1,lat2,lon2)){
            double dLat = Math.toRadians(lat2 - lat1);
            double dLon = Math.toRadians(lon2 - lon1);
            lat1 = Math.toRadians(lat1);
            lat2 = Math.toRadians(lat2);

            double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
            c = 2 * Math.asin(Math.sqrt(a));
        }

        return radioTierraKm * c;

    }

    public boolean compruebaEntradas(double lat1, double lon1, double lat2, double lon2){
        boolean res=false;
        if(lat1>=-90 && lat1<=90){
            if(lat2>=-90 && lat2<=90){
                if(lon1>=-180 && lon1<=180){
                    if(lon2>=-180 && lon2<=180){
                        res = true;
                    }
                }
            }
        }
        return res;
    }

    public GasolineraDAO(Context _context){
        remoteFetch = new RemoteFetch();
        context=_context;
    }

    public boolean obtenGasolineras(){
        try {
            remoteFetch.getJSON();
            listaGasolineras = ParserJSON.readJsonStream(remoteFetch.getBufferedDataGasolineras());
            remoteFetch.writeBuffer(context);



            Log.d("ENTRA", "Obten gasolineras:"+listaGasolineras.size());
            return true;
        }catch(RuntimeException e){
            Log.e("ERROR","Error en la obtención de gasolineras: "+e.getMessage());
            e.printStackTrace();
            throw e;
        }//try
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }//obtenGasolineras

    public boolean obtenGasolinerasSinconexion(){
        try {

            remoteFetch.readBuffer(context);
            BufferedInputStream buffer=remoteFetch.getBufferedDataGasolineras();
            listaGasolineras = ParserJSON.readJsonStream(buffer);

            Log.d("ENTRA", "Obten gasolineras:"+listaGasolineras.size());
            return true;
        }catch(Exception e){
            Log.e("ERROR","Error en la obtención de gasolineras: ",e);
            return false;
        }//try
    }//obtenGasolinerassinconexion


    public List<Gasolinera> getListaGasolineras(){
        return listaGasolineras;
    }
}
