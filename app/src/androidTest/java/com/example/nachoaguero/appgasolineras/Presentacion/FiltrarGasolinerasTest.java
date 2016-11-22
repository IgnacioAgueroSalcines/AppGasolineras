package com.example.nachoaguero.appgasolineras.Presentacion;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.util.Log;

import com.example.nachoaguero.appgasolineras.Datos.Gasolinera;
import com.example.nachoaguero.appgasolineras.Datos.GasolineraDAO;
import com.example.nachoaguero.appgasolineras.Negocio.GestionGasolinera;
import com.example.nachoaguero.appgasolineras.Negocio.IGestionGasolinera;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by NachoAguero on 05/11/2016.
 */
@RunWith(AndroidJUnit4.class)
public class FiltrarGasolinerasTest extends AndroidTestCase{

    IGestionGasolinera gestion;
    List<Gasolinera> listaFiltrada;
    List<Gasolinera> listaTotal;


    /**
     * Test de filtrar por carburante
     */
    @Test
    public void filtrarCarburanteDiesel()  {
        gestion=new GestionGasolinera(this.getContext());
        listaTotal= new ArrayList<Gasolinera>();
                                                                                        //diesel
        Gasolinera gasolinera0= new Gasolinera(1,"Santander","Cantabria","Calle Castilla1",1.5,Double.MAX_VALUE,"Avia",1,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera1= new Gasolinera(2,"Santander","Cantabria","Calle Castilla2",Double.MAX_VALUE,Double.MAX_VALUE,"Avia",1.2,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera2= new Gasolinera(3,"Santander","Cantabria","Calle Castilla3",Double.MAX_VALUE,Double.MAX_VALUE,"Avia",1.3,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera3= new Gasolinera(4,"Santander","Cantabria","Calle Castilla4",1.7,Double.MAX_VALUE,"Avia",1.4,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera4= new Gasolinera(5,"Santander","Cantabria","Calle Castilla5",Double.MAX_VALUE,1.15,"Avia",Double.MAX_VALUE,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera5= new Gasolinera(6,"Santander","Cantabria","Calle Castilla6",1.2,Double.MAX_VALUE,"Avia",1.6,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera6= new Gasolinera(7,"Santander","Cantabria","Calle Castilla7",1.1,Double.MAX_VALUE,"Avia",Double.MAX_VALUE,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera7= new Gasolinera(8,"Santander","Cantabria","Calle Castilla8",Double.MAX_VALUE,Double.MAX_VALUE,"Avia",Double.MAX_VALUE,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        listaTotal.add(gasolinera0);
        listaTotal.add(gasolinera1);
        listaTotal.add(gasolinera2);
        listaTotal.add(gasolinera3);
        listaTotal.add(gasolinera4);
        listaTotal.add(gasolinera5);
        listaTotal.add(gasolinera6);
        listaTotal.add(gasolinera7);
        gestion.setListaGasolineras(listaTotal);
        listaFiltrada =gestion.filtraPorCarburante("diesel");
        //comprobacion de que todos los que estan en la listaFiltrada tienen gasolina diesel
        for(int i = 0; i< listaFiltrada.size(); i++){
            assertTrue(listaFiltrada.get(i).getGasoleo_a()!=Double.MAX_VALUE);
        }

        //comprobacion de que estan en la lista todos los de gasolina diesel
        for(int i=0;i<listaTotal.size();i++){
            if(listaTotal.get(i).getGasoleo_a()!=Double.MAX_VALUE){
                assertTrue(listaFiltrada.contains(listaTotal.get(i)));
            }
        }


    }

    @Test
    public void filtrarCarburante95()  {

        gestion=new GestionGasolinera(this.getContext());
        listaTotal= new ArrayList<Gasolinera>();
                                                                                              //gasolina 95
        Gasolinera gasolinera0= new Gasolinera(1,"Santander","Cantabria","Calle Castilla1",1.5,1.2,"Avia",1,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera1= new Gasolinera(2,"Santander","Cantabria","Calle Castilla2",Double.MAX_VALUE,1.4,"Avia",1.2,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera2= new Gasolinera(3,"Santander","Cantabria","Calle Castilla3",Double.MAX_VALUE,1.2,"Avia",1.3,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera3= new Gasolinera(4,"Santander","Cantabria","Calle Castilla4",1.7,1.7,"Avia",1.4,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera4= new Gasolinera(5,"Santander","Cantabria","Calle Castilla5",Double.MAX_VALUE,1.15,"Avia",Double.MAX_VALUE,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera5= new Gasolinera(6,"Santander","Cantabria","Calle Castilla6",1.2,Double.MAX_VALUE,"Avia",1.6,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera6= new Gasolinera(7,"Santander","Cantabria","Calle Castilla7",1.1,Double.MAX_VALUE,"Avia",Double.MAX_VALUE,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera7= new Gasolinera(8,"Santander","Cantabria","Calle Castilla8",Double.MAX_VALUE,Double.MAX_VALUE,"Avia",Double.MAX_VALUE,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        listaTotal.add(gasolinera0);
        listaTotal.add(gasolinera1);
        listaTotal.add(gasolinera2);
        listaTotal.add(gasolinera3);
        listaTotal.add(gasolinera4);
        listaTotal.add(gasolinera5);
        listaTotal.add(gasolinera6);
        listaTotal.add(gasolinera7);
        gestion.setListaGasolineras(listaTotal);
        listaFiltrada =gestion.filtraPorCarburante("gasolina95");
        //comprobacion de que todos los que estan en la listaFiltrada tienen gasolina del 95
        for(int i = 0; i< listaFiltrada.size(); i++){
            assertTrue(listaFiltrada.get(i).getGasolina_95()!=Double.MAX_VALUE);
        }

        //comprobacion de que estan en la lista todos los de gasolina del 95
        for(int i=0;i<listaTotal.size();i++){
            if(listaTotal.get(i).getGasolina_95()!=Double.MAX_VALUE){
                assertTrue(listaFiltrada.contains(listaTotal.get(i)));
            }
        }


    }

    @Test
    public void filtrarCarburante98()  {
        gestion=new GestionGasolinera(this.getContext());
        listaTotal= new ArrayList<Gasolinera>();
                                                                                                     //gasolina 98
        Gasolinera gasolinera0= new Gasolinera(1,"Santander","Cantabria","Calle Castilla1",1.5,1.2,"Avia",1,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera1= new Gasolinera(2,"Santander","Cantabria","Calle Castilla2",Double.MAX_VALUE,1.4,"Avia",1.2,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera2= new Gasolinera(3,"Santander","Cantabria","Calle Castilla3",Double.MAX_VALUE,1.2,"Avia",1.3,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera3= new Gasolinera(4,"Santander","Cantabria","Calle Castilla4",1.7,1.7,"Avia",1.4,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera4= new Gasolinera(5,"Santander","Cantabria","Calle Castilla5",Double.MAX_VALUE,1.15,"Avia",Double.MAX_VALUE,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera5= new Gasolinera(6,"Santander","Cantabria","Calle Castilla6",1.2,Double.MAX_VALUE,"Avia",1.6,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera6= new Gasolinera(7,"Santander","Cantabria","Calle Castilla7",1.1,Double.MAX_VALUE,"Avia",Double.MAX_VALUE,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera7= new Gasolinera(8,"Santander","Cantabria","Calle Castilla8",Double.MAX_VALUE,Double.MAX_VALUE,"Avia",Double.MAX_VALUE,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        listaTotal.add(gasolinera0);
        listaTotal.add(gasolinera1);
        listaTotal.add(gasolinera2);
        listaTotal.add(gasolinera3);
        listaTotal.add(gasolinera4);
        listaTotal.add(gasolinera5);
        listaTotal.add(gasolinera6);
        listaTotal.add(gasolinera7);
        gestion.setListaGasolineras(listaTotal);
        listaFiltrada =gestion.filtraPorCarburante("gasolina98");
        listaTotal= gestion.getListaGasolineras();
        //comprobacion de que todos los que estan en la listaFiltrada tienen gasolina 98
        for(int i = 0; i< listaFiltrada.size(); i++){
            assertTrue(listaFiltrada.get(i).getGasolina_98()!=Double.MAX_VALUE);
        }

        //comprobacion de que estan en la lista todos los de gasolina 98
        for(int i=0;i<listaTotal.size();i++) {
            if (listaTotal.get(i).getGasolina_98() != Double.MAX_VALUE) {
                assertTrue(listaFiltrada.contains(listaTotal.get(i)));
            }
        }
    }

    @Test
    public void filtrarCarburanteDieselSuper()  {

        gestion=new GestionGasolinera(this.getContext());
        listaTotal= new ArrayList<Gasolinera>();
                                                                                                            //gasolina diesel super
        Gasolinera gasolinera0= new Gasolinera(1,"Santander","Cantabria","Calle Castilla1",1.5,1.2,"Avia",1,"24H",1.5,1,1);
        Gasolinera gasolinera1= new Gasolinera(2,"Santander","Cantabria","Calle Castilla2",Double.MAX_VALUE,1.4,"Avia",1.2,"24H",Double.MAX_VALUE,1,1);
        Gasolinera gasolinera2= new Gasolinera(3,"Santander","Cantabria","Calle Castilla3",Double.MAX_VALUE,1.2,"Avia",1.3,"24H",1.2,1,1);
        Gasolinera gasolinera3= new Gasolinera(4,"Santander","Cantabria","Calle Castilla4",1.7,1.7,"Avia",1.4,"24H",1.2,1,1);
        Gasolinera gasolinera4= new Gasolinera(5,"Santander","Cantabria","Calle Castilla5",Double.MAX_VALUE,1.15,"Avia",Double.MAX_VALUE,"24H",Double.MAX_VALUE,1,1);
        Gasolinera gasolinera5= new Gasolinera(6,"Santander","Cantabria","Calle Castilla6",1.2,Double.MAX_VALUE,"Avia",1.6,"24H",Double.MAX_VALUE,1,1);
        Gasolinera gasolinera6= new Gasolinera(7,"Santander","Cantabria","Calle Castilla7",1.1,Double.MAX_VALUE,"Avia",Double.MAX_VALUE,"24H",Double.MAX_VALUE,1,1);
        Gasolinera gasolinera7= new Gasolinera(8,"Santander","Cantabria","Calle Castilla8",Double.MAX_VALUE,Double.MAX_VALUE,"Avia",Double.MAX_VALUE,"24H",1.3,1,1);
        listaTotal.add(gasolinera0);
        listaTotal.add(gasolinera1);
        listaTotal.add(gasolinera2);
        listaTotal.add(gasolinera3);
        listaTotal.add(gasolinera4);
        listaTotal.add(gasolinera5);
        listaTotal.add(gasolinera6);
        listaTotal.add(gasolinera7);
        gestion.setListaGasolineras(listaTotal);
        listaFiltrada =gestion.filtraPorCarburante("dieselsuper");
        //comprobacion de que todos los que estan en la listaFiltrada tienen gasolina diesel super
        for(int i = 0; i< listaFiltrada.size(); i++){
            assertTrue(listaFiltrada.get(i).getGasoleoSuper()!=Double.MAX_VALUE);
        }

        //comprobacion de que estan en la lista todos los de gasolina diesel super
        for(int i=0;i<listaTotal.size();i++){
            if(listaTotal.get(i).getGasoleoSuper()!=Double.MAX_VALUE){
                assertTrue(listaFiltrada.contains(listaTotal.get(i)));
            }
        }


    }


    /**
     * Test de filtrado por marca
     */
    @Test
    public void filtrarPorMarcaAvia()  {
        gestion=new GestionGasolinera(this.getContext());
        listaTotal= new ArrayList<Gasolinera>();
        //gasolina diesel super
        Gasolinera gasolinera0= new Gasolinera(1,"Santander","Cantabria","Calle Castilla1",1.5,1.2,"Avia",1,"24H",1.5,1,1);
        Gasolinera gasolinera1= new Gasolinera(2,"Santander","Cantabria","Calle Castilla2",Double.MAX_VALUE,1.4,"Repsol",1.2,"24H",Double.MAX_VALUE,1,1);
        Gasolinera gasolinera2= new Gasolinera(3,"Santander","Cantabria","Calle Castilla3",Double.MAX_VALUE,1.2,"Carrefour",1.3,"24H",1.2,1,1);
        Gasolinera gasolinera3= new Gasolinera(4,"Santander","Cantabria","Calle Castilla4",1.7,1.7,"Avia",1.4,"24H",1.2,1,1);
        Gasolinera gasolinera4= new Gasolinera(5,"Santander","Cantabria","Calle Castilla5",Double.MAX_VALUE,1.15,"Cepsa",Double.MAX_VALUE,"24H",Double.MAX_VALUE,1,1);
        Gasolinera gasolinera5= new Gasolinera(6,"Santander","Cantabria","Calle Castilla6",1.2,Double.MAX_VALUE,"Avia",1.6,"24H",Double.MAX_VALUE,1,1);
        Gasolinera gasolinera6= new Gasolinera(7,"Santander","Cantabria","Calle Castilla7",1.1,Double.MAX_VALUE,"Cepsa",Double.MAX_VALUE,"24H",Double.MAX_VALUE,1,1);
        Gasolinera gasolinera7= new Gasolinera(8,"Santander","Cantabria","Calle Castilla8",Double.MAX_VALUE,Double.MAX_VALUE,"bp",Double.MAX_VALUE,"24H",1.3,1,1);
        listaTotal.add(gasolinera0);
        listaTotal.add(gasolinera1);
        listaTotal.add(gasolinera2);
        listaTotal.add(gasolinera3);
        listaTotal.add(gasolinera4);
        listaTotal.add(gasolinera5);
        listaTotal.add(gasolinera6);
        listaTotal.add(gasolinera7);
        gestion.setListaGasolineras(listaTotal);
        int n=gestion.getListaGasolineras().size();
        gestion.listaResguardo();
        //listaFiltrada =gestion.filtraPorCarburante("gasolina95");
        listaFiltrada =gestion.filtraPorMarca("Avia");

        //comprobacion de que todos los que estan en la listaFiltrada tienen gasolina diesel super
        for(int i = 0; i< listaFiltrada.size(); i++){
            assertTrue(listaFiltrada.get(i).getRotulo().equals("Avia"));
        }

        //comprobacion de que estan en la lista todos los de gasolina diesel super
        for(int i=0;i<listaTotal.size();i++){
            if(listaTotal.get(i).getRotulo().equals("Avia")){
                assertTrue(listaFiltrada.contains(listaTotal.get(i)));
            }
        }

    }




    @Test
    public void filtrarPorMarcaRepsol()  {
        gestion=new GestionGasolinera(this.getContext());
        listaTotal= new ArrayList<Gasolinera>();
        //gasolina diesel super
        Gasolinera gasolinera0= new Gasolinera(1,"Santander","Cantabria","Calle Castilla1",1.5,1.2,"Avia",1,"24H",1.5,1,1);
        Gasolinera gasolinera1= new Gasolinera(2,"Santander","Cantabria","Calle Castilla2",Double.MAX_VALUE,1.4,"Repsol",1.2,"24H",Double.MAX_VALUE,1,1);
        Gasolinera gasolinera2= new Gasolinera(3,"Santander","Cantabria","Calle Castilla3",Double.MAX_VALUE,1.2,"Carrefour",1.3,"24H",1.2,1,1);
        Gasolinera gasolinera3= new Gasolinera(4,"Santander","Cantabria","Calle Castilla4",1.7,1.7,"Avia",1.4,"24H",1.2,1,1);
        Gasolinera gasolinera4= new Gasolinera(5,"Santander","Cantabria","Calle Castilla5",Double.MAX_VALUE,1.15,"Cepsa",Double.MAX_VALUE,"24H",Double.MAX_VALUE,1,1);
        Gasolinera gasolinera5= new Gasolinera(6,"Santander","Cantabria","Calle Castilla6",1.2,Double.MAX_VALUE,"Avia",1.6,"24H",Double.MAX_VALUE,1,1);
        Gasolinera gasolinera6= new Gasolinera(7,"Santander","Cantabria","Calle Castilla7",1.1,Double.MAX_VALUE,"Cepsa",Double.MAX_VALUE,"24H",Double.MAX_VALUE,1,1);
        Gasolinera gasolinera7= new Gasolinera(8,"Santander","Cantabria","Calle Castilla8",Double.MAX_VALUE,Double.MAX_VALUE,"bp",Double.MAX_VALUE,"24H",1.3,1,1);
        listaTotal.add(gasolinera0);
        listaTotal.add(gasolinera1);
        listaTotal.add(gasolinera2);
        listaTotal.add(gasolinera3);
        listaTotal.add(gasolinera4);
        listaTotal.add(gasolinera5);
        listaTotal.add(gasolinera6);
        listaTotal.add(gasolinera7);
        gestion.setListaGasolineras(listaTotal);
        int n=gestion.getListaGasolineras().size();
        gestion.listaResguardo();
        //listaFiltrada =gestion.filtraPorCarburante("gasolina95");
        listaFiltrada =gestion.filtraPorMarca("Repsol");

        //comprobacion de que todos los que estan en la listaFiltrada tienen gasolina diesel super
        for(int i = 0; i< listaFiltrada.size(); i++){
            assertTrue(listaFiltrada.get(i).getRotulo().equals("Repsol"));
        }

        //comprobacion de que estan en la lista todos los de gasolina diesel super
        for(int i=0;i<listaTotal.size();i++){
            if(listaTotal.get(i).getRotulo().equals("Repsol")){
                assertTrue(listaFiltrada.contains(listaTotal.get(i)));
            }
        }

    }


    @Test
    public void filtrarPorMarcaShell()  {
        gestion=new GestionGasolinera(this.getContext());
        listaTotal= new ArrayList<Gasolinera>();
        //gasolina diesel super
        Gasolinera gasolinera0= new Gasolinera(1,"Santander","Cantabria","Calle Castilla1",1.5,1.2,"Shell",1,"24H",1.5,1,1);
        Gasolinera gasolinera1= new Gasolinera(2,"Santander","Cantabria","Calle Castilla2",Double.MAX_VALUE,1.4,"Shell",1.2,"24H",Double.MAX_VALUE,1,1);
        Gasolinera gasolinera2= new Gasolinera(3,"Santander","Cantabria","Calle Castilla3",Double.MAX_VALUE,1.2,"Carrefour",1.3,"24H",1.2,1,1);
        Gasolinera gasolinera3= new Gasolinera(4,"Santander","Cantabria","Calle Castilla4",1.7,1.7,"Avia",1.4,"24H",1.2,1,1);
        Gasolinera gasolinera4= new Gasolinera(5,"Santander","Cantabria","Calle Castilla5",Double.MAX_VALUE,1.15,"Cepsa",Double.MAX_VALUE,"24H",Double.MAX_VALUE,1,1);
        Gasolinera gasolinera5= new Gasolinera(6,"Santander","Cantabria","Calle Castilla6",1.2,Double.MAX_VALUE,"Avia",1.6,"24H",Double.MAX_VALUE,1,1);
        Gasolinera gasolinera6= new Gasolinera(7,"Santander","Cantabria","Calle Castilla7",1.1,Double.MAX_VALUE,"Cepsa",Double.MAX_VALUE,"24H",Double.MAX_VALUE,1,1);
        Gasolinera gasolinera7= new Gasolinera(8,"Santander","Cantabria","Calle Castilla8",Double.MAX_VALUE,Double.MAX_VALUE,"bp",Double.MAX_VALUE,"24H",1.3,1,1);
        listaTotal.add(gasolinera0);
        listaTotal.add(gasolinera1);
        listaTotal.add(gasolinera2);
        listaTotal.add(gasolinera3);
        listaTotal.add(gasolinera4);
        listaTotal.add(gasolinera5);
        listaTotal.add(gasolinera6);
        listaTotal.add(gasolinera7);
        gestion.setListaGasolineras(listaTotal);
        int n=gestion.getListaGasolineras().size();
        gestion.listaResguardo();
        //listaFiltrada =gestion.filtraPorCarburante("gasolina95");
        listaFiltrada =gestion.filtraPorMarca("Shell");

        //comprobacion de que todos los que estan en la listaFiltrada tienen gasolina diesel super
        for(int i = 0; i< listaFiltrada.size(); i++){
            assertTrue(listaFiltrada.get(i).getRotulo().equals("Shell"));
        }

        //comprobacion de que estan en la lista todos los de gasolina diesel super
        for(int i=0;i<listaTotal.size();i++){
            if(listaTotal.get(i).getRotulo().equals("Shell")){
                assertTrue(listaFiltrada.contains(listaTotal.get(i)));
            }
        }

    }

    @Test
    public void filtrarPorMarcaGalp() {
        gestion = new GestionGasolinera(this.getContext());
        listaTotal = new ArrayList<Gasolinera>();
        //gasolina diesel super
        Gasolinera gasolinera0 = new Gasolinera(1, "Santander", "Cantabria", "Calle Castilla1", 1.5, 1.2, "Shell", 1, "24H", 1.5, 1, 1);
        Gasolinera gasolinera1 = new Gasolinera(2, "Santander", "Cantabria", "Calle Castilla2", Double.MAX_VALUE, 1.4, "Shell", 1.2, "24H", Double.MAX_VALUE, 1, 1);
        Gasolinera gasolinera2 = new Gasolinera(3, "Santander", "Cantabria", "Calle Castilla3", Double.MAX_VALUE, 1.2, "Carrefour", 1.3, "24H", 1.2, 1, 1);
        Gasolinera gasolinera3 = new Gasolinera(4, "Santander", "Cantabria", "Calle Castilla4", 1.7, 1.7, "Galp", 1.4, "24H", 1.2, 1, 1);
        Gasolinera gasolinera4 = new Gasolinera(5, "Santander", "Cantabria", "Calle Castilla5", Double.MAX_VALUE, 1.15, "Cepsa", Double.MAX_VALUE, "24H", Double.MAX_VALUE, 1, 1);
        Gasolinera gasolinera5 = new Gasolinera(6, "Santander", "Cantabria", "Calle Castilla6", 1.2, Double.MAX_VALUE, "Galp", 1.6, "24H", Double.MAX_VALUE, 1, 1);
        Gasolinera gasolinera6 = new Gasolinera(7, "Santander", "Cantabria", "Calle Castilla7", 1.1, Double.MAX_VALUE, "Cepsa", Double.MAX_VALUE, "24H", Double.MAX_VALUE, 1, 1);
        Gasolinera gasolinera7 = new Gasolinera(8, "Santander", "Cantabria", "Calle Castilla8", Double.MAX_VALUE, Double.MAX_VALUE, "bp", Double.MAX_VALUE, "24H", 1.3, 1, 1);
        listaTotal.add(gasolinera0);
        listaTotal.add(gasolinera1);
        listaTotal.add(gasolinera2);
        listaTotal.add(gasolinera3);
        listaTotal.add(gasolinera4);
        listaTotal.add(gasolinera5);
        listaTotal.add(gasolinera6);
        listaTotal.add(gasolinera7);
        gestion.setListaGasolineras(listaTotal);
        int n = gestion.getListaGasolineras().size();
        gestion.listaResguardo();
        //listaFiltrada =gestion.filtraPorCarburante("gasolina95");
        listaFiltrada = gestion.filtraPorMarca("Galp");

        //comprobacion de que todos los que estan en la listaFiltrada tienen gasolina diesel super
        for (int i = 0; i < listaFiltrada.size(); i++) {
            assertTrue(listaFiltrada.get(i).getRotulo().equals("Galp"));
        }

        //comprobacion de que estan en la lista todos los de gasolina diesel super
        for (int i = 0; i < listaTotal.size(); i++) {
            if (listaTotal.get(i).getRotulo().equals("Galp")) {
                assertTrue(listaFiltrada.contains(listaTotal.get(i)));
            }
        }
    }

    @Test
    public void filtrarPorMarcaCampsa() {
        gestion = new GestionGasolinera(this.getContext());
        listaTotal = new ArrayList<Gasolinera>();
        //gasolina diesel super
        Gasolinera gasolinera0 = new Gasolinera(1, "Santander", "Cantabria", "Calle Castilla1", 1.5, 1.2, "Campsa", 1, "24H", 1.5, 1, 1);
        Gasolinera gasolinera1 = new Gasolinera(2, "Santander", "Cantabria", "Calle Castilla2", Double.MAX_VALUE, 1.4, "Shell", 1.2, "24H", Double.MAX_VALUE, 1, 1);
        Gasolinera gasolinera2 = new Gasolinera(3, "Santander", "Cantabria", "Calle Castilla3", Double.MAX_VALUE, 1.2, "Campsa", 1.3, "24H", 1.2, 1, 1);
        Gasolinera gasolinera3 = new Gasolinera(4, "Santander", "Cantabria", "Calle Castilla4", 1.7, 1.7, "Galp", 1.4, "24H", 1.2, 1, 1);
        Gasolinera gasolinera4 = new Gasolinera(5, "Santander", "Cantabria", "Calle Castilla5", Double.MAX_VALUE, 1.15, "Cepsa", Double.MAX_VALUE, "24H", Double.MAX_VALUE, 1, 1);
        Gasolinera gasolinera5 = new Gasolinera(6, "Santander", "Cantabria", "Calle Castilla6", 1.2, Double.MAX_VALUE, "Galp", 1.6, "24H", Double.MAX_VALUE, 1, 1);
        Gasolinera gasolinera6 = new Gasolinera(7, "Santander", "Cantabria", "Calle Castilla7", 1.1, Double.MAX_VALUE, "Campsa", Double.MAX_VALUE, "24H", Double.MAX_VALUE, 1, 1);
        Gasolinera gasolinera7 = new Gasolinera(8, "Santander", "Cantabria", "Calle Castilla8", Double.MAX_VALUE, Double.MAX_VALUE, "bp", Double.MAX_VALUE, "24H", 1.3, 1, 1);
        listaTotal.add(gasolinera0);
        listaTotal.add(gasolinera1);
        listaTotal.add(gasolinera2);
        listaTotal.add(gasolinera3);
        listaTotal.add(gasolinera4);
        listaTotal.add(gasolinera5);
        listaTotal.add(gasolinera6);
        listaTotal.add(gasolinera7);
        gestion.setListaGasolineras(listaTotal);
        int n = gestion.getListaGasolineras().size();
        gestion.listaResguardo();
        //listaFiltrada =gestion.filtraPorCarburante("gasolina95");
        listaFiltrada = gestion.filtraPorMarca("Campsa");

        //comprobacion de que todos los que estan en la listaFiltrada tienen gasolina diesel super
        for (int i = 0; i < listaFiltrada.size(); i++) {
            assertTrue(listaFiltrada.get(i).getRotulo().equals("Campsa"));
        }

        //comprobacion de que estan en la lista todos los de gasolina diesel super
        for (int i = 0; i < listaTotal.size(); i++) {
            if (listaTotal.get(i).getRotulo().equals("Campsa")) {
                assertTrue(listaFiltrada.contains(listaTotal.get(i)));
            }
        }
    }

    @Test
    public void filtrarPorMarcaCarrefour() {
        gestion = new GestionGasolinera(this.getContext());
        listaTotal = new ArrayList<Gasolinera>();
        //gasolina diesel super
        Gasolinera gasolinera0 = new Gasolinera(1, "Santander", "Cantabria", "Calle Castilla1", 1.5, 1.2, "Campsa", 1, "24H", 1.5, 1, 1);
        Gasolinera gasolinera1 = new Gasolinera(2, "Santander", "Cantabria", "Calle Castilla2", Double.MAX_VALUE, 1.4, "Shell", 1.2, "24H", Double.MAX_VALUE, 1, 1);
        Gasolinera gasolinera2 = new Gasolinera(3, "Santander", "Cantabria", "Calle Castilla3", Double.MAX_VALUE, 1.2, "Carrefour", 1.3, "24H", 1.2, 1, 1);
        Gasolinera gasolinera3 = new Gasolinera(4, "Santander", "Cantabria", "Calle Castilla4", 1.7, 1.7, "Galp", 1.4, "24H", 1.2, 1, 1);
        Gasolinera gasolinera4 = new Gasolinera(5, "Santander", "Cantabria", "Calle Castilla5", Double.MAX_VALUE, 1.15, "Carrefour", Double.MAX_VALUE, "24H", Double.MAX_VALUE, 1, 1);
        Gasolinera gasolinera5 = new Gasolinera(6, "Santander", "Cantabria", "Calle Castilla6", 1.2, Double.MAX_VALUE, "Galp", 1.6, "24H", Double.MAX_VALUE, 1, 1);
        Gasolinera gasolinera6 = new Gasolinera(7, "Santander", "Cantabria", "Calle Castilla7", 1.1, Double.MAX_VALUE, "Carrefour", Double.MAX_VALUE, "24H", Double.MAX_VALUE, 1, 1);
        Gasolinera gasolinera7 = new Gasolinera(8, "Santander", "Cantabria", "Calle Castilla8", Double.MAX_VALUE, Double.MAX_VALUE, "bp", Double.MAX_VALUE, "24H", 1.3, 1, 1);
        listaTotal.add(gasolinera0);
        listaTotal.add(gasolinera1);
        listaTotal.add(gasolinera2);
        listaTotal.add(gasolinera3);
        listaTotal.add(gasolinera4);
        listaTotal.add(gasolinera5);
        listaTotal.add(gasolinera6);
        listaTotal.add(gasolinera7);
        gestion.setListaGasolineras(listaTotal);
        int n = gestion.getListaGasolineras().size();
        gestion.listaResguardo();
        //listaFiltrada =gestion.filtraPorCarburante("gasolina95");
        listaFiltrada = gestion.filtraPorMarca("Carrefour");

        //comprobacion de que todos los que estan en la listaFiltrada tienen gasolina diesel super
        for (int i = 0; i < listaFiltrada.size(); i++) {
            assertTrue(listaFiltrada.get(i).getRotulo().equals("Carrefour"));
        }

        //comprobacion de que estan en la lista todos los de gasolina diesel super
        for (int i = 0; i < listaTotal.size(); i++) {
            if (listaTotal.get(i).getRotulo().equals("Carrefour")) {
                assertTrue(listaFiltrada.contains(listaTotal.get(i)));
            }
        }
    }

    @Test
    public void filtrarPorMarcaPetronor() {
        gestion = new GestionGasolinera(this.getContext());
        listaTotal = new ArrayList<Gasolinera>();
        //gasolina diesel super
        Gasolinera gasolinera0 = new Gasolinera(1, "Santander", "Cantabria", "Calle Castilla1", 1.5, 1.2, "Petronor", 1, "24H", 1.5, 1, 1);
        Gasolinera gasolinera1 = new Gasolinera(2, "Santander", "Cantabria", "Calle Castilla2", Double.MAX_VALUE, 1.4, "Shell", 1.2, "24H", Double.MAX_VALUE, 1, 1);
        Gasolinera gasolinera2 = new Gasolinera(3, "Santander", "Cantabria", "Calle Castilla3", Double.MAX_VALUE, 1.2, "Campsa", 1.3, "24H", 1.2, 1, 1);
        Gasolinera gasolinera3 = new Gasolinera(4, "Santander", "Cantabria", "Calle Castilla4", 1.7, 1.7, "Galp", 1.4, "24H", 1.2, 1, 1);
        Gasolinera gasolinera4 = new Gasolinera(5, "Santander", "Cantabria", "Calle Castilla5", Double.MAX_VALUE, 1.15, "Petronor", Double.MAX_VALUE, "24H", Double.MAX_VALUE, 1, 1);
        Gasolinera gasolinera5 = new Gasolinera(6, "Santander", "Cantabria", "Calle Castilla6", 1.2, Double.MAX_VALUE, "Galp", 1.6, "24H", Double.MAX_VALUE, 1, 1);
        Gasolinera gasolinera6 = new Gasolinera(7, "Santander", "Cantabria", "Calle Castilla7", 1.1, Double.MAX_VALUE, "Petronor", Double.MAX_VALUE, "24H", Double.MAX_VALUE, 1, 1);
        Gasolinera gasolinera7 = new Gasolinera(8, "Santander", "Cantabria", "Calle Castilla8", Double.MAX_VALUE, Double.MAX_VALUE, "bp", Double.MAX_VALUE, "24H", 1.3, 1, 1);
        listaTotal.add(gasolinera0);
        listaTotal.add(gasolinera1);
        listaTotal.add(gasolinera2);
        listaTotal.add(gasolinera3);
        listaTotal.add(gasolinera4);
        listaTotal.add(gasolinera5);
        listaTotal.add(gasolinera6);
        listaTotal.add(gasolinera7);
        gestion.setListaGasolineras(listaTotal);
        int n = gestion.getListaGasolineras().size();
        gestion.listaResguardo();
        //listaFiltrada =gestion.filtraPorCarburante("gasolina95");
        listaFiltrada = gestion.filtraPorMarca("Petronor");

        //comprobacion de que todos los que estan en la listaFiltrada tienen gasolina diesel super
        for (int i = 0; i < listaFiltrada.size(); i++) {
            assertTrue(listaFiltrada.get(i).getRotulo().equals("Petronor"));
        }

        //comprobacion de que estan en la lista todos los de gasolina diesel super
        for (int i = 0; i < listaTotal.size(); i++) {
            if (listaTotal.get(i).getRotulo().equals("Petronor")) {
                assertTrue(listaFiltrada.contains(listaTotal.get(i)));
            }
        }
    }

    /**
     * Test para filtrar por un carburante que no exista
     */
    @Test
    public void filtrarCarburanteInexistente()  {
        gestion=new GestionGasolinera(this.getContext());
        listaTotal= new ArrayList<Gasolinera>();
        //diesel
        Gasolinera gasolinera0= new Gasolinera(1,"Santander","Cantabria","Calle Castilla1",1.5,Double.MAX_VALUE,"Avia",1,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera1= new Gasolinera(2,"Santander","Cantabria","Calle Castilla2",Double.MAX_VALUE,Double.MAX_VALUE,"Avia",1.2,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera2= new Gasolinera(3,"Santander","Cantabria","Calle Castilla3",Double.MAX_VALUE,Double.MAX_VALUE,"Avia",1.3,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera3= new Gasolinera(4,"Santander","Cantabria","Calle Castilla4",1.7,Double.MAX_VALUE,"Avia",1.4,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera4= new Gasolinera(5,"Santander","Cantabria","Calle Castilla5",Double.MAX_VALUE,1.15,"Avia",Double.MAX_VALUE,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera5= new Gasolinera(6,"Santander","Cantabria","Calle Castilla6",1.2,Double.MAX_VALUE,"Avia",1.6,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera6= new Gasolinera(7,"Santander","Cantabria","Calle Castilla7",1.1,Double.MAX_VALUE,"Avia",Double.MAX_VALUE,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera7= new Gasolinera(8,"Santander","Cantabria","Calle Castilla8",Double.MAX_VALUE,Double.MAX_VALUE,"Avia",Double.MAX_VALUE,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        listaTotal.add(gasolinera0);
        listaTotal.add(gasolinera1);
        listaTotal.add(gasolinera2);
        listaTotal.add(gasolinera3);
        listaTotal.add(gasolinera4);
        listaTotal.add(gasolinera5);
        listaTotal.add(gasolinera6);
        listaTotal.add(gasolinera7);
        gestion.setListaGasolineras(listaTotal);
        listaFiltrada =gestion.filtraPorCarburante("carbon");
        //comprobacion de que todos los que estan en la listaFiltrada tienen gasolina diesel
        assertTrue(listaFiltrada.size()==0);
    }

    /**
     * Test que filtra carburante que no este disponible en ninguna gasolinera
     */
    @Test
    public void filtrarCarburanteSinGasolinera()  {
        gestion=new GestionGasolinera(this.getContext());
        listaTotal= new ArrayList<Gasolinera>();
        //diesel no esta en ninguna gasolinera
        Gasolinera gasolinera0= new Gasolinera(1,"Santander","Cantabria","Calle Castilla1",Double.MAX_VALUE,Double.MAX_VALUE,"Avia",1,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera1= new Gasolinera(2,"Santander","Cantabria","Calle Castilla2",Double.MAX_VALUE,Double.MAX_VALUE,"Avia",1.2,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera2= new Gasolinera(3,"Santander","Cantabria","Calle Castilla3",Double.MAX_VALUE,Double.MAX_VALUE,"Avia",1.3,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera3= new Gasolinera(4,"Santander","Cantabria","Calle Castilla4",Double.MAX_VALUE,Double.MAX_VALUE,"Avia",1.4,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera4= new Gasolinera(5,"Santander","Cantabria","Calle Castilla5",Double.MAX_VALUE,1.15,"Avia",Double.MAX_VALUE,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera5= new Gasolinera(6,"Santander","Cantabria","Calle Castilla6",Double.MAX_VALUE,Double.MAX_VALUE,"Avia",1.6,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera6= new Gasolinera(7,"Santander","Cantabria","Calle Castilla7",Double.MAX_VALUE,Double.MAX_VALUE,"Avia",Double.MAX_VALUE,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        Gasolinera gasolinera7= new Gasolinera(8,"Santander","Cantabria","Calle Castilla8",Double.MAX_VALUE,Double.MAX_VALUE,"Avia",Double.MAX_VALUE,"24H",Double.MAX_VALUE,Double.MAX_VALUE,1);
        listaTotal.add(gasolinera0);
        listaTotal.add(gasolinera1);
        listaTotal.add(gasolinera2);
        listaTotal.add(gasolinera3);
        listaTotal.add(gasolinera4);
        listaTotal.add(gasolinera5);
        listaTotal.add(gasolinera6);
        listaTotal.add(gasolinera7);
        gestion.setListaGasolineras(listaTotal);
        listaFiltrada =gestion.filtraPorCarburante("diesel");
        //comprobacion de que todos los que estan en la listaFiltrada tienen gasolina diesel
        assertTrue(listaFiltrada.size()==0);
    }
    }