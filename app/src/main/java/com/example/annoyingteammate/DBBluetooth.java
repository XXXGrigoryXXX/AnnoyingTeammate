package com.example.annoyingteammate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBBluetooth extends SQLiteOpenHelper {

    public DBBluetooth(Context context) {

        super(context, "DBBluetooth", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table bluetooth ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "description text,"
                + "notification text" + ");");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
