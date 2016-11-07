package com.example.nachoaguero.appgasolineras;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import com.example.nachoaguero.appgasolineras.Negocio.GestionGasolinera;
import com.example.nachoaguero.appgasolineras.Negocio.IGestionGasolinera;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by NachoAguero on 05/11/2016.
 */
@RunWith(AndroidJUnit4.class)
public class InsertaDistanciaTest extends AndroidTestCase{

    @Test
    public void calculaDistancia()  {
        //parametro a de calculo, ubicacion 1: Ayuntamiento Santander (43.462175,-3.809989)
        double lat1=43.462175;
        double lon1=-3.809989;
        //parametro b de calculo, ubicacion 2: Facultad de ciencias (43.471528,-3.801128)
        double lat2=43.471528;
        double lon2=-3.801128;
        //variable con la distancia calculada entre los parametros de calculo: 1.2625 km, por maps
        double distanciaReal=1.2625 ;
        //criterio de aproximacion
        double crt=0.100;
        //variable de respuesta de la ejecucion del calculo de la distancia
        IGestionGasolinera gestion=new GestionGasolinera(this.getContext());
        double res=gestion.DistanciaKm(lat1,lon1,lat2,lon2);
        //assert con el resultado del calculo
        assertTrue((distanciaReal-crt)<=res);
        assertTrue(res<=distanciaReal+crt);

    }


    @Test
    public void CompruebaEntradas(){
        IGestionGasolinera gestion = new GestionGasolinera(this.getContext());
        double res=0;
        //se comprueba que el parametro latitud cumple las restricciones propias de la ubicacion
        //lat1>=90
        res=gestion.DistanciaKm(91,-3.809989,43.471528,-3.801128);
        assertTrue(res==0);
        //lat1<=-90
        res=gestion.DistanciaKm(-91,-3.809989,43.471528,-3.801128);
        assertTrue(res==0);
        //se comprueba que el parametro longitud cumple las restricciones propias de la ubicacion
        //long1>=180
        res=gestion.DistanciaKm(43.462175,181,43.471528,-3.801128);
        assertTrue(res==0);
        //long1<=-180
        res=gestion.DistanciaKm(43.462175,-181,43.471528,-3.801128);
        assertTrue(res==0);

        //se comprueba que el parametro latitud cumple las restricciones propias de la ubicacion
        //lat2>=90
        res=gestion.DistanciaKm(43.462175,-3.809989,91,-3.801128);
        assertTrue(res==0);
        //lat2<=-90
        res=gestion.DistanciaKm(43.462175,-3.809989,-91,-3.801128);
        assertTrue(res==0);
        //se comprueba que el parametro longitud cumple las restricciones propias de la ubicacion
        //long2>=180
        res=gestion.DistanciaKm(43.462175,-3.809989,43.471528,181);
        assertTrue(res==0);
        //long2<=-180
        res=gestion.DistanciaKm(43.462175,-3.809989,43.471528,-181);
        assertTrue(res==0);

    }

}
