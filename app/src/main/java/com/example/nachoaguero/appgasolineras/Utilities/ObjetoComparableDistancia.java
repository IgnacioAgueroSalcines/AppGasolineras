package com.example.nachoaguero.appgasolineras.Utilities;

import com.example.nachoaguero.appgasolineras.Datos.Gasolinera;

import java.util.Comparator;

/**
 * Created by jorge on 21/11/2016.
 */

public class ObjetoComparableDistancia implements Comparator<Gasolinera>{


    public int compare(Gasolinera gas1, Gasolinera gas2) {
        if(gas1.getDistancia()>gas2.getDistancia())
            return 1;
        else if(gas1.getDistancia()<gas2.getDistancia())
            return -1;
        else
            return 0;
    }

}
