package com.niteshkumarjha.leboncoinapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    private Context context;
    private List<Album> albumList;
    private AlbumViewModel viewModel;

    public AlbumAdapter(Context context, AlbumViewModel viewModel, List<Album> albumList) {
        this.context = context;
        this.viewModel = viewModel;
        this.albumList = albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.titleTextView.setText(album.getTitle());

        Glide.with(context).load(album.getThumbNailUrl()).placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_background).into(holder.thumNailImageView);

        holder.itemView.setOnClickListener(v -> {
            viewModel.onThumbnailClicked(album.getUrl());
        });
    }


    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public ImageView thumNailImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            thumNailImageView = itemView.findViewById(R.id.thumbnailImageView);
        }
    }
}
