package com.example.nachoaguero.appgasolineras.Negocio;

/**
 * Created by deivid on 18/10/16.
 */
import android.util.Log;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import com.example.nachoaguero.appgasolineras.*;
import com.example.nachoaguero.appgasolineras.Datos.Gasolinera;


public class DatosGasolineras {

    private List<Gasolinera> listaGasolineras;
    private RemoteFetch remoteFetch;

    public DatosGasolineras(){
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

    public List<Gasolinera> getListaGasolineras() {
        return listaGasolineras;
    }//getListadoGasolineras

    public void setListaGasolineras(List<Gasolinera> listaGasolineras) {
        this.listaGasolineras = listaGasolineras;
    }//setListadoGasolineras

    public String getTextoGasolineras(){
        String textoGasolineras="";
        if(listaGasolineras!=null){
            for (int i=0; i<listaGasolineras.size(); i++){
                textoGasolineras=textoGasolineras+listaGasolineras.get(i).getRotulo()+"\n"+
                        listaGasolineras.get(i).getDireccion()+"\n"+
                        listaGasolineras.get(i).getLocalidad()+"\n"+
                        "Precio diesel: "+listaGasolineras.get(i).getGasoleo_a()+" "+"\n"+
                        "Precio gasolina 95: "+listaGasolineras.get(i).getGasolina_95()+" "+"\n\n";
            }//for
        }else{
            textoGasolineras="Sin gasolineras";
        }//if
        return textoGasolineras;
    }//getCantabriaGasolineras

    public void ordenaGasolinerasPorPrecio(){
        Collections.sort(listaGasolineras);
    }
}
