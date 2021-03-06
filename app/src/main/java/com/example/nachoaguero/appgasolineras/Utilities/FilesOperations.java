package com.example.nachoaguero.appgasolineras.Utilities;


import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by alejandro on 25/10/16.
 */

public class FilesOperations {

    public static void writeInputStream(InputStream in, Context c) {
        try {
            File file = new File(c.getFilesDir(), "inputStreamJson");
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
            //e.printStackTrace();//pre-sonar
        }//try
    }//writeInputStream

    public static InputStream readInputStream(Context c) throws IOException {
        InputStream in = null;
        try {
            File file = new File(c.getFilesDir(), "inputStreamJson");
            in = new FileInputStream(file);

        } catch (Exception e) {
            throw new RuntimeException(e);
            //e.printStackTrace(); //pre-sonar
        }

        return in;
        //writeInputStream

    }

}