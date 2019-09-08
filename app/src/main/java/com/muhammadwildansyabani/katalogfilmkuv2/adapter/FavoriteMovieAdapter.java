package com.muhammadwildansyabani.katalogfilmkuv2.adapter;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.muhammadwildansyabani.katalogfilmkuv2.R;
import com.muhammadwildansyabani.katalogfilmkuv2.entity.MovieParcelable;
import com.muhammadwildansyabani.katalogfilmkuv2.entity.MovieViewHolder;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private Cursor listFavoriteMovie;

    public FavoriteMovieAdapter(Cursor items){
        replaceAll(items);
    }


    public void replaceAll(Cursor items){
        listFavoriteMovie=items;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MovieViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder favoriteViewHolder, int i) {
        favoriteViewHolder.bind(getItem(i));

    }

    @Override
    public int getItemCount() {
        if (listFavoriteMovie==null)return 0;
        return listFavoriteMovie.getCount();
    }

    private MovieParcelable getItem(int position){
        if (!listFavoriteMovie.moveToPosition(position)){
            throw new IllegalStateException("Invalid Position!");
        }
        return new MovieParcelable(listFavoriteMovie);
    }
}
