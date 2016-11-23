package com.example.nachoaguero.appgasolineras;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import com.example.nachoaguero.appgasolineras.Datos.Gasolinera;
import com.example.nachoaguero.appgasolineras.Datos.GasolineraDAO;
import com.example.nachoaguero.appgasolineras.Negocio.GestionGasolinera;
import com.example.nachoaguero.appgasolineras.Negocio.IGestionGasolinera;
import com.example.nachoaguero.appgasolineras.Presentacion.ListaGasolinerasActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorge on 20/11/2016.
 */
@RunWith(AndroidJUnit4.class)
public class OrdenaGasolinerasTest extends AndroidTestCase {

    IGestionGasolinera gestion;

    @Test
    public void testOrdenaPrecio(){

        Gasolinera gas1 = new Gasolinera(1, "Torrelavega", "Cantabria", "Calle 1",
                    0.90, 1.10, "Gasolinera A", 1.20, "horario",  1.00, 0.0, 0.0);
        Gasolinera gas2 = new Gasolinera(2, "Torrelavega", "Cantabria", "Calle 2",
                1.90, 2.10, "Gasolinera A", 2.20, "horario",  2.00, 0.0, 0.0);


        gestion = new GestionGasolinera(this.getContext());

        List<Gasolinera> lista = new ArrayList<Gasolinera>();
        gestion.setListaGasolineras(lista);
        lista.add(gas1);
        lista.add(gas2);
        gestion.ordenaGasolinerasPorPrecio("gasolina95");
        assertTrue(lista.get(0).getGasolina_95()<lista.get(1).getGasolina_95());

        gestion.ordenaGasolinerasPorPrecio("gasolina98");
        assertTrue(lista.get(0).getGasolina_98()<lista.get(1).getGasolina_98());

        gestion.ordenaGasolinerasPorPrecio("diesel");
        assertTrue(lista.get(0).getGasoleo_a()<lista.get(1).getGasoleo_a());

        gestion.ordenaGasolinerasPorPrecio("super");
        assertTrue(lista.get(0).getGasoleoSuper()<lista.get(1).getGasoleoSuper());
    }


    @Test
    public void testOrdenaDistancia(){

        Gasolinera gas1 = new Gasolinera(1, "Torrelavega", "Cantabria", "Calle 1",
                0.90, 1.10, "Gasolinera A", 1.20, "horario",  1.00, 0.0, 0.0);
        gas1.setDistancia(20);
        Gasolinera gas2 = new Gasolinera(2, "Torrelavega", "Cantabria", "Calle 2",
                1.90, 2.10, "Gasolinera A", 2.20, "horario",  2.00, 0.0, 0.0);
        gas2.setDistancia(10);

        Gasolinera gas3 = new Gasolinera(1, "Torrelavega", "Cantabria", "Calle 1",
                0.90, 1.10, "Gasolinera A", 1.20, "horario",  1.00, 0.0, 0.0);
        gas3.setDistancia(30);
        Gasolinera gas4 = new Gasolinera(2, "Torrelavega", "Cantabria", "Calle 2",
                1.90, 2.10, "Gasolinera A", 2.20, "horario",  2.00, 0.0, 0.0);
        gas4.setDistancia(40);


        gestion = new GestionGasolinera(this.getContext());

        List<Gasolinera> lista = new ArrayList<Gasolinera>();
        gestion.setListaGasolineras(lista);
        lista.add(gas1); //20
        lista.add(gas2); //10
        lista.add(gas3); //30
        lista.add(gas4); //40
        gestion.ordenaGasolinerasPorDistancia();
        assertTrue(lista.get(0).getDistancia()<lista.get(1).getDistancia());
    }
}
