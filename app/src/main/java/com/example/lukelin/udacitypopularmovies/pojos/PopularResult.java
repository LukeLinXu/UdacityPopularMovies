package com.example.lukelin.udacitypopularmovies.pojos;

import java.util.List;

/**
 * Created by lukelin on 2016-09-15.
 */
public class PopularResult {
    private int page;
    private List<Movie> results;
    private int total_results;
    private int total_pages;

    public int getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }
}
