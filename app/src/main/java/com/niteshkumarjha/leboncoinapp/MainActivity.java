package com.niteshkumarjha.leboncoinapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private AlbumAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new AlbumAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        AlbumViewModel viewModel = new ViewModelProvider(this).get(AlbumViewModel.class);
        viewModel.getAlbumList().observe(this, this::updateUI);
        viewModel.fetchData(requestQueue);
    }

    private void updateUI(List<Album> albumList){
        adapter.setAlbumList(albumList);
    }

}