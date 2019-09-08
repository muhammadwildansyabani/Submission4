package com.muhammadwildansyabani.katalogfilmkuv2.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import static android.provider.BaseColumns._ID;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_DESC_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_POPULARITY_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_POSTER_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_RELEASE_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_TITLE_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.COLUMN_VOTE_MOVIE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.getColumnInt;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.getColumnString;

public class MovieParcelable implements Parcelable {


    @SerializedName("id")
    int id;
    @SerializedName("title")
    String title;
    @SerializedName("overview")
    String description;
    @SerializedName("vote_average")
    String vote_average;
    @SerializedName("release_date")
    String release_date;
    @SerializedName("poster_path")
    String poster_path;
    @SerializedName("popularity")
    String popularity;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPoster_path() {
        return poster_path;
    }

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }


    public String getPopularity() {
        return popularity;
    }


    public MovieParcelable(JSONObject object){

        try{
            int id = object.getInt("id");
            String title=object.getString("title");
            String description=object.getString("overview");
            String vote_average=object.getString("vote_average");
            String release_date=object.getString("release_date");
            String poster_path=object.getString("poster_path");
            String popularity = object.getString("popularity");

            this.id=id;
            this.title=title;
            this.description=description;
            this.vote_average=vote_average;
            this.release_date=release_date;
            this.poster_path=poster_path;
            this.popularity=popularity;


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.vote_average);
        dest.writeString(this.release_date);
        dest.writeString(this.poster_path);
        dest.writeString(this.popularity);

    }

    protected MovieParcelable(Parcel in) {
        this.id=in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.vote_average = in.readString();
        this.release_date = in.readString();
        this.poster_path = in.readString();
        this.popularity=in.readString();

    }

    public static final Creator<MovieParcelable> CREATOR = new Creator<MovieParcelable>() {
        @Override
        public MovieParcelable createFromParcel(Parcel source) {
            return new MovieParcelable(source);
        }

        @Override
        public MovieParcelable[] newArray(int size) {
            return new MovieParcelable[size];
        }
    };

    @Override
    public String toString() {
        return "MovieParcelable{" +
                "id = '" + id + "" +
                ",poster_path = '" + poster_path + "" +
                ",original_title = '" + title + "" +
//                ",overview = '" + description + "" +
                ",release_date = '" + release_date + "" +
                ",popularity = '" + popularity + "" + "" +
                ",vote_average = '" + vote_average + "" +
                "}";
    }

    public MovieParcelable(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.title = getColumnString(cursor, COLUMN_TITLE_MOVIE);
        this.description = getColumnString(cursor, COLUMN_DESC_MOVIE);
        this.poster_path = getColumnString(cursor, COLUMN_POSTER_MOVIE);
        this.popularity = getColumnString(cursor,COLUMN_POPULARITY_MOVIE);
        this.vote_average = getColumnString(cursor,COLUMN_VOTE_MOVIE);
        this.release_date = getColumnString(cursor,COLUMN_RELEASE_MOVIE);
    }
}
