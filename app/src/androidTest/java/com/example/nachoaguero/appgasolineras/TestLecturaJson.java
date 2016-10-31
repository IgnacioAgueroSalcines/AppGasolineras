package com.example.nachoaguero.appgasolineras;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.nachoaguero.appgasolineras.Utilities.ParserJSON;
import com.example.nachoaguero.appgasolineras.Utilities.RemoteFetch;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
/**
 * Created by Alexandru on 20/10/2016.
 */
/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestLecturaJson {

    RemoteFetch r = new RemoteFetch();
    ParserJSON pj = new ParserJSON();

    @Test
    public void comprobarLecturaJson() throws NullPointerException, IOException {
        r.getJSON();
        // COMPROBAR QUE LA LECTURA DEL JSON SE HA REALIZADO CON ÉXITO
        if(r.getBufferedDataGasolineras()==null){
            throw new NullPointerException("La lectura de los datos no se ha producido");
        }
    }
    @Test
    public void testStatusConsulta() throws Exception{
        RemoteFetch r = new RemoteFetch();
        r.getJSON();
        String status = ParserJSON.checkStatus(r.getBufferedDataGasolineras());
        assertEquals("OK", status);
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.nachoaguero.appgasolineras", appContext.getPackageName());
    }




}

