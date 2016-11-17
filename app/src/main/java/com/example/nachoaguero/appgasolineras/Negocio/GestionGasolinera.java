package com.example.nachoaguero.appgasolineras.Negocio;

/**
 * Created by deivid on 18/10/16.
 */
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.nachoaguero.appgasolineras.Datos.Gasolinera;
import com.example.nachoaguero.appgasolineras.Datos.GasolineraDAO;
import com.example.nachoaguero.appgasolineras.Datos.IGasolineraDAO;
import com.example.nachoaguero.appgasolineras.Utilities.ParserJSON;
import com.example.nachoaguero.appgasolineras.Utilities.RemoteFetch;


public class GestionGasolinera implements IGestionGasolinera {

    private IGasolineraDAO gasolineraDAO;
    private List<Gasolinera> listaGasolinerasSoporte;
    private static final double radioTierraKm = 6378.0;

    public GestionGasolinera(Context context){

        gasolineraDAO=new GasolineraDAO(context);
    }

    public boolean obtenGasolineras(){
        boolean res= gasolineraDAO.obtenGasolineras();
        listaResguardo();
        return res;
    }//obtenGasolineras


    public List<Gasolinera> getListaGasolineras() {
        return gasolineraDAO.getListaGasolineras();
    }//getListadoGasolineras


    public void ordenaGasolinerasPorPrecio(){
        Collections.sort(gasolineraDAO.getListaGasolineras());
    }

    public void listaResguardo(){
        listaGasolinerasSoporte=gasolineraDAO.getListaGasolineras();
    }

    public void filtraPorCarburante(String carburante) {
        gasolineraDAO.setListaGasolineras(listaGasolinerasSoporte);
        List<Gasolinera> listaGasolineras=gasolineraDAO.getListaGasolineras();
        for (int i=0;i<listaGasolineras.size();i++) {
            switch(carburante){
                case "Gasolina 95":
                    if(listaGasolineras.get(i).getGasolina_95()==Integer.MAX_VALUE){
                        listaGasolineras.remove(listaGasolineras.get(i));
                    }
                    break;
                case "Gasolina 98":
                    if(listaGasolineras.get(i).getGasolina_98()==Integer.MAX_VALUE){
                        listaGasolineras.remove(listaGasolineras.get(i));
                    }
                    break;
                case "Diésel":
                    if(listaGasolineras.get(i).getGasoleo_a()==Integer.MAX_VALUE){
                        listaGasolineras.remove(listaGasolineras.get(i));
                    }
                    break;
                case "Diésel Super":
                    if(listaGasolineras.get(i).getGasoleoSuper()==Integer.MAX_VALUE){
                        listaGasolineras.remove(listaGasolineras.get(i));
                    }
                    break;
                default:

            }

        }
       // ordenaGasolinerasPorPrecio();

    }

    public List<Gasolinera> filtraPorMarca(String marca){
        List<Gasolinera> listaGasolineras=listaGasolinerasSoporte;
        List<Gasolinera> filtrada=new ArrayList<Gasolinera>();
        for (int i=0;i<listaGasolineras.size();i++) {
            switch(marca){
                case "Repsol":
                    if(listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("repsol")){
                        filtrada.add(listaGasolineras.get(i));
                    }
                    break;
                case "Avia":
                    if(!listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("avia")){
                        listaGasolineras.remove(i);
                    }
                    break;
                case "Campsa":
                    if(!listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("campsa")){
                        listaGasolineras.remove(i);
                    }
                    break;
                case "Carrefour":
                    if(!listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("carrefour")){
                        listaGasolineras.remove(i);
                    }
                    break;
                case "Cepsa":
                    if(!listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("cepsa")){
                        listaGasolineras.remove(i);
                    }
                    break;
                case "Galp":
                    if(!listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("galp")){
                        listaGasolineras.remove(i);
                    }
                    break;
                case "Petronor":
                    if(!listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("petronor")){
                        listaGasolineras.remove(i);
                    }
                    break;
                case "Shell":
                    if(!listaGasolineras.get(i).getRotulo().trim().toLowerCase()
                            .equals("shell")){
                        listaGasolineras.remove(i);
                    }
                    break;
                default:
                    break;
            }

        }
       // ordenaGasolinerasPorPrecio();
        return filtrada;
    }

    public boolean obtenGasolinerasSinconexion(){
        boolean res= gasolineraDAO.obtenGasolinerasSinconexion();
        listaResguardo();
        return res;

    }


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

}
