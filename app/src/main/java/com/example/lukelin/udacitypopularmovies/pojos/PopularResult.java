package com.example.lukelin.udacitypopularmovies.pojos;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by lukelin on 2016-09-15.
 */
public class PopularResult extends RealmObject {
    private int page;
    private RealmList<Movie> results;
    private int total_results;
    private int total_pages;

    public PopularResult() {
    }

    public PopularResult(int page, RealmList<Movie> results, int total_results, int total_pages) {
        this.page = page;
        this.results = results;
        this.total_results = total_results;
        this.total_pages = total_pages;
    }

    public int getPage() {
        return page;
    }

    public RealmList<Movie> getResults() {
        return results;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }
}
