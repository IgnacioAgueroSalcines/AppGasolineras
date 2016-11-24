package com.example.nachoaguero.appgasolineras.Negocio;

/**
 * Created by deivid on 18/10/16.
 */
import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.nachoaguero.appgasolineras.Datos.Gasolinera;
import com.example.nachoaguero.appgasolineras.Datos.GasolineraDAO;
import com.example.nachoaguero.appgasolineras.Datos.IGasolineraDAO;
import com.example.nachoaguero.appgasolineras.Utilities.ObjetoComparableDistancia;
import com.example.nachoaguero.appgasolineras.Utilities.ObjetoComparablePrecio;


public class GestionGasolinera implements IGestionGasolinera {

    private IGasolineraDAO gasolineraDAO;
    private String tipoCarburanteActivo;
    private List<Gasolinera> listaGasolinerasSoporte;
    private static final double radioTierraKm = 6378.0;

    public GestionGasolinera(Context context){
        tipoCarburanteActivo="gasolina95";
        gasolineraDAO=new GasolineraDAO(context);
    }

    public boolean obtenGasolineras(){
        boolean res= gasolineraDAO.obtenGasolineras();
        listaResguardo();
        return res;
    }//obtenGasolineras


    public String getTipoCarburanteActivo() {
        return tipoCarburanteActivo;
    }

    public void setTipoCarburanteActivo(String tipoCarburanteActivo) {
        this.tipoCarburanteActivo = tipoCarburanteActivo;
    }

    public List<Gasolinera> getListaGasolineras() {
        return gasolineraDAO.getListaGasolineras();
        //return gasolineraDAO.getListaGasolineras();
    }//getListadoGasolineras

    public void setListaGasolineras(List<Gasolinera> lista) {
        gasolineraDAO.setListaGasolineras(lista);
    }

    public void ordenaGasolinerasPorPrecio(String tipo){
        ObjetoComparablePrecio o = new ObjetoComparablePrecio(tipo);
        Collections.sort(gasolineraDAO.getListaGasolineras(), o);
    }

    public void ordenaGasolinerasPorDistancia(){
        ObjetoComparableDistancia o = new ObjetoComparableDistancia();
        Collections.sort(gasolineraDAO.getListaGasolineras(), o);
    }



    public void listaResguardo(){
        listaGasolinerasSoporte=gasolineraDAO.getListaGasolineras();
    }

    public List<Gasolinera> getListaResguardo(){
        return listaGasolinerasSoporte;
    }

    public List<Gasolinera> filtraPorCarburante(String carburante) {
        List<Gasolinera> listaGasolineras=gasolineraDAO.getListaGasolineras();
        List<Gasolinera> filtrada=new ArrayList<Gasolinera>();
        for (int i=0;i<listaGasolineras.size();i++) {
            switch(quitaEspacioAcentos(carburante)){
                case "gasolina95":
                    if(listaGasolineras.get(i).getGasolina_95()!=Double.MAX_VALUE){
                        filtrada.add(listaGasolineras.get(i));
                    }
                    break;
                case "gasolina98":
                    if(listaGasolineras.get(i).getGasolina_98()!=Double.MAX_VALUE){
                        filtrada.add(listaGasolineras.get(i));                    }
                    break;
                case "diesel":
                    if(listaGasolineras.get(i).getGasoleo_a()!=Double.MAX_VALUE){
                        filtrada.add(listaGasolineras.get(i));                    }
                    break;
                case "dieselsuper":
                    if(listaGasolineras.get(i).getGasoleoSuper()!=Double.MAX_VALUE){
                        filtrada.add(listaGasolineras.get(i));                    }
                    break;
                default:
                    break;
            }

        }
        tipoCarburanteActivo=carburante;
        return filtrada;
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
                    if(listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("avia")){
                        filtrada.add(listaGasolineras.get(i));                    }
                    break;
                case "Campsa":
                    if(listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("campsa")){
                        filtrada.add(listaGasolineras.get(i));                    }
                    break;
                case "Carrefour":
                    if(listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("carrefour")){
                        filtrada.add(listaGasolineras.get(i));                    }
                    break;
                case "Cepsa":
                    if(listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("cepsa")){
                        filtrada.add(listaGasolineras.get(i));                    }
                    break;
                case "Galp":
                    if(listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("galp")){
                        filtrada.add(listaGasolineras.get(i));                    }
                    break;
                case "Petronor":
                    if(listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("petronor")){
                        filtrada.add(listaGasolineras.get(i));                    }
                    break;
                case "Shell":
                    if(listaGasolineras.get(i).getRotulo().trim().toLowerCase()
                            .equals("shell")){
                        filtrada.add(listaGasolineras.get(i));                    }
                    break;
                case "Otros":
                    if(!listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("petronor") &&
                    !listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("repsol")   &&
                            !listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("avia")&&
                            !listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("campsa")&&
                            !listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("carrefour")&&
                            !listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("cepsa")&&
                            !listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("galp")&&
                            !listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("petronor")&&
                            !listaGasolineras.get(i).getRotulo().trim().toLowerCase().equals("shell")
                            ){
                        filtrada.add(listaGasolineras.get(i));
                    }
                    break;
                default:
                    break;
            }

        }
        ordenaGasolinerasPorPrecio(tipoCarburanteActivo);
        gasolineraDAO.setListaGasolineras(filtrada);
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


    public String quitaEspacioAcentos(String carburante){
         String s=carburante.toLowerCase();
        s=s.replace(" ","");
        s=s.replace("é","e");
        return s;
    }

}
