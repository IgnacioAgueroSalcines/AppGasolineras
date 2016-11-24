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


    public void setListaGasolineras(List<Gasolinera> lista) {
        listaGasolineras=lista;
    }
}
