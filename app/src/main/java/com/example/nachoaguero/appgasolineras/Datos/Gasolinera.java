package com.example.nachoaguero.appgasolineras.Datos;

import java.util.Comparator;

/**
 * Created by deivid on 18/10/16.
 */

public class Gasolinera implements Comparable<Gasolinera> {

    private int IDEESS;
    private String localidad;
    private String provincia;
    private String direccion;
    private double gasoleo_a;
    private double gasolina_95;
    private String rotulo;
    private double gasolina_98;
    private String horario;
    private double gasoleoSuper;
    private double latitud;
    private double longitud;



    public Gasolinera(int IDEESS, String localidad, String provincia, String direccion, double gasoleo_a, double gasolina_95, String rotulo,double gasolina_98,String horario, double gasoleoSuper, double longitud, double latitud) {
        this.IDEESS = IDEESS;
        this.localidad = localidad;
        this.provincia = provincia;
        this.direccion = direccion;
        this.gasoleo_a = gasoleo_a;
        this.gasolina_95 = gasolina_95;
        this.rotulo = rotulo;
        this.gasolina_98=gasolina_98;
        this.horario=horario;
        this.gasoleoSuper=gasoleoSuper;
        this.latitud=latitud;
        this.longitud=longitud;
    }
    public int getIDEESS()
    {
        return IDEESS;
    }

    public void setIDEESS(int IDEESS)

    {
        this.IDEESS = IDEESS;
    }

    public String getLocalidad() {

        return localidad;
    }

    public void setLocalidad(String localidad) {

        this.localidad = localidad;
    }

    public String getProvincia() {

        return provincia;
    }

    public void setProvincia(String provincia) {

        this.provincia = provincia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public double getGasoleo_a()
    {
        return gasoleo_a;
    }

    public void setGasoleo_a(double gasoleo_a) {
        this.gasoleo_a = gasoleo_a; }

    public String getRotulo()
    {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public double getGasolina_95() {
        return gasolina_95;
    }

    public void setGasolina_95(double gasolina_95) {
        this.gasolina_95 = gasolina_95;
    }

    public double getGasoleoSuper() {
        return gasoleoSuper;
    }

    public double getGasolina_98() {
        return gasolina_98;
    }

    public void setGasolina_98(double gasolina_98) {
        this.gasolina_98 = gasolina_98;
    }

    public String getHorario() {
        return horario;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setGasoleoSuper(double gasoleoSuper) {
        this.gasoleoSuper = gasoleoSuper;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }


    public int compareTo(Gasolinera another) {

            if(this.getGasolina_95()>another.getGasolina_95())
                return 1;
            else if(this.getGasolina_95()<another.getGasolina_95())
                return -1;
            else
                return 0;

    }
}





