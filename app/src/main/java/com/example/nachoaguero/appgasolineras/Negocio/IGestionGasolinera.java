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

    String getTipoCarburanteActivo();

    void setTipoCarburanteActivo(String tipo);

    List<Gasolinera> getListaGasolineras()//getListadoGasolineras
    ;
    void setListaGasolineras(List<Gasolinera> lista);

    void ordenaGasolinerasPorPrecio(String tipo);

    void ordenaGasolinerasPorDistancia();


    double DistanciaKm(double lat1, double lon1, double lat2, double lon2);



}
