package com.muhammadwildansyabani.katalogfilmkuv2.detail;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.muhammadwildansyabani.katalogfilmkuv2.R;
import com.muhammadwildansyabani.katalogfilmkuv2.db.FavoriteTvShowHelper;
import com.muhammadwildansyabani.katalogfilmkuv2.entity.TvShowParcelable;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_DESC_TV_SHOW;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_FIRST_AIR_DATE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_ID_TV_SHOW;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_NAME_TV_SHOW;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_POPULARITY_TV_SHOW;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_POSTER_TV_SHOW;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_VOTE_TV_SHOW;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.CONTENT_URI_TV_SHOW;

public class DetailActivityTvShow extends AppCompatActivity {
    public static final String TV_SHOW_ITEM = "tv_show";
    private TextView mJudul, mRating, mOverview, mFirstAirDate, mPopularity;
    private ImageView mPoster;
    FloatingActionButton ivFavorite;
    final static String posterUrl= "https://image.tmdb.org/t/p/w500/";

    private FavoriteTvShowHelper favoriteTvShowHelper;
    private Boolean isFavorite = false;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);

        TvShowParcelable Isi = getIntent().getParcelableExtra(TV_SHOW_ITEM);

        mJudul=findViewById(R.id.txt_film_name_tv_show);
        mRating=findViewById(R.id.txt_vote_tv_show);
        mPopularity=findViewById(R.id.txt_popularity_tv_show);
        mOverview=findViewById(R.id.txt_overview_tv_show);
        mFirstAirDate=findViewById(R.id.txt_first_air_date);
        mPoster=findViewById(R.id.img_poster_tv_show);

        ivFavorite = findViewById(R.id.fab_tv_show);

        Picasso.with(this).load(posterUrl+Isi.getPoster_path()).into(mPoster);

        mJudul.setText(Isi.getName());
        mRating.setText(Isi.getVote_average());
        String firstAirDate =Isi.getRelease_date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(firstAirDate);
            SimpleDateFormat baru=new SimpleDateFormat("EEEE, dd MMM yyyy");
            String NewRelease = baru.format(date);
            mFirstAirDate.setText(NewRelease);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mOverview.setText(Isi.getDescription());
        mPopularity.setText(Isi.getPopularity());

        loadDataSQLite();
        getSupportActionBar().setTitle(Isi.getName());
        ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavorite) FavoriteRemove();
                else FavoriteSave();

                isFavorite = !isFavorite;
                setFavorite();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(favoriteTvShowHelper !=null) favoriteTvShowHelper.close();
    }



    public void setFavorite(){
        if (isFavorite)ivFavorite.setImageResource(R.drawable.ic_favorite_selected);
        else ivFavorite.setImageResource(R.drawable.ic_favorite_border_white_24dp);
    }

    private void loadDataSQLite(){
        TvShowParcelable Isi = getIntent().getParcelableExtra("tv_show");
        favoriteTvShowHelper = new FavoriteTvShowHelper(this);
        favoriteTvShowHelper.open();

        Cursor cursor = getContentResolver().query(Uri.parse(CONTENT_URI_TV_SHOW + "/" + Isi.getId()),null,
                null,
                null,
                null);

        if (cursor != null){
            if (cursor.moveToFirst())isFavorite=true;
            cursor.close();
        }
        setFavorite();
    }


    private void FavoriteSave(){
        TvShowParcelable Isi = getIntent().getParcelableExtra("tv_show");
        ContentValues values=new ContentValues();
        values.put(COLUMN_ID_TV_SHOW,Isi.getId());
        values.put(COLUMN_NAME_TV_SHOW,Isi.getName());
        values.put(COLUMN_POSTER_TV_SHOW,Isi.getPoster_path());
        values.put(COLUMN_DESC_TV_SHOW,Isi.getDescription());
        values.put(COLUMN_POPULARITY_TV_SHOW,Isi.getPopularity());
        values.put(COLUMN_VOTE_TV_SHOW,Isi.getVote_average());
        values.put(COLUMN_FIRST_AIR_DATE,Isi.getRelease_date());
        getContentResolver().insert(CONTENT_URI_TV_SHOW,values);
        Toast.makeText(this,"Tv Show has Set Favorited",Toast.LENGTH_SHORT).show();
    }

    private void FavoriteRemove(){
        TvShowParcelable Isi = getIntent().getParcelableExtra("tv_show");
        getContentResolver().delete(Uri.parse(CONTENT_URI_TV_SHOW + "/" + Isi.getId() ),null,
                null);
        Toast.makeText(this,"Unfavorited",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
