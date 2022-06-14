package com.android.ocr_digitalisation.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.android.ocr_digitalisation.data.model.Recensement;
import com.android.ocr_digitalisation.utils.Note;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "recensementdb";

    private static final int DB_VERSION = 1;

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Note.TABLE_NAME + " ("
                + Note.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Note.DISTRICT + " TEXT,"
                + Note.COMMUNE + " TEXT,"
                + Note.FOKONTANY + " TEXT,"
                + Note.NOM + " TEXT,"
                + Note.PRENOM + " TEXT,"
                + Note.DATENAISSANCE + " TEXT,"
                + Note.LIEUNAISSANCE + " TEXT,"
                + Note.SEXE + " TEXT,"
                + Note.PERE + " TEXT,"
                + Note.MERE + " TEXT,"
                + Note.DOMICILE + " TEXT,"
                + Note.PROFESSION + " TEXT,"
                + Note.CIN + " TEXT,"
                + Note.DATECREATION + " TEXT,"
                + Note.LIEUCREATION + " TEXT,"
                + Note.NUMEROSERIE + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Note.TABLE_NAME);
        onCreate(db);
    }

}
