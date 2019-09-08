package com.muhammadwildansyabani.katalogfilmkuv2.adapter;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.muhammadwildansyabani.katalogfilmkuv2.R;
import com.muhammadwildansyabani.katalogfilmkuv2.entity.TvShowParcelable;
import com.muhammadwildansyabani.katalogfilmkuv2.entity.TvShowViewHolder;

public class FavoriteTvShowAdapter extends RecyclerView.Adapter<TvShowViewHolder> {

    private Cursor listFavoriteTvShow;

    public FavoriteTvShowAdapter(Cursor items){
        replaceAll(items);
    }


    public void replaceAll(Cursor items){
        listFavoriteTvShow=items;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TvShowViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tv_show,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder favoriteViewHolder, int i) {
        favoriteViewHolder.bind(getItem(i));

    }

    @Override
    public int getItemCount() {
        if (listFavoriteTvShow==null)return 0;
        return listFavoriteTvShow.getCount();
    }

    private TvShowParcelable getItem(int position){
        if (!listFavoriteTvShow.moveToPosition(position)){
            throw new IllegalStateException("Invalid Position!");
        }
        return new TvShowParcelable(listFavoriteTvShow);
    }
}
