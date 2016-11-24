package com.example.nachoaguero.appgasolineras.Datos;

import java.util.List;

/**
 * Created by NachoAguero on 24/10/2016.
 */

public interface IGasolineraDAO {
     boolean obtenGasolineras();
     List<Gasolinera> getListaGasolineras();
     void setListaGasolineras(List<Gasolinera> lista);
     boolean obtenGasolinerasSinconexion();
}
