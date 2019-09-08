package com.muhammadwildansyabani.katalogfilmkuv2.entity;

import android.app.Activity;
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
import com.muhammadwildansyabani.katalogfilmkuv2.detail.DetailActivityTvShow;

public class TvShowViewHolder extends RecyclerView.ViewHolder {
    private ImageView mPoster;
    private TextView mTitle,mOverview,mPopularity, mVote, mFirstAirDate;
    private CardView mListView;
    private Activity activity;


    public TvShowViewHolder(@NonNull View itemView) {
        super(itemView);


        mPoster=itemView.findViewById(R.id.img_poster_tv_show);
        mTitle=itemView.findViewById(R.id.txt_film_name_tv_show);
        mOverview=itemView.findViewById(R.id.txt_overview_tv_show);
        mPopularity=itemView.findViewById(R.id.txt_popularity_tv_show);
        mVote= itemView.findViewById(R.id.txt_vote_tv_show);
        mFirstAirDate=itemView.findViewById(R.id.txt_first_air_date);
        mListView=itemView.findViewById(R.id.cv_item_tv_show);


    }
    public void bind(final TvShowParcelable item){
        mTitle.setText(item.getName());
        mOverview.setText(item.getDescription());

        Glide.with(itemView.getContext())
                .load(BuildConfig.BASE_URL_IMG + "w185" + item.getPoster_path())
                .into(mPoster);

        mListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(itemView.getContext(), DetailActivityTvShow.class);
                detail.putExtra(DetailActivityTvShow.TV_SHOW_ITEM,item);
                itemView.getContext().startActivity(detail);
            }
        });


    }
}
