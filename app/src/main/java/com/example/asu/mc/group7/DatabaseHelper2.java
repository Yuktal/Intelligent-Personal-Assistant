package com.example.asu.mc.group7;

/**
 * Created by LUCIFER on 06-Mar-17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;


public class DatabaseHelper2 extends SQLiteOpenHelper {
    public static int ccount=1;
    public static int rcount=1;
    public static final String DATABASE_NAME = "group72.db";
    public static final String TABLE_NAME = "Testdb";
    public static final String COL_1 = "ID";
    public static String COL_2 = "X_";
    public static String COL_3 = "Y_";
    public static String COL_4 = "Z_";
    public static String Class = "Class";
    public String testout;
    public double taylor;
    public double hotwheels = 0;

    public DatabaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +" (ID INTEGER, X_1 REAL,Y_1 REAL,Z_1 REAL,X_2 REAL,Y_2 REAL,Z_2 REAL,X_3 REAL,Y_3 REAL,Z_3 REAL,X_4 REAL,Y_4 REAL,Z_4 REAL,X_5 REAL,Y_5 REAL,Z_5 REAL,X_6 REAL,Y_6 REAL,Z_6 REAL,X_7 REAL,Y_7 REAL,Z_7 REAL,X_8 REAL,Y_8 REAL,Z_8 REAL,X_9 REAL,Y_9 REAL,Z_9 REAL,X_10 REAL,Y_10 REAL,Z_10 REAL,X_11 REAL,Y_11 REAL,Z_11 REAL,X_12 REAL,Y_12 REAL,Z_12 REAL,X_13 REAL,Y_13 REAL,Z_13 REAL,X_14 REAL,Y_14 REAL,Z_14 REAL,X_15 REAL,Y_15 REAL,Z_15 REAL,X_16 REAL,Y_16 REAL,Z_16 REAL,X_17 REAL,Y_17 REAL,Z_17 REAL,X_18 REAL,Y_18 REAL,Z_18 REAL,X_19 REAL,Y_19 REAL,Z_19 REAL,X_20 REAL,Y_20 REAL,Z_20 REAL,X_21 REAL,Y_21 REAL,Z_21 REAL,X_22 REAL,Y_22 REAL,Z_22 REAL,X_23 REAL,Y_23 REAL,Z_23 REAL,X_24 REAL,Y_24 REAL,Z_24 REAL,X_25 REAL,Y_25 REAL,Z_25 REAL,X_26 REAL,Y_26 REAL,Z_26 REAL,X_27 REAL,Y_27 REAL,Z_27 REAL,X_28 REAL,Y_28 REAL,Z_28 REAL,X_29 REAL,Y_29 REAL,Z_29 REAL,X_30 REAL,Y_30 REAL,Z_30 REAL,X_31 REAL,Y_31 REAL,Z_31 REAL,X_32 REAL,Y_32 REAL,Z_32 REAL,X_33 REAL,Y_33 REAL,Z_33 REAL,X_34 REAL,Y_34 REAL,Z_34 REAL,X_35 REAL,Y_35 REAL,Z_35 REAL,X_36 REAL,Y_36 REAL,Z_36 REAL,X_37 REAL,Y_37 REAL,Z_37 REAL,X_38 REAL,Y_38 REAL,Z_38 REAL,X_39 REAL,Y_39 REAL,Z_39 REAL,X_40 REAL,Y_40 REAL,Z_40 REAL,X_41 REAL,Y_41 REAL,Z_41 REAL,X_42 REAL,Y_42 REAL,Z_42 REAL,X_43 REAL,Y_43 REAL,Z_43 REAL,X_44 REAL,Y_44 REAL,Z_44 REAL,X_45 REAL,Y_45 REAL,Z_45 REAL,X_46 REAL,Y_46 REAL,Z_46 REAL,X_47 REAL,Y_47 REAL,Z_47 REAL,X_48 REAL,Y_48 REAL,Z_48 REAL,X_49 REAL,Y_49 REAL,Z_49 REAL,X_50 REAL,Y_50 REAL,Z_50 REAL,Class varchar(20))");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public int insertData(Context sercontext,double xvalue,double yvalue,double zvalue) {
        if (Varex.gvar2 == 1) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            if (ccount == 51)
                ccount = 1;
            COL_2 = "X_";
            COL_3 = "Y_";
            COL_4 = "Z_";
            COL_2 = COL_2.concat(Integer.toString(ccount));
            COL_3 = COL_3.concat(Integer.toString(ccount));
            COL_4 = COL_4.concat(Integer.toString(ccount));
            if (ccount == 1) {
                contentValues.put(COL_1, rcount);
                contentValues.put(Class, "0");
            }
            contentValues.put(COL_2, xvalue);
            contentValues.put(COL_3, yvalue);
            contentValues.put(COL_4, zvalue);
            if (ccount == 1) {

                db.insert(TABLE_NAME, null, contentValues);
                ccount = 2;
                rcount++;
            } else {
                db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{Integer.toString(rcount - 1)});
                ccount++;
            }
            if(ccount==51) {
                Svmt svm = new Svmt();
                svm.trainer(sercontext, 1);

                try {
                    taylor = Predictor.predicto(sercontext);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                db.delete(TABLE_NAME, "ID = ?",new String[] {Integer.toString(rcount-1)});
                rcount--;
                if(taylor==1) {
                    testout = "Running";
                    Varex.last = 1;

                }
            }
        }
        return 0;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }
    public int getCnum() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor reg2 = db.rawQuery("select * from "+TABLE_NAME, null);
        return reg2.getCount();
    }


    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        rcount--;
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

}
