package com.example.asu.mc.group7;

/**
 * Created by LUCIFER on 06-Mar-17.
 */

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;




public class Axel extends Service implements SensorEventListener{

    Sensor accelerometer;
    SensorManager sm;
    DatabaseHelper MyDB;
    DatabaseHelper2 MyDB2;
    String kylie = "Running";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, 100000);
        kylie = intent.getExtras().getString("kendal");
       if(kylie.compareTo("0")!=0)
        MyDB = new DatabaseHelper(getApplicationContext());
        if(kylie.compareTo("0")==0){
            MyDB2 = new DatabaseHelper2(getApplicationContext());
        }

       return null;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(kylie.compareTo("0")!=0)
        MyDB.insertData(event.values[0],event.values[1],event.values[2], kylie);
        if(kylie.compareTo("0")==0)
        {
            MyDB2.insertData(getApplicationContext(),event.values[0],event.values[1],event.values[2]);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onDestroy()
    {
    }
}
