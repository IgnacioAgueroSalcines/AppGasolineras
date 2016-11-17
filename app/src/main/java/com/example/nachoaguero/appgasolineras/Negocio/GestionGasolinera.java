package com.example.nachoaguero.appgasolineras.Negocio;

/**
 * Created by deivid on 18/10/16.
 */
import android.content.Context;
import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.nachoaguero.appgasolineras.Datos.Gasolinera;
import com.example.nachoaguero.appgasolineras.Datos.GasolineraDAO;
import com.example.nachoaguero.appgasolineras.Datos.IGasolineraDAO;
import com.example.nachoaguero.appgasolineras.Utilities.ParserJSON;
import com.example.nachoaguero.appgasolineras.Utilities.RemoteFetch;


public class GestionGasolinera implements IGestionGasolinera {

    private IGasolineraDAO gasolineraDAO;

    public GestionGasolinera(Context context){

        gasolineraDAO=new GasolineraDAO(context);
    }

    @Override
    public boolean obtenGasolineras(){
        return gasolineraDAO.obtenGasolineras();
    }//obtenGasolineras

    @Override
    public List<Gasolinera> getListaGasolineras() {
        return gasolineraDAO.getListaGasolineras();
    }//getListadoGasolineras

    @Override
    public void ordenaGasolinerasPorPrecio(){
        Collections.sort(getListaGasolineras());
    }

    private class DistanceComparator implements Comparator<Gasolinera> {

        @Override
        public int compare(Gasolinera gas1, Gasolinera gas2) {
            if(gas1.getDistancia()>gas2.getDistancia())
                return 1;
            else if(gas1.getDistancia()<gas2.getDistancia())
                return -1;
            else
                return 0;
        }
    }

    @Override
    public void ordenaGasolinerasPorDistancia(){
        Collections.sort(getListaGasolineras(), new DistanceComparator());
    }

    public boolean obtenGasolinerasSinconexion(){return gasolineraDAO.obtenGasolinerasSinconexion();}

    private static final double radioTierraKm = 6378.0;


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


}
