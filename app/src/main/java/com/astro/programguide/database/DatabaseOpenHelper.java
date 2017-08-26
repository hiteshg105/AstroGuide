package com.astro.programguide.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.astro.programguide.database.dao.ChannelDAO;

/**
 * Created by hitesh on 8/21/17.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static DatabaseOpenHelper instance;
    private static final int          DATABASE_VERSION = 1;

    // Database Name
    private static final String       DATABASE_NAME    = "programManager";

    private Context                   context;

    private DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static synchronized DatabaseOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseOpenHelper(context);
        }
        return instance;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + ChannelDAO.TABLE_CHANNELS + "(" + ChannelDAO.KEY_ID +
                " INTEGER PRIMARY KEY," + ChannelDAO.KEY_TITLE + " TEXT,"+ ChannelDAO.KEY_IS_FAVOURITE + " INTEGER,"
                + ChannelDAO.KEY_STB_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(ContentValues values, String tableName) {
        SQLiteDatabase db = DatabaseOpenHelper.getInstance(context).getWritableDatabase();
        db.replace(tableName, null, values);
        return true;
    }

    public boolean update(String tableName, ContentValues values, String whereClause, String[] whereAgs) {
        SQLiteDatabase db = DatabaseOpenHelper.getInstance(context).getWritableDatabase();
        db.update(tableName, values, whereClause, whereAgs);
        return true;
    }

    public void delete(String tableName, String whereClause, String[] whereAgs) {
        try {
            SQLiteDatabase db = DatabaseOpenHelper.getInstance(context).getWritableDatabase();
            db.delete(tableName, whereClause, whereAgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean replace(ContentValues values, String tableName) {
        SQLiteDatabase db = DatabaseOpenHelper.getInstance(context).getWritableDatabase();
        db.replace(tableName, null, values);
        return true;
    }

}
