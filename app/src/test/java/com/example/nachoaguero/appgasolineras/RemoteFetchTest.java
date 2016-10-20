package com.example.nachoaguero.appgasolineras;

import java.io.IOException;
import com.example.nachoaguero.appgasolineras.Datos.RemoteFetch;

/**
 * Created by malda on 20/10/2016.
 */

public class RemoteFetchTest {

    public void comprobarLecturaJson() throws NullPointerException {
        RemoteFetch r=new RemoteFetch();
        // COMPROBAR QUE LA LECTURA DEL JSON SE HA REALIZADO CON ÉXITO
        if(r.getBufferedDataGasolineras()==null){
            throw new NullPointerException("La lectura de los datos no se ha producido");
        }
    }
}
