package com.example.nachoaguero.appgasolineras.Utilities;



import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileOperations {

           public static void writeInputStream(InputStream in, Context c){
            try {
                File file = new File(c.getFilesDir(), "inputStreamJson");
                OutputStream out = new FileOutputStream(file);
                byte[] buf = new byte[1024];
                int len;
                while((len=in.read(buf))>0){
                    out.write(buf,0,len);
                }
                out.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }//try
        }//writeInputStream

        public static InputStream readInputStream(Context c){
            InputStream in = null;
            try {
                File file = new File(c.getFilesDir(), "inputStreamJson");
                in = new FileInputStream(file);
            } catch (Exception e) {
                e.printStackTrace();
            }//try
            return in;
        }//writeInputStream
    }

