package com.example.nachoaguero.appgasolineras.Utilities;

/**
 * Created by jorge on 03/11/2016.
 */

/**
 * Clase para manejar posiciones geograficas
 */
public class Posicion {

    double latitud;
    double longitud;
    private static final double radioTierraKm = 6378.0;


    public Posicion(double latitud, double longitud){
        latitud = latitud;
        longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double DistanciaKm(double lat1, double lon1, double lat2, double lon2){
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return radioTierraKm * c;

    }
}