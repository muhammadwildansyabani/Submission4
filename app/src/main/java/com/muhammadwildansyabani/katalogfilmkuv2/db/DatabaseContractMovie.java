package com.muhammadwildansyabani.katalogfilmkuv2.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContractMovie {

    public static final class MovieColumns implements BaseColumns {
        public static String TABLE_MOVIE = "movie_favorite";
        public static String COLUMN_ID = "_id";
        public static String COLUMN_TITLE_MOVIE = "original_title";
        public static String COLUMN_DESC_MOVIE = "overview";
        public static String COLUMN_POSTER_MOVIE = "image_poster";
        public static String COLUMN_POPULARITY_MOVIE = "popularity";
        public static String COLUMN_RELEASE_MOVIE = "release_date";
        public static String COLUMN_VOTE_MOVIE = "vote_average";

        public static Uri CONTENT_URI_MOVIE = new Uri.Builder().scheme("content")
                .authority(AUTHORITY)
                .appendPath(TABLE_MOVIE)
                .build();
    }
    public static final String AUTHORITY = "com.muhammadwildansyabani.katalogfilmkuv2";


    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
}
