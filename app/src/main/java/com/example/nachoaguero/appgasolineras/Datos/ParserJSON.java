package com.example.nachoaguero.appgasolineras.Datos;

/**
 * Created by Alexandru on 18/10/2016.
 */


        import android.util.JsonReader;
        import android.util.JsonToken;
        import android.util.Log;

        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.util.ArrayList;
        import java.util.List;

        import com.example.nachoaguero.appgasolineras.Datos.Gasolinera;

/**
 *
 * Clase que contiene los metodos necesarios para parsear el JSON que devuelve el servicio REST con
 * las estaciones de servicios.
 */

public class ParserJSON{

    /**
     * @param in Inputsream que contiene los datos del JSON
     * @return Retorno de una lista de gasolineras en la que se guardarán las estaciones de servicio
     *          obtenidas tras parsear el JSON
     * @throws IOException
     */
    public static List<Gasolinera> readJsonStream (InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readArrayGasolineras(reader);
        } finally {
            reader.close();
        }
    }

    public static List readArrayGasolineras (JsonReader reader) throws IOException {
        List<Gasolinera> listGasolineras = new ArrayList<Gasolinera>();

        reader.beginObject();
        while(reader.hasNext()){
            String name = reader.nextName();
            Log.d("ENTRA", "Nombre del elemento: "+name);
            if(name.equals("ListaEESSPrecio")){
                reader.beginArray();
                while (reader.hasNext()){
                    listGasolineras.add(readGasolinera(reader));
                }//while
                reader.endArray();
            }else{
                reader.skipValue();
                //if
            }
        }//while
        reader.endObject();
        return listGasolineras;
    }//readArrayGasolineras

    public static Gasolinera readGasolinera (JsonReader reader) throws IOException {
        reader.beginObject();
        boolean add = false;
        String rotulo="", localidad ="", provincia="",direccion="";
        int id = -1;
        double gasoleoA = 0.0, sinplomo95 =10000.0;

        while(reader.hasNext()){
            String name = reader.nextName();

            if (name.equals("Rótulo") && reader.peek() != JsonToken.NULL) {
                rotulo = reader.nextString();
            }else if (name.equals("Localidad") && reader.peek() != JsonToken.NULL) {
                localidad = reader.nextString();
            }else if(name.equals("Provincia")&& reader.peek() != JsonToken.NULL){
                provincia = reader.nextString();
            }else if(name.equals("IDEESS")&& reader.peek() != JsonToken.NULL){
                id = reader.nextInt();
            }else if(name.equals("Precio Gasoleo A") && reader.peek() != JsonToken.NULL) {
                gasoleoA = Double.parseDouble(reader.nextString().replace(",","."));
            }else if(name.equals("Precio Gasolina 95 Protección")&& reader.peek() != JsonToken.NULL) {
                sinplomo95 = Double.parseDouble(reader.nextString().replace(",", "."));

            }else if(name.equals("Dirección")&& reader.peek() != JsonToken.NULL){
                direccion = reader.nextString();
            }else{
                reader.skipValue();
            }//if

        }// while

        reader.endObject();

        return new Gasolinera(id,localidad,provincia,direccion,gasoleoA, sinplomo95,rotulo);
    }// readGasolinera
}//ParserJSON

