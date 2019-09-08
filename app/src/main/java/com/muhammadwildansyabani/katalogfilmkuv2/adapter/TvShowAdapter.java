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
import com.muhammadwildansyabani.katalogfilmkuv2.entity.TvShowParcelable;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {
    private List<TvShowParcelable> mData;
    private Context context;

    public TvShowAdapter(List<TvShowParcelable>mData, Context context) {
        this.mData=mData;
        this.context=context;
    }

    @NonNull
    @Override
    public TvShowAdapter.TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tv_show, viewGroup, false);
        return new TvShowViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.TvShowViewHolder tvShowViewHolder, final int position) {

        tvShowViewHolder.textViewTitle.setText(mData.get(position).getName());
        tvShowViewHolder.textViewOverview.setText(mData.get(position).getDescription());
        tvShowViewHolder.textViewReleaseDate.setText(mData.get(position).getRelease_date());

        Picasso.with(context).load("https://image.tmdb.org/t/p/w185/" +mData.get(position).getPoster_path()).placeholder(context.getResources().getDrawable(R.drawable.ic_launcher_background)).error(context.getResources().getDrawable(R.drawable.ic_launcher_foreground)).into(tvShowViewHolder.imgViewPoster);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder {
        ImageView imgViewPoster;
        TextView textViewTitle, textViewReleaseDate, textViewOverview;

        TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewPoster = itemView.findViewById(R.id.img_item_poster_tv_show);
            textViewTitle = itemView.findViewById(R.id.tv_item_title_tv_show);
            textViewReleaseDate = itemView.findViewById(R.id.tv_item_first_air_date_tv_show);
            textViewOverview = itemView.findViewById(R.id.tv_item_overview_tv_show);
        }
    }
}
