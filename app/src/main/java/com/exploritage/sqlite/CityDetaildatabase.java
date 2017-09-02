package com.exploritage.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.exploritage.model.DownloadDestinationInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kartikeya on 27/7/17.
 */

public class CityDetaildatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "audiodestiondetail";
    private static final String TABLE_NAME = "destinationdetail";

    private static final String KEY_DESTINATION_ID = "destinationID";
    private static final String KEY_CITY_ID = "cityID";
    private static final String KEY_DESTINATION_NAME = "destinationName";
    private static final String KEY_DESTINATION_IMAGE_URL = "destinationImageUrl";
    private static final String KEY_AUDIO_PATH = "audioFileUrl";

    public CityDetaildatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_DESTINATION_ID + " TEXT PRIMARY KEY,"
                + KEY_CITY_ID + " TEXT,"
                + KEY_DESTINATION_NAME + " TEXT,"
                + KEY_DESTINATION_IMAGE_URL + " TEXT,"
                + KEY_AUDIO_PATH + " TEXT" + ")";
        database.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }

    public void insert(DownloadDestinationInformation destinationInformation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DESTINATION_ID, destinationInformation.getObjectID());
        values.put(KEY_CITY_ID, destinationInformation.getCityID());
        values.put(KEY_DESTINATION_NAME, destinationInformation.getDestinationName());
        values.put(KEY_DESTINATION_IMAGE_URL, destinationInformation.getDestinationImageUrl());
        values.put(KEY_AUDIO_PATH,destinationInformation.getAudioFilePath());

        try {
            db.insert(TABLE_NAME, null, values);
        } catch (SQLiteException e) {
            e.printStackTrace();
            db.update(TABLE_NAME, values, null, null);
        }
        db.close();
    }

    public List<DownloadDestinationInformation> getDetail(String cityID) {
        List<DownloadDestinationInformation> cityInformationArrayList = new ArrayList<DownloadDestinationInformation>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, KEY_CITY_ID + "=?",
                new String[]{cityID}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                DownloadDestinationInformation destinationInformation = new DownloadDestinationInformation(cursor.getString(cursor.getColumnIndex(KEY_CITY_ID)), cursor.getString(cursor.getColumnIndex(KEY_DESTINATION_ID)), cursor.getString(cursor.getColumnIndex(KEY_DESTINATION_NAME)), cursor.getString(cursor.getColumnIndex(KEY_DESTINATION_IMAGE_URL)), cursor.getString(cursor.getColumnIndex(KEY_AUDIO_PATH)));
                cityInformationArrayList.add(destinationInformation);
            } while (cursor.moveToNext());
        }
        return cityInformationArrayList;
    }
    public DownloadDestinationInformation getDetailByDestinationID(String destinationID) {
        DownloadDestinationInformation destinationInformation = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, KEY_DESTINATION_ID + "=?",
                new String[]{destinationID}, null, null, null);
        if (cursor.moveToNext()) {
            destinationInformation = new DownloadDestinationInformation(cursor.getString(cursor.getColumnIndex(KEY_CITY_ID)), cursor.getString(cursor.getColumnIndex(KEY_DESTINATION_ID)), cursor.getString(cursor.getColumnIndex(KEY_DESTINATION_NAME)), cursor.getString(cursor.getColumnIndex(KEY_DESTINATION_IMAGE_URL)), cursor.getString(cursor.getColumnIndex(KEY_AUDIO_PATH)));
        }
        return destinationInformation;
    }

    public void delete(String destinationID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_DESTINATION_ID + " = ?",
                new String[]{destinationID});
        db.close();
    }


//    public List<DownloadDestinationInformation> getAllDetails() {
//        List<DownloadDestinationInformation> cityInformationArrayList = new ArrayList<DownloadDestinationInformation>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                DownloadDestinationInformation audioCityInformation = new DownloadDestinationInformation(cursor.getString(cursor.getColumnIndex(KEY_CITY_NAME)), cursor.getString(cursor.getColumnIndex(KEY_CITY_IMAGE_URL)), cursor.getString(cursor.getColumnIndex(KEY_DESTINATION_NAME)), cursor.getString(cursor.getColumnIndex(KEY_DESTINATION_IMAGE_URL)));
//                cityInformationArrayList.add(audioCityInformation);
//            } while (cursor.moveToNext());
//        }
//
//        // return contact list
//        return cityInformationArrayList;
//    }
}
