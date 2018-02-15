package com.example.asu.mc.group7;

/**
 * Created by LUCIFER on 21-Apr-17.
 */

import android.content.Context;
import android.database.Cursor;
import android.location.Location;

import java.util.*;
import java.lang.*;
import java.io.*;

public class DistanceCalculator
{

    int dloop;
    float ugh = 100;
    float [] dist = new float[1];
    public void run(Cursor allin, double lat2, double lon2)
    {
        Varex.omni = "entered";
        int n = allin.getCount();
        allin.moveToFirst();
        for(dloop = 0; dloop<n; dloop++)
        {
            Location.distanceBetween(allin.getDouble(0),allin.getDouble(1),lat2,lon2,dist);
            if(dist[0]<=ugh) {
                Varex.omni = "condition correct";
                Varex.fix = 1;
            }
            Varex.cur = dist[0];
            if(dloop!=n-1)
                allin.moveToNext();
        }
    }

}
