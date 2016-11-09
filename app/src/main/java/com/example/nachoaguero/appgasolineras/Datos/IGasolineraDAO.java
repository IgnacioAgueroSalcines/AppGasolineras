package com.example.nachoaguero.appgasolineras.Datos;

import java.util.List;

/**
 * Created by NachoAguero on 24/10/2016.
 */

public interface IGasolineraDAO {
     boolean obtenGasolineras();
     List<Gasolinera> getListaGasolineras();
     boolean obtenGasolinerasSinconexion();
    double DistanciaKm(double lat1, double lon1, double lat2, double lon2);

}
