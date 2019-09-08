package com.muhammadwildansyabani.katalogfilmkuv2.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.muhammadwildansyabani.katalogfilmkuv2.db.FavoriteMovieHelper;

import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.AUTHORITY;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.CONTENT_URI_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.TABLE_MOVIE;

public class FavoriteMovieProvider extends ContentProvider {
    private static final int FAVORITE=100;
    private static final int FAVORITE_ID=101;
    private FavoriteMovieHelper favoriteHelperMovie;


    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY,TABLE_MOVIE,FAVORITE);
        sUriMatcher.addURI(AUTHORITY,TABLE_MOVIE + "/#",FAVORITE_ID);
    }

    @Override
    public boolean onCreate() {
        favoriteHelperMovie=new FavoriteMovieHelper(getContext());
        favoriteHelperMovie.open();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;

        switch (sUriMatcher.match(uri)){
            case FAVORITE:
                cursor=favoriteHelperMovie.queryProvider();
                break;
            case FAVORITE_ID:
                cursor = favoriteHelperMovie.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor= null;
                break;
        }
        if(cursor!=null){
            cursor.setNotificationUri(getContext().getContentResolver(),uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long added_movie;
        switch (sUriMatcher.match(uri)){
            case FAVORITE:
                added_movie=favoriteHelperMovie.insertProvider(values);
                break;
            default:
                added_movie=0;
                break;
        }
        if (added_movie>0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return uri.parse(CONTENT_URI_MOVIE + "/" + added_movie);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int deleted;
        switch (sUriMatcher.match(uri)){
            case FAVORITE_ID:
                deleted=favoriteHelperMovie.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                deleted=0;
                break;
        }
        if (deleted>0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return deleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int updated;
        switch (sUriMatcher.match(uri)){
            case FAVORITE_ID:
                updated=favoriteHelperMovie.updateProvider(uri.getLastPathSegment(),values);
                break;
            default:
                updated=0;
                break;
        }
        if (updated>0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return updated;
    }
}
