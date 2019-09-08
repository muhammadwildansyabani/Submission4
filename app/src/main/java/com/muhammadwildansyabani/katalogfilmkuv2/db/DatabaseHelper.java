package com.muhammadwildansyabani.katalogfilmkuv2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_DESC_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_ID;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_POPULARITY_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_POSTER_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_RELEASE_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_TITLE_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_VOTE_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.TABLE_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_DESC_TV_SHOW;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_FIRST_AIR_DATE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_ID_TV_SHOW;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_NAME_TV_SHOW;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_POPULARITY_TV_SHOW;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_POSTER_TV_SHOW;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_VOTE_TV_SHOW;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.TABLE_TV_SHOW;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "favorite";
    private static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_FAVORITE_MOVIE ="create table " + TABLE_MOVIE + " ( " +
                COLUMN_ID + " integer primary key autoincrement, " +
                COLUMN_TITLE_MOVIE + " text not null, " +
                COLUMN_DESC_MOVIE + " text not null, " +
                COLUMN_POSTER_MOVIE + " text not null, " +
                COLUMN_POPULARITY_MOVIE + " text not null, " +
                COLUMN_RELEASE_MOVIE + " text not null, " +
                COLUMN_VOTE_MOVIE + " text not null " +
                " ); " ;
        db.execSQL(CREATE_TABLE_FAVORITE_MOVIE
        );

        String CREATE_TABLE_FAVORITE_TV_SHOW="create table " + TABLE_TV_SHOW + " ( " +
                COLUMN_ID_TV_SHOW + " integer primary key autoincrement, " +
                COLUMN_NAME_TV_SHOW + " text not null, " +
                COLUMN_DESC_TV_SHOW + " text not null, " +
                COLUMN_POSTER_TV_SHOW + " text not null, " +
                COLUMN_POPULARITY_TV_SHOW + " text not null, " +
                COLUMN_FIRST_AIR_DATE + " text not null, " +
                COLUMN_VOTE_TV_SHOW + " text not null " +
                " ); " ;
        db.execSQL(CREATE_TABLE_FAVORITE_TV_SHOW
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_MOVIE );
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_TV_SHOW);
        onCreate(db);
    }
}
