package com.niteshkumarjha.leboncoinapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private AlbumAdapter adapter;
    private AlbumViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        viewModel = new ViewModelProvider(this).get(AlbumViewModel.class);

        adapter = new AlbumAdapter(this, viewModel, new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        viewModel.getAlbumList().observe(this, this::updateUI);
        viewModel.fetchData(requestQueue);

        viewModel = new ViewModelProvider(this).get(AlbumViewModel.class);
        viewModel.getSelectedImageUrl().observe(this, imageUrl -> {
            Intent intent = new Intent(this, LargerImageActivity.class);
            intent.putExtra("imageUrl", imageUrl);
            startActivity(intent);
        });
    }

    private void updateUI(List<Album> albumList) {
        adapter.setAlbumList(albumList);
    }
}