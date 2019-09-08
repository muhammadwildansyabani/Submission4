package com.muhammadwildansyabani.katalogfilmkuv2.entity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.muhammadwildansyabani.katalogfilmkuv2.BuildConfig;
import com.muhammadwildansyabani.katalogfilmkuv2.R;
import com.muhammadwildansyabani.katalogfilmkuv2.detail.DetailActivityMovie;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    private ImageView mPoster;
    private TextView mTitle,mOverview,mPopularity, mVote, mRelease;
    private CardView mListView;


    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);


        mPoster=itemView.findViewById(R.id.img_item_poster_movie);
        mTitle=itemView.findViewById(R.id.tv_item_title_movie);
        mOverview=itemView.findViewById(R.id.tv_item_overview_movie);
        mPopularity = itemView.findViewById(R.id.txt_popularity_movie);
        mVote= itemView.findViewById(R.id.txt_vote_movie);
        mRelease=itemView.findViewById(R.id.tv_item_first_air_date_movie);
        mListView=itemView.findViewById(R.id.cv_item_movie);


    }
    public void bind(final MovieParcelable item){
        mTitle.setText(item.getTitle());
        mOverview.setText(item.getDescription());
        mRelease.setText(item.getRelease_date());

        Glide.with(itemView.getContext())
                .load(BuildConfig.BASE_URL_IMG + "w185" + item.getPoster_path())
                .into(mPoster);

        mListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(itemView.getContext(), DetailActivityMovie.class);
                detail.putExtra(DetailActivityMovie.MOVIE_ITEM,item);
                itemView.getContext().startActivity(detail);
            }
        });


    }
}
