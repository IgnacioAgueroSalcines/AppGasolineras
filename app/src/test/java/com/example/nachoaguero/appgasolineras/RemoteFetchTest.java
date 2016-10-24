package com.example.nachoaguero.appgasolineras;

import java.io.IOException;
import com.example.nachoaguero.appgasolineras.Utilities.RemoteFetch;

import org.junit.Test;

/**
 * Created by malda on 20/10/2016.
 */

public class RemoteFetchTest {
    RemoteFetch r=new RemoteFetch();

    @Test
    public void comprobarLecturaJson() throws NullPointerException, IOException {
       r.getJSON();
        // COMPROBAR QUE LA LECTURA DEL JSON SE HA REALIZADO CON ÉXITO
        if(r.getBufferedDataGasolineras()==null){
            throw new NullPointerException("La lectura de los datos no se ha producido");
        }
    }
}
