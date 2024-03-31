package com.niteshkumarjha.leboncoinapp.data.local.entity;

import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity(tableName="albums")
public class AlbumEntity {
    @PrimaryKey
    public int id;
    public String title;
    public String url;
    public String thumbnailUrl;
}
