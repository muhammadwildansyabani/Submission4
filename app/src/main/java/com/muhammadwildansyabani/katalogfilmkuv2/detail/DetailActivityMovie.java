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
import com.muhammadwildansyabani.katalogfilmkuv2.db.FavoriteMovieHelper;
import com.muhammadwildansyabani.katalogfilmkuv2.entity.MovieParcelable;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_DESC_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_ID;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_POPULARITY_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_POSTER_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_RELEASE_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_TITLE_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_VOTE_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.CONTENT_URI_MOVIE;

public class DetailActivityMovie extends AppCompatActivity {
    public static final String MOVIE_ITEM = "movie";
    private TextView mJudul, mRating, mOverview, mReleaseDate, mPopularity;
    private ImageView mPoster;
    FloatingActionButton ivFavorite;
    final static String posterUrl= "https://image.tmdb.org/t/p/w500/";

    private FavoriteMovieHelper favoriteMovieHelper;
    private Boolean isFavorite = false;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        MovieParcelable Isi = getIntent().getParcelableExtra(MOVIE_ITEM);

        mJudul=findViewById(R.id.txt_film_name_movie);
        mRating=findViewById(R.id.txt_vote_movie);
        mPopularity=findViewById(R.id.txt_popularity_movie);
        mOverview=findViewById(R.id.txt_overview_movie);
        mReleaseDate=findViewById(R.id.txt_release_date);
        mPoster=findViewById(R.id.img_poster_movie);

        ivFavorite = findViewById(R.id.fab_movie);

        Picasso.with(this).load(posterUrl+Isi.getPoster_path()).into(mPoster);

        mJudul.setText(Isi.getTitle());
        mRating.setText(Isi.getVote_average());
        String release =Isi.getRelease_date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(release);
            SimpleDateFormat baru=new SimpleDateFormat("EEEE, dd MMM yyyy");
            String NewRelease = baru.format(date);
            mReleaseDate.setText(NewRelease);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mOverview.setText(Isi.getDescription());
        mPopularity.setText(Isi.getPopularity());

        loadDataSQLite();
        getSupportActionBar().setTitle(Isi.getTitle());
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
        if(favoriteMovieHelper !=null) favoriteMovieHelper.close();
    }



    public void setFavorite(){
        if (isFavorite)ivFavorite.setImageResource(R.drawable.ic_favorite_selected);
        else ivFavorite.setImageResource(R.drawable.ic_favorite_border_white_24dp);
    }

    private void loadDataSQLite(){
        MovieParcelable Isi = getIntent().getParcelableExtra("movie");
        favoriteMovieHelper =new FavoriteMovieHelper(this);
        favoriteMovieHelper.open();

        Cursor cursor = getContentResolver().query(Uri.parse(CONTENT_URI_MOVIE + "/" + Isi.getId()),null,
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
        MovieParcelable Isi = getIntent().getParcelableExtra("movie");
        ContentValues values=new ContentValues();
        values.put(COLUMN_ID,Isi.getId());
        values.put(COLUMN_TITLE_MOVIE,Isi.getTitle());
        values.put(COLUMN_POSTER_MOVIE,Isi.getPoster_path());
        values.put(COLUMN_DESC_MOVIE,Isi.getDescription());
        values.put(COLUMN_POPULARITY_MOVIE,Isi.getPopularity());
        values.put(COLUMN_VOTE_MOVIE,Isi.getVote_average());
        values.put(COLUMN_RELEASE_MOVIE,Isi.getRelease_date());
        getContentResolver().insert(CONTENT_URI_MOVIE,values);
        Toast.makeText(this,"Film has Set Favorited",Toast.LENGTH_SHORT).show();
    }

    private void FavoriteRemove(){
        MovieParcelable Isi = getIntent().getParcelableExtra("movie");
        getContentResolver().delete(Uri.parse(CONTENT_URI_MOVIE + "/" + Isi.getId() ),null,
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
