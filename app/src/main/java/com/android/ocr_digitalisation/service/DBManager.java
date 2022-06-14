package com.android.ocr_digitalisation.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.ocr_digitalisation.data.model.Recensement;
import com.android.ocr_digitalisation.utils.Note;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DBManager {
    private DBHandler dbHandler;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        try{
            dbHandler = new DBHandler(context);
            database = dbHandler.getWritableDatabase();

        }catch (Exception exception){
            Log.d("Error", "error in openning dbmanager");
            exception.printStackTrace();
        }
        return this;
    }

    public void close() {
        dbHandler.close();
    }

    public void addFicheRecensementTest() {
        try{
            for (int i = 0; i <= 1000; i++) {
                ContentValues values = new ContentValues();

                values.put(Note.DISTRICT, "district" + i);
                values.put(Note.COMMUNE, "commune" + i);
                values.put(Note.FOKONTANY, "fokontany" + i);
                values.put(Note.NOM, "nom" + i);
                values.put(Note.PRENOM, "prenom" + i);
                values.put(Note.DATENAISSANCE, "datenaissance" + i);
                values.put(Note.LIEUNAISSANCE, "lieunaissance" + i);
                values.put(Note.SEXE, "sexe" + i);
                values.put(Note.PERE, "pere" + i);
                values.put(Note.MERE, "mere" + i);
                values.put(Note.DOMICILE, "domicile" + i);
                values.put(Note.PROFESSION, "profession" + i);
                values.put(Note.CIN, "cin" + i);
                values.put(Note.DATECREATION, "datecreation" + i);
                values.put(Note.LIEUCREATION, "lieucreation" + i);
                values.put(Note.NUMEROSERIE, "numeroserie" + i);

                Log.d("addFicheRecensementTest", "Nombre fichier : " + i);

                database.insert(Note.TABLE_NAME, null, values);
            }
        }catch (Exception exception){
            Log.d("Error", "error in addRecensement on getrecensement");
            exception.printStackTrace();
        }

    }

    public void addFicheRecensement(String district, String commune, String fokontany, String nom, String prenom, String datenaissance, String lieunaissance, String sexe, String pere, String mere, String domicile, String profession, String cin, String datecreation, String lieucreation, String numeroserie) {
        try{
            ContentValues values = new ContentValues();

            values.put(Note.DISTRICT, district);
            values.put(Note.COMMUNE, commune);
            values.put(Note.FOKONTANY, fokontany);
            values.put(Note.NOM, nom);
            values.put(Note.PRENOM, prenom);
            values.put(Note.DATENAISSANCE, datenaissance);
            values.put(Note.LIEUNAISSANCE, lieunaissance);
            values.put(Note.SEXE, sexe);
            values.put(Note.PERE, pere);
            values.put(Note.MERE, mere);
            values.put(Note.DOMICILE, domicile);
            values.put(Note.PROFESSION, profession);
            values.put(Note.CIN, cin);
            values.put(Note.DATECREATION, datecreation);
            values.put(Note.LIEUCREATION, lieucreation);
            values.put(Note.NUMEROSERIE, numeroserie);

            database.insert(Note.TABLE_NAME, null, values);
        }catch (Exception exception){
            Log.d("Error", "error in addRecensement on getrecensement");
            exception.printStackTrace();
        }
    }

    public Recensement getRecensement(long id) {
        Log.d("Call function","getRecensement()");
        Recensement fiche = null;
        try {
            Cursor cursor = database.query(Note.TABLE_NAME,
                    new String[]{Note.ID,Note.DISTRICT, Note.COMMUNE, Note.FOKONTANY, Note.NOM, Note.PRENOM, Note.DATENAISSANCE, Note.LIEUNAISSANCE, Note.SEXE, Note.PERE, Note.MERE, Note.DOMICILE, Note.PROFESSION, Note.CIN, Note.DATECREATION, Note.LIEUCREATION, Note. NUMEROSERIE},
                    Note.ID + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);

            if (cursor != null)
                cursor.moveToFirst();

            // prepare note object
            fiche = new Recensement(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(8),
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getString(12),
                    cursor.getString(13),
                    cursor.getString(14),
                    cursor.getString(15),
                    cursor.getString(16));

            // close the db connection
            // cursor.close();

        } catch (Exception exception) {
            Log.d("Error", "error in cursor on getrecensement");
            exception.printStackTrace();
        }
        return fiche;
    }

    public Cursor fetch() {
        String[] columns = new String[]{Note.ID, Note.DISTRICT, Note.COMMUNE, Note.FOKONTANY, Note.NOM, Note.PRENOM, Note.DATENAISSANCE, Note.LIEUNAISSANCE, Note.SEXE, Note.PERE, Note.MERE, Note.DOMICILE, Note.PROFESSION, Note.CIN, Note.DATECREATION, Note.LIEUCREATION, Note. NUMEROSERIE};
        Cursor cursor = database.query(Note.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public ArrayList<Recensement> readRecensement() {
        // on below line we are creating a new array list.
        ArrayList<Recensement> recensementArrayList = new ArrayList<>();
        try {
            // on below line we are creating a
            // database for reading our database.
            // SQLiteDatabase db = this.getReadableDatabase();
            // SQLiteDatabase db = new DBHandler(context).getWritableDatabase();

            Log.d("Result : ","Avant query");
            String Query = "SELECT  * FROM " + Note.TABLE_NAME;
            Cursor cursorRecensement = database.rawQuery(Query, null);

            if (cursorRecensement != null) {
                cursorRecensement.moveToFirst();
            }

            Log.d("Result : ","mihiditra boucle");
            // moving our cursor to first position.
            if (cursorRecensement.moveToFirst()) {
                do {
                    Log.d("Result : ",cursorRecensement.getString(1));
                    // on below line we are adding the data from cursor to our array list.
                    recensementArrayList.add(new Recensement(cursorRecensement.getString(0),
                            cursorRecensement.getString(1),
                            cursorRecensement.getString(2),
                            cursorRecensement.getString(3),
                            cursorRecensement.getString(4),
                            cursorRecensement.getString(5),
                            cursorRecensement.getString(6),
                            cursorRecensement.getString(7),
                            cursorRecensement.getString(8),
                            cursorRecensement.getString(9),
                            cursorRecensement.getString(10),
                            cursorRecensement.getString(11),
                            cursorRecensement.getString(12),
                            cursorRecensement.getString(13),
                            cursorRecensement.getString(14),
                            cursorRecensement.getString(15)));
                } while (cursorRecensement.moveToNext());
                // cursor to next.
            }
            cursorRecensement.close();
        } catch (Exception exception) {
            {
                Log.d("Error", "error in cursor");
                exception.printStackTrace();
            }
        }
        return recensementArrayList;
    }

    public void deleteRecensement(Recensement recensement) {
        database.delete(Note.TABLE_NAME, Note.ID + " = ?",
                new String[]{String.valueOf(recensement.getId())});
    }

    public void censusFileDump(){
        database.execSQL("DELETE FROM " + Note.TABLE_NAME);
    }

    public int getRecensementsCount() {
        String countQuery = "SELECT  * FROM " + Note.TABLE_NAME;
        Cursor cursor = database.rawQuery(countQuery, null);

        int count = cursor.getCount();

        // return count
        return count;
    }

}
