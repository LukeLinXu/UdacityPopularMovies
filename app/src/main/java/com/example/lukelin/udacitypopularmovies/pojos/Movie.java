package com.example.lukelin.udacitypopularmovies.pojos;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lukelin on 2016-09-15.
 */
public class Movie extends RealmObject{
    private String poster_path;
    private boolean adult;
    private String overview;
    private String release_date;
    @PrimaryKey
    private String id;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private double popularity;
    private int vote_count;
    private boolean video;
    private double vote_average;
    private String status;
    private String budget;

    public String getPoster_path() {
        return "http://image.tmdb.org/t/p/w185"+poster_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getId() {
        return id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getStatus() {
        return status;
    }

    public String getBudget() {
        return budget;
    }
}
