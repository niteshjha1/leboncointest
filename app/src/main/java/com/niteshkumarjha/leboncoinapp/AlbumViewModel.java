package com.niteshkumarjha.leboncoinapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AlbumViewModel extends ViewModel {
    private static final String TAG = "AlbumViewModel";

    private MutableLiveData<List<Album>> albumListLiveData = new MutableLiveData<>();
    private List<Album> albumList = new ArrayList<>();

    public LiveData<List<Album>> getAlbumList() {
        return albumListLiveData;
    }

    void fetchData(RequestQueue requestQueue) {
        String url = "https://static.leboncoin.fr/img/shared/technical-test.json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Album album = new Album();
                    album.setId(jsonObject.getInt("id"));
                    album.setTitle(jsonObject.getString("title"));
                    album.setUrl(jsonObject.getString("url"));
                    album.setThumbNailUrl(jsonObject.getString("thumbnailUrl"));
                    albumList.add(album);
                }
                albumListLiveData.setValue(albumList);
            } catch (JSONException e) {
                Log.e(TAG, "Problem in reading Data : " + e);
                throw new RuntimeException(e);
            }
        }, error -> {
            Log.e(TAG, "Problem in fetching Data : " + error.getMessage());
        });
        requestQueue.add(stringRequest);
    }
}
