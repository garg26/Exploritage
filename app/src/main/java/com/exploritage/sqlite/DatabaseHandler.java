package com.exploritage.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.exploritage.model.DownloadDestinationInformation;
import com.exploritage.model.DownloadCityInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kartikeya on 27/7/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "audiocitydetail";
    private static final String TABLE_NAME = "details";

    private static final String KEY_CITY_ID = "cityID";
    private static final String KEY_CITY_NAME = "cityName";
    private static final String KEY_CITY_IMAGE_URL = "cityImageUrl";
    private static final String KEY_AUDIO_URL = "audioUrl";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_CITY_ID + " TEXT PRIMARY KEY,"
                + KEY_CITY_NAME + " TEXT,"
                + KEY_CITY_IMAGE_URL + " TEXT,"
                + KEY_AUDIO_URL + " TEXT" + ")";
        database.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }

    public void insert(DownloadCityInformation cityInformation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CITY_ID, cityInformation.getCityID());
        values.put(KEY_CITY_NAME, cityInformation.getCityName());
        values.put(KEY_CITY_IMAGE_URL, cityInformation.getCityImageUrl());

        try {
            db.insert(TABLE_NAME, null, values);
        } catch (SQLiteException e) {
            e.printStackTrace();
            db.update(TABLE_NAME, values, null, null);
        }
        db.close();
    }

    public List<DownloadCityInformation> getDetail(String cityID) {
        List<DownloadCityInformation> cityInformationArrayList = new ArrayList<DownloadCityInformation>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, KEY_CITY_NAME + "=?",
                new String[]{cityID}, null,null,null);
        if (cursor.moveToFirst()) {
            do {
                DownloadCityInformation audioCityInformation = new DownloadCityInformation(cursor.getString(cursor.getColumnIndex(KEY_CITY_ID)), cursor.getString(cursor.getColumnIndex(KEY_CITY_NAME)), cursor.getString(cursor.getColumnIndex(KEY_CITY_IMAGE_URL)));
                cityInformationArrayList.add(audioCityInformation);
            } while (cursor.moveToNext());
        }
        return cityInformationArrayList;
    }

    public void delete(String cityID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_CITY_ID + " = ?",
                new String[]{cityID});
        db.close();
    }

    public List<DownloadCityInformation> getAllDetails() {
        List<DownloadCityInformation> cityInformationList = new ArrayList<DownloadCityInformation>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DownloadCityInformation cityInformation = new DownloadCityInformation(cursor.getString(cursor.getColumnIndex(KEY_CITY_ID)), cursor.getString(cursor.getColumnIndex(KEY_CITY_NAME)), cursor.getString(cursor.getColumnIndex(KEY_CITY_IMAGE_URL)));
                cityInformationList.add(cityInformation);
            } while (cursor.moveToNext());
        }

        // return contact list
        return cityInformationList;
    }
}
