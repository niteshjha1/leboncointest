package com.niteshkumarjha.leboncoinapp.data.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.niteshkumarjha.leboncoinapp.data.local.database.dao.AlbumDao;
import com.niteshkumarjha.leboncoinapp.data.local.entity.AlbumEntity;

@Database(entities = {AlbumEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AlbumDao albumDao();

    private static volatile AppDatabase instance;
    private static final String LEBONCOIN_DATA = "leboncoin_database";

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, LEBONCOIN_DATA).build();
        }
        return instance;
    }
}
