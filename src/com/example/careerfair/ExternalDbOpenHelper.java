/**
 * This class handles the database set up/opening from a pre-populated database stored in the assets folder
 * 
 * Majority of code borrowed from:
 * http://blog.softeq.com/2012/12/using-pre-populated-sqlite-database-in.html
 * with some modifications
 * 
 * @author Hannah Wilder
 * @version 1.0
 */

package com.example.careerfair;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class ExternalDbOpenHelper extends SQLiteOpenHelper {

     //Path to the device folder with databases
    public static String DB_PATH;

     //Database file name
    public static String DB_NAME;
    public SQLiteDatabase database;
    public final Context context;
 
   // private ArrayList companies = new ArrayList<String>();
    /**
     * returns the database associated with the helper
     * 
     * @return the database associated with the helper
     */
     public SQLiteDatabase getDb() {
        return database;
    }

     /**
      * ExternalDbOpenHelper constructor
      * 
      * @param context (use 'this')
      * @param databaseName - the name of the database to open (careerFairDB.db)
      * @return whether the database exists
      */
     public ExternalDbOpenHelper(Context context, String databaseName) {
    	super(context, databaseName, null, 1);
        this.context = context;
     //Write a full path to the databases of your application
     String packageName = context.getPackageName();
     DB_PATH = String.format("//data//data//%s//databases//", packageName);
     DB_NAME = databaseName;
     openDataBase();
    }

     /**
      * Creates the database if it doesn't exist 
      * 
      * @return whether the database exists
      */
    public void createDataBase() {
        boolean dbExist = checkDataBase();
        if (!dbExist) {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e(this.getClass().toString(), "Copying error");
                throw new Error("Error copying database!");
            }
        } else {
            Log.i(this.getClass().toString(), "Database already exists");
        }
    }

    /**
     * Checks to see if the database already exists
     * 
     * @return whether the database exists
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDb = null;
        try {
            String path = DB_PATH + DB_NAME;
            checkDb = SQLiteDatabase.openDatabase(path, null,
                          SQLiteDatabase.OPEN_READONLY);
        } catch (SQLException e) {
            Log.i(this.getClass().toString(), "Database does not exist yet, ignore above errors, database will be created momentarily");
        }
        //Android doesn�t like resource leaks, everything should 
        // be closed
        if (checkDb != null) {
            checkDb.close();
        }
        return checkDb != null;
    }

    /**
     * Copies the database in the asset folder to the local database
     * 
     * @throws IOException
     */
    private void copyDataBase() throws IOException {
        //Open a stream for reading from our ready-made database
        //The stream source is located in the assets
        InputStream externalDbStream = context.getAssets().open(DB_NAME);

         //Path to the created empty database on your Android device
        String outFileName = DB_PATH + DB_NAME;

         //Now create a stream for writing the database byte by byte
        OutputStream localDbStream = new FileOutputStream(outFileName);

         //Copying the database
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = externalDbStream.read(buffer)) > 0) {
            localDbStream.write(buffer, 0, bytesRead);
        }
        //Don�t forget to close the streams
        localDbStream.close();
        externalDbStream.close();
    }

    /**
     * Opens the database associated with this class
     * 
     * @returns the database associated with this class
     * @throws SQLException
     */
    public SQLiteDatabase openDataBase() throws SQLException {
        String path = DB_PATH + DB_NAME;
        if (database == null) {
            createDataBase();
            database = SQLiteDatabase.openDatabase(path, null,
                SQLiteDatabase.OPEN_READWRITE);
        }
        return database;
    }

    /**
     * Disposes of instances of ExternalDbOpenHelper
     * 
     */
    @Override
    public synchronized void close() {
        if (database != null) {
            database.close();
        }
        super.close();
    }
    
    /**
     * Method not in use right now
     * 
     * @param  SQLiteDatabase db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {}
    
    /**
     * Method not in use right now
     * 
     * @param  SQLiteDatabase db
     * @param oldVersion - the old version number of the database
     * @param newVersion - the new version number of the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}


