package com.muhammadwildansyabani.katalogfilmkuv2.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import static android.provider.BaseColumns._ID;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.getColumnIntTv;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.getColumnStringTv;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_DESC_TV_SHOW;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_FIRST_AIR_DATE;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_NAME_TV_SHOW;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_POPULARITY_TV_SHOW;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_POSTER_TV_SHOW;
import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.COLUMN_VOTE_TV_SHOW;

public class TvShowParcelable implements Parcelable {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("overview")
    String description;
    @SerializedName("vote_average")
    String vote_average;
    @SerializedName("first_air_date")
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

    public String getName() {
        return name;
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

    public String getPoster_path() {
        return poster_path;
    }

    public String getPopularity() {
        return popularity;
    }


    public TvShowParcelable(JSONObject object){

        try{
            int id = object.getInt("id");
            String name=object.getString("name");
            String description=object.getString("overview");
            String vote_average=object.getString("vote_average");
            String release_date=object.getString("first_air_date");
            String poster_path=object.getString("poster_path");
            String popularity = object.getString("popularity");

            this.id=id;
            this.name=name;
            this.description=description;
            this.vote_average=vote_average;
            this.release_date=release_date;
            this.poster_path=poster_path;
            this.popularity=popularity;



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected TvShowParcelable(Parcel in) {
        this.id=in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.vote_average = in.readString();
        this.release_date = in.readString();
        this.poster_path = in.readString();
        this.popularity = in.readString();
    }

    public static final Creator<TvShowParcelable> CREATOR = new Creator<TvShowParcelable>() {
        @Override
        public TvShowParcelable createFromParcel(Parcel source) {
            return new TvShowParcelable(source);
        }

        @Override
        public TvShowParcelable[] newArray(int size) {
            return new TvShowParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.description);
        parcel.writeString(this.vote_average);
        parcel.writeString(this.release_date);
        parcel.writeString(this.poster_path);
        parcel.writeString(this.popularity);
    }
    @Override
    public String toString() {
        return "TvShowParcelable{" +
                "id = '" + id + "" +
                ",poster_path = '" + poster_path + "" +
                ",name = '" + name + "" +
                ",first_air_date = '" + release_date + "" +
                ",popularity = '" + popularity + "" +
                ",vote_average = '" + vote_average + "" +
                "}";
    }

    public TvShowParcelable(Cursor cursor) {
        this.id = getColumnIntTv(cursor, _ID);
        this.name = getColumnStringTv(cursor, COLUMN_NAME_TV_SHOW);
        this.description = getColumnStringTv(cursor, COLUMN_DESC_TV_SHOW);
        this.poster_path = getColumnStringTv(cursor, COLUMN_POSTER_TV_SHOW);
        this.popularity = getColumnStringTv(cursor,COLUMN_POPULARITY_TV_SHOW);
        this.vote_average = getColumnStringTv(cursor,COLUMN_VOTE_TV_SHOW);
        this.release_date = getColumnStringTv(cursor,COLUMN_FIRST_AIR_DATE);
    }

}
