package com.example.nachoaguero.appgasolineras.Utilities;

import com.example.nachoaguero.appgasolineras.Datos.Gasolinera;

import java.util.Comparator;

/**
 * Created by NachoAguero on 17/11/2016.
 */

public class ObjetoComparable implements Comparator<Gasolinera>{

    private String tipo;

    public ObjetoComparable(String tipo){
        this.tipo=tipo;
    }

    public String quitaEspacioAcentos(String carburante){
        String s=carburante.toLowerCase();
        s=s.replace(" ","");
        s=s.replace("é","e");
        return s;
    }



    public int compare(Gasolinera o1, Gasolinera o2) {
        switch (quitaEspacioAcentos(tipo)) {
            case ("gasolina95"):
                if (o1.getGasolina_95() > o2.getGasolina_95())
                    return 1;
                else if (o1.getGasolina_95() < o2.getGasolina_95())
                    return -1;
                else
                    return 0;
            case ("gasolina98"):
                if (o1.getGasolina_98() > o2.getGasolina_98())
                    return 1;
                else if (o1.getGasolina_98() < o2.getGasolina_98())
                    return -1;
                else
                    return 0;
            case ("diesel"):
                if (o1.getGasoleo_a() > o2.getGasoleo_a())
                    return 1;
                else if (o1.getGasoleo_a() < o2.getGasoleo_a())
                    return -1;
                else
                    return 0;
            case ("dieselsuper"):
                if (o1.getGasoleoSuper() > o2.getGasoleoSuper())
                    return 1;
                else if (o1.getGasoleoSuper() < o2.getGasoleoSuper())
                    return -1;
                else
                    return 0;
        }
        return 0;
    }
}
