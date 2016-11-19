package com.example.nachoaguero.appgasolineras.Negocio;

import com.example.nachoaguero.appgasolineras.Datos.Gasolinera;

import java.util.List;

/**
 * Created by NachoAguero on 24/10/2016.
 */
public interface IGestionGasolinera {

    boolean obtenGasolineras();//obtenGasolineras
     boolean obtenGasolinerasSinconexion();
    List<Gasolinera> getListaGasolineras();//getListadoGasolineras
    void setListaGasolineras(List<Gasolinera> lista);
    void ordenaGasolinerasPorPrecio(String tipo);
    List<Gasolinera> filtraPorCarburante(String carburante);
    List<Gasolinera> filtraPorMarca(String marca);
    void listaResguardo();
    List<Gasolinera> getListaResguardo();
    String getTipoCarburanteActivo();
    void setTipoCarburanteActivo(String tipo);
    String quitaEspacioAcentos(String carburante);
    double DistanciaKm(double lat1, double lon1, double lat2, double lon2);



}
