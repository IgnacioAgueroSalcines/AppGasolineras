package com.example.nachoaguero.appgasolineras.Negocio;

/**
 * Created by deivid on 18/10/16.
 */
import android.content.Context;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.nachoaguero.appgasolineras.Datos.Gasolinera;
import com.example.nachoaguero.appgasolineras.Datos.GasolineraDAO;
import com.example.nachoaguero.appgasolineras.Datos.IGasolineraDAO;
import com.example.nachoaguero.appgasolineras.Utilities.ObjetoComparableDistancia;
import com.example.nachoaguero.appgasolineras.Utilities.ObjetoComparablePrecio;


public class GestionGasolinera implements IGestionGasolinera {

    private IGasolineraDAO gasolineraDAO;
    private String tipoCarburanteActivo;
    private List<Gasolinera> listaGasolineras;

    public GestionGasolinera(Context context){
        gasolineraDAO=new GasolineraDAO(context);
    }

    @Override
    public boolean obtenGasolineras(){
        return gasolineraDAO.obtenGasolineras();
    }//obtenGasolineras

    public String getTipoCarburanteActivo() {
        return tipoCarburanteActivo;
    }

    public void setTipoCarburanteActivo(String tipoCarburanteActivo) {
        this.tipoCarburanteActivo = tipoCarburanteActivo;
    }

    @Override
    public List<Gasolinera> getListaGasolineras() {
        listaGasolineras=gasolineraDAO.getListaGasolineras();
        return listaGasolineras;
        //return gasolineraDAO.getListaGasolineras();
    }//getListadoGasolineras

    public void setListaGasolineras(List<Gasolinera> lista) {
        listaGasolineras=lista;
    }

    public void ordenaGasolinerasPorPrecio(String tipo){
        ObjetoComparablePrecio o = new ObjetoComparablePrecio(tipo);
        Collections.sort(listaGasolineras, o);
    }

    public void ordenaGasolinerasPorDistancia(){
        ObjetoComparableDistancia o = new ObjetoComparableDistancia();
        Collections.sort(listaGasolineras, o);
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
            double latitud1 = Math.toRadians(lat1);
            double latitud2 = Math.toRadians(lat2);

            double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(latitud1) * Math.cos(latitud2);
            c = 2 * Math.asin(Math.sqrt(a));
        }

        return radioTierraKm * c;

    }


}
