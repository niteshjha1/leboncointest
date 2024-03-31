package com.niteshkumarjha.leboncoinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class LargerImageActivity extends AppCompatActivity {

    ImageView largerImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_larger_image);

        largerImageView = findViewById(R.id.largerImageView);

        String imageUrl = getIntent().getStringExtra("imageUrl");

        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(largerImageView);
    }
}