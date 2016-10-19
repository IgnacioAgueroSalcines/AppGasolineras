package com.example.nachoaguero.appgasolineras.Datos;

/**
 * Created by malda on 18/10/2016.
 */




        import android.util.Log;
        import java.io.BufferedInputStream;
        import java.io.IOException;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.List;


/**
 * Clase en la que se realizan la descarga de los datos desde el servicio REST
 */

public class RemoteFetch {
    //URL para obtener todas las gasolineras
    //private static final String URL_GASOLINERAS_SPAIN="https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/";

    //ID de la comunidad autonoma de Cantabria: 06
     private static final String URL_GASOLINERAS_CANTABRIA="https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/FiltroCCAA/06";

    //ID de Santander: 5819
   //rivate static final String URL_GASOLINERAS_SANTANDER="https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/FiltroMunicipio/5819";

    //Web de ayuda con todos los filtros posibles
    //https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/help

    ///////////////////////////////////////////
    // LISTA de GASOLINERA
    private List<Gasolinera> gasolineraList;
    //////////////////////////////////////////

    private BufferedInputStream bufferedDataGasolineras;

    /**
     * Metodo que a través de una dirección URL obtiene el bufferedInputStream correspondiente
     * al JSON alojado en el servidor y lo almacena en el atributo bufferedDataGasolineras de la
     * clase
     * @throws IOException
     */

    public void getJSON() throws IOException{
        URL url = new URL(URL_GASOLINERAS_CANTABRIA);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.addRequestProperty("Accept", "application/json");
        //BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        bufferedDataGasolineras =  new BufferedInputStream(urlConnection.getInputStream());
        //Comprobamos que el archivo JSON se ha leido con éxito
        comprobarLecturaJson();
        //gasolineraList = ParserJSON.readJsonStream(new BufferedInputStream(urlConnection.getInputStream()));
    }//getJSON

    /**
     * Retorna el BufferedInputStream con el JSON, pero para que el objeto no este vacío debemos de
     * llamar antes a getJSON
     * @return
     */
    public BufferedInputStream getBufferedDataGasolineras() {
        return bufferedDataGasolineras;
    }//getBufferedDataGasolineras

    public void comprobarLecturaJson() throws IOException{
        // COMPROBAR QUE LA LECTURA DEL JSON SE HA REALIZADO CON ÉXITO
        if(bufferedDataGasolineras==null){
            throw new IOException("La lectura de los datos no se ha producido");
        }
    }

}//RemoteFetch
