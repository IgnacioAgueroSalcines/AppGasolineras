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



    public Gasolinera(int IDEESS, String localidad, String provincia, String direccion, double gasoleo_a, double gasolina_95, String rotulo) {
        this.IDEESS = IDEESS;
        this.localidad = localidad;
        this.provincia = provincia;
        this.direccion = direccion;
        this.gasoleo_a = gasoleo_a;
        this.gasolina_95 = gasolina_95;
        this.rotulo = rotulo;
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

    @Override
    public int compareTo(Gasolinera another) {

            if(this.getGasolina_95()>another.getGasolina_95())
                return 1;
            else if(this.getGasolina_95()<another.getGasolina_95())
                return -1;
            else
                return 0;

    }

}





