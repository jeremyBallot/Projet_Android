package com.example.etu.projet_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by etu on 22/12/15.
 */
public class etuaffiche {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NOM = "nom";
    public static final String KEY_PRENOM = "prenom";
    public static final String KEY_AGE = "age" ;
    public static final String KEY_EMAIL = "email";

    private static final String TAG = "etuaffiche";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "BDD_ANDROID";
    private static final String SQLITE_TABLE = "ETU";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    //creation de la bases
    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    KEY_NOM + "," +
                    KEY_PRENOM + "," +
                    KEY_AGE  + "," +
                    KEY_EMAIL + "," +
                    " UNIQUE (" + KEY_EMAIL + "))";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + "");
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(db);
        }
    }

    public etuaffiche(Context ctx) {
        this.mCtx = ctx;
    }

    public etuaffiche open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createUser(String nom, String prenom,
                           String age, String email) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NOM, nom);
        initialValues.put(KEY_PRENOM, prenom);
        initialValues.put(KEY_AGE , age);
        initialValues.put(KEY_EMAIL, email);

        return mDb.insert(SQLITE_TABLE, null, initialValues);
    }

    //supprime tt les étudiants
    public boolean deleteAllETU() {

        int doneDelete = 0;
        doneDelete = mDb.delete(SQLITE_TABLE, null, null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }

    // permet de rechercher par nom
    public Cursor fetchNomEtu(String nom) throws SQLException {
        Log.w(TAG, nom);
        Cursor mCursor = null;
        if (nom == null || nom.length() == 0) {
            mCursor = mDb.query(SQLITE_TABLE, new String[]{KEY_ROWID,
                            KEY_NOM, KEY_PRENOM, KEY_AGE , KEY_EMAIL},
                    null, null, null, null, null);

        } else {
            mCursor = mDb.query(true, SQLITE_TABLE, new String[]{KEY_ROWID,
                            KEY_NOM, KEY_PRENOM, KEY_AGE , KEY_EMAIL},
                    KEY_NOM + " like '%" + nom + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    // affiche tt les etudiants
    public Cursor fetchAll() {

        Cursor mCursor = mDb.query(SQLITE_TABLE, new String[]{KEY_ROWID,
                        KEY_NOM, KEY_PRENOM, KEY_AGE , KEY_EMAIL},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //permet d'insérer les étudiants
    public void insertEtu() {

        createUser("Ballot", "Jeremy", "26", "ballot.jeremy@gmail.com");
        createUser("estelle", "catherine", "18", "c.estelle@hotmail.fr");


    }
}

//fonction que je n'est pas eu le tps de mettre en place au niveau de la fenetre de la base de donnée
//la bare de recherche etant adaptable selon un menu avec des item chekable donc par nom, prenom ou age

/*public Cursor fetchPrenomEtu(String nom) throws SQLException {
        Log.w(TAG, prenom);
        Cursor mCursor = null;
        if (prenom == null || prenom.length() == 0) {
            mCursor = mDb.query(SQLITE_TABLE, new String[]{KEY_ROWID,
                            KEY_NOM, KEY_PRENOM, KEY_AGE , KEY_EMAIL},
                    null, null, null, null, null);

        } else {
            mCursor = mDb.query(true, SQLITE_TABLE, new String[]{KEY_ROWID,
                            KEY_NOM, KEY_PRENOM, KEY_AGE , KEY_EMAIL},
                    KEY_NOM + " like '%" + prenom + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }*/

// suppression selon un nom
/*
    public void deleteCountry(String nom) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SQLITE_TABLE , KEY_nom + " = ?",
            new String[] { String.valueOf(c.getnom()) });
            db.close();
}
*/

// insertion 

/*
public void insertEtu (String nom, String prenom, String age, String email){
    createUser(nom, prenom, age, email);


}*/