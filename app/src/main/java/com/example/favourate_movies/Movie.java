package com.example.favourate_movies;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Movie implements Parcelable {
        String movieName,movieDescription,movieGenere,movieImdb;
        int movieRating,movieYear;

    public Movie(String movieName, String movieDescription, String movieGenere, String movieImdb, int movieYear, int movieRating) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.movieGenere = movieGenere;
        this.movieImdb = movieImdb;
        this.movieRating = movieRating;
        this.movieYear = movieYear;
    }


    protected Movie(Parcel in) {
        movieName = in.readString();
        movieDescription = in.readString();
        movieGenere = in.readString();
        movieImdb = in.readString();
        movieRating = in.readInt();
        movieYear = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public String toString() {
        return "Movie{" +
                "movieName='" + movieName + '\'' +
                ", movieDescription='" + movieDescription + '\'' +
                ", movieGenere='" + movieGenere + '\'' +
                ", movieImdb='" + movieImdb + '\'' +
                ", movieRating=" + movieRating +
                ", movieYear=" + movieYear +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(movieName);
        parcel.writeString(movieDescription);
        parcel.writeString(movieGenere);
        parcel.writeString(movieImdb);
        parcel.writeInt(movieRating);
        parcel.writeInt(movieYear);
    }
}
