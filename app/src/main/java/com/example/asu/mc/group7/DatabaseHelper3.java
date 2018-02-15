package com.example.asu.mc.group7;

/**
 * Created by LUCIFER on 06-Mar-17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper3 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "group73.db";
    public static final String TABLE_NAME = "Location";
    public static String COL_1 = "Lan";
    public static String COL_2 = "Lon";
    public static String COL_3 = "Time";

    public DatabaseHelper3(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +" (Lan REAL,Lon REAL,Time varchar(20))");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public int insertData(double lan,double lon,String dtime) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
                contentValues.put(COL_1, lan);
                contentValues.put(COL_2, lon);
                contentValues.put(COL_3, dtime);
                db.insert(TABLE_NAME, null, contentValues);
        return 0;
    }

    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }
    public int getCnum() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor reg1 = db.rawQuery("select * from "+TABLE_NAME, null);
        return reg1.getCount();
    }


    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
}
