package com.muhammadwildansyabani.katalogfilmkuv2.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.muhammadwildansyabani.katalogfilmkuv2.db.FavoriteTvShowHelper;

import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.AUTHORITY;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.CONTENT_URI_TV_SHOW;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.TABLE_TV_SHOW;

public class FavoriteTvShowProvider extends ContentProvider {
    private static final int FAVORITE_TV=100;
    private static final int FAVORITE_ID_TV=101;
    private FavoriteTvShowHelper favoriteHelperTvShow;


    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY,TABLE_TV_SHOW,FAVORITE_TV);
        sUriMatcher.addURI(AUTHORITY,TABLE_TV_SHOW + "/#",FAVORITE_ID_TV);
    }

    @Override
    public boolean onCreate() {
        favoriteHelperTvShow=new FavoriteTvShowHelper(getContext());
        favoriteHelperTvShow.open();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;

        switch (sUriMatcher.match(uri)){
            case FAVORITE_TV:
                cursor=favoriteHelperTvShow.queryProvider();
                break;
            case FAVORITE_ID_TV:
                cursor = favoriteHelperTvShow.queryByIdProvider(uri.getLastPathSegment());
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
        long added_tv_show;
        switch (sUriMatcher.match(uri)){
            case FAVORITE_TV:
                added_tv_show=favoriteHelperTvShow.insertProvider(values);
                break;
            default:
                added_tv_show=0;
                break;
        }
        if (added_tv_show>0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return uri.parse(CONTENT_URI_TV_SHOW + "/" + added_tv_show);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int deleted;
        switch (sUriMatcher.match(uri)){
            case FAVORITE_ID_TV:
                deleted=favoriteHelperTvShow.deleteProvider(uri.getLastPathSegment());
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
            case FAVORITE_ID_TV:
                updated=favoriteHelperTvShow.updateProvider(uri.getLastPathSegment(),values);
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
