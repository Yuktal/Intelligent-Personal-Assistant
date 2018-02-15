package com.example.asu.mc.group7;

import android.database.Cursor;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import libsvm.*;


import android.content.Context;

/**
 * Created by LUCIFER on 31-Mar-17.
 */

public class Svmt {
    DatabaseHelper MyDB;
    DatabaseHelper2 MyDB2;
    Cursor cara;

    String gigi = null;
    int bella = 0, i = 0, j = 0;
    double axy = 0;
    int comp = 0;
    String fname=null;

    public void trainer(Context context, int trainortest) {
        File sdpath = Environment.getExternalStorageDirectory();
        if(trainortest==0) {
            fname = "heart_scale";
            MyDB = new DatabaseHelper(context);
            cara = MyDB.getAllData();
        }
        else {
            fname = "heart_scale_test";
            MyDB2 = new DatabaseHelper2(context);
            cara = MyDB2.getAllData();
        }
        File fi = new File(sdpath,fname);

        bella = cara.getCount();
        cara.moveToFirst();
        try {
            FileOutputStream fos = new FileOutputStream(fi);
            for (i = 0; i < bella; i++) {
                if (cara.getString(151).compareTo("Running") == 0)
                    comp = 1;
                else if (cara.getString(151).compareTo("Walking") == 0)
                    comp = 2;
                else if (cara.getString(151).compareTo("Eating") == 0)
                    comp = 3;
                else if (cara.getString(151).compareTo("0") == 0)
                    comp = 0;
                if (i == 0)
                    gigi = Integer.toString(comp);
                else
                    gigi = gigi + Integer.toString(comp);
                for (j = 1; j <= 150; j++) {
                    gigi = gigi + " " + j + ":" + cara.getString(j);
                }
                gigi = gigi + "\n";
                cara.moveToNext();
            }
            fos.write(gigi.getBytes());
            fos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
