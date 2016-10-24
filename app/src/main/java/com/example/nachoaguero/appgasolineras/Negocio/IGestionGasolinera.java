package com.example.nachoaguero.appgasolineras.Negocio;

import com.example.nachoaguero.appgasolineras.Datos.Gasolinera;

import java.util.List;

/**
 * Created by NachoAguero on 24/10/2016.
 */
public interface IGestionGasolinera {
    boolean obtenGasolineras()//obtenGasolineras
    ;

    List<Gasolinera> getListaGasolineras()//getListadoGasolineras
    ;

    void ordenaGasolinerasPorPrecio();
}
