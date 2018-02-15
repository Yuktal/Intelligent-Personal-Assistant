package com.example.asu.mc.group7;

import android.Manifest;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import java.io.*;
import java.text.DateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity{

    private static RadioGroup radio_adriana;
    int stay;
    private static RadioButton radio_candice;
    private static int sel;
    public int trainortest = 5, enough = 99, trained = 0;
    Intent serviceintent,serviceintent2;
    public int trainflag = 0, mediator = 0;
    public LocationManager locationManager;
    public LocationListener listener;
    public double lan,lon;
    public Cursor allin;
    public String currentTimeString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AudioManager audio = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        audio.setRingerMode(0);

        String mfile= Environment.getExternalStorageDirectory()+"/daredevil.mp3";
        //   musicIntent=new Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER);
        //  startActivity(musicIntent);
        final MediaPlayer mp = new MediaPlayer();
        mp.setLooping(true);
        try {
            mp.setDataSource(mfile); //mFile is the path to your mp3 file
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mp.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

        serviceintent = new Intent(this, Axel.class);
        radio_adriana = (RadioGroup) findViewById(R.id.behati);

        File filedg = new File(MainActivity.this.getDatabasePath("group7.db").getPath());
        if(filedg.exists())
            new File(MainActivity.this.getDatabasePath("group7.db").getPath()).delete();

        File filedg71 = new File(MainActivity.this.getDatabasePath("group7.db-journal").getPath());
        if(filedg71.exists())
            new File(MainActivity.this.getDatabasePath("group7.db-journal").getPath()).delete();

        File filedg72 = new File(MainActivity.this.getDatabasePath("group72.db").getPath());
        if(filedg72.exists())
            new File(MainActivity.this.getDatabasePath("group72.db").getPath()).delete();

        File filedg721 = new File(MainActivity.this.getDatabasePath("group72.db-journal").getPath());
        if(filedg721.exists())
            new File(MainActivity.this.getDatabasePath("group72.db-journal").getPath()).delete();

        File modeldel = new File(Environment.getExternalStorageDirectory() + "/heart_scale.model");
        if(modeldel.exists())
            new File(Environment.getExternalStorageDirectory() + "/heart_scale.model").delete();

        File modelnel = new File(Environment.getExternalStorageDirectory() + "/heart_scale.model");
        if(modelnel.exists())
            new File(Environment.getExternalStorageDirectory() + "/heart_scale.model").delete();

        File modelgel = new File(Environment.getExternalStorageDirectory() + "/heart_scale.model");
        if(modelgel.exists())
            new File(Environment.getExternalStorageDirectory() + "/heart_scale.model").delete();

        File locdel = new File(MainActivity.this.getDatabasePath("group73.db").getPath());
        if(locdel.exists())
            new File(MainActivity.this.getDatabasePath("group73.db").getPath()).delete();

        File locdelj = new File(MainActivity.this.getDatabasePath("group73.db-journal").getPath());
        if(locdelj.exists())
            new File(MainActivity.this.getDatabasePath("group73.db-journal").getPath()).delete();


        final ServiceConnection serve = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        final ServiceConnection serve2 = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name2, IBinder service2) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name2) {

            }
        };

        Button button = (Button) findViewById(R.id.button2);
        Button button2 = (Button) findViewById(R.id.button3);
        Button button5 = (Button) findViewById(R.id.button5);



        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(trained == 0) {

                    sel = radio_adriana.getCheckedRadioButtonId();
                    radio_candice = (RadioButton) findViewById(sel);

                    Varex.gvar = 1;
                    Varex.lbl = radio_candice.getText().toString();
                    serviceintent.putExtra("kendal", radio_candice.getText().toString());
                    //Start Service
                    bindService(serviceintent, serve, Context.BIND_AUTO_CREATE);
                    trainflag = 1;
                    trained = 1;
                    locationManager.requestLocationUpdates("gps", 1000, 0, listener);
                }

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(trained == 1) {
                    unbindService(serve);
                    Varex.gvar = 0;

                    DatabaseHelper MyDBbg = new DatabaseHelper(getApplicationContext());
                    enough = MyDBbg.getCnum();
                    if(enough!=-1)
                    MyDBbg.deleteData(Integer.toString(enough));


                    trained = 0;
                    mediator = 0;
                    currentTimeString =  DateFormat.getDateTimeInstance().format(new Date());
                    DatabaseHelper3 MyDB3 = new DatabaseHelper3(getApplicationContext());
                    MyDB3.insertData(lan,lon,currentTimeString);
                    allin = MyDB3.getAllData();

                }


            }
        });



        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(trained!=1&&trainflag==1) {


                    Svmt svm = new Svmt();
                    trainortest = 0;
                    svm.trainer(v.getContext(), trainortest);

                mediator = 1;
                    svm_traine t2 = new svm_traine();
                    try {
                        t2.run();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    serviceintent2 = new Intent(getApplicationContext(), Axel.class);
                    serviceintent2.putExtra("kendal", Integer.toString(0));
                    Varex.gvar2 = 1;
                    bindService(serviceintent2, serve2, Context.BIND_AUTO_CREATE);

                    Runnable r = new Runnable() {

                        @Override
                        public void run() {
                            while (true) {
                                if (Varex.last == 1) {

                                    DistanceCalculator Gilgamesh = new DistanceCalculator();
                                    Gilgamesh.run(allin, lan, lon);
                                        if(Varex.fix==1) {
                                            mp.start();
                                            Varex.last = 0;
                                            Varex.fix = 0;
                                        }

                                }
                            }
                        }
                    };
                    Thread musician = new Thread(r);
                    musician.start();
                    trainflag = 0;
                }
            }

        });


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lon = location.getLongitude();
                lan = location.getLatitude();
                Varex.lati = lan;
                Varex.longi = lon;
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        configure_button();
        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                configure_button();
                break;
            default:
                break;
        }
    }

    void configure_button() {
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }
    }


}
