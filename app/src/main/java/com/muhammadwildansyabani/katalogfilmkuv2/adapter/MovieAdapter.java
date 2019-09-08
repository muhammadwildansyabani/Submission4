package com.muhammadwildansyabani.katalogfilmkuv2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.muhammadwildansyabani.katalogfilmkuv2.R;
import com.muhammadwildansyabani.katalogfilmkuv2.entity.MovieParcelable;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<MovieParcelable> mData;
    private Context context;

    public MovieAdapter(List<MovieParcelable>mData, Context context) {
        this.mData=mData;
        this.context=context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, final int position) {

        movieViewHolder.textViewTitle.setText(mData.get(position).getTitle());
        movieViewHolder.textViewOverview.setText(mData.get(position).getDescription());
        movieViewHolder.textViewReleaseDate.setText(mData.get(position).getRelease_date());

        Picasso.with(context).load("https://image.tmdb.org/t/p/w185/" +mData.get(position).getPoster_path()).placeholder(context.getResources().getDrawable(R.drawable.ic_launcher_background)).error(context.getResources().getDrawable(R.drawable.ic_launcher_foreground)).into(movieViewHolder.imgViewPoster);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imgViewPoster;
        TextView textViewTitle, textViewReleaseDate, textViewOverview;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewPoster = itemView.findViewById(R.id.img_item_poster_movie);
            textViewTitle = itemView.findViewById(R.id.tv_item_title_movie);
            textViewReleaseDate = itemView.findViewById(R.id.tv_item_first_air_date_movie);
            textViewOverview = itemView.findViewById(R.id.tv_item_overview_movie);
        }
    }
}
