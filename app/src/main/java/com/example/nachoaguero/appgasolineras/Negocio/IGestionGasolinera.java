package com.example.nachoaguero.appgasolineras.Negocio;

import com.example.nachoaguero.appgasolineras.Datos.Gasolinera;

import java.util.List;

/**
 * Created by NachoAguero on 24/10/2016.
 */
public interface IGestionGasolinera {
    boolean obtenGasolineras()//obtenGasolineras
    ;
     boolean obtenGasolinerasSinconexion();

    List<Gasolinera> getListaGasolineras()//getListadoGasolineras
    ;

    void ordenaGasolinerasPorPrecio();
    void filtraPorCarburante(String carburante);
    void listaResguardo();


    double DistanciaKm(double lat1, double lon1, double lat2, double lon2);



}
