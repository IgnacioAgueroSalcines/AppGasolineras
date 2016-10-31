package com.example.nachoaguero.appgasolineras.Datos;

import java.util.List;

/**
 * Created by NachoAguero on 24/10/2016.
 */

public interface IGasolineraDAO {
    public boolean obtenGasolineras();
    public List<Gasolinera> getListaGasolineras();
    public boolean obtenGasolinerasSinconexion();
}
