package com.example.nachoaguero.appgasolineras.Utilities;

import com.example.nachoaguero.appgasolineras.Datos.Gasolinera;

import java.util.Comparator;

/**
 * Created by jorge on 21/11/2016.
 */

public class ObjetoComparablePrecio implements Comparator<Gasolinera> {

    private String tipo;

    public ObjetoComparablePrecio(String tipo) {
        this.tipo = tipo;
    }

    public String quitaEspacioAcentos(String carburante){
        String s=carburante.toLowerCase();
        s=s.replace(" ","");
        s=s.replace("é","e");
        return s;
    }

    public int compare(Gasolinera gas1, Gasolinera gas2) {
        switch(quitaEspacioAcentos(tipo)){
            case("gasolina95"):
                if(gas1.getGasolina_95()>gas2.getGasolina_95())
                    return 1;
                else if(gas1.getGasolina_95()<gas2.getGasolina_95())
                    return -1;
                else
                    return 0;
            case("gasolina98"):
                if(gas1.getGasolina_98()>gas2.getGasolina_98())
                    return 1;
                else if(gas1.getGasolina_98()<gas2.getGasolina_98())
                    return -1;
                else
                    return 0;
            case("diesel"):
                if(gas1.getGasoleo_a()>gas2.getGasoleo_a())
                    return 1;
                else if(gas1.getGasoleo_a()<gas2.getGasoleo_a())
                    return -1;
                else
                    return 0;
            case("super"):
                if(gas1.getGasoleoSuper()>gas2.getGasoleoSuper())
                    return 1;
                else if(gas1.getGasoleoSuper()<gas2.getGasoleoSuper())
                    return -1;
                else
                    return 0;
        }
        return 0;
    }

}
