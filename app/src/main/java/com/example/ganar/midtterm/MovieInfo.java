/***
 * Midterm
 * Movie App
 * Gana Ramesan
 * */
package com.example.ganar.midtterm;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ganar on 10/16/2017.
 */

public class MovieInfo implements Parcelable {
    String movie_name,overview,Releasedate,rating,popularity,imageurl,posterpath;

    public MovieInfo() {

    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleasedate() {
        return Releasedate;
    }

    public void setReleasedate(String releasedate) {
        Releasedate = releasedate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPosterpath() {
        return posterpath;
    }

    public void setPosterpath(String posterpath) {
        this.posterpath = posterpath;
    }

    protected MovieInfo(Parcel in) {
        movie_name = in.readString();
        overview = in.readString();
        Releasedate = in.readString();
        rating = in.readString();
        popularity = in.readString();
        imageurl = in.readString();
        posterpath = in.readString();
    }

    public static final Creator<MovieInfo> CREATOR = new Creator<MovieInfo>() {
        @Override
        public MovieInfo createFromParcel(Parcel in) {
            return new MovieInfo(in);
        }

        @Override
        public MovieInfo[] newArray(int size) {
            return new MovieInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(movie_name);
        parcel.writeString(overview);
        parcel.writeString(Releasedate);
        parcel.writeString(rating);
        parcel.writeString(popularity);
        parcel.writeString(imageurl);
        parcel.writeString(posterpath);
    }

    @Override
    public int hashCode() {
        int hash =17;
        hash = hash*31+getMovie_name().hashCode();
        hash = hash*31+getOverview().hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof MovieInfo)){
            return false;
        }
        MovieInfo tobj = (MovieInfo) obj;
        return this.getMovie_name().equals(tobj.getMovie_name()) && this.getOverview().equals(tobj.getOverview());
    }
}
