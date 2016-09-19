package com.example.lukelin.udacitypopularmovies.pojos;

import java.util.List;

/**
 * Created by lukelin on 2016-09-19.
 */
public class GetReviewResponse {
    private String id;
    private int page;
    private List<Review> results;
    private int total_pages;
    private int total_results;

    public String getId() {
        return id;
    }

    public int getPage() {
        return page;
    }

    public List<Review> getResults() {
        return results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }
}
