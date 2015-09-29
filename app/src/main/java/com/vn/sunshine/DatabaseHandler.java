package com.vn.sunshine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vn on 28/9/15.
 */


public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "weatherManager";

    // Weathers table name
    private static final String TABLE_WEATHER = "weather";

    // Weathers Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LONG = "long";
    private static final String KEY_DATA = "data";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WEATHER_TABLE = "CREATE TABLE " + TABLE_WEATHER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LAT + " DOUBLE,"
                + KEY_LONG + " DOUBLE," + KEY_DATA + " TEXT" + ")";
        db.execSQL(CREATE_WEATHER_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEATHER);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new weather
    void addWeather(Weather weather) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LAT, weather.get_lat()); // Weather Name
        values.put(KEY_LONG, weather.get_long()); // Weather Phone
        values.put(KEY_DATA,weather.get_weather());

        // Inserting Row
        db.insert(TABLE_WEATHER, null, values);
        db.close(); // Closing database connection
    }

    // Getting single weather
    Weather getWeather(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WEATHER, new String[] { KEY_ID,
                        KEY_LAT, KEY_LONG,KEY_DATA }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Weather weather = new Weather(Integer.parseInt(cursor.getString(0)),
                cursor.getDouble(1), cursor.getDouble(2), cursor.getString(3));
        // return weather
        return weather;
    }

    // Getting All Weathers
    public List<Weather> getAllWeathers() {
        List<Weather> weatherList = new ArrayList<Weather>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_WEATHER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Weather weather = new Weather();
                weather.set_id(Integer.parseInt(cursor.getString(0)));
                weather.set_lat(cursor.getDouble(1));
                weather.set_long(cursor.getDouble(2));
                weather.set_weather(cursor.getString(3));
                // Adding weather to list
                weatherList.add(weather);
            } while (cursor.moveToNext());
        }

        // return weather list
        return weatherList;
    }

    // Updating single weather
    public int updateWeather(Weather weather) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LAT, weather.get_lat()); // Weather Name
        values.put(KEY_LONG, weather.get_long()); // Weather Phone
        values.put(KEY_DATA,weather.get_weather());

        // updating row
        return db.update(TABLE_WEATHER, values, KEY_ID + " = ?",
                new String[] { String.valueOf(weather.get_id()) });
    }

    // Deleting single weather
    public void deleteWeather(Weather weather) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WEATHER, KEY_ID + " = ?",
                new String[] { String.valueOf(weather.get_id()) });
        db.close();
    }


    // Getting weathers Count
    public int getWeathersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_WEATHER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

}