package com.example.nachoaguero.appgasolineras.Negocio;

/**
 * Created by deivid on 18/10/16.
 */
import android.content.Context;
import android.util.Log;

import java.util.Collections;
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
        gasolineraDAO.ordenaGasolinerasPorPrecio();

    }

    @Override
    public void filtraPorCarburante(String carburante){
        gasolineraDAO.filtraPorCarburante( carburante);
    }

    public boolean obtenGasolinerasSinconexion(){return gasolineraDAO.obtenGasolinerasSinconexion();}

    private static final double radioTierraKm = 6378.0;


    public double DistanciaKm(double lat1, double lon1, double lat2, double lon2){
        return gasolineraDAO.DistanciaKm(lat1,lon1,lat2,lon2);

    }

    @Override
    public void listaResguardo(){
        gasolineraDAO.listaResguardo();
    }
}
