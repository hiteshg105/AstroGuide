package com.astro.programguide.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.astro.programguide.data.models.Channel;
import com.astro.programguide.database.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hitesh on 8/21/17.
 */

public class ChannelDAO {

    // Channels table name
    public static final String TABLE_CHANNELS   = "channels";

    // Contacts Table Columns names
    public static final String KEY_ID           = "channelId";
    public static final String KEY_TITLE        = "channelTitle";
    public static final String KEY_STB_NO       = "channelStbNumber";
    public static final String KEY_IS_FAVOURITE = "channelFavorite";
    DatabaseOpenHelper         dbHelper;

    public ChannelDAO(Context context) {
        this.dbHelper = DatabaseOpenHelper.getInstance(context);
    }

    public void insertChannels(List<Channel> channels) {

    }

    public void replaceFavouriteChannel(Channel channel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, channel.getChannelId());
        contentValues.put(KEY_TITLE, channel.getChannelTitle());
        contentValues.put(KEY_STB_NO, channel.getChannelStbNumber());
        contentValues.put(KEY_IS_FAVOURITE, channel.isFavourite()?1:0);

        dbHelper.replace(contentValues, TABLE_CHANNELS);
    }

    public void deleteChannel(Channel channel) {
        dbHelper.delete(TABLE_CHANNELS, KEY_ID + "= ?", new String[] { String.valueOf(channel.getChannelId()) });
    }

    public ArrayList<Channel> getFavouriteChannels() {

        Cursor cursor = null;
        ArrayList<Channel> channelArrayList = new ArrayList<>();
        try {
            cursor = dbHelper.getReadableDatabase().query(TABLE_CHANNELS, null, KEY_IS_FAVOURITE + " =?",
                    new String[] { "1" }, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Channel channel = new Channel();
                    channel.setChannelId(Integer.valueOf(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                    channel.setChannelTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                    channel.setChannelStbNumber(Integer.valueOf(cursor.getString(cursor.getColumnIndex(KEY_STB_NO))));
                    channel.setFavourite(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(KEY_IS_FAVOURITE))));
                    channelArrayList.add(channel);
                } while (cursor.moveToNext());

            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return channelArrayList;
    }
}
