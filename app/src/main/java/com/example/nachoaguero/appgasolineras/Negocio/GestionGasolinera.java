package com.example.nachoaguero.appgasolineras.Negocio;

/**
 * Created by deivid on 18/10/16.
 */
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

    public GestionGasolinera(){
        gasolineraDAO=new GasolineraDAO();
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
}
