package com.muhammadwildansyabani.katalogfilmkuv2.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContractTvShow {

    public static final class TvShowColumns implements BaseColumns {
        public static String TABLE_TV_SHOW = "tv_show_favorite";
        public static String COLUMN_ID_TV_SHOW = "_id";
        public static String COLUMN_NAME_TV_SHOW = "name";
        public static String COLUMN_DESC_TV_SHOW = "overview";
        public static String COLUMN_POSTER_TV_SHOW = "image_poster";
        public static String COLUMN_POPULARITY_TV_SHOW = "popularity";
        public static String COLUMN_VOTE_TV_SHOW = "vote_average";
        public static String COLUMN_FIRST_AIR_DATE = "first_air_date";

        public static Uri CONTENT_URI_TV_SHOW = new Uri.Builder().scheme("content")
                .authority(AUTHORITY)
                .appendPath(TABLE_TV_SHOW)
                .build();
    }
    public static final String AUTHORITY = "com.muhammadwildansyabani.katalogfilmkuv2";


    public static String getColumnStringTv(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnIntTv(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
}
