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

    IGestionGasolinera gestion;

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
        gestion=new GestionGasolinera(this.getContext());
        double res=gestion.DistanciaKm(lat1,lon1,lat2,lon2);
        //assert con el resultado del calculo
        assertTrue((distanciaReal-crt)<=res);
        assertTrue(res<=distanciaReal+crt);


        //prueba de calculo de distancia entre la torre eiffel y la casa blanca 6163.1584 km
        //eiffel    48.857890, 2.295179
        lat1=48.857890;
        lon1=2.295179;
        //casa blanca   38.898511, -77.036036
        lat2=38.898511;
        lon2=-77.036036;
        res=gestion.DistanciaKm(lat1,lon1,lat2,lon2);
        distanciaReal=6163.1584;
        crt=6;//debido a la concavidad de la tierra, la distancia entre dos puntos varia, como las funtes de informacion
        // de la distancia generalmente no las tienen en cuenta, se introduce un mayor crt
        //assert con el resultado del calculo
        assertTrue((distanciaReal-crt)<=res);
        assertTrue(res<=distanciaReal+crt);


        //prueba de calculo de distancia entre el ayuntamiento de santander y la plaza Dam amsterdam    1182.7113 km
        //ayuntamiento 43.462175,-3.809989
        lat1=43.462175;
        lon1=-3.809989;
        //plaza Dam   52.372949, 4.893444
        lat2=52.372949;
        lon2=4.893444;
        res=gestion.DistanciaKm(lat1,lon1,lat2,lon2);
        distanciaReal=1182.7113 ;
        crt=10;//debido a la concavidad de la tierra, la distancia entre dos puntos varia, como las funtes de informacion
        // de la distancia generalmente no las tienen en cuenta, se introduce un mayor crt
        //assert con el resultado del calculo
        assertTrue((distanciaReal-crt)<=res);
        assertTrue(res<=distanciaReal+crt);


        //prueba de calculo de distancia entre la sagrada familia de barcelona y el monte fuji  10402.1413 km
        //sagrada familia 41.403308, 2.173937
        lat1=41.403308;
        lon1=2.173937;
        //plaza Dam   35.358101, 138.731941
        lat2=35.358101;
        lon2=138.731941;
        res=gestion.DistanciaKm(lat1,lon1,lat2,lon2);
        distanciaReal=10402.1413 ;
        crt=10;//debido a la concavidad de la tierra, la distancia entre dos puntos varia, como las funtes de informacion
        // de la distancia generalmente no las tienen en cuenta, se introduce un mayor crt
        //assert con el resultado del calculo
        assertTrue((distanciaReal-crt)<=res);
        assertTrue(res<=distanciaReal+crt);

    }


    @Test
    public void CompruebaEntradas(){
        gestion = new GestionGasolinera(this.getContext());
        double res=0;
        //se comprueba que el parametro latitud cumple las restricciones propias de la ubicacion
        //lat1>=90
        res=gestion.DistanciaKm(91,-3.809989,43.471528,-3.801128);
        assertTrue(Math.abs(res-0.0)<0.000000001);
        //lat1<=-90
        res=gestion.DistanciaKm(-91,-3.809989,43.471528,-3.801128);
        assertTrue(Math.abs(res-0.0)<0.000000001);
        //se comprueba que el parametro longitud cumple las restricciones propias de la ubicacion
        //long1>=180
        res=gestion.DistanciaKm(43.462175,181,43.471528,-3.801128);
        assertTrue(Math.abs(res-0.0)<0.000000001);
        //long1<=-180
        res=gestion.DistanciaKm(43.462175,-181,43.471528,-3.801128);
        assertTrue(Math.abs(res-0.0)<0.000000001);

        //se comprueba que el parametro latitud cumple las restricciones propias de la ubicacion
        //lat2>=90
        res=gestion.DistanciaKm(43.462175,-3.809989,91,-3.801128);
        assertTrue(Math.abs(res-0.0)<0.000000001);
        //lat2<=-90
        res=gestion.DistanciaKm(43.462175,-3.809989,-91,-3.801128);
        assertTrue(Math.abs(res-0.0)<0.000000001);
        //se comprueba que el parametro longitud cumple las restricciones propias de la ubicacion
        //long2>=180
        res=gestion.DistanciaKm(43.462175,-3.809989,43.471528,181);
        assertTrue(Math.abs(res-0.0)<0.000000001);
        //long2<=-180
        res=gestion.DistanciaKm(43.462175,-3.809989,43.471528,-181);
        assertTrue(Math.abs(res-0.0)<0.000000001);

    }

}
