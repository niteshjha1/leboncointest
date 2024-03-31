package com.niteshkumarjha.leboncoinapp.data.local.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.niteshkumarjha.leboncoinapp.data.local.entity.AlbumEntity;

import java.util.List;

@Dao
public interface AlbumDao {
    @Query("SELECT * FROM albums")
    LiveData<List<AlbumEntity>>getAllAlbums();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<AlbumEntity> albums);

}
