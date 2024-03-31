package com.niteshkumarjha.leboncoinapp.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.niteshkumarjha.leboncoinapp.data.local.database.AppDatabase;
import com.niteshkumarjha.leboncoinapp.data.local.database.dao.AlbumDao;
import com.niteshkumarjha.leboncoinapp.data.local.entity.AlbumEntity;

import java.util.List;

public class AlbumRepository {
    private AlbumDao albumDao;
    private LiveData<List<AlbumEntity>> allAlbums;

    public AlbumRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        albumDao = database.albumDao();
        allAlbums = albumDao.getAllAlbums();
    }

    public LiveData<List<AlbumEntity>> getAllAlbums(){
        return allAlbums;
    }

    public void insertAlbums(List<AlbumEntity> albums){
        new InsertAlbumAsyncTask(albumDao).execute(albums);
    }

    private static class InsertAlbumAsyncTask extends AsyncTask<List<AlbumEntity>, Void, Void > {
        private AlbumDao albumDao;

        private InsertAlbumAsyncTask(AlbumDao albumDao){
            this.albumDao = albumDao;
        }

        @Override
        protected Void doInBackground(List<AlbumEntity>... lists) {
            albumDao.insertAll(lists[0]);
            return null;
        }
    }
}
