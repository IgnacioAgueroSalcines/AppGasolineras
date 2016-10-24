package com.example.nachoaguero.appgasolineras.Datos;

import android.util.Log;

import com.example.nachoaguero.appgasolineras.Utilities.ParserJSON;
import com.example.nachoaguero.appgasolineras.Utilities.RemoteFetch;

import java.util.Collections;
import java.util.List;

/**
 * Created by NachoAguero on 24/10/2016.
 */

public class GasolineraDAO implements IGasolineraDAO{
    private List<Gasolinera> listaGasolineras;
    private RemoteFetch remoteFetch;


    public GasolineraDAO(){
        remoteFetch = new RemoteFetch();
    }

    public boolean obtenGasolineras(){
        try {
            remoteFetch.getJSON();
            listaGasolineras = ParserJSON.readJsonStream(remoteFetch.getBufferedDataGasolineras());
            Log.d("ENTRA", "Obten gasolineras:"+listaGasolineras.size());
            return true;
        }catch(Exception e){
            Log.e("ERROR","Error en la obtención de gasolineras: "+e.getMessage());
            e.printStackTrace();
            return false;
        }//try
    }//obtenGasolineras


    public List<Gasolinera> getListaGasolineras(){
        return listaGasolineras;
    }
}
